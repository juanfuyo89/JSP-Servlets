/**
 * 
 */
package junit.sgp.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dto.Persona;
import sga.services.PersonaService;
import sga.services.impl.PersonaServiceImpl;

/**
 * @author Juan Carlos Fuyo
 */
public class PersonaTestServices {

	/**
	 * Atributo usuarioService para comunicarnos con la capa de servicios
	 */
	PersonaService personaService;

	@Before
	public void setUp() {
		this.personaService = PersonaServiceImpl.getInstance();
	}

	@After
	public void tearDown() {
		// Tareas a realizar después de cada test
	}

	@Test
	public void insertarPersona() {
		Persona personaDto = new Persona();
		personaDto.setCedula("1033718747");
		personaDto.setNombre("Juan Carlos");
		personaDto.setApePaterno("Fuyo");
		personaDto.setApeMaterno("González");
		personaDto.setGenero("M");
		personaDto.setFecNacimiento(new Date(0));
		Assert.assertTrue(this.personaService.guardarPersona(personaDto, 0));
	}

	@Test
	public void consultarPersonas() {
		List<Persona> personas = this.personaService.getAllPersonas();
		for (Persona persona : personas) {
			System.out.println(persona.toString());
		}
		System.out.println("---------");
	}

	@Test
	public void consultarPersonaByCedula() {
		Persona persona = this.personaService.getPersonaById("7654321");
		System.out.println(persona.toString());
		System.out.println("---------");
	}

	@Test
	public void modificarPersona() {
		Persona personaDto = new Persona();
		personaDto.setCedula("1033718747");
		personaDto.setNombre("Juan Carlos");
		personaDto.setApePaterno("Fuyo");
		personaDto.setApeMaterno("González");
		personaDto.setGenero("M");
		personaDto.setFecNacimiento(new Date(0));
		Assert.assertTrue(this.personaService.guardarPersona(personaDto, 1));
	}

	@Test
	public void eliminarPersona() {
		List<String> personas = new ArrayList<String>();
		personas.add("7654321");
		Assert.assertTrue(this.personaService.eliminarPersonas(personas));
	}

}
