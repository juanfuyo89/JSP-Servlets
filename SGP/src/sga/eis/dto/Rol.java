/**
 * 
 */
package sga.eis.dto;

import java.io.Serializable;


/**
 * Esta clase representa la tabla Roles.
 * 
 * @author Juan Carlos Fuyo
 */
public class Rol implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Este atributo mapea la colunma id_rol en la tabla Roles.
     */
    protected Integer         idRol;

    /**
     * Este atributo mapea la colunma rol_name en la tabla Roles.
     */
    protected String          nombre;
    
    /**
     * Constructor vacio
     */
    public Rol(){
    }
    
    /**
     * Metodo para crear la primary-key de la tabla Roles
     * 
     * @return RolPk
     */
    public RolPk createPk() {
        return new RolPk(idRol);
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

    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idRol == null) ? 0 : idRol.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    /* (non-Javadoc)
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
        if (!(obj instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) obj;
        if (idRol == null) {
            if (other.idRol != null) {
                return false;
            }
        }
        else if (!idRol.equals(other.idRol)) {
            return false;
        }
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        }
        else if (!nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Rol [idRol=" + idRol + ", nombre=" + nombre + "]";
    }
    
}
