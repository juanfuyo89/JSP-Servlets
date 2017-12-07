/**
 * 
 */
package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la primary key de la tabla Productos.
 * 
 * @author Juan Carlos Fuyo
 */
public class ProductoPk implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * Este atributo mapea la primary-key de la colunma id_producto en la tabla
     * Productos.
     */
    protected Integer         idProducto;

    /**
     * Constructor vacio
     */
    public ProductoPk() {
    }

    public ProductoPk(final Integer idProducto) {
        this.idProducto = idProducto;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idProducto == null) ? 0 : idProducto.hashCode());
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
        if (!(obj instanceof ProductoPk)) {
            return false;
        }
        ProductoPk other = (ProductoPk) obj;
        if (idProducto == null) {
            if (other.idProducto != null) {
                return false;
            }
        }
        else if (!idProducto.equals(other.idProducto)) {
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
        return "ProductoPk [idProducto=" + idProducto + "]";
    }

}
