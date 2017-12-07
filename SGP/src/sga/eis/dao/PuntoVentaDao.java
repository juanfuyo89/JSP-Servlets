package sga.eis.dao;

import java.sql.Connection;
import sga.eis.dto.PuntoVenta;
import sga.eis.dto.PuntoVentaPk;
import sga.eis.exceptions.PuntoVentaDaoException;

/**
 * Interface que define los metodos implementados por el Dao de tipo PuntoVenta
 * 
 * @author Juan Carlos Fuyo
 *
 */
public interface PuntoVentaDao {

    /**
     * Inserta un nuevo registro en la tabla Puntos_venta.
     */
    public PuntoVentaPk insert(PuntoVenta dto) throws PuntoVentaDaoException;

    /**
     * Actualiza un unico registro en la tabla Puntos_venta.
     */
    public void update(PuntoVentaPk pk, PuntoVenta dto) throws PuntoVentaDaoException;

    /**
     * Elimina un unico registro en la tabla Puntos_venta.
     */
    public void delete(PuntoVentaPk pk) throws PuntoVentaDaoException;

    /**
     * Retorna un unico registro en la tabla Puntos_venta que conicida con la
     * primary-key especificada.
     */
    public PuntoVenta findByPrimaryKey(PuntoVentaPk pk) throws PuntoVentaDaoException;

    /**
     * Retorna un registro de la tabla Puntos_venta que coincida con el criterio
     * 'id_PuntoVenta = :idPuntoVenta'.
     */
    public PuntoVenta findByPrimaryKey(Integer idPuntoVenta)
            throws PuntoVentaDaoException;

    /**
     * Retorna todas filas de la tabla Puntos_venta que coincidan con el criterio
     * 'nombre_pdv = :nombre'.
     */
    public PuntoVenta[] findByName(String nombre) throws PuntoVentaDaoException;

    /**
     * Retorna todas las filas de la tabla Puntos_venta.
     */
    public PuntoVenta[] findAll() throws PuntoVentaDaoException;

    /**
     * Retorna todos los registros de la tabla Puntos_venta que coincidan con el
     * criterio 'id_admin = :idPersona'.
     */
    public PuntoVenta findByAdmin(String idPersona) throws PuntoVentaDaoException;

    /**
     * Retorna todos los registros de la tabla Puntos_venta que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    public PuntoVenta[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws PuntoVentaDaoException;

    /**
     * Retorna todos los registros de la tabla Puntos_venta que coincidan con el
     * WHERE SQL especificado arbitrariamente
     */
    public PuntoVenta[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws PuntoVentaDaoException;

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
