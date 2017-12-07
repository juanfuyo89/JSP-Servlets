package sga.eis.dao;

import java.sql.Connection;
import sga.eis.dto.*;
import sga.eis.exceptions.*;

/**
 * Interface que define los metodos implementados por el Dao de tipo Usuario
 * 
 * @author Juan Carlos Fuyo
 */
public interface UsuarioDao {

    /**
     * Inserta un nuevo registro en la tabla Usuarios.
     */
    public UsuarioPk insert(Usuario dto) throws UsuarioDaoException;

    /**
     * Actualiza un unico registro en la tabla Usuarios.
     */
    public void update(UsuarioPk pk, Usuario dto) throws UsuarioDaoException;

    /**
     * Elimina un unico registro en la tabla Usuarios.
     */
    public void delete(UsuarioPk pk) throws UsuarioDaoException;

    /**
     * Retorna un unico registro en la tabla Usuarios que conicida con la
     * primary-key especificada.
     */
    public Usuario findByPrimaryKey(UsuarioPk pk) throws UsuarioDaoException;

    /**
     * Retorna un registro de la tabla Usuarios que coincida con el criterio
     * 'id_usuario = :idUsuario'.
     */
    public Usuario findByPrimaryKey(Integer idUsuario)
            throws UsuarioDaoException;

    /**
     * Retorna un registro de la tabla Usuarios que coincida con el criterio
     * 'username = :username'.
     */
    public Usuario findByFullName(String username) throws UsuarioDaoException;

    /**
     * Retorna todas las filas de la tabla Usuarios.
     */
    public Usuario[] findAll() throws UsuarioDaoException;

    /**
     * Retorna todos los registros de la tabla Usuarios que coincidan con el
     * criterio 'id_persona = :idPersona'.
     */
    public Usuario findByPersona(String idPersona) throws UsuarioDaoException;

    /**
     * Retorna todos los registros de la tabla Usuarios que coincidan con el
     * criterio 'id_rol = :idRol'.
     */
    public Usuario[] findByRol(Integer idRol) throws UsuarioDaoException;

    /**
     * Retorna todos los registros de la tabla Usuarios que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    public Usuario[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws UsuarioDaoException;

    /**
     * Retorna todos los registros de la tabla Usuarios que coincidan con el
     * WHERE SQL especificado arbitrariamente
     */
    public Usuario[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws UsuarioDaoException;

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
