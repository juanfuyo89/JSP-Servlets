package sga.eis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import sga.eis.dao.TipoDao;
import sga.eis.dto.Tipo;
import sga.eis.dto.TipoPk;
import sga.eis.exceptions.TipoDaoException;
import sga.eis.jdbc.connect.ResourceManager;
import sga.eis.jdbc.utilities.IDaoConstants;

/**
 * Clase que implementa los metodos de consulta y modificación de la tabla Tipos
 * de la BD
 * 
 * @author Juan Carlos Fuyo
 */
public class TipoDaoImpl implements TipoDao {

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
    protected final String     SQL_SELECT         = "SELECT id_tipo, nombre_tipo FROM "
                                                          + getTableName() + "";

    /**
     * Sentencia SQL INSERT para esta tabla
     */
    protected final String     SQL_INSERT         = "INSERT INTO "
                                                          + getTableName()
                                                          + " ( nombre_tipo ) VALUES ( ? )";

    /**
     * Sentencia SQL UPDATE para esta tabla
     */
    protected final String     SQL_UPDATE         = "UPDATE "
                                                          + getTableName()
                                                          + " SET nombre_tipo=? WHERE id_tipo=?";

    /**
     * Sentencia SQL DELETE para esta tabla
     */
    protected final String     SQL_DELETE         = "DELETE FROM "
                                                          + getTableName()
                                                          + " WHERE id_tipo = ?";

    /**
     * Indice de la columna id_tipo
     */
    protected static final int COLUMN_ID_TIPO     = 1;

    /**
     * Indice de la columna nombre_tipo
     */
    protected static final int COLUMN_NOMBRE_TIPO = 2;

    /**
     * Numero de columnas
     */
    protected static final int NUMBER_OF_COLUMNS  = 2;

    /**
     * Indice de la primary-key id_tipo
     */
    protected static final int PK_COLUMN_ID_TIPO  = 1;

    /**
     * Constructor vacio de la clase
     */
    public TipoDaoImpl() {
    }

    /**
     * Constructor conparametros de la clase
     * 
     * @param userConn
     */
    public TipoDaoImpl(final java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Inserta un nuevo registro en la tabla Tipos.
     */
    @Override
    public TipoPk insert(Tipo dto) throws TipoDaoException {
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
                dto.setIdTipo(new Integer(rs.getInt(1)));
            }
            return dto.createPk();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new TipoDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Actualiza un unico registro en la tabla Tipos.
     */
    @Override
    public void update(TipoPk pk, Tipo dto) throws TipoDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_UPDATE + " with DTO: " + dto);
            stmt = userConn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, dto.getNombre());
            stmt.setInt(index, pk.getIdTipo().intValue());
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new TipoDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Elimina un unico registro en la tabla Tipos.
     */
    @Override
    public void delete(TipoPk pk) throws TipoDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_DELETE + " with PK: " + pk);
            stmt = userConn.prepareStatement(SQL_DELETE);
            if (pk.getIdTipo() != null) {
                stmt.setInt(IDaoConstants.INT_ONE, pk.getIdTipo().intValue());
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
            throw new TipoDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Retorna un unico registro en la tabla Tipos que conicida con la
     * primary-key especificada.
     */
    @Override
    public Tipo findByPrimaryKey(TipoPk pk) throws TipoDaoException {
        return findByPrimaryKey(pk.getIdTipo());
    }

    /**
     * Retorna un registro de la tabla Tipos que coincida con el criterio
     * 'id_tipo = :idTipo'.
     */
    @Override
    public Tipo findByPrimaryKey(Integer idTipo) throws TipoDaoException {
        Tipo[] ret = findByDynamicSelect(SQL_SELECT + " WHERE id_tipo = ?",
                new Object[] { idTipo });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna un registro de la tabla Tipos que coincida con el criterio
     * 'nombre_tipo LIKE %:nombre%'.
     */
    @Override
    public Tipo[] findByName(String nombre) throws TipoDaoException {
        return findByDynamicSelect(SQL_SELECT + " WHERE nombre_tipo LIKE ?",
                new Object[] { "%" + nombre + "%" });
    }

    /**
     * Retorna un registro de la tabla Tipos que coincida con el criterio
     * 'nombre_tipo = :nombre'.
     */
    @Override
    public Tipo findByFullName(String nombre) throws TipoDaoException {
        Tipo[] ret = findByDynamicSelect(SQL_SELECT + " WHERE nombre_tipo = ?",
                new Object[] { nombre });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todas las filas de la tabla Tipos.
     */
    @Override
    public Tipo[] findAll() throws TipoDaoException {
        return findByDynamicSelect(SQL_SELECT + " ORDER BY id_tipo", null);
    }

    /**
     * Retorna todos los registros de la tabla Tipos que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    @Override
    public Tipo[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws TipoDaoException {
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
            throw new TipoDaoException("Exception: " + e.getMessage(), e);
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
     * Retorna todos los registros de la tabla Tipos que coincidan con el WHERE
     * SQL especificado arbitrariamente
     */
    @Override
    public Tipo[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws TipoDaoException {
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
            throw new TipoDaoException("Exception: " + e.getMessage(), e);
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
    protected Tipo fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Tipo dto = new Tipo();
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
    protected Tipo[] fetchMultiResults(ResultSet rs) throws SQLException {
        Collection<Tipo> resultList = new ArrayList<Tipo>();
        while (rs.next()) {
            Tipo dto = new Tipo();
            populateDto(dto, rs);
            resultList.add(dto);
        }
        Tipo ret[] = new Tipo[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }

    /**
     * Se encarga de poblar un DTO con datos de un ResultSet especificado
     */
    protected void populateDto(Tipo dto, ResultSet rs) throws SQLException {
        dto.setIdTipo(new Integer(rs.getInt(COLUMN_ID_TIPO)));
        dto.setNombre(rs.getString(COLUMN_NOMBRE_TIPO));
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
        return IDaoConstants.TABLE_TIPOS;
    }

}
