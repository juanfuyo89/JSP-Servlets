package sga.services.exceptions;

/**
 * Clase de Excepciones de la capa de Servicios
 * 
 * @author Juan Carlos Fuyo
 */
public class BusinessException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * BusinessException
     * 
     * @param mensaje
     * @param e
     */
    public BusinessException(String mensaje, Exception e) {
        super(mensaje, e);
    }

}
