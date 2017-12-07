package sga.eis.factory;

import java.sql.Connection;
import sga.eis.dao.*;
import sga.eis.jdbc.*;

/**
 * Fabrica de objetos de tipo UsuarioDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class UsuarioDaoFactory {

    /**
     * Metodo que retorna una nueva instancia de la clase UsuarioDaoImpl
     * 
     * @return UsuarioDao
     */
    public static UsuarioDao create() {
        return new UsuarioDaoImpl();
    }

    /**
     * Metodo que retorna una nueva instancia de la clase UsuarioDaoImpl
     * 
     * @param conn
     * @return UsuarioDao
     */
    public static UsuarioDao create(Connection conn) {
        return new UsuarioDaoImpl(conn);
    }

}
