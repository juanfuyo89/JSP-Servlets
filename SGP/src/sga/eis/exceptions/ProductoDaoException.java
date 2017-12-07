package sga.eis.exceptions;

/**
 * Clase de Excepciones de tipo ProductoDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class ProductoDaoException extends DaoException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method 'ProductoDaoException'
     * 
     * @param message
     */
    public ProductoDaoException(String message) {
        super(message);
    }
    
    /**
     * Method 'ProductoDaoException'
     * 
     * @param message
     * @param cause
     */
    public ProductoDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
