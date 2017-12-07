package sga.eis.exceptions;

/**
 * Clase de Excepciones de tipo RolDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class RolDaoException extends DaoException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method 'RolDaoException'
     * 
     * @param message
     */
    public RolDaoException(String message) {
        super(message);
    }
    
    /**
     * Method 'RolDaoException'
     * 
     * @param message
     * @param cause
     */
    public RolDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
