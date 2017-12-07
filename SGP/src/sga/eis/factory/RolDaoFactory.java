package sga.eis.factory;

import java.sql.Connection;
import sga.eis.dao.RolDao;
import sga.eis.jdbc.RolDaoImpl;

/**
 * Fabrica de objetos de tipo RolDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class RolDaoFactory {

    /**
     * Metodo que retorna una nueva instancia de la clase RolDaoImpl
     * 
     * @return RolDao
     */
    public static RolDao create() {
        return new RolDaoImpl();
    }

    /**
     * Metodo que retorna una nueva instancia de la clase RolDaoImpl
     * 
     * @param conn
     * @return RolDao
     */
    public static RolDao create(Connection conn) {
        return new RolDaoImpl(conn);
    }

}
