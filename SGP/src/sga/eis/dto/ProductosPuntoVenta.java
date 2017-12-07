package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la tabla Productos_punto_venta.
 * 
 * @author Juan Carlos Fuyo 
 */
public class ProductosPuntoVenta implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la colunma id_pdv en la tabla Productos_punto_venta.
     */
    protected Integer         idPuntoVenta;

    /**
     * Este atributo mapea la colunma id_producto en la tabla
     * Productos_punto_venta.
     */
    protected Integer         idProducto;

    /**
     * Este atributo mapea la colunma cantidad en la tabla
     * Productos_punto_venta.
     */
    protected int             cantidad;

    /**
     * Constructor vacio
     */
    public ProductosPuntoVenta() {
    }

    /**
     * Metodo para crear la primary-key de la tabla Productos_punto_venta
     * 
     * @return ProductosPuntoVentaPk
     */
    public ProductosPuntoVentaPk createPk() {
        return new ProductosPuntoVentaPk(idPuntoVenta, idProducto);
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cantidad;
        result = prime * result
                + ((idProducto == null) ? 0 : idProducto.hashCode());
        result = prime * result
                + ((idPuntoVenta == null) ? 0 : idPuntoVenta.hashCode());
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
        if (!(obj instanceof ProductosPuntoVenta)) {
            return false;
        }
        ProductosPuntoVenta other = (ProductosPuntoVenta) obj;
        if (cantidad != other.cantidad) {
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
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProductosPuntoVenta [idPuntoVenta=" + idPuntoVenta
                + ", idProducto=" + idProducto + ", cantidad=" + cantidad + "]";
    }

}
