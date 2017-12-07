package sga.eis.factory;

import java.sql.Connection;
import sga.eis.dao.*;
import sga.eis.jdbc.*;

/**
 * Fabrica de objetos de tipo PersonaDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class PersonaDaoFactory {

    /**
     * Metodo que retorna una nueva instancia de la clase PersonaDaoImpl
     * 
     * @return PersonaDao
     */
    public static PersonaDao create() {
        return new PersonaDaoImpl();
    }

    /**
     * Metodo que retorna una nueva instancia de la clase PersonaDaoImpl
     * 
     * @param conn
     * @return PersonaDao
     */
    public static PersonaDao create(Connection conn) {
        return new PersonaDaoImpl(conn);
    }

}
