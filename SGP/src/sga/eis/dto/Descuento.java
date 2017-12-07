package sga.eis.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Esta clase representa la tabla Descuentos en BD.
 * 
 * @author Juan Carlos Fuyo
 */
public class Descuento implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la colunma id_dcto en la tabla Descuentos.
     */
    protected Integer         idDcto;

    /**
     * Este atributo mapea la colunma descripcion en la tabla Descuentos.
     */
    protected String          descripcion;

    /**
     * Este atributo mapea la colunma fecha_inicial en la tabla Descuentos.
     */
    protected Date            fechInicial;

    /**
     * Este atributo mapea la colunma fecha_final en la tabla Descuentos.
     */
    protected Date            fechFinal;

    /**
     * Este atributo mapea la colunma porcentaje en la tabla Descuentos.
     */
    protected int             porcentaje = 0;

    /**
     * Constructor vacio
     */
    public Descuento() {
    }

    /**
     * Metodo para crear la primary-key de la tabla Descuentos
     * 
     * @return DescuentoPk
     */
    public DescuentoPk createPk() {
        return new DescuentoPk(idDcto);
    }

    /**
     * @return the idDcto
     */
    public Integer getIdDcto() {
        return idDcto;
    }

    /**
     * @param idDcto the idDcto to set
     */
    public void setIdDcto(Integer idDcto) {
        this.idDcto = idDcto;
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
     * @return the fechInicial
     */
    public Date getFechInicial() {
        return fechInicial;
    }

    /**
     * @param fechInicial the fechInicial to set
     */
    public void setFechInicial(Date fechInicial) {
        this.fechInicial = fechInicial;
    }

    /**
     * @return the fechFinal
     */
    public Date getFechFinal() {
        return fechFinal;
    }

    /**
     * @param fechFinal the fechFinal to set
     */
    public void setFechFinal(Date fechFinal) {
        this.fechFinal = fechFinal;
    }

    /**
     * @return the porcentaje
     */
    public int getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
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
                + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result
                + ((fechFinal == null) ? 0 : fechFinal.hashCode());
        result = prime * result
                + ((fechInicial == null) ? 0 : fechInicial.hashCode());
        result = prime * result + ((idDcto == null) ? 0 : idDcto.hashCode());
        result = prime * result + porcentaje;
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
        if (!(obj instanceof Descuento)) {
            return false;
        }
        Descuento other = (Descuento) obj;
        if (descripcion == null) {
            if (other.descripcion != null) {
                return false;
            }
        }
        else if (!descripcion.equals(other.descripcion)) {
            return false;
        }
        if (fechFinal == null) {
            if (other.fechFinal != null) {
                return false;
            }
        }
        else if (!fechFinal.equals(other.fechFinal)) {
            return false;
        }
        if (fechInicial == null) {
            if (other.fechInicial != null) {
                return false;
            }
        }
        else if (!fechInicial.equals(other.fechInicial)) {
            return false;
        }
        if (idDcto == null) {
            if (other.idDcto != null) {
                return false;
            }
        }
        else if (!idDcto.equals(other.idDcto)) {
            return false;
        }
        if (porcentaje != other.porcentaje) {
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
        return "Descuento [idDcto=" + idDcto + ", descripcion=" + descripcion
                + ", fechInicial=" + fechInicial + ", fechFinal=" + fechFinal
                + ", porcentaje=" + porcentaje + "]";
    }

}
