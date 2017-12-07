package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la primary key de la tabla Tipos.
 * 
 * @author Juan Carlos Fuyo 
 */
public class TipoPk implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la primary-key de la colunma id_tipo en la tabla
     * Tipos.
     */
    protected Integer         idTipo;

    /**
     * Constructor vacio
     */
    public TipoPk() {
    }

    /**
     * Constructor con parametros
     * 
     * @param idRol
     */
    public TipoPk(Integer idTipo) {
        this.idTipo = idTipo;
    }

    
    /**
     * @return the idTipo
     */
    public Integer getIdTipo() {
        return idTipo;
    }

    
    /**
     * @param idTipo the idTipo to set
     */
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idTipo == null) ? 0 : idTipo.hashCode());
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
        if (!(obj instanceof TipoPk)) {
            return false;
        }
        TipoPk other = (TipoPk) obj;
        if (idTipo == null) {
            if (other.idTipo != null) {
                return false;
            }
        }
        else if (!idTipo.equals(other.idTipo)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TipoPk [idTipo=" + idTipo + "]";
    }
    
}
