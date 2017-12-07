package sga.eis.factory;

import java.sql.Connection;
import sga.eis.dao.PuntoVentaDao;
import sga.eis.jdbc.PuntoVentaDaoImpl;

/**
 * Fabrica de objetos de tipo PuntoVentaDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class PuntoVentaDaoFactory {

    /**
     * Metodo que retorna una nueva instancia de la clase PuntoVentaDaoImpl
     * 
     * @return PuntoVentaDao
     */
    public static PuntoVentaDao create() {
        return new PuntoVentaDaoImpl();
    }

    /**
     * Metodo que retorna una nueva instancia de la clase PuntoVentaDaoImpl
     * 
     * @param conn
     * @return PuntoVentaDao
     */
    public static PuntoVentaDao create(Connection conn) {
        return new PuntoVentaDaoImpl(conn);
    }

}
