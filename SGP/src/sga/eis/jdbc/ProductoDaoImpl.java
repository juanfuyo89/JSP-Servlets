package sga.eis.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import sga.eis.dao.ProductoDao;
import sga.eis.dto.Descuento;
import sga.eis.dto.Marca;
import sga.eis.dto.Producto;
import sga.eis.dto.ProductoPk;
import sga.eis.dto.Tipo;
import sga.eis.exceptions.ProductoDaoException;
import sga.eis.jdbc.connect.ResourceManager;
import sga.eis.jdbc.utilities.IDaoConstants;
import sga.web.model.ProductoBean;

/**
 * Clase que implementa los metodos de consulta y modificación de la tabla
 * Productos de la BD
 * 
 * @author Juan Carlos Fuyo
 */
public class ProductoDaoImpl implements ProductoDao {

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
    protected final String     SQL_SELECT            = "SELECT id_producto, nombre, descripcion, costo, precio, marca, tipo, descuento FROM "
                                                             + getTableName()
                                                             + "";

    /**
     * Sentencia SQL INSERT para esta tabla
     */
    protected final String     SQL_INSERT            = "INSERT INTO "
                                                             + getTableName()
                                                             + " ( nombre, descripcion, costo, precio, marca, tipo, descuento ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";

    /**
     * Sentencia SQL UPDATE para esta tabla
     */
    protected final String     SQL_UPDATE            = "UPDATE "
                                                             + getTableName()
                                                             + " SET  nombre=?, descripcion=?, costo=?, precio=?, marca=?, tipo=?, descuento=? WHERE id_producto=?";

    /**
     * Sentencia SQL DELETE para esta tabla
     */
    protected final String     SQL_DELETE            = "DELETE FROM "
                                                             + getTableName()
                                                             + " WHERE id_producto = ?";

    /**
     * Indice de la columna id_producto
     */
    protected static final int COLUMN_ID_PRODUCTO    = 1;

    /**
     * Indice de la columna nombre
     */
    protected static final int COLUMN_NOMBRE         = 2;
    /**
     * Indice de la columna descripcion
     */
    protected static final int COLUMN_DESCRIPCION    = 3;

    /**
     * Indice de la columna costo
     */
    protected static final int COLUMN_COSTO          = 4;

    /**
     * Indice de la columna precio
     */
    protected static final int COLUMN_PRECIO         = 5;

    /**
     * Indice de la columna marca
     */
    protected static final int COLUMN_MARCA          = 6;

    /**
     * Indice de la columna tipo
     */
    protected static final int COLUMN_TIPO           = 7;

    /**
     * Indice de la columna descuento
     */
    protected static final int COLUMN_DESCUENTO      = 8;

    /**
     * Numero de columnas
     */
    protected static final int NUMBER_OF_COLUMNS     = 8;

    /**
     * Indice de la primary-key id_producto
     */
    protected static final int PK_COLUMN_ID_PRODUCTO = 1;

    /**
     * Constructor vacio de la clase
     */
    public ProductoDaoImpl() {
    }

    /**
     * Constructor conparametros de la clase
     * 
     * @param userConn
     */
    public ProductoDaoImpl(final java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Inserta un nuevo registro en la tabla Productos.
     */
    @Override
    public ProductoPk insert(Producto dto) throws ProductoDaoException {
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
            stmt.setString(index++, dto.getDescripcion());
            stmt.setDouble(index++, dto.getCosto());
            stmt.setDouble(index++, dto.getPrecio());
            stmt.setInt(index++, dto.getMarca());
            stmt.setInt(index++, dto.getTipo());
            if (dto.getDescuento() != 0) {
                stmt.setInt(index++, dto.getDescuento());
            }
            else {
                stmt.setNull(index++, java.sql.Types.INTEGER);
            }
            System.out.println("Executing " + SQL_INSERT + " with DTO: " + dto);
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
            // retorna valores de las columnas auto-increment
            rs = stmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                dto.setIdProducto(new Integer(rs.getInt(1)));
            }
            reset(dto);
            return dto.createPk();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new ProductoDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Actualiza un unico registro en la tabla Productos.
     */
    @Override
    public void update(ProductoPk pk, Producto dto) throws ProductoDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_UPDATE + " with DTO: " + dto);
            stmt = userConn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, dto.getNombre());
            stmt.setString(index++, dto.getDescripcion());
            stmt.setDouble(index++, dto.getCosto());
            stmt.setDouble(index++, dto.getPrecio());
            stmt.setInt(index++, dto.getMarca());
            stmt.setInt(index++, dto.getTipo());
            if (dto.getDescuento() != 0) {
                stmt.setInt(index++, dto.getDescuento());
            }
            else {
                stmt.setNull(index++, java.sql.Types.INTEGER);
            }
            stmt.setInt(index, pk.getIdProducto().intValue());
            int rows = stmt.executeUpdate();
            reset(dto);
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new ProductoDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Elimina un unico registro en la tabla Productos.
     */
    @Override
    public void delete(ProductoPk pk) throws ProductoDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_DELETE + " with PK: " + pk);
            stmt = userConn.prepareStatement(SQL_DELETE);
            if (pk.getIdProducto() != null) {
                stmt.setInt(IDaoConstants.INT_ONE,
                        pk.getIdProducto().intValue());
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
            throw new ProductoDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Retorna un unico registro en la tabla Productos que conicida con la
     * primary-key especificada.
     */
    @Override
    public Producto findByPrimaryKey(ProductoPk pk) throws ProductoDaoException {
        return findByPrimaryKey(pk.getIdProducto());
    }

    /**
     * Retorna un registro de la tabla Productos que coincida con el criterio
     * 'id_Producto = :idProducto'.
     */
    @Override
    public Producto findByPrimaryKey(Integer idProducto)
            throws ProductoDaoException {
        Producto[] ret = findByDynamicSelect(SQL_SELECT
                + " WHERE id_producto = ?", new Object[] { idProducto });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna un registro de la tabla Productos que coincida con el criterio
     * 'nombre = :nombre'.
     */
    @Override
    public Producto findByFullName(String nombre) throws ProductoDaoException {
        Producto[] ret = findByDynamicSelect(SQL_SELECT + " WHERE nombre = ?",
                new Object[] { nombre });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todos los Productos con nombres de tipo marca y porcentaje de
     * descuento
     */
    public List<ProductoBean> findAllBeans() throws ProductoDaoException {
        List<ProductoBean> productos = new ArrayList<ProductoBean>();
        String spCall = "{call get_all_products}";
        ResultSet res = null;
        // Llamada al procedimiento almacenado
        try {
            // Validamos la conexion
            this.validateConnection();
            // Preparamos la llamada al SP
            CallableStatement select = userConn.prepareCall(spCall);
            // Se ejecuta el procedimiento almacenado
            res = select.executeQuery();
            while (res.next()) {
                ProductoBean producto = new ProductoBean();
                populateBean(producto, res);
                productos.add(producto);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new ProductoDaoException("Exception: " + e.getMessage(), e);
        }
        return productos;
    }

    /**
     * Retorna todos los registros de la tabla Productos que coincidan con el
     * criterio 'nombre LIKE %:nombre%'.
     */
    @Override
    public List<ProductoBean> findByName(String nombre)
            throws ProductoDaoException {
        List<ProductoBean> productos = new ArrayList<ProductoBean>();
        String spCall = "{call get_products_by_name (?)}";
        ResultSet res = null;
        // Llamada al procedimiento almacenado
        try {
            // Validamos la conexion
            this.validateConnection();
            // Preparamos la llamada al SP
            CallableStatement select = userConn.prepareCall(spCall);
            select.setString(IDaoConstants.INT_ONE, nombre);
            // Se ejecuta el procedimiento almacenado
            res = select.executeQuery();
            while (res.next()) {
                ProductoBean producto = new ProductoBean();
                populateBean(producto, res);
                productos.add(producto);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new ProductoDaoException("Exception: " + e.getMessage(), e);
        }
        return productos;
    }

    /**
     * Retorna todos los registros de la tabla Productos que coincidan con el
     * criterio 'tipo = :idTipo'.
     */
    @Override
    public List<ProductoBean> findByTipo(Integer idTipo)
            throws ProductoDaoException {
        List<ProductoBean> productos = new ArrayList<ProductoBean>();
        String spCall = "{call get_products_by_type (?)}";
        ResultSet res = null;
        // Llamada al procedimiento almacenado
        try {
            // Validamos la conexion
            this.validateConnection();
            // Preparamos la llamada al SP
            CallableStatement select = userConn.prepareCall(spCall);
            select.setInt(IDaoConstants.INT_ONE, idTipo);
            // Se ejecuta el procedimiento almacenado
            res = select.executeQuery();
            while (res.next()) {
                ProductoBean producto = new ProductoBean();
                populateBean(producto, res);
                productos.add(producto);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new ProductoDaoException("Exception: " + e.getMessage(), e);
        }
        return productos;
    }

    /**
     * Retorna todos los registros de la tabla Productos que coincidan con el
     * criterio 'marca = :idMarca'.
     */
    @Override
    public List<ProductoBean> findByMarca(Integer idMarca)
            throws ProductoDaoException {
        List<ProductoBean> productos = new ArrayList<ProductoBean>();
        String spCall = "{call get_products_by_team (?)}";
        ResultSet res = null;
        // Llamada al procedimiento almacenado
        try {
            // Validamos la conexion
            this.validateConnection();
            // Preparamos la llamada al SP
            CallableStatement select = userConn.prepareCall(spCall);
            select.setInt(IDaoConstants.INT_ONE, idMarca);
            // Se ejecuta el procedimiento almacenado
            res = select.executeQuery();
            while (res.next()) {
                ProductoBean producto = new ProductoBean();
                populateBean(producto, res);
                productos.add(producto);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new ProductoDaoException("Exception: " + e.getMessage(), e);
        }
        return productos;
    }

    /**
     * Retorna todos los registros de la tabla Productos que coincidan con el
     * criterio 'descuento = :idDcto'.
     */
    @Override
    public Producto[] findByDescuento(Integer idDcto)
            throws ProductoDaoException {
        return findByDynamicSelect(SQL_SELECT + " WHERE descuento = ?",
                new Object[] { idDcto });
    }

    /**
     * Retorna todos los registros de la tabla Productos que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    @Override
    public Producto[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws ProductoDaoException {
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
            throw new ProductoDaoException("Exception: " + e.getMessage(), e);
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
     * Retorna todos los registros de la tabla Productos que coincidan con el
     * WHERE SQL especificado arbitrariamente
     */
    @Override
    public Producto[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws ProductoDaoException {
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
            throw new ProductoDaoException("Exception: " + e.getMessage(), e);
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
    protected Producto fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Producto dto = new Producto();
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
    protected Producto[] fetchMultiResults(ResultSet rs) throws SQLException {
        Collection<Producto> resultList = new ArrayList<Producto>();
        while (rs.next()) {
            Producto dto = new Producto();
            populateDto(dto, rs);
            resultList.add(dto);
        }
        Producto ret[] = new Producto[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }

    /**
     * Se encarga de poblar un DTO con datos de un ResultSet especificado
     */
    protected void populateDto(Producto dto, ResultSet rs) throws SQLException {
        dto.setIdProducto(new Integer(rs.getInt(COLUMN_ID_PRODUCTO)));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setCosto(rs.getDouble(COLUMN_COSTO));
        dto.setPrecio(rs.getDouble(COLUMN_PRECIO));
        dto.setMarca(rs.getInt(COLUMN_MARCA));
        dto.setTipo(rs.getInt(COLUMN_TIPO));
        dto.setDescuento(rs.getInt(COLUMN_DESCUENTO));
    }

    /**
     * Se encarga de poblar un DTO con datos de un ResultSet especificado
     */
    protected void populateBean(ProductoBean productoBean, ResultSet rs)
            throws SQLException {
        Producto producto = new Producto();
        producto.setIdProducto(rs.getInt(IDaoConstants.INT_ONE));
        producto.setNombre(rs.getString(IDaoConstants.INT_TWO));
        producto.setCosto(rs.getDouble(IDaoConstants.INT_THREE));
        producto.setPrecio(rs.getDouble(IDaoConstants.INT_FOUR));
        Tipo tipo = new Tipo();
        tipo.setNombre(rs.getString(IDaoConstants.INT_FIVE));
        Marca marca = new Marca();
        marca.setNombre(rs.getString(IDaoConstants.INT_SIX));
        Descuento descuento = new Descuento();
        descuento.setPorcentaje(rs.getInt(IDaoConstants.INT_SEVEN));
        productoBean.setProducto(producto);
        productoBean.setTipo(tipo);
        productoBean.setMarca(marca);
        productoBean.setDescuento(descuento);
    }

    /**
     * Restablece los atributos modificados en el DTO
     */
    protected void reset(Producto dto) {
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
        return IDaoConstants.TABLE_PRODUCTOS;
    }

}
