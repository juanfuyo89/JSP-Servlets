package sga.eis.dto;

import java.io.Serializable;

/**
 * @author Juan Carlos Fuyo
 * 
 * Esta clase representa la primary key de la tabla Puntos_venta.
 */
public class PuntoVentaPk implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Este atributo mapea la primary-key de la colunma id_usuario en la tabla
     * Puntos_venta.
     */
    protected Integer         idPuntoVenta;
    
    /**
     * Constructor vacio
     */
    public PuntoVentaPk(){
    }

    /**
     * Constructor con parametros
     * 
     * @param idPuntoVenta
     */
    public PuntoVentaPk(Integer idPuntoVenta) {
        this.idPuntoVenta = idPuntoVenta;
    }
    
    /**
     * @return the idPuntoVenta
     */
    public Integer getIdPuntoVenta() {
        return idPuntoVenta;
    }
    
    /**
     * @param idPuntoVenta the idPuntoVenta to set
     */
    public void setIdPuntoVenta(Integer idPuntoVenta) {
        this.idPuntoVenta = idPuntoVenta;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idPuntoVenta == null) ? 0 : idPuntoVenta.hashCode());
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
        if (!(obj instanceof PuntoVentaPk)) {
            return false;
        }
        PuntoVentaPk other = (PuntoVentaPk) obj;
        if (idPuntoVenta == null) {
            if (other.idPuntoVenta != null) {
                return false;
            }
        }
        else if (!idPuntoVenta.equals(other.idPuntoVenta)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PuntoVentaPk [idPuntoVenta=" + idPuntoVenta + "]";
    }
    
}
