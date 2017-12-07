/**
 * 
 */
package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la tabla Puntos_venta.
 * 
 * @author Juan Carlos Fuyo 
 */
public class PuntoVenta implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la colunma id_pdv en la tabla Puntos_venta.
     */
    protected Integer         idPuntoVenta;

    /**
     * Este atributo mapea la colunma nombre_pdv en la tabla Puntos_venta.
     */
    protected String          nombre;

    /**
     * Este atributo mapea la colunma direccion en la tabla Puntos_venta.
     */
    protected String          direccion;

    /**
     * Este atributo mapea la colunma telefono en la tabla Puntos_venta.
     */
    protected String          telefono;

    /**
     * Este atributo mapea la colunma id_admin en la tabla Puntos_venta.
     */
    protected String          idAdmin;
    
    /**
     * Constructor vacio
     */
    public PuntoVenta(){}
    
    /**
     * Metodo para crear la primary-key de la tabla Puntos_venta
     * 
     * @return PuntoVentaPk
     */
    public PuntoVentaPk createPk() {
        return new PuntoVentaPk(idPuntoVenta);
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
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    
    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    
    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    /**
     * @return the idAdmin
     */
    public String getIdAdmin() {
        return idAdmin;
    }

    
    /**
     * @param idAdmin the idAdmin to set
     */
    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((direccion == null) ? 0 : direccion.hashCode());
        result = prime * result + ((idAdmin == null) ? 0 : idAdmin.hashCode());
        result = prime * result
                + ((idPuntoVenta == null) ? 0 : idPuntoVenta.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result
                + ((telefono == null) ? 0 : telefono.hashCode());
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
        if (!(obj instanceof PuntoVenta)) {
            return false;
        }
        PuntoVenta other = (PuntoVenta) obj;
        if (direccion == null) {
            if (other.direccion != null) {
                return false;
            }
        }
        else if (!direccion.equals(other.direccion)) {
            return false;
        }
        if (idAdmin == null) {
            if (other.idAdmin != null) {
                return false;
            }
        }
        else if (!idAdmin.equals(other.idAdmin)) {
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
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        }
        else if (!nombre.equals(other.nombre)) {
            return false;
        }
        if (telefono == null) {
            if (other.telefono != null) {
                return false;
            }
        }
        else if (!telefono.equals(other.telefono)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PuntoVenta [idPuntoVenta=" + idPuntoVenta + ", nombre="
                + nombre + ", direccion=" + direccion + ", telefono="
                + telefono + ", idAdmin=" + idAdmin + "]";
    }
    
}
