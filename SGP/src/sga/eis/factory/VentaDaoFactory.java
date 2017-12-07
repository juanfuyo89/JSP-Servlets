package sga.eis.factory;

import java.sql.Connection;
import sga.eis.dao.VentaDao;
import sga.eis.jdbc.VentaDaoImpl;

/**
 * Fabrica de objetos de tipo VentaDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class VentaDaoFactory {

    /**
     * Metodo que retorna una nueva instancia de la clase VentaDaoImpl
     * 
     * @return VentaDao
     */
    public static VentaDao create() {
        return new VentaDaoImpl();
    }

    /**
     * Metodo que retorna una nueva instancia de la clase VentaDaoImpl
     * 
     * @param conn
     * @return VentaDao
     */
    public static VentaDao create(Connection conn) {
        return new VentaDaoImpl(conn);
    }

}
