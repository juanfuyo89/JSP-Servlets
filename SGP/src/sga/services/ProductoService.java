package sga.services;

import java.util.List;

import sga.eis.dto.Descuento;
import sga.eis.dto.Marca;
import sga.eis.dto.Producto;
import sga.eis.dto.Tipo;
import sga.web.model.ProductoBean;

/**
 * Interface que define los metodos para el manejo de Productos en la capa de
 * servicios
 * 
 * @author Juan Carlos Fuyo
 */
public interface ProductoService {

    /**
     * Metodo que retorna todos los productos creados en sistema
     * 
     * @return List<ProductoBean>
     */
    public List<ProductoBean> getAllProductos();

    /**
     * Metodo que retorna un Producto de acuerdo al id
     * 
     * @param idProducto
     * @return Producto
     */
    public Producto getProductoById(Integer idProducto);

    /**
     * Metodo que retorna los Productos de acuerdo al nombre
     * 
     * @param nombre
     * @return List<ProductoBean>
     */
    public List<ProductoBean> getProductosByName(String nombre);

    /**
     * Metodo que retorna un Producto de acuerdo al nombre completo
     * 
     * @param nombre
     * @return Producto
     */
    public Producto getProductoByFullName(String nombre);

    /**
     * Metodo que retorna una lista de Productos de acuerdo al Tipo de Producto
     * 
     * @param tipo
     * @return List<ProductoBean>
     */
    public List<ProductoBean> getProductosByTipo(Integer tipo);

    /**
     * Metodo que retorna una lista de Productos de acuerdo a la Marca
     * 
     * @param marca
     * @return List<ProductoBean>
     */
    public List<ProductoBean> getProductosByMarca(Integer marca);

    /**
     * Metodo que elimina un grupo de Productos del sistema de acuerdo al
     * paramtero idProductos
     * 
     * @param idProductos
     * @return boolean
     */
    public boolean eliminarProductos(List<Integer> idProductos);

    /**
     * Metodo que guarda un Producto en el sistema
     * 
     * @param Producto
     * @return boolean
     */
    public boolean guardarProducto(Producto producto);

    /**
     * Metodo que retorna todos los tipos creados en sistema
     * 
     * @return List<Tipo>
     */
    public List<Tipo> getAllTipos();

    /**
     * Metodo que retorna un Tipo de acuerdo al id
     * 
     * @param idTipo
     * @return Tipo
     */
    public Tipo getTipoById(Integer idTipo);

    /**
     * Metodo que retorna los Tipos de acuerdo al nombre
     * 
     * @param nombre
     * @return List<Tipo>
     */
    public List<Tipo> getTiposByName(String nombre);

    /**
     * Metodo que retorna los Tipos de acuerdo al nombre completo
     * 
     * @param nombre
     * @return Tipo
     */
    public Tipo getTipoByFullName(String nombre);

    /**
     * Metodo que elimina un grupo de Tipos del sistema de acuerdo al paramtero
     * idTipos
     * 
     * @param idTipos
     * @return boolean
     */
    public boolean eliminarTipos(List<Integer> idTipos);

    /**
     * Metodo que guarda un Tipo en el sistema
     * 
     * @param Tipo
     * @return boolean
     */
    public boolean guardarTipo(Tipo tipo);

    /**
     * Metodo que retorna todas las marcas creadas en sistema
     * 
     * @return List<Marca>
     */
    public List<Marca> getAllMarcas();

    /**
     * Metodo que retorna una Marca de acuerdo al id
     * 
     * @param idMarca
     * @return Marca
     */
    public Marca getMarcaById(Integer idMarca);

    /**
     * Metodo que retorna las Marcas de acuerdo al nombre
     * 
     * @param nombre
     * @return List<Marca>
     */
    public List<Marca> getMarcasByName(String nombre);

    /**
     * Metodo que retorna una Marca de acuerdo al nombre completo
     * 
     * @param nombre
     * @return Marca
     */
    public Marca getMarcaByFullName(String nombre);

    /**
     * Metodo que elimina un grupo de Marcas del sistema de acuerdo al paramtero
     * idMarcas
     * 
     * @param idMarcas
     * @return boolean
     */
    public boolean eliminarMarcas(List<Integer> idMarcas);

    /**
     * Metodo que guarda una Marca en el sistema
     * 
     * @param Marca
     * @return boolean
     */
    public boolean guardarMarca(Marca marca);

    /**
     * Metodo que retorna todos los descuentos creados en sistema
     * 
     * @return List<Descuento>
     */
    public List<Descuento> getAllDescuentos();

    /**
     * Metodo que retorna un Descuento de acuerdo al id
     * 
     * @param idDescuento
     * @return Descuento
     */
    public Descuento getDescuentoById(Integer idDescuento);

    /**
     * Metodo que elimina un grupo de Descuentos del sistema de acuerdo al
     * paramtero idDescuentos
     * 
     * @param idDescuentos
     * @return boolean
     */
    public boolean eliminarDescuentos(List<Integer> idDescuentos);

    /**
     * Metodo que guarda un Descuento en el sistema
     * 
     * @param Descuento
     * @return boolean
     */
    public boolean guardarDescuento(Descuento descuento);

}
