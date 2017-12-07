package sga.eis.exceptions;

/**
 * Clase de Excepciones de tipo DescuentoDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class DescuentoDaoException extends DaoException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method 'DescuentoDaoException'
     * 
     * @param message
     */
    public DescuentoDaoException(String message) {
        super(message);
    }
    /**
     * Method 'DescuentoDaoException'
     * 
     * @param message
     * @param cause
     */
    public DescuentoDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
