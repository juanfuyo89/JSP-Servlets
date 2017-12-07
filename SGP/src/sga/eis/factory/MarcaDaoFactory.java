package sga.eis.factory;

import java.sql.Connection;
import sga.eis.dao.MarcaDao;
import sga.eis.jdbc.MarcaDaoImpl;

/**
 * Fabrica de objetos de tipo MarcaDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class MarcaDaoFactory {

    /**
     * Metodo que retorna una nueva instancia de la clase MarcaDaoImpl
     * 
     * @return MarcaDao
     */
    public static MarcaDao create() {
        return new MarcaDaoImpl();
    }

    /**
     * Metodo que retorna una nueva instancia de la clase MarcaDaoImpl
     * 
     * @param conn
     * @return MarcaDao
     */
    public static MarcaDao create(Connection conn) {
        return new MarcaDaoImpl(conn);
    }

}
