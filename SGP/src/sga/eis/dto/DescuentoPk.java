package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la primary key de la tabla Descuentos.
 * 
 * @author Juan Carlos Fuyo 
 */
public class DescuentoPk implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la primary-key de la colunma id_dcto en la tabla
     * Descuentos.
     */
    protected Integer         idDcto;

    /**
     * Constructor vacio
     */
    public DescuentoPk() {
    }

    /**
     * Constructor con parametros
     * 
     * @param idDcto
     */
    public DescuentoPk(Integer idDcto) {
        this.idDcto = idDcto;
    }

    
    /**
     * @return the idDcto
     */
    public Integer getIdDcto() {
        return idDcto;
    }

    
    /**
     * @param idDcto the idDcto to set
     */
    public void setIdDcto(Integer idDcto) {
        this.idDcto = idDcto;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idDcto == null) ? 0 : idDcto.hashCode());
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
        if (!(obj instanceof DescuentoPk)) {
            return false;
        }
        DescuentoPk other = (DescuentoPk) obj;
        if (idDcto == null) {
            if (other.idDcto != null) {
                return false;
            }
        }
        else if (!idDcto.equals(other.idDcto)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DescuentoPk [idDcto=" + idDcto + "]";
    }
    
}
