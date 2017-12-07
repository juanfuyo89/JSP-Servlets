package sga.eis.dao;

import java.sql.Connection;
import sga.eis.dto.Venta;
import sga.eis.dto.VentaPk;
import sga.eis.exceptions.VentaDaoException;

/**
 * Interface que define los metodos implementados por el Dao de tipo Venta
 * 
 * @author Juan Carlos Fuyo
 */
public interface VentaDao {

    /**
     * Inserta un nuevo registro en la tabla Ventas.
     */
    public VentaPk insert(Venta dto) throws VentaDaoException;

    /**
     * Actualiza un unico registro en la tabla Ventas.
     */
    public void update(VentaPk pk, Venta dto) throws VentaDaoException;

    /**
     * Elimina un unico registro en la tabla Ventas.
     */
    public void delete(VentaPk pk) throws VentaDaoException;

    /**
     * Retorna todas las filas de la tabla Ventas.
     */
    public Venta[] findAll() throws VentaDaoException;

    /**
     * Retorna un unico registro en la tabla Ventas que conicida con la
     * primary-key especificada.
     */
    public Venta findByPrimaryKey(VentaPk pk) throws VentaDaoException;

    /**
     * Retorna un registro de la tabla Ventas que coincida con el criterio
     * 'id_venta = :idVenta'.
     */
    public Venta findByPrimaryKey(Integer idVenta)
            throws VentaDaoException;

    /**
     * Retorna todos los registros de la tabla Ventas que coincidan con el
     * criterio 'id_persona = :idPersona'.
     */
    public Venta[] findByPersona(String idPersona) throws VentaDaoException;

    /**
     * Retorna todos los registros de la tabla Ventas que coincidan con el
     * criterio 'id_producto = :idProducto'.
     */
    public Venta[] findByProducto(Integer idProducto) throws VentaDaoException;

    /**
     * Retorna todos los registros de la tabla Ventas que coincidan con el
     * criterio 'id_pdv = :idPuntoVenta'.
     */
    public Venta[] findByPuntoVenta(Integer idPuntoVenta) throws VentaDaoException;

    /**
     * Retorna todos los registros de la tabla Ventas que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    public Venta[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws VentaDaoException;

    /**
     * Retorna todos los registros de la tabla Ventas que coincidan con el WHERE
     * SQL especificado arbitrariamente
     */
    public Venta[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws VentaDaoException;

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
