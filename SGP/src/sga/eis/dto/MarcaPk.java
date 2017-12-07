package sga.eis.dto;

import java.io.Serializable;

/**
 * @author Juan Carlos Fuyo
 * 
 * Esta clase representa la primary key de la tabla Marcas.
 */
public class MarcaPk implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Este atributo mapea la primary-key de la colunma id_marca en la tabla
     * Marcas.
     */
    protected Integer         idMarca;

    /**
     * Constructor vacio
     */
    public MarcaPk() {
    }

    /**
     * Constructor con parametros
     * 
     * @param idMarca
     */
    public MarcaPk(Integer idMarca) {
        this.idMarca = idMarca;
    }

    
    /**
     * @return the idMarca
     */
    public Integer getIdMarca() {
        return idMarca;
    }

    
    /**
     * @param idMarca the idMarca to set
     */
    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idMarca == null) ? 0 : idMarca.hashCode());
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
        if (!(obj instanceof MarcaPk)) {
            return false;
        }
        MarcaPk other = (MarcaPk) obj;
        if (idMarca == null) {
            if (other.idMarca != null) {
                return false;
            }
        }
        else if (!idMarca.equals(other.idMarca)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MarcaPk [idMarca=" + idMarca + "]";
    }
    
}
