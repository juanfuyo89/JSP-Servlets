package sga.services.impl;

import sga.services.exceptions.BusinessException;
import sga.web.utilities.IGlobalConstant;
import sga.eis.dao.PersonaDao;
import sga.eis.dto.Persona;
import sga.eis.dto.PersonaPk;
import sga.eis.exceptions.PersonaDaoException;
import sga.eis.factory.PersonaDaoFactory;
import sga.eis.jdbc.connect.ResourceManager;
import sga.services.PersonaService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Clase de servicios que implementa los metodos definidos por la interfaz
 * PersonaService
 * 
 * @author Juan Carlos Fuyo Gonzalez
 */
public class PersonaServiceImpl implements PersonaService {

    /**
     * Objeto de la clase para implementar el patron singleton
     */
    private static PersonaService personaServiceInstance;

    /**
     * Atributo personaDao para comunicarnos con la capa de datos
     */
    PersonaDao                    personaDao;

    /**
     * Constructor sin argumentos privado, para implementar el patron singleton
     */
    private PersonaServiceImpl() {
    }

    /**
     * Metodo encargado de recuperar una instancia de personaDao haciendo uso
     * del patrón Factory
     * 
     * @return PersonaDao
     */
    private PersonaDao getPersonaDao() {
        if (this.personaDao != null) {
            return this.personaDao;
        }
        return PersonaDaoFactory.create();
    }

    /**
     * Metodo para crear una nueva y unica instancia si es que no existe
     * 
     * @return personaServiceInstance
     */
    public static PersonaService getInstance() {
        if (personaServiceInstance == null) {
            personaServiceInstance = new PersonaServiceImpl();
        }
        return personaServiceInstance;
    }

    /**
     * Metodo que retorna todos las personas creadas en sistema
     * 
     * @return List<Persona>
     */
    public List<Persona> getAllPersonas() {
        try {
            this.personaDao = getPersonaDao();
            return Arrays.asList(this.personaDao.findAll());
        }
        catch (PersonaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el arreglo de personas en la BD",
                    ex);
        }
    }

    /**
     * Metodo que retorna una persona de acuerdo al paramtero cedula
     * 
     * @param cedula
     * @return Persona
     */
    public Persona getPersonaById(String cedula) {
        this.personaDao = getPersonaDao();
        try {
            return this.personaDao.findByPrimaryKey(cedula);
        }
        catch (PersonaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener la persona con id:" + cedula,
                    ex);
        }
    }

    /**
     * Metodo que elimina un grupo de personas del sistema de acuerdo al
     * paramtero cedulas
     * 
     * @param cedulas
     * @return Persona
     */
    @SuppressWarnings("null")
    @Override
    public boolean eliminarPersonas(List<String> cedulas) {

        // Hacemos este metodo transaccional, ya que elmina todos o ninguno
        // y podrá dejar afectado el estado de la Base de Datos

        this.personaDao = getPersonaDao();
        Connection conn = null;

        try {
            // Comenzamos la transaccion, si algun elemento no se elminina
            // entonces ninguno se elmina
            // Obtenemos una conexion del pool

            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);

            this.personaDao.setUserConn(conn);
            // Tenemos dos posibles opciones, crear un SQL con los id's a
            // eliminar o eliminar registro a registro. Elegimos la segunda
            // opcion
            for (String persona : cedulas) {
                this.personaDao.delete(new PersonaPk(persona));
            }

            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;

        }
        catch (PersonaDaoException ex) {
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
                            + cedulas, ex);
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
            // Cerramos la conexion para regresala al pool
            ResourceManager.close(conn);
        }
    }

    /**
     * Metodo que guarda una persona en el sistema
     * 
     * @param persona
     * @param modificar (1 para modificar, 0 para insertar)
     * @return boolean
     */
    public boolean guardarPersona(Persona persona, int modificar) {

        // Hacemos el metodo transaccional, ya que puede quedar afectado el
        // estado
        // de la Base de Datos
        Connection conn = null;
        this.personaDao = getPersonaDao();
        try {
            // Comenzamos la transaccion

            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);

            this.personaDao.setUserConn(conn);

            // Revisamos si es un insert o un update, dependiendo si se tiene o
            // no el valor de la PK
            if (modificar == IGlobalConstant.INT_ZERO) {
                this.personaDao.insert(persona);
            }
            else {
                this.personaDao.update(persona.createPk(), persona);
            }

            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;

        }
        catch (PersonaDaoException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException("No se puedo agregar la Persona:"
                    + persona + " a la BD", ex);
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
            // Cerramos la conexion para regresala al pool
            ResourceManager.close(conn);
        }
    }
}
