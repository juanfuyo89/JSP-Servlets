package sga.eis.factory;

import java.sql.Connection;
import sga.eis.dao.ProductosPuntoVentaDao;
import sga.eis.jdbc.ProductosPuntoVentaDaoImpl;

/**
 * Fabrica de objetos de tipo ProductosPuntoVentaDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class ProductosPuntoVentaDaoFactory {

    /**
     * Metodo que retorna una nueva instancia de la clase ProductosPuntoVentaDaoImpl
     * 
     * @return ProductosPuntoVentaDao
     */
    public static ProductosPuntoVentaDao create() {
        return new ProductosPuntoVentaDaoImpl();
    }

    /**
     * Metodo que retorna una nueva instancia de la clase ProductosPuntoVentaDaoImpl
     * 
     * @param conn
     * @return ProductosPuntoVentaDao
     */
    public static ProductosPuntoVentaDao create(Connection conn) {
        return new ProductosPuntoVentaDaoImpl(conn);
    }

}
