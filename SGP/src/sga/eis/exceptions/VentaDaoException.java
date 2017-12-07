package sga.eis.exceptions;

/**
 * Clase de Excepciones de tipo VentaDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class VentaDaoException extends DaoException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method 'VentaDaoException'
     * 
     * @param message
     */
    public VentaDaoException(String message) {
        super(message);
    }
    
    /**
     * Method 'VentaDaoException'
     * 
     * @param message
     * @param cause
     */
    public VentaDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
