package sga.eis.factory;

import java.sql.Connection;
import sga.eis.dao.TipoDao;
import sga.eis.jdbc.TipoDaoImpl;

/**
 * Fabrica de objetos de tipo TipoDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class TipoDaoFactory {

    /**
     * Metodo que retorna una nueva instancia de la clase TipoDaoImpl
     * 
     * @return TipoDao
     */
    public static TipoDao create() {
        return new TipoDaoImpl();
    }

    /**
     * Metodo que retorna una nueva instancia de la clase TipoDaoImpl
     * 
     * @param conn
     * @return TipoDao
     */
    public static TipoDao create(Connection conn) {
        return new TipoDaoImpl(conn);
    }

}
