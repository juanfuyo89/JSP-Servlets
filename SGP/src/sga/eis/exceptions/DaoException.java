package sga.eis.exceptions;

/**
 * Clase de Excepciones de tipo Dao general
 * 
 * @author Juan Carlos Fuyo
 */
public class DaoException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    protected Throwable throwable;

    /**
     * Method 'DaoException'
     * 
     * @param message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Method 'DaoException'
     * 
     * @param message
     * @param throwable
     */
    public DaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

    /**
     * Method 'getCause'
     * 
     * @return Throwable
     */
    public Throwable getCause() {
        return throwable;
    }

}
