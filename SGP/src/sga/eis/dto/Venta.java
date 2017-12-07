package sga.eis.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Esta clase representa la tabla Ventas.
 * 
 * @author Juan Carlos Fuyo
 */
public class Venta implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la colunma id_venta en la tabla Ventas.
     */
    protected Integer         idVenta;

    /**
     * Este atributo mapea la colunma id_persona en la tabla Ventas.
     */
    protected String          idPersona;

    /**
     * Este atributo mapea la colunma id_producto en la tabla Ventas.
     */
    protected Integer         idProducto;

    /**
     * Este atributo mapea la colunma id_pdv en la tabla Ventas.
     */
    protected Integer         idPuntoVenta;

    /**
     * Este atributo mapea la colunma fecha_venta en la tabla Ventas.
     */
    protected Date            fecVenta;

    /**
     * Este atributo mapea la colunma precio_venta en la tabla Ventas.
     */
    protected double          precioVenta;

    /**
     * Este atributo mapea la colunma cantidad en la tabla Ventas.
     */
    protected int             cantidad;

    /**
     * Constructor vacio
     */
    public Venta() {
    }

    /**
     * Metodo para crear la primary-key de la tabla Ventas
     * 
     * @return VentaPk
     */
    public VentaPk createPk() {
        return new VentaPk(idVenta);
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

    /**
     * @return the idPersona
     */
    public String getIdPersona() {
        return idPersona;
    }

    /**
     * @param idPersona the idPersona to set
     */
    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * @return the idProducto
     */
    public Integer getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the fecVenta
     */
    public Date getFecVenta() {
        return fecVenta;
    }

    /**
     * @param fecVenta the fecVenta to set
     */
    public void setFecVenta(Date fecVenta) {
        this.fecVenta = fecVenta;
    }

    /**
     * @return the precioVenta
     */
    public double getPrecioVenta() {
        return precioVenta;
    }

    /**
     * @param precioVenta the precioVenta to set
     */
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
        result = prime * result + cantidad;
        result = prime * result
                + ((fecVenta == null) ? 0 : fecVenta.hashCode());
        result = prime * result
                + ((idPersona == null) ? 0 : idPersona.hashCode());
        result = prime * result
                + ((idProducto == null) ? 0 : idProducto.hashCode());
        result = prime * result
                + ((idPuntoVenta == null) ? 0 : idPuntoVenta.hashCode());
        result = prime * result + ((idVenta == null) ? 0 : idVenta.hashCode());
        long temp;
        temp = Double.doubleToLongBits(precioVenta);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        if (!(obj instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) obj;
        if (cantidad != other.cantidad) {
            return false;
        }
        if (fecVenta == null) {
            if (other.fecVenta != null) {
                return false;
            }
        }
        else if (!fecVenta.equals(other.fecVenta)) {
            return false;
        }
        if (idPersona == null) {
            if (other.idPersona != null) {
                return false;
            }
        }
        else if (!idPersona.equals(other.idPersona)) {
            return false;
        }
        if (idProducto == null) {
            if (other.idProducto != null) {
                return false;
            }
        }
        else if (!idProducto.equals(other.idProducto)) {
            return false;
        }
        if (idPuntoVenta == null) {
            if (other.idPuntoVenta != null) {
                return false;
            }
        }
        else if (!idPuntoVenta.equals(other.idPuntoVenta)) {
            return false;
        }
        if (idVenta == null) {
            if (other.idVenta != null) {
                return false;
            }
        }
        else if (!idVenta.equals(other.idVenta)) {
            return false;
        }
        if (Double.doubleToLongBits(precioVenta) != Double.doubleToLongBits(other.precioVenta)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Venta [idVenta=" + idVenta + ", idPersona=" + idPersona
                + ", idProducto=" + idProducto + ", idPuntoVenta="
                + idPuntoVenta + ", fecVenta=" + fecVenta + ", precioVenta="
                + precioVenta + ", cantidad=" + cantidad + "]";
    }

}
