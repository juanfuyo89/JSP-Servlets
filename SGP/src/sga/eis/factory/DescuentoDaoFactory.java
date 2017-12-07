package sga.eis.factory;

import java.sql.Connection;
import sga.eis.dao.DescuentoDao;
import sga.eis.jdbc.DescuentoDaoImpl;

/**
 * Fabrica de objetos de tipo DescuentoDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class DescuentoDaoFactory {

    /**
     * Metodo que retorna una nueva instancia de la clase DescuentoDaoImpl
     * 
     * @return DescuentoDao
     */
    public static DescuentoDao create() {
        return new DescuentoDaoImpl();
    }

    /**
     * Metodo que retorna una nueva instancia de la clase DescuentoDaoImpl
     * 
     * @param conn
     * @return DescuentoDao
     */
    public static DescuentoDao create(Connection conn) {
        return new DescuentoDaoImpl(conn);
    }

}
