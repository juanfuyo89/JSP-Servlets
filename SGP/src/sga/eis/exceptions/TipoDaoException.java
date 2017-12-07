package sga.eis.exceptions;

/**
 * Clase de Excepciones de tipo TipoDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class TipoDaoException extends DaoException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method 'TipoDaoException'
     * 
     * @param message
     */
    public TipoDaoException(String message) {
        super(message);
    }

    /**
     * Method 'TipoDaoException'
     * 
     * @param message
     * @param cause
     */
    public TipoDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
