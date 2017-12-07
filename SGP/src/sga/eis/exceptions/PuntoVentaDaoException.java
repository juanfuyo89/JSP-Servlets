package sga.eis.exceptions;

/**
 * Clase de Excepciones de tipo PuntoVentaDao
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class PuntoVentaDaoException extends DaoException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method 'PuntoVentaDaoException'
     * 
     * @param message
     */
    public PuntoVentaDaoException(String message) {
        super(message);
    }
    
    /**
     * Method 'PuntoVentaDaoException'
     * 
     * @param message
     * @param cause
     */
    public PuntoVentaDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
