package sga.eis.dao;

import sga.eis.dto.*;
import sga.eis.exceptions.*;
import java.sql.Connection;

/**
 * Interface que define los metodos implementados por el Dao de tipo Persona
 * 
 * @author Juan Carlos Fuyo
 */
public interface PersonaDao {

    /**
     * Inserta un nuevo registro en la tabla Personas.
     */
    public void insert(Persona dto) throws PersonaDaoException;

    /**
     * Actualiza un unico registro en la tabla Personas.
     */
    public void update(PersonaPk pk, Persona dto) throws PersonaDaoException;

    /**
     * Elimina un unico registro en la tabla Personas.
     */
    public void delete(PersonaPk pk) throws PersonaDaoException;

    /**
     * Retorna un unico registro en la tabla Personas que conicida con la
     * primary-key especificada.
     */
    public Persona findByPrimaryKey(PersonaPk pk) throws PersonaDaoException;

    /**
     * Retorna un registro de la tabla Personas que coincida con el criterio
     * 'cedula = :cedula'.
     */
    public Persona findByPrimaryKey(String cedula) throws PersonaDaoException;

    /**
     * Retorna todas las filas de la tabla Personas.
     */
    public Persona[] findAll() throws PersonaDaoException;

    /**
     * Retorna todos los registros de la tabla Personas que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    public Persona[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws PersonaDaoException;

    /**
     * Retorna todos los registros de la tabla Personas que coincidan con el
     * WHERE SQL especificado arbitrariamente
     */
    public Persona[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws PersonaDaoException;

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
