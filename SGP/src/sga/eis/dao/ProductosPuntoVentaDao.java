package sga.eis.dao;

import java.sql.Connection;
import sga.eis.dto.ProductosPuntoVenta;
import sga.eis.dto.ProductosPuntoVentaPk;
import sga.eis.exceptions.ProductosPuntoVentaDaoException;

/**
 * Interface que define los metodos implementados por el Dao de tipo
 * ProductosPuntoVenta
 * 
 * @author Juan Carlos Fuyo
 */
public interface ProductosPuntoVentaDao {

    /**
     * Inserta un nuevo registro en la tabla Productos_punto_venta.
     */
    public ProductosPuntoVentaPk insert(ProductosPuntoVenta dto)
            throws ProductosPuntoVentaDaoException;

    /**
     * Actualiza un unico registro en la tabla Productos_punto_venta.
     */
    public void update(ProductosPuntoVentaPk pk, ProductosPuntoVenta dto)
            throws ProductosPuntoVentaDaoException;

    /**
     * Elimina un unico registro en la tabla Productos_punto_venta.
     */
    public void delete(ProductosPuntoVentaPk pk)
            throws ProductosPuntoVentaDaoException;

    /**
     * Retorna un unico registro en la tabla Productos_punto_venta que conicida
     * con la primary-key especificada.
     */
    public ProductosPuntoVenta findByPrimaryKey(ProductosPuntoVentaPk pk)
            throws ProductosPuntoVentaDaoException;

    /**
     * Retorna un registro de la tabla Productos_punto_venta que coincida con el
     * criterio 'id_pdv = :idPuntoVenta AND id_producto = :idProducto'.
     */
    public ProductosPuntoVenta findByPrimaryKey(Integer idPuntoVenta, Integer idProducto)
            throws ProductosPuntoVentaDaoException;

    /**
     * Retorna todas las filas de la tabla Productos_punto_venta.
     */
    public ProductosPuntoVenta[] findAll()
            throws ProductosPuntoVentaDaoException;

    /**
     * Retorna todos los registros de la tabla Productos_punto_venta que
     * coincidan con el criterio 'id_pdv = :idPuntoVenta'.
     */
    public ProductosPuntoVenta[] findByPuntoVenta(Integer idPuntoVenta)
            throws ProductosPuntoVentaDaoException;

    /**
     * Retorna todos los registros de la tabla Productos_punto_venta que
     * coincidan con el criterio 'id_producto = :idProducto'.
     */
    public ProductosPuntoVenta[] findByProducto(Integer idProducto)
            throws ProductosPuntoVentaDaoException;

    /**
     * Retorna todos los registros de la tabla Productos_punto_venta que
     * coincidan con la sentencia SQL especificada arbitrariamente
     */
    public ProductosPuntoVenta[] findByDynamicSelect(String sql,
            Object[] sqlParams) throws ProductosPuntoVentaDaoException;

    /**
     * Retorna todos los registros de la tabla Productos_punto_venta que
     * coincidan con el WHERE SQL especificado arbitrariamente
     */
    public ProductosPuntoVenta[] findByDynamicWhere(String sql,
            Object[] sqlParams) throws ProductosPuntoVentaDaoException;

    /**
     * Sets the value of maxRows
     */
    public void setMaxRows(int maxRows);

    /**
     * Gets the value of maxRows
     */
    public int getMaxRows();

    /**
     * Retorna la conexión actual del usuario
     * 
     * @return Connection
     */
    public Connection getUserConn();

    /**
     * Setea la conexión a usar con la BD
     * 
     * @param userConn
     */
    public void setUserConn(Connection userConn);

}
