package sga.eis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import sga.eis.dao.VentaDao;
import sga.eis.dto.Venta;
import sga.eis.dto.VentaPk;
import sga.eis.exceptions.VentaDaoException;
import sga.eis.jdbc.connect.ResourceManager;
import sga.eis.jdbc.utilities.IDaoConstants;

/**
 * Clase que implementa los metodos de consulta y modificación de la tabla
 * Ventas de la BD
 * 
 * @author Juan Carlos Fuyo
 */
public class VentaDaoImpl implements VentaDao {

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
    protected final String     SQL_SELECT          = "SELECT id_venta, id_persona, id_producto, id_pdv, fecha_venta, precio_venta, cantidad FROM "
                                                           + getTableName()
                                                           + "";

    /**
     * Sentencia SQL INSERT para esta tabla
     */
    protected final String     SQL_INSERT          = "INSERT INTO "
                                                           + getTableName()
                                                           + " ( id_persona, id_producto, id_pdv, fecha_venta, precio_venta, cantidad ) VALUES ( ?, ?, ?, ?, ?, ? )";

    /**
     * Sentencia SQL UPDATE para esta tabla
     */
    protected final String     SQL_UPDATE          = "UPDATE "
                                                           + getTableName()
                                                           + " SET  cantidad=? WHERE id_venta=?";

    /**
     * Sentencia SQL DELETE para esta tabla
     */
    protected final String     SQL_DELETE          = "DELETE FROM "
                                                           + getTableName()
                                                           + " WHERE id_venta=?";

    /**
     * Indice de la columna id_venta
     */
    protected static final int COLUMN_ID_VENTA     = 1;

    /**
     * Indice de la columna id_persona
     */
    protected static final int COLUMN_ID_PERSONA   = 2;

    /**
     * Indice de la columna id_producto
     */
    protected static final int COLUMN_ID_PRODUCTO  = 3;

    /**
     * Indice de la columna id_pdv
     */
    protected static final int COLUMN_ID_PDV       = 4;
    /**
     * Indice de la columna fecha_venta
     */
    protected static final int COLUMN_FECHA_VENTA  = 5;

    /**
     * Indice de la columna precio_venta
     */
    protected static final int COLUMN_PRECIO_VENTA = 6;
    /**
     * Indice de la columna cantidad
     */
    protected static final int COLUMN_CANTIDAD     = 7;
    /**
     * Numero de columnas
     */
    protected static final int NUMBER_OF_COLUMNS   = 7;

    /**
     * Indice de la primary-key id_venta
     */
    protected static final int PK_COLUMN_ID_VENTA  = 1;

    /**
     * Constructor vacio de la clase
     */
    public VentaDaoImpl() {
    }

    /**
     * Constructor conparametros de la clase
     * 
     * @param userConn
     */
    public VentaDaoImpl(final java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Inserta un nuevo registro en la tabla Ventas.
     */
    @Override
    public VentaPk insert(Venta dto) throws VentaDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            stmt = userConn.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            if (dto.getIdPersona() != null) {
                stmt.setString(index++, dto.getIdPersona());
            }
            else {
                stmt.setNull(index++, java.sql.Types.VARCHAR);
            }
            stmt.setInt(index++, dto.getIdProducto());
            stmt.setInt(index++, dto.getIdPuntoVenta());
            stmt.setDate(index++,
                    new java.sql.Date(dto.getFecVenta().getTime()));
            stmt.setDouble(index++, dto.getPrecioVenta());
            stmt.setInt(index++, dto.getCantidad());
            System.out.println("Executing " + SQL_INSERT + " with DTO: " + dto);
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
            // retorna valores de las columnas auto-increment
            rs = stmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                dto.setIdVenta(new Integer(rs.getInt(1)));
            }
            reset(dto);
            return dto.createPk();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new VentaDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Actualiza un unico registro en la tabla Ventas.
     */
    @Override
    public void update(VentaPk pk, Venta dto) throws VentaDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_UPDATE + " with DTO: " + dto);
            stmt = userConn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setInt(index++, dto.getCantidad());
            stmt.setInt(index, pk.getIdVenta());
            int rows = stmt.executeUpdate();
            reset(dto);
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new VentaDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Elimina un unico registro en la tabla Ventas.
     */
    @Override
    public void delete(VentaPk pk) throws VentaDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_DELETE + " with PK: " + pk);
            stmt = userConn.prepareStatement(SQL_DELETE);
            if (pk.getIdVenta() != null) {
                stmt.setInt(IDaoConstants.INT_ONE, pk.getIdVenta());
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
            throw new VentaDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Retorna un unico registro en la tabla Ventas que conicida con la
     * primary-key especificada.
     */
    @Override
    public Venta findByPrimaryKey(VentaPk pk) throws VentaDaoException {
        return findByPrimaryKey(pk.getIdVenta());
    }

    /**
     * Retorna un registro de la tabla Ventas que coincida con el criterio
     * 'id_persona = :idPersona AND id_producto = :idProducto'.
     */
    @Override
    public Venta findByPrimaryKey(Integer idVenta) throws VentaDaoException {
        Venta[] ret = findByDynamicSelect(SQL_SELECT + " WHERE id_venta = ?",
                new Object[] { idVenta });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todas las filas de la tabla Ventas.
     */
    @Override
    public Venta[] findAll() throws VentaDaoException {
        return findByDynamicSelect(SQL_SELECT + " ORDER BY id_persona", null);
    }

    /**
     * Retorna todos los registros de la tabla Ventas que coincidan con el
     * criterio 'id_persona = :idPersona'.
     */
    @Override
    public Venta[] findByPersona(String idPersona) throws VentaDaoException {
        return findByDynamicSelect(SQL_SELECT + " WHERE id_persona = ?",
                new Object[] { idPersona });
    }

    /**
     * Retorna todos los registros de la tabla Ventas que coincidan con el
     * criterio 'id_producto = :idProducto'.
     */
    @Override
    public Venta[] findByProducto(Integer idProducto) throws VentaDaoException {
        return findByDynamicSelect(SQL_SELECT + " WHERE id_producto = ?",
                new Object[] { idProducto });
    }

    /**
     * Retorna todos los registros de la tabla Ventas que coincidan con el
     * criterio 'id_pdv = :idPuntoVenta'.
     */
    @Override
    public Venta[] findByPuntoVenta(Integer idPuntoVenta)
            throws VentaDaoException {
        return findByDynamicSelect(SQL_SELECT + " WHERE id_pdv = ?",
                new Object[] { idPuntoVenta });
    }

    /**
     * Retorna todos los registros de la tabla Ventas que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    @Override
    public Venta[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws VentaDaoException {
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
            throw new VentaDaoException("Exception: " + e.getMessage(), e);
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
     * Retorna todos los registros de la tabla Ventas que coincidan con el WHERE
     * SQL especificado arbitrariamente
     */
    @Override
    public Venta[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws VentaDaoException {
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
            throw new VentaDaoException("Exception: " + e.getMessage(), e);
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
    protected Venta fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Venta dto = new Venta();
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
    protected Venta[] fetchMultiResults(ResultSet rs) throws SQLException {
        Collection<Venta> resultList = new ArrayList<Venta>();
        while (rs.next()) {
            Venta dto = new Venta();
            populateDto(dto, rs);
            resultList.add(dto);
        }
        Venta ret[] = new Venta[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }

    /**
     * Se encarga de poblar un DTO con datos de un ResultSet especificado
     */
    protected void populateDto(Venta dto, ResultSet rs) throws SQLException {
        dto.setIdVenta(new Integer(rs.getInt(COLUMN_ID_VENTA)));
        dto.setIdPersona(rs.getString(COLUMN_ID_PERSONA));
        dto.setIdProducto(new Integer(rs.getInt(COLUMN_ID_PRODUCTO)));
        dto.setIdPuntoVenta(new Integer(rs.getInt(COLUMN_ID_PDV)));
        dto.setFecVenta(rs.getDate(COLUMN_FECHA_VENTA));
        dto.setPrecioVenta(rs.getDouble(COLUMN_PRECIO_VENTA));
        dto.setCantidad(rs.getInt(COLUMN_CANTIDAD));
    }

    /**
     * Restablece los atributos modificados en el DTO
     */
    protected void reset(Venta dto) {
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
        return IDaoConstants.TABLE_VENTAS;
    }

}
