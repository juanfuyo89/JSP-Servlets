package sga.eis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import sga.eis.dao.ProductosPuntoVentaDao;
import sga.eis.dto.ProductosPuntoVenta;
import sga.eis.dto.ProductosPuntoVentaPk;
import sga.eis.exceptions.ProductosPuntoVentaDaoException;
import sga.eis.jdbc.connect.ResourceManager;
import sga.eis.jdbc.utilities.IDaoConstants;

/**
 * Clase que implementa los metodos de consulta y modificación de la tabla
 * Productos_punto_venta de la BD
 * 
 * @author Juan Carlos Fuyo
 */
public class ProductosPuntoVentaDaoImpl implements ProductosPuntoVentaDao {

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
    protected final String     SQL_SELECT            = "SELECT id_pdv, id_producto, cantidad FROM "
                                                             + getTableName()
                                                             + "";

    /**
     * Sentencia SQL INSERT para esta tabla
     */
    protected final String     SQL_INSERT            = "INSERT INTO "
                                                             + getTableName()
                                                             + " ( id_pdv, id_producto, cantidad ) VALUES ( ?, ?, ? )";

    /**
     * Sentencia SQL UPDATE para esta tabla
     */
    protected final String     SQL_UPDATE            = "UPDATE "
                                                             + getTableName()
                                                             + " SET  cantidad=? WHERE id_pdv=? AND id_producto=?";

    /**
     * Sentencia SQL DELETE para esta tabla
     */
    protected final String     SQL_DELETE            = "DELETE FROM "
                                                             + getTableName()
                                                             + " WHERE id_pdv=? AND id_producto=?";

    /**
     * Indice de la columna id_pdv
     */
    protected static final int COLUMN_ID_PDV         = 1;

    /**
     * Indice de la columna id_producto
     */
    protected static final int COLUMN_ID_PRODUCTO    = 2;

    /**
     * Indice de la columna cantidad
     */
    protected static final int COLUMN_CANTIDAD       = 3;

    /**
     * Numero de columnas
     */
    protected static final int NUMBER_OF_COLUMNS     = 3;

    /**
     * Indice de la primary-key id_pdv
     */
    protected static final int PK_COLUMN_ID_PDV      = 1;

    /**
     * Indice de la primary-key id_producto
     */
    protected static final int PK_COLUMN_ID_PRODUCTO = 2;

    /**
     * Constructor vacio de la clase
     */
    public ProductosPuntoVentaDaoImpl() {
    }

    /**
     * Constructor conparametros de la clase
     * 
     * @param userConn
     */
    public ProductosPuntoVentaDaoImpl(final java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Inserta un nuevo registro en la tabla Productos_punto_venta.
     */
    @Override
    public ProductosPuntoVentaPk insert(ProductosPuntoVenta dto)
            throws ProductosPuntoVentaDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            stmt = userConn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, dto.getIdPuntoVenta());
            stmt.setInt(index++, dto.getIdProducto());
            stmt.setInt(index++, dto.getCantidad());
            System.out.println("Executing " + SQL_INSERT + " with DTO: " + dto);
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
            reset(dto);
            return dto.createPk();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new ProductosPuntoVentaDaoException("Exception: "
                    + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Actualiza un unico registro en la tabla Productos_punto_venta.
     */
    @Override
    public void update(ProductosPuntoVentaPk pk, ProductosPuntoVenta dto)
            throws ProductosPuntoVentaDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_UPDATE + " with DTO: " + dto);
            stmt = userConn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setInt(index++, dto.getCantidad());
            stmt.setInt(index++, dto.getIdPuntoVenta());
            stmt.setInt(index, dto.getIdProducto());
            int rows = stmt.executeUpdate();
            reset(dto);
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new ProductosPuntoVentaDaoException("Exception: "
                    + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Elimina un unico registro en la tabla Productos_punto_venta.
     */
    @Override
    public void delete(ProductosPuntoVentaPk pk)
            throws ProductosPuntoVentaDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_DELETE + " with PK: " + pk);
            stmt = userConn.prepareStatement(SQL_DELETE);
            if (pk.getIdPuntoVenta() != null && pk.getIdProducto() != null) {
                stmt.setInt(IDaoConstants.INT_ONE, pk.getIdPuntoVenta());
                stmt.setInt(IDaoConstants.INT_TWO,
                        pk.getIdProducto().intValue());
            }
            else {
                stmt.setNull(IDaoConstants.INT_ONE, java.sql.Types.INTEGER);
                stmt.setNull(IDaoConstants.INT_TWO, java.sql.Types.INTEGER);
            }
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new ProductosPuntoVentaDaoException("Exception: "
                    + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Retorna un unico registro en la tabla Productos_punto_venta que conicida
     * con la primary-key especificada.
     */
    @Override
    public ProductosPuntoVenta findByPrimaryKey(ProductosPuntoVentaPk pk)
            throws ProductosPuntoVentaDaoException {
        return findByPrimaryKey(pk.getIdPuntoVenta(), pk.getIdProducto());
    }

    /**
     * Retorna un registro de la tabla Productos_punto_venta que coincida con el
     * criterio 'id_pdv = :idPuntoVenta AND id_producto = :idProducto'.
     */
    @Override
    public ProductosPuntoVenta findByPrimaryKey(Integer idPuntoVenta,
            Integer idProducto) throws ProductosPuntoVentaDaoException {
        ProductosPuntoVenta[] ret = findByDynamicSelect(SQL_SELECT
                + " WHERE id_pdv = ? AND id_producto = ?", new Object[] {
                idPuntoVenta, idProducto });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todas las filas de la tabla Productos_punto_venta.
     */
    @Override
    public ProductosPuntoVenta[] findAll()
            throws ProductosPuntoVentaDaoException {
        return findByDynamicSelect(SQL_SELECT + " ORDER BY id_pdv", null);
    }

    /**
     * Retorna todos los registros de la tabla Productos_punto_venta que
     * coincidan con el criterio 'id_pdv = :idPuntoVenta'.
     */
    @Override
    public ProductosPuntoVenta[] findByPuntoVenta(Integer idPuntoVenta)
            throws ProductosPuntoVentaDaoException {
        return findByDynamicSelect(SQL_SELECT + " WHERE id_pdv = ?",
                new Object[] { idPuntoVenta });
    }

    /**
     * Retorna todos los registros de la tabla Productos_punto_venta que
     * coincidan con el criterio 'id_producto = :idProducto'.
     */
    @Override
    public ProductosPuntoVenta[] findByProducto(Integer idProducto)
            throws ProductosPuntoVentaDaoException {
        return findByDynamicSelect(SQL_SELECT + " WHERE id_producto = ?",
                new Object[] { idProducto });
    }

    /**
     * Retorna todos los registros de la tabla Productos_punto_venta que
     * coincidan con la sentencia SQL especificada arbitrariamente
     */
    @Override
    public ProductosPuntoVenta[] findByDynamicSelect(String sql,
            Object[] sqlParams) throws ProductosPuntoVentaDaoException {
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
            throw new ProductosPuntoVentaDaoException("Exception: "
                    + e.getMessage(), e);
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
     * Retorna todos los registros de la tabla Productos_punto_venta que
     * coincidan con el WHERE SQL especificado arbitrariamente
     */
    @Override
    public ProductosPuntoVenta[] findByDynamicWhere(String sql,
            Object[] sqlParams) throws ProductosPuntoVentaDaoException {
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
            throw new ProductosPuntoVentaDaoException("Exception: "
                    + e.getMessage(), e);
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
    protected ProductosPuntoVenta fetchSingleResult(ResultSet rs)
            throws SQLException {
        if (rs.next()) {
            ProductosPuntoVenta dto = new ProductosPuntoVenta();
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
    protected ProductosPuntoVenta[] fetchMultiResults(ResultSet rs)
            throws SQLException {
        Collection<ProductosPuntoVenta> resultList = new ArrayList<ProductosPuntoVenta>();
        while (rs.next()) {
            ProductosPuntoVenta dto = new ProductosPuntoVenta();
            populateDto(dto, rs);
            resultList.add(dto);
        }
        ProductosPuntoVenta ret[] = new ProductosPuntoVenta[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }

    /**
     * Se encarga de poblar un DTO con datos de un ResultSet especificado
     */
    protected void populateDto(ProductosPuntoVenta dto, ResultSet rs)
            throws SQLException {
        dto.setIdPuntoVenta(new Integer(rs.getInt(COLUMN_ID_PDV)));
        dto.setIdProducto(new Integer(rs.getInt(COLUMN_ID_PRODUCTO)));
        dto.setCantidad(rs.getInt(COLUMN_CANTIDAD));
    }

    /**
     * Restablece los atributos modificados en el DTO
     */
    protected void reset(ProductosPuntoVenta dto) {
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
        return IDaoConstants.TABLE_PROD_PTOS_VTA;
    }

}
