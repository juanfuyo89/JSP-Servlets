/**
 * 
 */
package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la primary key de la tabla Roles.
 * 
 * @author Juan Carlos Fuyo 
 */
public class RolPk implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la primary-key de la colunma id_rol en la tabla
     * Roles.
     */
    protected Integer         idRol;

    /**
     * Constructor vacio
     */
    public RolPk() {
    }

    /**
     * Constructor con parametros
     * 
     * @param idRol
     */
    public RolPk(Integer idRol) {
        this.idRol = idRol;
    }

    /**
     * @return the idRol
     */
    public Integer getIdRol() {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idRol == null) ? 0 : idRol.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RolPk)) {
            return false;
        }
        RolPk other = (RolPk) obj;
        if (idRol == null) {
            if (other.idRol != null) {
                return false;
            }
        }
        else if (!idRol.equals(other.idRol)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RolPk [idRol=" + idRol + "]";
    }

}
