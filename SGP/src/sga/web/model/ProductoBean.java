package sga.web.model;

import java.io.Serializable;
import sga.eis.dto.Descuento;
import sga.eis.dto.Marca;
import sga.eis.dto.Producto;
import sga.eis.dto.Tipo;

/**
 * Clase que modela un Producto encapsulando sus objetos asociados {Producto,
 * Marca, Tipo y Descuento} para manipularlo en los JSPs
 * 
 * @author Juan Carlos Fuyo
 */
public class ProductoBean implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo de tipo Producto
     */
    private Producto  producto;

    /**
     * Atributo de tipo Producto
     */
    private Tipo      tipo;

    /**
     * Atributo de tipo Producto
     */
    private Marca     marca;

    /**
     * Atributo de tipo Producto
     */
    private Descuento descuento;

    /**
     * Constructor vacio
     */
    public ProductoBean() {
    }

    /**
     * Constructor con parametros
     * 
     * @param producto
     * @param tipo
     * @param marca
     * @param descuento
     */
    public ProductoBean(Producto producto, Tipo tipo, Marca marca,
            Descuento descuento) {
        super();
        this.producto = producto;
        this.tipo = tipo;
        this.marca = marca;
        this.descuento = descuento;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    /**
     * @return the marca
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }

    /**
     * @return the descuento
     */
    public Descuento getDescuento() {
        return descuento;
    }

}
