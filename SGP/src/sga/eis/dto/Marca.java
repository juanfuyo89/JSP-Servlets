package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la tabla Marcas.
 * 
 * @author Juan Carlos Fuyo 
 */
public class Marca implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la colunma id_marca en la tabla Marcas.
     */
    protected Integer         idMarca;

    /**
     * Este atributo mapea la colunma nombre_marca en la tabla Marcas.
     */
    protected String          nombre;

    /**
     * Este atributo mapea la colunma nacional en la tabla Marcas.
     */
    protected int             esNacional;

    /**
     * Constructor vacio
     */
    public Marca() {
    }
    
    /**
     * Metodo para crear la primary-key de la tabla Marcas
     * 
     * @return MarcaPk
     */
    public MarcaPk createPk() {
        return new MarcaPk(idMarca);
    }

    
    /**
     * @return the idMarca
     */
    public Integer getIdMarca() {
        return idMarca;
    }

    
    /**
     * @param idMarca the idMarca to set
     */
    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
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
     * @return the esNacional
     */
    public int getEsNacional() {
        return esNacional;
    }

    
    /**
     * @param esNacional the esNacional to set
     */
    public void setEsNacional(int esNacional) {
        this.esNacional = esNacional;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + esNacional;
        result = prime * result + ((idMarca == null) ? 0 : idMarca.hashCode());
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
        if (!(obj instanceof Marca)) {
            return false;
        }
        Marca other = (Marca) obj;
        if (esNacional != other.esNacional) {
            return false;
        }
        if (idMarca == null) {
            if (other.idMarca != null) {
                return false;
            }
        }
        else if (!idMarca.equals(other.idMarca)) {
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
        return "Marca [idMarca=" + idMarca + ", nombre=" + nombre
                + ", esNacional=" + esNacional + "]";
    }
    
}
