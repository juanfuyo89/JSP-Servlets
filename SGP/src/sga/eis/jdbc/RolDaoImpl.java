package sga.eis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import sga.eis.dao.RolDao;
import sga.eis.dto.Rol;
import sga.eis.dto.RolPk;
import sga.eis.exceptions.RolDaoException;
import sga.eis.jdbc.connect.ResourceManager;
import sga.eis.jdbc.utilities.IDaoConstants;

/**
 * Clase que implementa los metodos de consulta y modificación de la tabla Roles
 * de la BD
 * 
 * @author Juan Carlos Fuyo
 */
public class RolDaoImpl implements RolDao {

    /**
     * Atributo de Conexión a la BD.
     */
    private Connection         userConn;

    /**
     * los metodos de consulta pasaran este valor al metodo setMaxRows de JDBC
     */
    protected int              maxRows;

    /**
     * Todos los metodos de consulta en esta clase usan esta constante SELECT
     * para construir sus queries
     */
    protected final String     SQL_SELECT        = "SELECT id_rol, rol_name FROM "
                                                         + getTableName() + "";

    /**
     * Sentencia SQL INSERT para esta tabla
     */
    protected final String     SQL_INSERT        = "INSERT INTO "
                                                         + getTableName()
                                                         + " ( rol_name ) VALUES ( ? )";

    /**
     * Sentencia SQL UPDATE para esta tabla
     */
    protected final String     SQL_UPDATE        = "UPDATE "
                                                         + getTableName()
                                                         + " SET rol_name=? WHERE id_rol=?";

    /**
     * Sentencia SQL DELETE para esta tabla
     */
    protected final String     SQL_DELETE        = "DELETE FROM "
                                                         + getTableName()
                                                         + " WHERE id_rol = ?";

    /**
     * Indice de la columna id_rol
     */
    protected static final int COLUMN_ID_ROL     = 1;

    /**
     * Indice de la columna rol_name
     */
    protected static final int COLUMN_ROL_NAME   = 2;

    /**
     * Numero de columnas
     */
    protected static final int NUMBER_OF_COLUMNS = 2;

    /**
     * Indice de la primary-key id_rol
     */
    protected static final int PK_COLUMN_ID_ROL  = 1;

    /**
     * Constructor vacio de la clase
     */
    public RolDaoImpl() {
    }

    /**
     * Constructor conparametros de la clase
     * 
     * @param userConn
     */
    public RolDaoImpl(final java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Inserta un nuevo registro en la tabla Roles.
     */
    @Override
    public RolPk insert(Rol dto) throws RolDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            stmt = userConn.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            stmt.setString(index++, dto.getNombre());
            System.out.println("Executing " + SQL_INSERT + " with DTO: " + dto);
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
            // retorna valores de las columnas auto-increment
            rs = stmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                dto.setIdRol(new Integer(rs.getInt(1)));
            }
            reset(dto);
            return dto.createPk();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RolDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Actualiza un unico registro en la tabla Roles.
     */
    @Override
    public void update(RolPk pk, Rol dto) throws RolDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_UPDATE + " with DTO: " + dto);
            stmt = userConn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, dto.getNombre());
            stmt.setInt(index, pk.getIdRol().intValue());
            int rows = stmt.executeUpdate();
            reset(dto);
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RolDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Elimina un unico registro en la tabla Roles.
     */
    @Override
    public void delete(RolPk pk) throws RolDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_DELETE + " with PK: " + pk);
            stmt = userConn.prepareStatement(SQL_DELETE);
            if (pk.getIdRol() != null) {
                stmt.setInt(IDaoConstants.INT_ONE, pk.getIdRol().intValue());
            }
            else {
                stmt.setNull(IDaoConstants.INT_ONE, java.sql.Types.INTEGER);
            }
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RolDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Retorna un unico registro en la tabla Roles que conicida con la
     * primary-key especificada.
     */
    @Override
    public Rol findByPrimaryKey(RolPk pk) throws RolDaoException {
        return findByPrimaryKey(pk.getIdRol());
    }

    /**
     * Retorna un registro de la tabla Roles que coincida con el criterio
     * 'id_rol = :idRol'.
     */
    @Override
    public Rol findByPrimaryKey(Integer idRol) throws RolDaoException {
        Rol[] ret = findByDynamicSelect(SQL_SELECT + " WHERE id_rol = ?",
                new Object[] { idRol });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todas las filas de la tabla Roles.
     */
    @Override
    public Rol[] findAll() throws RolDaoException {
        return findByDynamicSelect(SQL_SELECT + " ORDER BY id_rol", null);
    }

    /**
     * Retorna todos los registros de la tabla Roles que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    @Override
    public Rol[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws RolDaoException {
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
        catch (SQLException e) {
            e.printStackTrace();
            throw new RolDaoException("Exception: " + e.getMessage(), e);
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
     * Retorna todos los registros de la tabla Roles que coincidan con el WHERE
     * SQL especificado arbitrariamente
     */
    @Override
    public Rol[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws RolDaoException {
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
        catch (Exception e) {
            e.printStackTrace();
            throw new RolDaoException("Exception: " + e.getMessage(), e);
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
    protected Rol fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Rol dto = new Rol();
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
    protected Rol[] fetchMultiResults(ResultSet rs) throws SQLException {
        Collection<Rol> resultList = new ArrayList<Rol>();
        while (rs.next()) {
            Rol dto = new Rol();
            populateDto(dto, rs);
            resultList.add(dto);
        }
        Rol ret[] = new Rol[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }

    /**
     * Se encarga de poblar un DTO con datos de un ResultSet especificado
     */
    protected void populateDto(Rol dto, ResultSet rs) throws SQLException {
        dto.setIdRol(new Integer(rs.getInt(COLUMN_ID_ROL)));
        dto.setNombre(rs.getString(COLUMN_ROL_NAME));
    }

    /**
     * Restablece los atributos modificados en el DTO
     */
    protected void reset(Rol dto) {
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

    /**
     * @return the userConn
     */
    @Override
    public Connection getUserConn() {
        return userConn;
    }

    /**
     * @param userConn the userConn to set
     */
    @Override
    public void setUserConn(Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * @return the maxRows
     */
    @Override
    public int getMaxRows() {
        return maxRows;
    }

    /**
     * @param maxRows the maxRows to set
     */
    @Override
    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    /**
     * Metodo que retorna el nombre de la tabla de BD
     * 
     * @return String
     */
    public String getTableName() {
        return IDaoConstants.TABLE_ROLES;
    }

}
