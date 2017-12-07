package sga.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import sga.eis.dao.UsuarioDao;
import sga.eis.dto.Usuario;
import sga.eis.dto.UsuarioPk;
import sga.eis.exceptions.UsuarioDaoException;
import sga.eis.factory.UsuarioDaoFactory;
import sga.eis.jdbc.connect.ResourceManager;
import sga.services.UsuarioService;
import sga.services.exceptions.BusinessException;
import sga.web.utilities.IGlobalConstant;

/**
 * Clase que implementa los metodos de la capa de servicios para manipular un
 * Usuario
 * 
 * @author Juan Carlos Fuyo
 */
public class UsuarioServiceImpl implements UsuarioService {

    /**
     * Objeto de la clase para implementar el patron singleton
     */
    private static UsuarioService usuarioServiceInstance;

    /**
     * Atributo usuarioDao para comunicarnos con la capa de datos
     */
    UsuarioDao                    usuarioDao;

    /**
     * Constructor sin argumentos privado, para implementar el patron singleton
     */
    private UsuarioServiceImpl() {
    }

    /**
     * Metodo para crear una nueva y unica instancia si es que no existe
     * 
     * @return usuarioServiceInstance
     */
    public static UsuarioService getInstance() {
        if (usuarioServiceInstance == null) {
            usuarioServiceInstance = new UsuarioServiceImpl();
        }
        return usuarioServiceInstance;
    }

    /**
     * Metodo encargado de recuperar una instancia de UsuarioDao haciendo uso
     * del patrón Factory
     * 
     * @return UsuarioDao
     */
    public UsuarioDao getUsuarioDao() {
        if (this.usuarioDao != null) {
            return this.usuarioDao;
        }
        return UsuarioDaoFactory.create();
    }

    /**
     * Metodo que verifica que exista un usuario en sistema
     * 
     * @param usuario
     * @return boolean
     */
    public Usuario usuarioExistente(Usuario usuarioDto) {
        Usuario[] usuarios;
        Usuario userReturn = new Usuario();
        try {
            this.usuarioDao = getUsuarioDao();
            // Buscamos el objeto por UserName y password
            final String SQL_WHERE = "username = ? and passwd = ?";
            Object[] sqlParams = { usuarioDto.getUsername(),
                    DigestUtils.md5Hex(usuarioDto.getPassword()) };
            usuarios = this.usuarioDao.findByDynamicWhere(SQL_WHERE, sqlParams);
            if (usuarios.length > 0) {
                userReturn = usuarios[IGlobalConstant.INT_ZERO];
            }
        }
        catch (UsuarioDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el usuario en la BD", ex);
        }
        return userReturn;
    }

    /**
     * Metodo que retorna todos los usuarios creados en sistema
     * 
     * @return List<Usuario>
     */
    @Override
    public List<Usuario> getAllUsuarios() {
        try {
            this.usuarioDao = getUsuarioDao();
            return Arrays.asList(this.usuarioDao.findAll());
        }
        catch (UsuarioDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el arreglo de Usuarios en la BD",
                    ex);
        }
    }

    /**
     * Metodo que retorna un Usuario de acuerdo al id
     * 
     * @param idUsuario
     * @return Usuario
     */
    @Override
    public Usuario getUsuarioById(Integer idUsuario) {
        this.usuarioDao = getUsuarioDao();
        try {
            return this.usuarioDao.findByPrimaryKey(idUsuario);
        }
        catch (UsuarioDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Usuario con id: "
                            + idUsuario, ex);
        }
    }

    /**
     * Metodo que retorna un Usuario de acuerdo al username
     * 
     * @param username
     * 
     * @return Usuario
     */
    @Override
    public Usuario getUsuarioByFullName(String username) {
        this.usuarioDao = getUsuarioDao();
        try {
            return this.usuarioDao.findByFullName(username);
        }
        catch (UsuarioDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Usuario con username: "
                            + username, ex);
        }
    }

	/**
	 * Metodo que retorna un Usuario de acuerdo al idPersona
	 * 
	 * @param cedula
	 * 
	 * @return Usuario
	 */
    @Override
    public Usuario getUsuarioByPersona(String cedula) {
        this.usuarioDao = getUsuarioDao();
        try {
            return this.usuarioDao.findByPersona(cedula);
        }
        catch (UsuarioDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Usuario con idPersona: "
                            + cedula, ex);
        }
    }

    /**
     * Metodo que elimina un grupo de Usuarios del sistema de acuerdo al
     * paramtero idUsuarios
     * 
     * @param idUsuarios
     * @return boolean
     */
    @SuppressWarnings("null")
    @Override
    public boolean eliminarUsuarios(List<Integer> idUsuarios) {
        Connection conn = null;
        this.usuarioDao = getUsuarioDao();
        try {
            // Comenzamos la transaccion, si algun elemento no se elimina
            // entonces ninguno se elmina
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.usuarioDao.setUserConn(conn);
            // Eliminamos registro a registro
            for (Integer usuario : idUsuarios) {
                this.usuarioDao.delete(new UsuarioPk(usuario));
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (UsuarioDaoException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException(
                    "Existe un problema para eliminar los elementos: "
                            + idUsuarios, ex);
        }
        catch (SQLException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException(
                    "Existe un problema con la Base de Datos", ex);
        }
        finally {
            // Cerramos la conexiÃ³n para regresala al pool
            ResourceManager.close(conn);
        }
    }

    /**
     * Metodo que guarda un Usuario en el sistema
     * 
     * @param Usuario
     * @return boolean
     */
    @Override
    public boolean guardarUsuario(Usuario usuario) {
        Connection conn = null;
        this.usuarioDao = getUsuarioDao();
        // Comenzamos la transaccion
        try {
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.usuarioDao.setUserConn(conn);
            // Ciframos la contraseña bajo el algoritmo MD5
            usuario.setPassword(DigestUtils.md5Hex(usuario.getPassword()));
            // Revisamos si es un insert o un update, dependiendo si se tiene o
            // no el valor de la PK
            if (usuario.getIdUsuario() == null) {
                this.usuarioDao.insert(usuario);
            }
            else {
                this.usuarioDao.update(usuario.createPk(), usuario);
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (UsuarioDaoException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException("No se puedo agregar el Usuario:"
                    + usuario + " a la BD", ex);
        }
        catch (SQLException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException(
                    "Existe un problema con la Base de Datos", ex);
        }
        finally {
            // Cerramos la conexión para regresala al pool
            ResourceManager.close(conn);
        }
    }

}
