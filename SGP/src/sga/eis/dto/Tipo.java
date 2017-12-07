package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la tabla Tipos.
 * 
 * @author Juan Carlos Fuyo
 */
public class Tipo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Este atributo mapea la colunma id_tipo en la tabla Tipos.
     */
    protected Integer         idTipo;

    /**
     * Este atributo mapea la colunma nombre_tipo en la tabla Tipos.
     */
    protected String          nombre;

    /**
     * Constructor vacio
     */
    public Tipo() {
    }
    
    /**
     * Metodo para crear la primary-key de la tabla Tipos
     * 
     * @return TipoPk
     */
    public TipoPk createPk() {
        return new TipoPk(idTipo);
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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idTipo == null) ? 0 : idTipo.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
        if (!(obj instanceof Tipo)) {
            return false;
        }
        Tipo other = (Tipo) obj;
        if (idTipo == null) {
            if (other.idTipo != null) {
                return false;
            }
        }
        else if (!idTipo.equals(other.idTipo)) {
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
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Tipo [idTipo=" + idTipo + ", nombre=" + nombre + "]";
    }

}