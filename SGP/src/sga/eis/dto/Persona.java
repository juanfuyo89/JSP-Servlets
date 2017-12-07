package sga.eis.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Esta clase representa la tabla Personas.
 * 
 * @author Juan Carlos Fuyo
 *
 */
public class Persona implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la colunma cedula en la tabla Personas.
     */
    protected String          cedula;

    /**
     * Este atributo mapea la colunma nombre en la tabla Personas.
     */
    protected String          nombre;

    /**
     * Este atributo mapea la colunma apellido_paterno en la tabla Personas.
     */
    protected String          apePaterno;

    /**
     * Este atributo mapea la colunma apellido_materno en la tabla Personas.
     */
    protected String          apeMaterno;

    /**
     * Este atributo mapea la colunma genero en la tabla Personas.
     */
    protected String          genero;

    /**
     * Este atributo mapea la colunma fecha_nacimiento en la tabla Personas.
     */
    protected Date            fecNacimiento;

    /**
     * Constructor vacio
     */
    public Persona() {
    }

    /**
     * Method 'getCedula'
     * 
     * @return String
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Metodo para crear la primary-key de la tabla Personas
     * 
     * @return PersonaPk
     */
    public PersonaPk createPk() {
        return new PersonaPk(this.cedula);
    }

    /**
     * Method 'setIdPersona'
     * 
     * @param idPersona
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Method 'getNombre'
     * 
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method 'setNombre'
     * 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Method 'getApellido'
     * 
     * @return String
     */
    public String getApePaterno() {
        return apePaterno;
    }

    /**
     * Method 'setApellido'
     * 
     * @param apellido
     */
    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    /**
     * @return the apeMaterno
     */
    public String getApeMaterno() {
        return apeMaterno;
    }

    /**
     * @param apeMaterno the apeMaterno to set
     */
    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the fecNacimiento
     */
    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    /**
     * @param fecNacimiento the fecNacimiento to set
     */
    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }
    
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((apeMaterno == null) ? 0 : apeMaterno.hashCode());
        result = prime * result
                + ((apePaterno == null) ? 0 : apePaterno.hashCode());
        result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
        result = prime * result
                + ((fecNacimiento == null) ? 0 : fecNacimiento.hashCode());
        result = prime * result + ((genero == null) ? 0 : genero.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    /**
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
        if (!(obj instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) obj;
        if (apeMaterno == null) {
            if (other.apeMaterno != null) {
                return false;
            }
        }
        else if (!apeMaterno.equals(other.apeMaterno)) {
            return false;
        }
        if (apePaterno == null) {
            if (other.apePaterno != null) {
                return false;
            }
        }
        else if (!apePaterno.equals(other.apePaterno)) {
            return false;
        }
        if (cedula == null) {
            if (other.cedula != null) {
                return false;
            }
        }
        else if (!cedula.equals(other.cedula)) {
            return false;
        }
        if (fecNacimiento == null) {
            if (other.fecNacimiento != null) {
                return false;
            }
        }
        else if (!fecNacimiento.equals(other.fecNacimiento)) {
            return false;
        }
        if (genero == null) {
            if (other.genero != null) {
                return false;
            }
        }
        else if (!genero.equals(other.genero)) {
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

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Persona [cedula=" + cedula + ", nombre=" + nombre
                + ", apePaterno=" + apePaterno + ", apeMaterno=" + apeMaterno
                + ", genero=" + genero + ", fecNacimiento=" + fecNacimiento
                + "]";
    }

}
