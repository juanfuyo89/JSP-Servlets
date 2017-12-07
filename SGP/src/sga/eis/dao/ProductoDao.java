package sga.eis.dao;

import java.sql.Connection;
import java.util.List;
import sga.eis.dto.Producto;
import sga.eis.dto.ProductoPk;
import sga.eis.exceptions.ProductoDaoException;
import sga.web.model.ProductoBean;

/**
 * Interface que define los metodos implementados por el Dao de tipo Producto
 * 
 * @author Juan Carlos Fuyo
 */
public interface ProductoDao {

    /**
     * Inserta un nuevo registro en la tabla Productos.
     */
    public ProductoPk insert(Producto dto) throws ProductoDaoException;

    /**
     * Actualiza un unico registro en la tabla Productos.
     */
    public void update(ProductoPk pk, Producto dto) throws ProductoDaoException;

    /**
     * Elimina un unico registro en la tabla Productos.
     */
    public void delete(ProductoPk pk) throws ProductoDaoException;

    /**
     * Retorna un unico registro en la tabla Productos que conicida con la
     * primary-key especificada.
     */
    public Producto findByPrimaryKey(ProductoPk pk) throws ProductoDaoException;

    /**
     * Retorna un registro de la tabla Productos que coincida con el criterio
     * 'id_Producto = :idProducto'.
     */
    public Producto findByPrimaryKey(Integer idProducto)
            throws ProductoDaoException;

    /**
     * Retorna un registro de la tabla Productos que coincida con el criterio
     * 'nombre = :nombre'.
     */
    public Producto findByFullName(String nombre) throws ProductoDaoException;

    /**
     * Retorna todos los Productos con nombres de tipo marca y porcentaje de
     * descuento
     */
    public List<ProductoBean> findAllBeans() throws ProductoDaoException;

    /**
     * Retorna todos los registros de la tabla Productos que coincidan con el
     * criterio 'nombre LIKE %:nombre%'.
     */
    public List<ProductoBean> findByName(String nombre)
            throws ProductoDaoException;

    /**
     * Retorna todos los registros de la tabla Productos que coincidan con el
     * criterio 'tipo = :idTipo'.
     */
    public List<ProductoBean> findByTipo(Integer idTipo)
            throws ProductoDaoException;

    /**
     * Retorna todos los registros de la tabla Productos que coincidan con el
     * criterio 'marca = :idMarca'.
     */
    public List<ProductoBean> findByMarca(Integer idMarca)
            throws ProductoDaoException;

    /**
     * Retorna todos los registros de la tabla Productos que coincidan con el
     * criterio 'descuento = :idDcto'.
     */
    public Producto[] findByDescuento(Integer idDcto)
            throws ProductoDaoException;

    /**
     * Retorna todos los registros de la tabla Productos que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    public Producto[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws ProductoDaoException;

    /**
     * Retorna todos los registros de la tabla Productos que coincidan con el
     * WHERE SQL especificado arbitrariamente
     */
    public Producto[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws ProductoDaoException;

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
