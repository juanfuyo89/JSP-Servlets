package junit.sgp.eis;

import java.sql.Date;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dao.PersonaDao;
import sga.eis.dto.Persona;
import sga.eis.exceptions.PersonaDaoException;
import sga.eis.factory.PersonaDaoFactory;

/**
 * Clase de prueba del DAO tipo Persona
 * 
 * @author Juan Carlos Fuyo
 */
public class PersonaTest {

    /**
     * Atributo usuarioDao para comunicarnos con la capa de datos
     */
    PersonaDao personaDao;

    @Before
    public void setUp() {
        this.personaDao = PersonaDaoFactory.create();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }

    @Test
    public void insertarPersona() {
        Persona personaDto = new Persona();
        personaDto.setCedula("1234567");
        personaDto.setNombre("Prueba");
        personaDto.setApePaterno("Uno");
        personaDto.setApeMaterno("JUnit");
        personaDto.setGenero("F");
        personaDto.setFecNacimiento(new Date(0));
        try {
            this.personaDao.insert(personaDto);
            Assert.assertTrue(true);
        }
        catch (PersonaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void consultarPersonas() {
        try {
            Persona[] personas =  this.personaDao.findAll();
            for (Persona persona : personas) {
                System.out.println(persona.toString());
            }
            System.out.println("---------");
        }
        catch (PersonaDaoException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void consultarPersonaByCedula(){
        try {
            Persona persona = this.personaDao.findByPrimaryKey("1033718747");
            System.out.println(persona.toString());
            System.out.println("---------");
        }
        catch (PersonaDaoException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void modificarPersona(){
        Persona personaDto = new Persona();
        personaDto.setCedula("1033718747");
        personaDto.setNombre("Juan Carlos");
        personaDto.setApePaterno("Fuyo");
        personaDto.setApeMaterno("González");
        personaDto.setGenero("M");
        personaDto.setFecNacimiento(new Date(0));
        try {
            this.personaDao.update(personaDto.createPk(),personaDto);
            Assert.assertTrue(true);
        }
        catch (PersonaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void eliminarPersona(){
        Persona personaDto = new Persona();
        personaDto.setCedula("1234567");
        try {
            this.personaDao.delete(personaDto.createPk());
            Assert.assertTrue(true);
        }
        catch (PersonaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
}
