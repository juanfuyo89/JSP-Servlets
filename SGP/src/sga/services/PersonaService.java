package sga.services;

import sga.eis.dto.Persona;
import java.util.List;

/**
 * Interface que define los metodos para el manejo de Personas en la capa de
 * servicios
 * 
 * @author Juan Carlos Fuyo
 */
public interface PersonaService {

	/**
	 * Metodo que retorna todas las personas creadas en sistema
	 * 
	 * @return List<Persona>
	 */
	public List<Persona> getAllPersonas();
	
	/**
	 * Metodo que retorna una persona de acuerdo al paramtero cedula
	 * 
	 * @param cedula
	 * 
	 * @return Persona
	 */
	public Persona getPersonaById(String cedula);
	
	/**
	 * Metodo que elimina un grupo de personas del sistema de acuerdo al paramtero
	 * cedulas
	 * 
	 * @param cedulas
	 * 
	 * @return boolean
	 */
	public boolean eliminarPersonas(List<String> idPersonas);
	
	/**
	 * Metodo que guarda una persona en el sistema
	 * 
	 * @param Persona
	 * @param modificar (1 para modificar, null para insertar)
	 * 
	 * @return boolean
	 */
	public boolean guardarPersona(Persona persona, int modificar);

}
