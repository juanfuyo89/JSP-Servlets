package sga.eis.dao;

import java.sql.Connection;
import sga.eis.dto.Descuento;
import sga.eis.dto.DescuentoPk;
import sga.eis.exceptions.DescuentoDaoException;

/**
 * Interface que define los metodos implementados por el Dao de tipo Descuento
 * 
 * @author Juan Carlos Fuyo
 */
public interface DescuentoDao {

    /**
     * Inserta un nuevo registro en la tabla Descuentos.
     */
    public DescuentoPk insert(Descuento dto) throws DescuentoDaoException;

    /**
     * Actualiza un unico registro en la tabla Descuentos.
     */
    public void update(DescuentoPk pk, Descuento dto) throws DescuentoDaoException;

    /**
     * Elimina un unico registro en la tabla Descuentos.
     */
    public void delete(DescuentoPk pk) throws DescuentoDaoException;

    /**
     * Retorna un unico registro en la tabla Descuentos que conicida con la
     * primary-key especificada.
     */
    public Descuento findByPrimaryKey(DescuentoPk pk) throws DescuentoDaoException;

    /**
     * Retorna un registro de la tabla Descuentos que coincida con el criterio
     * 'id_descuento = :idDescuento'.
     */
    public Descuento findByPrimaryKey(Integer idDescuento)
            throws DescuentoDaoException;

    /**
     * Retorna todas las filas de la tabla Descuentos.
     */
    public Descuento[] findAll() throws DescuentoDaoException;

    /**
     * Retorna todos los registros de la tabla Descuentos que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    public Descuento[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws DescuentoDaoException;

    /**
     * Retorna todos los registros de la tabla Descuentos que coincidan con el
     * WHERE SQL especificado arbitrariamente
     */
    public Descuento[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws DescuentoDaoException;

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
