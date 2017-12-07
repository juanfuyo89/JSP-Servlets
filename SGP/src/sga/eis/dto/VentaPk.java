package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la primary key de la tabla Ventas.
 * 
 * @author Juan Carlos Fuyo
 */
public class VentaPk implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la primary-key Compuesta de la colunma id_venta en
     * la tabla Ventas.
     */
    protected Integer         idVenta;

    /**
     * Constructor vacio
     */
    public VentaPk() {
    }

    /**
     * Constructor con parametros
     * 
     * @param idVenta
     */
    public VentaPk(Integer idVenta) {
        this.idVenta = idVenta;
    }

    
    /**
     * @return the idVenta
     */
    public Integer getIdVenta() {
        return idVenta;
    }

    
    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idVenta == null) ? 0 : idVenta.hashCode());
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
        if (!(obj instanceof VentaPk)) {
            return false;
        }
        VentaPk other = (VentaPk) obj;
        if (idVenta == null) {
            if (other.idVenta != null) {
                return false;
            }
        }
        else if (!idVenta.equals(other.idVenta)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "VentaPk [idVenta=" + idVenta + "]";
    }

}
