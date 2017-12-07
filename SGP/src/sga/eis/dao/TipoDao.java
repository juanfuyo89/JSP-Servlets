package sga.eis.dao;

import java.sql.Connection;
import sga.eis.dto.Tipo;
import sga.eis.dto.TipoPk;
import sga.eis.exceptions.TipoDaoException;

/**
 * Interface que define los metodos implementados por el Dao de tipo Tipo
 * 
 * @author Juan Carlos Fuyo
 */
public interface TipoDao {

    /**
     * Inserta un nuevo registro en la tabla Tipos.
     */
    public TipoPk insert(Tipo dto) throws TipoDaoException;

    /**
     * Actualiza un unico registro en la tabla Tipos.
     */
    public void update(TipoPk pk, Tipo dto) throws TipoDaoException;

    /**
     * Elimina un unico registro en la tabla Tipos.
     */
    public void delete(TipoPk pk) throws TipoDaoException;

    /**
     * Retorna un unico registro en la tabla Tipos que conicida con la
     * primary-key especificada.
     */
    public Tipo findByPrimaryKey(TipoPk pk) throws TipoDaoException;

    /**
     * Retorna un registro de la tabla Tipos que coincida con el criterio
     * 'id_tipo = :idTipo'.
     */
    public Tipo findByPrimaryKey(Integer idTipo) throws TipoDaoException;

    /**
     * Retorna todos los registros de la tabla Tipos que coincidan con el
     * criterio 'nombre_tipo LIKE %:nombre%'.
     */
    public Tipo[] findByName(String nombre) throws TipoDaoException;

    /**
     * Retorna un registro de la tabla Tipos que coincida con el
     * criterio 'nombre_tipo = :nombre'.
     */
    public Tipo findByFullName(String nombre) throws TipoDaoException;

    /**
     * Retorna todas las filas de la tabla Tipos.
     */
    public Tipo[] findAll() throws TipoDaoException;

    /**
     * Retorna todos los registros de la tabla Tipos que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    public Tipo[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws TipoDaoException;

    /**
     * Retorna todos los registros de la tabla Tipos que coincidan con el WHERE
     * SQL especificado arbitrariamente
     */
    public Tipo[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws TipoDaoException;

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
