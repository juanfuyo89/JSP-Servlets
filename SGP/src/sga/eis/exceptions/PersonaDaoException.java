package sga.eis.exceptions;

/**
 * Clase de Excepciones de tipo PersonaDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class PersonaDaoException extends DaoException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method 'PersonaDaoException'
     * 
     * @param message
     */
    public PersonaDaoException(String message) {
        super(message);
    }

    /**
     * Method 'PersonaDaoException'
     * 
     * @param message
     * @param cause
     */
    public PersonaDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
