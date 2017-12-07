package sga.eis.factory;

import java.sql.Connection;
import sga.eis.dao.ProductoDao;
import sga.eis.jdbc.ProductoDaoImpl;

/**
 * Fabrica de objetos de tipo ProductoDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class ProductoDaoFactory {

    /**
     * Metodo que retorna una nueva instancia de la clase ProductoDaoImpl
     * 
     * @return ProductoDao
     */
    public static ProductoDao create() {
        return new ProductoDaoImpl();
    }

    /**
     * Metodo que retorna una nueva instancia de la clase ProductoDaoImpl
     * 
     * @param conn
     * @return ProductoDao
     */
    public static ProductoDao create(Connection conn) {
        return new ProductoDaoImpl(conn);
    }

}
