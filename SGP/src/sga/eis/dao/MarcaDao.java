package sga.eis.dao;

import java.sql.Connection;
import sga.eis.dto.Marca;
import sga.eis.dto.MarcaPk;
import sga.eis.exceptions.MarcaDaoException;

/**
 * Interface que define los metodos implementados por el Dao de tipo Marca
 * 
 * @author Juan Carlos Fuyo
 */
public interface MarcaDao {

    /**
     * Inserta un nuevo registro en la tabla Marcas.
     */
    public MarcaPk insert(Marca dto) throws MarcaDaoException;

    /**
     * Actualiza un unico registro en la tabla Marcas.
     */
    public void update(MarcaPk pk, Marca dto) throws MarcaDaoException;

    /**
     * Elimina un unico registro en la tabla Marcas.
     */
    public void delete(MarcaPk pk) throws MarcaDaoException;

    /**
     * Retorna un unico registro en la tabla Marcas que conicida con la
     * primary-key especificada.
     */
    public Marca findByPrimaryKey(MarcaPk pk) throws MarcaDaoException;

    /**
     * Retorna un registro de la tabla Marcas que coincida con el criterio
     * 'id_marca = :idMarca'.
     */
    public Marca findByPrimaryKey(Integer idMarca) throws MarcaDaoException;

    /**
     * Retorna todos los registros de la tabla Marcas que coincidan con el
     * criterio 'nombre_marca LIKE %:nombre%'.
     */
    public Marca[] findByName(String nombre) throws MarcaDaoException;

    /**
     * Retorna un registro de la tabla Marcas que coincida con el criterio
     * 'nombre_marca = :nombre'.
     */
    public Marca findByFullName(String nombre) throws MarcaDaoException;

    /**
     * Retorna todas las filas de la tabla Marcas.
     */
    public Marca[] findAll() throws MarcaDaoException;

    /**
     * Retorna todos los registros de la tabla Marcas que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    public Marca[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws MarcaDaoException;

    /**
     * Retorna todos los registros de la tabla Marcas que coincidan con el WHERE
     * SQL especificado arbitrariamente
     */
    public Marca[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws MarcaDaoException;

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
