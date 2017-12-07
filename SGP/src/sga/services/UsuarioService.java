package sga.services;

import java.util.List;
import sga.eis.dto.Usuario;

/**
 * Interface que define los metodos para el manejo de Usuarios en la capa de
 * servicios
 *
 * @author Juan Carlos Fuyo
 */
public interface UsuarioService {

	/**
	 * Metodo que verifica que exista un usuario en sistema
	 * 
	 * @param usuario
	 * @return boolean
	 */
	public Usuario usuarioExistente(Usuario usuario);

	/**
	 * Metodo que retorna todos los usuarios creadas en sistema
	 * 
	 * @return List<Usuario>
	 */
	public List<Usuario> getAllUsuarios();

	/**
	 * Metodo que retorna un Usuario de acuerdo al id
	 * 
	 * @param idUsuario
	 * 
	 * @return Usuario
	 */
	public Usuario getUsuarioById(Integer idUsuario);

	/**
	 * Metodo que retorna un Usuario de acuerdo al username
	 * 
	 * @param username
	 * 
	 * @return Usuario
	 */
	public Usuario getUsuarioByFullName(String username);

	/**
	 * Metodo que retorna un Usuario de acuerdo al idPersona
	 * 
	 * @param cedula
	 * 
	 * @return Usuario
	 */
	public Usuario getUsuarioByPersona(String cedula);

	/**
	 * Metodo que elimina un grupo de Usuarios del sistema de acuerdo al paramtero
	 * idUsuarios
	 * 
	 * @param idUsuarios
	 * 
	 * @return boolean
	 */
	public boolean eliminarUsuarios(List<Integer> idUsuarios);

	/**
	 * Metodo que guarda un Usuario en el sistema
	 * 
	 * @param Usuario
	 * 
	 * @return boolean
	 */
	public boolean guardarUsuario(Usuario usuario);

}
