package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la tabla Productos.
 * 
 * @author Juan Carlos Fuyo
 */
public class Producto implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la colunma id_producto en la tabla Productos.
     */
    protected Integer         idProducto;

    /**
     * Este atributo mapea la colunma nombre en la tabla Productos.
     */
    protected String          nombre;

    /**
     * Este atributo mapea la colunma descripcion en la tabla Productos.
     */
    protected String          descripcion;

    /**
     * Este atributo mapea la colunma costo en la tabla Productos.
     */
    protected double          costo;

    /**
     * Este atributo mapea la colunma precio en la tabla Productos.
     */
    protected double          precio;

    /**
     * Este atributo mapea la colunma marca en la tabla Productos.
     */
    protected int             marca;

    /**
     * Este atributo mapea la colunma tipo en la tabla Productos.
     */
    protected int             tipo;

    /**
     * Este atributo mapea la colunma descuento en la tabla Productos.
     */
    protected int             descuento;

    /**
     * Constructor vacio
     */
    public Producto() {
    }

    /**
     * Metodo para crear la primary-key de la tabla Productos
     * 
     * @return ProductoPk
     */
    public ProductoPk createPk() {
        return new ProductoPk(idProducto);
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

    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    /**
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    
    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    
    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    
    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }


    /**
     * @return the marca
     */
    public int getMarca() {
        return marca;
    }

    
    /**
     * @param marca the marca to set
     */
    public void setMarca(int marca) {
        this.marca = marca;
    }

    
    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    
    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    
    /**
     * @return the descuento
     */
    public int getDescuento() {
        return descuento;
    }

    
    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(costo);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result
                + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + descuento;
        result = prime * result
                + ((idProducto == null) ? 0 : idProducto.hashCode());
        result = prime * result + marca;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        temp = Double.doubleToLongBits(precio);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + tipo;
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
        if (!(obj instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) obj;
        if (Double.doubleToLongBits(costo) != Double.doubleToLongBits(other.costo)) {
            return false;
        }
        if (descripcion == null) {
            if (other.descripcion != null) {
                return false;
            }
        }
        else if (!descripcion.equals(other.descripcion)) {
            return false;
        }
        if (descuento != other.descuento) {
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
        if (marca != other.marca) {
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
        if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (tipo != other.tipo) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Producto [idProducto=" + idProducto + ", nombre=" + nombre
                + ", descripcion=" + descripcion + ", costo=" + costo
                + ", precio=" + precio + ", marca=" + marca + ", tipo=" + tipo
                + ", descuento=" + descuento + "]";
    }

}
