package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la primary key de la tabla Usuarios.
 * 
 * @author Juan Carlos Fuyo
 */
public class UsuarioPk implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Este atributo mapea la primary-key de la colunma id_usuario en la tabla
     * Usuarios.
     */
    protected Integer         idUsuario;

    /**
     * Constructor vacio
     */
    public UsuarioPk() {
    }

    /**
     * Constructor con parametros
     * 
     * @param idUsuario
     */
    public UsuarioPk(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Sets the value of idUsuario
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Gets the value of idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Method 'equals'
     * 
     * @param _other
     * @return boolean
     */
    public boolean equals(Object _other) {
        if (_other == null) {
            return false;
        }

        if (_other == this) {
            return true;
        }

        if (!(_other instanceof UsuarioPk)) {
            return false;
        }

        final UsuarioPk _cast = (UsuarioPk) _other;
        if (idUsuario == null
                ? _cast.idUsuario != idUsuario
                : !idUsuario.equals(_cast.idUsuario)) {
            return false;
        }

        return true;
    }

    /**
     * Method 'hashCode'
     * 
     * @return int
     */
    public int hashCode() {
        int _hashCode = 0;
        if (idUsuario != null) {
            _hashCode = 29 * _hashCode + idUsuario.hashCode();
        }

        return _hashCode;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("ap.eis.dto.UsuarioPk: ");
        ret.append("idUsuario=" + idUsuario);
        return ret.toString();
    }

}
