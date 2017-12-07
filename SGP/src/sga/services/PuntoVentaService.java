package sga.services;

import java.util.List;
import sga.eis.dto.ProductosPuntoVenta;
import sga.eis.dto.PuntoVenta;

/**
 * Interface que define los metodos para el manejo de Puntos de Venta en la capa
 * de servicios
 * 
 * @author Juan Carlos Fuyo
 */
public interface PuntoVentaService {

    /**
     * Metodo que retorna todos los puntos de Venta creados en sistema
     * 
     * @return List<PuntoVenta>
     */
    public List<PuntoVenta> getAllPuntosVenta();

    /**
     * Metodo que retorna un PuntoVenta de acuerdo al id
     * 
     * @param idPuntoVenta
     * @return PuntoVenta
     */
    public PuntoVenta getPuntoVentaById(Integer idPuntoVenta);

    /**
     * Metodo que retorna un listado de Puntos de Venta de acuerdo al nombre
     * 
     * @param nombre
     * @return List<PuntoVenta>
     */
    public List<PuntoVenta> getPuntoVentaByName(String nombre);

    /**
     * Metodo que retorna un Punto de Venta de acuerdo al username del usuario
     * en sesion
     * 
     * @param nombre
     * @return PuntoVenta
     */
    public PuntoVenta getPuntoVentaByUser(String usuario);

    /**
     * Metodo que elimina un grupo de Puntos de Venta del sistema de acuerdo al
     * paramtero idPuntosVenta
     * 
     * @param idPuntosVenta
     * @return boolean
     */
    public boolean eliminarPuntosVenta(List<Integer> idPuntosVenta);

    /**
     * Metodo que guarda un Punto de Venta en el sistema
     * 
     * @param PuntoVenta
     * @return boolean
     */
    public boolean guardarPuntoVenta(PuntoVenta puntoVenta);

    /**
     * Metodo que retorna todos los Productos por punto de Venta creados en
     * sistema
     * 
     * @return List<ProductoPuntoVenta>
     */
    public List<ProductosPuntoVenta> getAllProductosPuntoVenta();

    /**
     * Metodo que retorna un registro de Productos por Punto de Venta de acuerdo
     * a sus ids
     * 
     * @param idPuntoVenta
     * @param idProducto
     * @return ProductoPuntoVenta
     */
    public ProductosPuntoVenta getProductoPuntoVentaById(Integer idPuntoVenta,
            Integer idProducto);

    /**
     * Metodo que retorna una lista de Productos por Punto de Venta de acuerdo
     * al parametro idPuntoVenta
     * 
     * @param idPuntoVenta 
     * @return List<ProductosPuntoVenta>
     */
    public List<ProductosPuntoVenta> getProductoPuntoVentaByPuntoVenta(Integer idPuntoVenta);

    /**
     * Metodo que retorna una lista de Productos por Punto de Venta de acuerdo
     * al parametro idPuntoVenta
     * 
     * @param idProducto
     * @return List<ProductosPuntoVenta>
     */
    public List<ProductosPuntoVenta> getProductoPuntoVentaByProducto(Integer idProducto);

    /**
     * Metodo que elimina un grupo de Puntos de Venta del sistema de acuerdo al
     * paramtero idPuntosVenta
     * 
     * @param idPuntosVenta
     * @param idProductos
     * @return boolean
     */
    public boolean eliminarProductosPuntoVenta(List<Integer> idPuntosVenta,
            List<Integer> idProductos);

    /**
     * Metodo que guarda Productos por Punto de Venta en el sistema
     * 
     * @param productosPuntoVenta
     * @return boolean
     */
    public boolean guardarProductoPuntoVenta(
            ProductosPuntoVenta productosPuntoVenta);

}
