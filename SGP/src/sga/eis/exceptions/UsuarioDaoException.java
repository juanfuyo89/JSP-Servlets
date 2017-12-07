package sga.eis.exceptions;

/**
 * Clase de Excepciones de tipo UsuarioDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class UsuarioDaoException extends DaoException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method 'UsuarioDaoException'
     * 
     * @param message
     */
    public UsuarioDaoException(String message) {
        super(message);
    }

    /**
     * Method 'UsuarioDaoException'
     * 
     * @param message
     * @param cause
     */
    public UsuarioDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
