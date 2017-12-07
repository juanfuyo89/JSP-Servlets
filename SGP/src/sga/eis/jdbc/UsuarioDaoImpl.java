package sga.eis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import sga.eis.dao.UsuarioDao;
import sga.eis.dto.Usuario;
import sga.eis.dto.UsuarioPk;
import sga.eis.exceptions.UsuarioDaoException;
import sga.eis.jdbc.connect.ResourceManager;
import sga.eis.jdbc.utilities.IDaoConstants;

/**
 * Clase que implementa los metodos de consulta y modificación de la tabla
 * Usuarios de la BD
 * 
 * @author Juan Carlos Fuyo
 */
public class UsuarioDaoImpl extends AbstractDAO implements UsuarioDao {

    /**
     * Atributo de Conexión a la BD.
     */
    protected Connection       userConn;

    /**
     * los metodos de consulta pasaran este valor al metodo setMaxRows de JDBC
     */
    protected int              maxRows;

    /**
     * Todos los metodos de consulta en esta clase usan esta constante SELECT
     * para construir sus queries
     */
    protected final String     SQL_SELECT           = "SELECT id_usuario, username, passwd, id_persona, id_rol FROM "
                                                            + getTableName()
                                                            + "";

    /**
     * Sentencia SQL INSERT para esta tabla
     */
    protected final String     SQL_INSERT           = "INSERT INTO "
                                                            + getTableName()
                                                            + " ( username, passwd, id_persona, id_rol ) VALUES ( ?, ?, ?, ? )";

    /**
     * Sentencia SQL UPDATE para esta tabla
     */
    protected final String     SQL_UPDATE           = "UPDATE "
                                                            + getTableName()
                                                            + " SET username=?, passwd=?, id_persona=?, id_rol=? WHERE id_usuario=?";

    /**
     * Sentencia SQL DELETE para esta tabla
     */
    protected final String     SQL_DELETE           = "DELETE FROM "
                                                            + getTableName()
                                                            + " WHERE id_usuario = ?";

    /**
     * Indice de la columna id_usuario
     */
    protected static final int COLUMN_ID_USUARIO    = 1;

    /**
     * Indice de la columna username
     */
    protected static final int COLUMN_USERNAME      = 2;

    /**
     * Indice de la columna password
     */
    protected static final int COLUMN_PASSWORD      = 3;

    /**
     * Indice de la columna id_persona
     */
    protected static final int COLUMN_ID_PERSONA    = 4;

    /**
     * Indice de la columna id_persona
     */
    protected static final int COLUMN_ID_ROL        = 5;

    /**
     * Numero de columnas
     */
    protected static final int NUMBER_OF_COLUMNS    = 5;

    /**
     * Indice de la primary-key id_usuario
     */
    protected static final int PK_COLUMN_ID_USUARIO = 1;

    /**
     * Constructor vacio de la clase UsuarioDaoImpl
     */
    public UsuarioDaoImpl() {
    }

    /**
     * Constructor con parametros de la clase UsuarioDaoImpl
     * 
     * @param userConn
     */
    public UsuarioDaoImpl(final java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Inserta un nuevo registro en la tabla Usuarios.
     */
    public UsuarioPk insert(Usuario dto) throws UsuarioDaoException {

        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Validamos la conexion
            this.validateConnection();
            stmt = userConn.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            stmt.setString(index++, dto.getUsername());
            stmt.setString(index++, dto.getPassword());
            if (dto.getIdPersona() != null) {
                stmt.setString(index++, dto.getIdPersona());
            }
            else {
                stmt.setNull(index++, java.sql.Types.VARCHAR);
            }
            stmt.setInt(index++, dto.getIdRol());
            System.out.println("Executing " + SQL_INSERT + " with DTO: " + dto);
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");

            // retorna valores de las columnas auto-increment
            rs = stmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                dto.setIdUsuario(new Integer(rs.getInt(1)));
            }

            reset(dto);
            return dto.createPk();
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new UsuarioDaoException("Exception: " + _e.getMessage(), _e);
        }
        finally {
            ResourceManager.close(stmt);
        }

    }

    /**
     * Actualiza un unico registro en la tabla Usuarios.
     */
    public void update(UsuarioPk pk, Usuario dto) throws UsuarioDaoException {

        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;

        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_UPDATE + " with DTO: " + dto);
            stmt = userConn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, dto.getUsername());
            stmt.setString(index++, dto.getPassword());
            stmt.setString(index++, dto.getIdPersona());
            stmt.setInt(index++, dto.getIdRol());
            stmt.setInt(index, pk.getIdUsuario().intValue());
            int rows = stmt.executeUpdate();
            reset(dto);
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new UsuarioDaoException("Exception: " + _e.getMessage(), _e);
        }
        finally {
            ResourceManager.close(stmt);
        }

    }

    /**
     * Elimina un unico registro en la tabla Usuarios.
     */
    public void delete(UsuarioPk pk) throws UsuarioDaoException {

        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;

        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_DELETE + " with PK: " + pk);
            stmt = userConn.prepareStatement(SQL_DELETE);
            if (pk.getIdUsuario() != null) {
                stmt.setInt(1, pk.getIdUsuario().intValue());
            }
            else {
                stmt.setNull(1, java.sql.Types.INTEGER);
            }

            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new UsuarioDaoException("Exception: " + _e.getMessage(), _e);
        }
        finally {
            ResourceManager.close(stmt);
        }

    }

    /**
     * Retorna un unico registro en la tabla Usuarios que conicida con la
     * primary-key especificada.
     */
    public Usuario findByPrimaryKey(UsuarioPk pk) throws UsuarioDaoException {
        return findByPrimaryKey(pk.getIdUsuario());
    }

    /**
     * Retorna un registro de la tabla Usuarios que coincida con el criterio
     * 'id_usuario = :idUsuario'.
     */
    public Usuario findByPrimaryKey(Integer idUsuario)
            throws UsuarioDaoException {
        Usuario ret[] = findByDynamicSelect(SQL_SELECT
                + " WHERE id_usuario = ?", new Object[] { idUsuario });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna un registro de la tabla Usuarios que coincida con el criterio
     * 'username = :username'.
     */
    public Usuario findByFullName(String username) throws UsuarioDaoException {
        Usuario ret[] = findByDynamicSelect(SQL_SELECT + " WHERE username = ?",
                new Object[] { username });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todas las filas de la tabla Usuarios.
     */
    public Usuario[] findAll() throws UsuarioDaoException {
        return findByDynamicSelect(SQL_SELECT + " ORDER BY id_usuario", null);
    }

    /**
     * Retorna todos los registros de la tabla Usuarios que coincidan con el
     * criterio 'id_persona = :idPersona'.
     */
    public Usuario findByPersona(String idPersona) throws UsuarioDaoException {
        Usuario ret[] = findByDynamicSelect(SQL_SELECT
                + " WHERE id_persona = ?", new Object[] { idPersona });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todos los registros de la tabla Usuarios que coincidan con el
     * criterio 'id_rol = :idRol'.
     */
    @Override
    public Usuario[] findByRol(Integer idRol) throws UsuarioDaoException {
        return findByDynamicSelect(SQL_SELECT + " WHERE id_rol = ?",
                new Object[] { idRol });
    }

    /**
     * Retorna todos los registros de la tabla Usuarios que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    public Usuario[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws UsuarioDaoException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + sql);
            // prepare statement
            stmt = userConn.prepareStatement(sql);
            stmt.setMaxRows(maxRows);

            // se setean los parametros de la consulta
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                stmt.setObject(i + 1, sqlParams[i]);
            }
            System.out.println(sql);
            rs = stmt.executeQuery();

            // recuperamos los resultados
            return fetchMultiResults(rs);
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new UsuarioDaoException("Exception: " + _e.getMessage(), _e);
        }
        finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (userConn != null) {
                ResourceManager.close(userConn);
            }
        }

    }

    /**
     * Retorna todos los registros de la tabla Usuarios que coincidan con el
     * WHERE SQL especificado arbitrariamente
     */
    public Usuario[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws UsuarioDaoException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            // construct the SQL statement
            final String SQL = SQL_SELECT + " WHERE " + sql;

            System.out.println("Executing " + SQL);
            // prepare statement
            stmt = userConn.prepareStatement(SQL);
            stmt.setMaxRows(maxRows);

            // se setean los parametros de la consulta
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                stmt.setObject(i + 1, sqlParams[i]);
            }

            rs = stmt.executeQuery();

            // recuperamos los resultados
            return fetchMultiResults(rs);
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new UsuarioDaoException("Exception: " + _e.getMessage(), _e);
        }
        finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (userConn != null) {
                ResourceManager.close(userConn);
            }
        }

    }

    /**
     * Recupera una unica fila desde el result set especificado
     */
    protected Usuario fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Usuario dto = new Usuario();
            populateDto(dto, rs);
            return dto;
        }
        else {
            return null;
        }

    }

    /**
     * Recupera multiples filas desde el result set especificado
     */
    protected Usuario[] fetchMultiResults(ResultSet rs) throws SQLException {
        Collection<Usuario> resultList = new ArrayList<Usuario>();
        while (rs.next()) {
            Usuario dto = new Usuario();
            populateDto(dto, rs);
            resultList.add(dto);
        }

        Usuario ret[] = new Usuario[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }

    /**
     * Se encarga de poblar un DTO con datos de un ResultSet especificado
     */
    protected void populateDto(Usuario dto, ResultSet rs) throws SQLException {
        dto.setIdUsuario(new Integer(rs.getInt(COLUMN_ID_USUARIO)));
        dto.setUsername(rs.getString(COLUMN_USERNAME));
        dto.setPassword(rs.getString(COLUMN_PASSWORD));
        dto.setIdPersona(rs.getString(COLUMN_ID_PERSONA));
        dto.setIdRol(new Integer(rs.getString(COLUMN_ID_ROL)));
    }

    /**
     * Restablece los atributos modificados en el DTO
     */
    protected void reset(Usuario dto) {
    }

    /**
     * Metodo encargado de validar que la conexión sea valida al momento de
     * usarla
     */
    public void validateConnection() {
        // Si la conexion es aún valida la usamos, de los contrario la traemos
        // de ResourceManager
        try {
            userConn = (userConn != null)
                    ? userConn
                    : ResourceManager.getConnection();
            userConn = (!userConn.isClosed())
                    ? userConn
                    : ResourceManager.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Agregamos estos metodos para poder recuperar o settear una conexion
    // externa

    /**
     * @return the userConn
     */
    public java.sql.Connection getUserConn() {
        return userConn;
    }

    /**
     * @param userConn the userConn to set
     */
    public void setUserConn(java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Sets the value of maxRows
     */
    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    /**
     * Gets the value of maxRows
     */
    public int getMaxRows() {
        return maxRows;
    }

    /**
     * Metodo que retorna el nombre de la tabla de BD
     * 
     * @return String
     */
    public String getTableName() {
        return IDaoConstants.TABLE_USUARIOS;
    }

}
