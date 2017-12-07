package sga.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import sga.eis.dao.ProductosPuntoVentaDao;
import sga.eis.dao.VentaDao;
import sga.eis.dto.ProductosPuntoVenta;
import sga.eis.dto.Venta;
import sga.eis.dto.VentaPk;
import sga.eis.exceptions.ProductosPuntoVentaDaoException;
import sga.eis.exceptions.VentaDaoException;
import sga.eis.factory.ProductosPuntoVentaDaoFactory;
import sga.eis.factory.VentaDaoFactory;
import sga.eis.jdbc.connect.ResourceManager;
import sga.services.VentaService;
import sga.services.exceptions.BusinessException;
import sga.web.utilities.IGlobalConstant;

/**
 * Clase que implementa los metodos de la capa de servicios para manipular un
 * Venta
 * 
 * @author Juan Carlos Fuyo
 */
public class VentaServiceImpl implements VentaService {

    /**
     * Objeto de la clase para implementar el patron singleton
     */
    private static VentaService ventaServiceInstance;

    /**
     * Atributo ventaDao para comunicarnos con la capa de datos
     */
    VentaDao                    ventaDao;

    /**
     * Constructor sin argumentos privado, para implementar el patron singleton
     */
    private VentaServiceImpl() {
    }

    /**
     * Metodo para crear una nueva y unica instancia si es que no existe
     * 
     * @return ventaServiceInstance
     */
    public static VentaService getInstance() {
        if (ventaServiceInstance == null) {
            ventaServiceInstance = new VentaServiceImpl();
        }
        return ventaServiceInstance;
    }

    /**
     * Metodo encargado de recuperar una instancia de VentaDao haciendo uso del
     * patrón Factory
     * 
     * @return VentaDao
     */
    public VentaDao getVentaDao() {
        if (this.ventaDao != null) {
            return this.ventaDao;
        }
        return VentaDaoFactory.create();
    }

    /**
     * Metodo que retorna todas las ventas creadas en sistema
     * 
     * @return List<Venta>
     */
    @Override
    public List<Venta> getAllVentas() {
        try {
            this.ventaDao = getVentaDao();
            return Arrays.asList(this.ventaDao.findAll());
        }
        catch (VentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el arreglo de Ventas en la BD",
                    ex);
        }
    }

    /**
     * Metodo que retorna una Venta de acuerdo al id
     * 
     * @param idVenta
     * @return Venta
     */
    @Override
    public Venta getVentaById(Integer idVenta) {
        this.ventaDao = getVentaDao();
        try {
            return this.ventaDao.findByPrimaryKey(idVenta);
        }
        catch (VentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Venta con id: " + idVenta,
                    ex);
        }
    }

    /**
     * Metodo que retorna un grupo de ventas de acuerdo al parametro cedula
     * 
     * @param cedula
     * @return List<Venta>
     */
    @Override
    public List<Venta> getVentaByPersona(String cedula) {
        this.ventaDao = getVentaDao();
        try {
            return Arrays.asList(this.ventaDao.findByPersona(cedula));
        }
        catch (VentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener la Venta con idVendedor: "
                            + cedula, ex);
        }
    }

    /**
     * Metodo que retorna un grupo de ventas de acuerdo al parametro idProducto
     * 
     * @param idProducto
     * @return List<Venta>
     */
    @Override
    public List<Venta> getVentaByProducto(Integer idProducto) {
        this.ventaDao = getVentaDao();
        try {
            return Arrays.asList(this.ventaDao.findByProducto(idProducto));
        }
        catch (VentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener la Venta con idProducto: "
                            + idProducto, ex);
        }
    }

    /**
     * Metodo que retorna un grupo de ventas de acuerdo al parametro
     * idPuntoVenta
     * 
     * @param idPuntoVenta
     * @return List<Venta>
     */
    @Override
    public List<Venta> getVentaByPuntoVenta(Integer idPuntoVenta) {
        this.ventaDao = getVentaDao();
        try {
            return Arrays.asList(this.ventaDao.findByPuntoVenta(idPuntoVenta));
        }
        catch (VentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener la Venta con idPuntoVenta: "
                            + idPuntoVenta, ex);
        }
    }

    /**
     * Metodo que elimina un grupo de Ventas del sistema de acuerdo al paramtero
     * idVentas
     * 
     * @param idVentas
     * @return boolean
     */
    @Override
    public boolean eliminarVenta(Integer idVenta) {
        Connection conn = null;
        this.ventaDao = getVentaDao();
        try {
            ProductosPuntoVentaDao productoPuntoVentaDao = ProductosPuntoVentaDaoFactory.create();
            Venta venta = this.getVentaById(idVenta);
            int cantidadAlmacen = productoPuntoVentaDao.findByPrimaryKey(
                    venta.getIdPuntoVenta(), venta.getIdProducto()).getCantidad();
            // Comenzamos la transaccion
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.ventaDao.setUserConn(conn);
            // Modificamos la tabla de Productos por PDV ya que al eliminar la
            // venta estos se modifican
            ProductosPuntoVenta productosPuntoVenta = new ProductosPuntoVenta();
            productosPuntoVenta.setIdPuntoVenta(venta.getIdPuntoVenta());
            productosPuntoVenta.setIdProducto(venta.getIdProducto());
            productosPuntoVenta.setCantidad(cantidadAlmacen
                    + venta.getCantidad());
            this.ventaDao.delete(new VentaPk(idVenta));
            productoPuntoVentaDao.setUserConn(conn);
            productoPuntoVentaDao.update(productosPuntoVenta.createPk(),
                    productosPuntoVenta);
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (VentaDaoException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException(
                    "Existe un problema para eliminar los elementos: "
                            + idVenta, ex);
        }
        catch (ProductosPuntoVentaDaoException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException(
                    "Existe un problema para eliminar los elementos: "
                            + idVenta, ex);
        }
        catch (SQLException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException(
                    "Existe un problema con la Base de Datos", ex);
        }
        finally {
            ResourceManager.close(conn);
        }
    }

    /**
     * Metodo que guarda una Venta en el sistema
     * 
     * @param Venta
     * @param cantidad
     * @return boolean
     */
    public boolean guardarVenta(Venta venta, int cantidad) {
        Connection conn = null;
        this.ventaDao = getVentaDao();
        // Comenzamos la transaccion
        try {
            // Obtenemos la Cantidad de Productos vendidos (en la venta a
            // modificar)
            int cantidadActual = (venta.getIdVenta() != null)
                    ? getVentaById(venta.getIdVenta()).getCantidad()
                    : IGlobalConstant.INT_ZERO;
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.ventaDao.setUserConn(conn);
            // Modificamos la tabla de Productos por PDV ya que en la venta
            // estos se modifican
            ProductosPuntoVentaDao productoPuntoVentaDao = ProductosPuntoVentaDaoFactory.create(conn);
            ProductosPuntoVenta productosPuntoVenta = new ProductosPuntoVenta();
            productosPuntoVenta.setIdPuntoVenta(venta.getIdPuntoVenta());
            productosPuntoVenta.setIdProducto(venta.getIdProducto());
            // Revisamos si es un insert o un update dependiendo del parametro
            // "guardar"
            if (venta.getIdVenta() == null) {
                productosPuntoVenta.setCantidad(cantidad - venta.getCantidad());
                this.ventaDao.insert(venta);
            }
            else {
                productosPuntoVenta.setCantidad((venta.getCantidad() > cantidadActual)
                        ? (cantidad - (venta.getCantidad() - cantidadActual))
                        : (cantidad + (cantidadActual - venta.getCantidad())));
                this.ventaDao.update(venta.createPk(), venta);
            }
            productoPuntoVentaDao.update(productosPuntoVenta.createPk(),
                    productosPuntoVenta);
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (VentaDaoException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException("No se puedo agregar el Venta:" + venta
                    + " a la BD", ex);
        }
        catch (ProductosPuntoVentaDaoException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException(
                    "No se puedo modificar el Nº de Productos:" + venta
                            + " a la BD", ex);
        }
        catch (SQLException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException(
                    "Existe un problema con la Base de Datos", ex);
        }
        finally {
            // Cerramos la conexión para regresala al pool
            ResourceManager.close(conn);
        }
    }

}
