package sga.eis.dao;

import java.sql.Connection;
import sga.eis.dto.Rol;
import sga.eis.dto.RolPk;
import sga.eis.exceptions.RolDaoException;

/**
 * Interface que define los metodos implementados por el Dao de tipo Rol
 * 
 * @author Juan Carlos Fuyo
 */
public interface RolDao {

    /**
     * Inserta un nuevo registro en la tabla Roles.
     */
    public RolPk insert(Rol dto) throws RolDaoException;

    /**
     * Actualiza un unico registro en la tabla Roles.
     */
    public void update(RolPk pk, Rol dto) throws RolDaoException;

    /**
     * Elimina un unico registro en la tabla Roles.
     */
    public void delete(RolPk pk) throws RolDaoException;

    /**
     * Retorna un unico registro en la tabla Roles que conicida con la
     * primary-key especificada.
     */
    public Rol findByPrimaryKey(RolPk pk) throws RolDaoException;

    /**
     * Retorna un registro de la tabla Roles que coincida con el criterio
     * 'id_rol = :idRol'.
     */
    public Rol findByPrimaryKey(Integer idRol)
            throws RolDaoException;

    /**
     * Retorna todas las filas de la tabla Roles.
     */
    public Rol[] findAll() throws RolDaoException;

    /**
     * Retorna todos los registros de la tabla Roles que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    public Rol[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws RolDaoException;

    /**
     * Retorna todos los registros de la tabla Roles que coincidan con el
     * WHERE SQL especificado arbitrariamente
     */
    public Rol[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws RolDaoException;

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
