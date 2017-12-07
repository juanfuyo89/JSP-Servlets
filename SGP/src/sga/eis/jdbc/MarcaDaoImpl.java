package sga.eis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import sga.eis.dao.MarcaDao;
import sga.eis.dto.Marca;
import sga.eis.dto.MarcaPk;
import sga.eis.exceptions.MarcaDaoException;
import sga.eis.jdbc.connect.ResourceManager;
import sga.eis.jdbc.utilities.IDaoConstants;

/**
 * Clase que implementa los metodos de consulta y modificación de la tabla
 * Marcas de la BD
 * 
 * @author Juan Carlos Fuyo
 */
public class MarcaDaoImpl implements MarcaDao {

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
    protected final String     SQL_SELECT          = "SELECT id_marca, nombre_marca, nacional FROM "
                                                           + getTableName()
                                                           + "";

    /**
     * Sentencia SQL INSERT para esta tabla
     */
    protected final String     SQL_INSERT          = "INSERT INTO "
                                                           + getTableName()
                                                           + " ( nombre_marca, nacional ) VALUES ( ?, ? )";

    /**
     * Sentencia SQL UPDATE para esta tabla
     */
    protected final String     SQL_UPDATE          = "UPDATE "
                                                           + getTableName()
                                                           + " SET  nombre_marca=?, nacional=? WHERE id_marca=?";

    /**
     * Sentencia SQL DELETE para esta tabla
     */
    protected final String     SQL_DELETE          = "DELETE FROM "
                                                           + getTableName()
                                                           + " WHERE id_marca = ?";

    /**
     * Indice de la columna id_marca
     */
    protected static final int COLUMN_ID_MARCA     = 1;

    /**
     * Indice de la columna nombre_marca
     */
    protected static final int COLUMN_NOMBRE_MARCA = 2;

    /**
     * Indice de la columna nacional
     */
    protected static final int COLUMN_NACIONAL     = 3;

    /**
     * Numero de columnas
     */
    protected static final int NUMBER_OF_COLUMNS   = 3;

    /**
     * Indice de la primary-key id_marca
     */
    protected static final int PK_COLUMN_ID_MARCA  = 1;

    /**
     * Constructor vacio
     */
    public MarcaDaoImpl() {
    }

    /**
     * Constructor con parametros
     * 
     * @param userConn
     */
    public MarcaDaoImpl(final java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Inserta un nuevo registro en la tabla Marcas.
     */
    @Override
    public MarcaPk insert(Marca dto) throws MarcaDaoException {
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
            stmt.setInt(index++, dto.getEsNacional());
            System.out.println("Executing " + SQL_INSERT + " with DTO: " + dto);
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
            // retorna valores de las columnas auto-increment
            rs = stmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                dto.setIdMarca(new Integer(rs.getInt(1)));
            }
            reset(dto);
            return dto.createPk();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new MarcaDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Actualiza un unico registro en la tabla Marcas.
     */
    @Override
    public void update(MarcaPk pk, Marca dto) throws MarcaDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_UPDATE + " with DTO: " + dto);
            stmt = userConn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, dto.getNombre());
            stmt.setInt(index++, dto.getEsNacional());
            stmt.setInt(index, pk.getIdMarca().intValue());
            int rows = stmt.executeUpdate();
            reset(dto);
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new MarcaDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Elimina un unico registro en la tabla Marcas.
     */
    @Override
    public void delete(MarcaPk pk) throws MarcaDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_DELETE + " with PK: " + pk);
            stmt = userConn.prepareStatement(SQL_DELETE);
            if (pk.getIdMarca() != null) {
                stmt.setInt(IDaoConstants.INT_ONE, pk.getIdMarca().intValue());
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
            throw new MarcaDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Retorna un unico registro en la tabla Marcas que conicida con la
     * primary-key especificada.
     */
    @Override
    public Marca findByPrimaryKey(MarcaPk pk) throws MarcaDaoException {
        return findByPrimaryKey(pk.getIdMarca());
    }

    /**
     * Retorna un registro de la tabla Marcas que coincida con el criterio
     * 'id_marca = :idMarca'.
     */
    @Override
    public Marca findByPrimaryKey(Integer idMarca) throws MarcaDaoException {
        Marca[] ret = findByDynamicSelect(SQL_SELECT + " WHERE id_marca = ?",
                new Object[] { idMarca });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todos los registros de la tabla Marcas que coincidan con el
     * criterio 'nombre_marca LIKE %:nombre%'.
     */
    @Override
    public Marca[] findByName(String nombre) throws MarcaDaoException {
        return findByDynamicSelect(SQL_SELECT + " WHERE nombre_marca LIKE ?",
                new Object[] { "%" + nombre + "%" });
    }

    /**
     * Retorna un registro de la tabla Marcas que coincida con el criterio
     * 'nombre_marca = :nombre'.
     */
    @Override
    public Marca findByFullName(String nombre) throws MarcaDaoException {
        Marca[] ret = findByDynamicSelect(SQL_SELECT
                + " WHERE nombre_marca = ?", new Object[] { nombre });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todas las filas de la tabla Marcas.
     */
    @Override
    public Marca[] findAll() throws MarcaDaoException {
        return findByDynamicSelect(SQL_SELECT + " ORDER BY id_marca", null);
    }

    /**
     * Retorna todos los registros de la tabla Marcas que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    @Override
    public Marca[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws MarcaDaoException {
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
            throw new MarcaDaoException("Exception: " + e.getMessage(), e);
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
     * Retorna todos los registros de la tabla Marcas que coincidan con el WHERE
     * SQL especificado arbitrariamente
     */
    @Override
    public Marca[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws MarcaDaoException {
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
            throw new MarcaDaoException("Exception: " + e.getMessage(), e);
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
    protected Marca fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Marca dto = new Marca();
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
    protected Marca[] fetchMultiResults(ResultSet rs) throws SQLException {
        Collection<Marca> resultList = new ArrayList<Marca>();
        while (rs.next()) {
            Marca dto = new Marca();
            populateDto(dto, rs);
            resultList.add(dto);
        }
        Marca ret[] = new Marca[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }

    /**
     * Se encarga de poblar un DTO con datos de un ResultSet especificado
     */
    protected void populateDto(Marca dto, ResultSet rs) throws SQLException {
        dto.setIdMarca(new Integer(rs.getInt(COLUMN_ID_MARCA)));
        dto.setNombre(rs.getString(COLUMN_NOMBRE_MARCA));
        dto.setEsNacional(rs.getInt(COLUMN_NACIONAL));
    }

    /**
     * Restablece los atributos modificados en el DTO
     */
    protected void reset(Marca dto) {
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
        return IDaoConstants.TABLE_MARCAS;
    }

}
