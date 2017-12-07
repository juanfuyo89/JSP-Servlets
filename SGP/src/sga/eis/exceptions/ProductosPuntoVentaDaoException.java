package sga.eis.exceptions;

/**
 * Clase de Excepciones de tipo ProductosPuntoVentaDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class ProductosPuntoVentaDaoException extends DaoException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method 'ProductosPuntoVentaDaoException'
     * 
     * @param message
     */
    public ProductosPuntoVentaDaoException(String message) {
        super(message);
    }
    
    /**
     * Method 'ProductosPuntoVentaDaoException'
     * 
     * @param message
     * @param cause
     */
    public ProductosPuntoVentaDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
