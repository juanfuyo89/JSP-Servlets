package sga.eis.exceptions;

/**
 * Clase de Excepciones de tipo MarcaDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class MarcaDaoException extends DaoException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method 'MarcaDaoException'
     * 
     * @param message
     */
    public MarcaDaoException(String message) {
        super(message);
    }
    
    /**
     * Method 'MarcaDaoException'
     * 
     * @param message
     * @param cause
     */
    public MarcaDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
