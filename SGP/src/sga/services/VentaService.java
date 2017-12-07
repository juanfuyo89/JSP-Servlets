package sga.services;

import java.util.List;

import sga.eis.dto.Venta;

/**
 * Interface que define los metodos para el manejo de Ventas en la capa de
 * servicios
 *
 * @author Juan Carlos Fuyo
 */
public interface VentaService {

	/**
	 * Metodo que retorna todas las ventas creadas en sistema
	 * 
	 * @return List<Venta>
	 */
	public List<Venta> getAllVentas();

	/**
	 * Metodo que retorna una Venta de acuerdo al id
	 * 
	 * @param idVenta
	 * 
	 * @return Venta
	 */
	public Venta getVentaById(Integer idVenta);

	/**
	 * Metodo que retorna un grupo de ventas de acuerdo al parametro cedula
	 * 
	 * @param cedula
	 * @return List<Venta>
	 */
        public List<Venta> getVentaByPersona(String cedula);

        /**
         * Metodo que retorna un grupo de ventas de acuerdo al parametro idProducto
         * 
         * @param idProducto
         * @return List<Venta>
         */
        public List<Venta> getVentaByProducto(Integer idProducto);

        /**
         * Metodo que retorna un grupo de ventas de acuerdo al parametro idPuntoVenta
         * 
         * @param idPuntoVenta
         * @return List<Venta>
         */
        public List<Venta> getVentaByPuntoVenta(Integer idPuntoVenta);

	/**
	 * Metodo que elimina una Venta del sistema de acuerdo al paramtero
	 * idVenta
	 * 
	 * @param idVenta
	 * 
	 * @return boolean
	 */
	public boolean eliminarVenta(Integer idVenta);

	/**
	 * Metodo que guarda una Venta en el sistema
	 * 
	 * @param Venta
	 * @param cantidad
	 * 
	 * @return boolean
	 */
	public boolean guardarVenta(Venta venta, int cantidad);

}
