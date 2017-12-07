package junit.sgp.eis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dao.RolDao;
import sga.eis.dto.Rol;
import sga.eis.dto.RolPk;
import sga.eis.exceptions.RolDaoException;
import sga.eis.factory.RolDaoFactory;

/**
 * Clase de prueba del DAO tipo Persona
 * 
 * @author Juan Carlos Fuyo
 */
public class RolTest {

    /**
     * Atributo usuarioDao para comunicarnos con la capa de datos
     */
    RolDao rolDao;
    
    @Before
    public void setUp() {
        this.rolDao = RolDaoFactory.create();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }
    
    @Test
    public void insertarRol() {
        Rol rolDto = new Rol();
        rolDto.setNombre("PRUEBA_DOS");
        try {
            RolPk rolPk = this.rolDao.insert(rolDto);
            if (rolPk.getIdRol() != null) {
                Assert.assertTrue(true);
            }else{
                Assert.assertTrue(false);
            }
        }
        catch (RolDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarRoles() {
        try {
            Rol[] roles =  this.rolDao.findAll();
            for (Rol rol : roles) {
                System.out.println(rol.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (RolDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void consultarRolById(){
        try {
            Rol rol = this.rolDao.findByPrimaryKey(1);
            System.out.println(rol.toString());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (RolDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void modificarRol(){
        Rol rolDto = new Rol();
        rolDto.setIdRol(1);
        rolDto.setNombre("PRUEBA_UNO");
        try {
            this.rolDao.update(rolDto.createPk(), rolDto);
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (RolDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void eliminarRol(){
        Rol rolDto = new Rol();
        rolDto.setIdRol(2);
        try {
            this.rolDao.delete(rolDto.createPk());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (RolDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
}
