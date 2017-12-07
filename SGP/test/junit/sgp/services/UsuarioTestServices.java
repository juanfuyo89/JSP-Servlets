/**
 * 
 */
package junit.sgp.services;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dto.Usuario;
import sga.services.UsuarioService;
import sga.services.impl.UsuarioServiceImpl;

/**
 * Clase de prueba de tipo Usuario de la capa de servicios
 * 
 * @author Juan Carlos Fuyo
 */
public class UsuarioTestServices {

    /**
     * Atributo usuarioService para comunicarnos con la capa de servicios
     */
    UsuarioService usuarioService;

    @Before
    public void setUp() {
        this.usuarioService = UsuarioServiceImpl.getInstance();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }
    
    @Test
    public void insertarUsuario(){
        Usuario usuarioDto = new Usuario();
        usuarioDto.setUsername("prueba4");
        usuarioDto.setPassword("1234");
        usuarioDto.setIdPersona("7654321");
        usuarioDto.setIdRol(3);
        Assert.assertTrue(usuarioService.guardarUsuario(usuarioDto));
    }

    @Test
    public void consultarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario()!=null) {
                System.out.println(usuario.toString());
                Assert.assertTrue(true);
            }
            else {
                Assert.assertTrue(false);
            }
        }
    }
    
    @Test
    public void consultarUsuarioById(){
        Usuario usuario = usuarioService.getUsuarioById(1);
        if (usuario.getIdPersona() != null) {
            Assert.assertTrue(true);
        }
        else {
                Assert.assertTrue(false);
        }
    }

    @Test
    public void validarUsuario() {
        Usuario usuarioDto = new Usuario();
        usuarioDto.setUsername("admin");
        usuarioDto.setPassword("1234");
        usuarioDto = usuarioService.usuarioExistente(usuarioDto);
        if (usuarioDto.getIdPersona() != null) {
                Assert.assertTrue(true);
        }
        else {
                Assert.assertTrue(false);
        }
    }

    @Test
    public void modificarUsuario(){
    	Usuario usuarioDto = new Usuario();
    	usuarioDto.setIdUsuario(1);
        usuarioDto.setUsername("admin");
        usuarioDto.setPassword("1234");
        usuarioDto.setIdPersona("1033718747");
        usuarioDto.setIdRol(1);
        Assert.assertTrue(usuarioService.guardarUsuario(usuarioDto));
    }
    
    @Test
    public void eliminarUsuario(){
        List<Integer> usuarios = new ArrayList<Integer>();
        usuarios.add(6);
        Assert.assertTrue(usuarioService.eliminarUsuarios(usuarios));
    }
    
}
