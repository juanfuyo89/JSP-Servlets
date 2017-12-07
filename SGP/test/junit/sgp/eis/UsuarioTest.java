package junit.sgp.eis;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dao.UsuarioDao;
import sga.eis.dto.Usuario;
import sga.eis.exceptions.UsuarioDaoException;
import sga.eis.factory.UsuarioDaoFactory;

/**
 * @author Juan Carlos Fuyo
 */
public class UsuarioTest {

	/**
	 * Atributo usuarioDao para comunicarnos con la capa de datos
	 */
	UsuarioDao usuarioDao;

	@Before
	public void setUp() {
		this.usuarioDao = UsuarioDaoFactory.create();
	}

	@After
	public void tearDown() {
		// Tareas a realizar después de cada test
	}

	@Test
	public void insertarUsuario() {
		Usuario usuarioDto = new Usuario();
		usuarioDto.setUsername("admin");
		usuarioDto.setPassword(DigestUtils.md5Hex("1234"));
		usuarioDto.setIdPersona("1033718747");
		usuarioDto.setIdRol(1);
		try {
			this.usuarioDao.insert(usuarioDto);
			Assert.assertTrue(true);
		} catch (UsuarioDaoException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void consultarUsuarios() {
		try {
			Usuario[] usuarios = this.usuarioDao.findAll();
			if (usuarios.length > 0) {
				for (Usuario usuario : usuarios) {
					System.out.println(usuario.toString());
				}
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (UsuarioDaoException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void validarUsuario() {
		Usuario usuarioDto = new Usuario();
		usuarioDto.setUsername("admin");
		usuarioDto.setPassword(DigestUtils.md5Hex("1234"));
		try {
			// Buscamos el objeto por UserName y password
			final String SQL_WHERE = "username = ? and passwd = ?";
			Object[] sqlParams = { usuarioDto.getUsername(), usuarioDto.getPassword() };
			Usuario[] usuarios = this.usuarioDao.findByDynamicWhere(SQL_WHERE, sqlParams);
			if (usuarios.length > 0) {
				System.out.println(usuarios[0].toString());
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (UsuarioDaoException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void consultarUsuarioById() {
		Usuario usuarioDto = new Usuario();
		usuarioDto.setIdUsuario(1);
		try {
			Usuario usuario = this.usuarioDao.findByPrimaryKey(1);
			if (usuario != null) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (UsuarioDaoException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void consultarUsuarioByPersona() {
		try {
			Usuario usuario = this.usuarioDao.findByPersona("1033718747");
			if (usuario != null) {
				System.out.println(usuario.toString());
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (UsuarioDaoException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void modificarUsuario() {
		Usuario usuarioDto = new Usuario();
		usuarioDto.setIdUsuario(2);
		usuarioDto.setUsername("adminpdv");
		usuarioDto.setPassword(DigestUtils.md5Hex("1234"));
		usuarioDto.setIdPersona("1033718747");
		usuarioDto.setIdRol(1);
		try {
			this.usuarioDao.update(usuarioDto.createPk(), usuarioDto);
			Assert.assertTrue(true);
		} catch (UsuarioDaoException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void eliminarUsuario() {
		Usuario usuarioDto = new Usuario();
		usuarioDto.setIdUsuario(3);
		try {
			this.usuarioDao.delete(usuarioDto.createPk());
			Assert.assertTrue(true);
		} catch (UsuarioDaoException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

}
