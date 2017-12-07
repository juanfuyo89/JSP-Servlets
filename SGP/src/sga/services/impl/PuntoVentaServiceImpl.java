package sga.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import sga.eis.dao.ProductosPuntoVentaDao;
import sga.eis.dao.PuntoVentaDao;
import sga.eis.dao.UsuarioDao;
import sga.eis.dto.ProductosPuntoVenta;
import sga.eis.dto.ProductosPuntoVentaPk;
import sga.eis.dto.PuntoVenta;
import sga.eis.dto.PuntoVentaPk;
import sga.eis.exceptions.ProductosPuntoVentaDaoException;
import sga.eis.exceptions.PuntoVentaDaoException;
import sga.eis.exceptions.UsuarioDaoException;
import sga.eis.factory.ProductosPuntoVentaDaoFactory;
import sga.eis.factory.PuntoVentaDaoFactory;
import sga.eis.factory.UsuarioDaoFactory;
import sga.eis.jdbc.connect.ResourceManager;
import sga.services.PuntoVentaService;
import sga.services.exceptions.BusinessException;

/**
 * Clase que implementa los metodos de la capa de servicios para manipular un
 * Punto de Venta
 * 
 * @author Juan Carlos Fuyo
 */
public class PuntoVentaServiceImpl implements sga.services.PuntoVentaService {

    /**
     * Objeto de la clase para implementar el patron singleton
     */
    private static PuntoVentaService puntoVentaServiceInstance;

    /**
     * Atributo puntoVentaDao para comunicarnos con la capa de datos
     */
    PuntoVentaDao                    puntoVentaDao;

    /**
     * Atributo ProductosPuntoVentaDao para comunicarnos con la capa de datos
     */
    ProductosPuntoVentaDao           productosPuntoVentaDao;

    /**
     * Constructor sin argumentos privado, para implementar el patron singleton
     */
    private PuntoVentaServiceImpl() {
    }

    /**
     * Metodo para crear una nueva y unica instancia si es que no existe
     * 
     * @return puntoVentaServiceInstance
     */
    public static PuntoVentaService getInstance() {
        if (puntoVentaServiceInstance == null) {
            puntoVentaServiceInstance = new PuntoVentaServiceImpl();
        }
        return puntoVentaServiceInstance;
    }

    /**
     * Metodo encargado de recuperar una instancia de PuntoVentaDao haciendo uso
     * del patrón Factory
     * 
     * @return PuntoVentaDao
     */
    public PuntoVentaDao getPuntoVentaDao() {
        if (this.puntoVentaDao != null) {
            return this.puntoVentaDao;
        }
        return PuntoVentaDaoFactory.create();
    }

    /**
     * Metodo encargado de recuperar una instancia de ProductosPuntoVentaDao
     * haciendo uso del patrón Factory
     * 
     * @return PuntoVentaDao
     */
    public ProductosPuntoVentaDao getProductosPuntoVentaDao() {
        if (this.productosPuntoVentaDao != null) {
            return this.productosPuntoVentaDao;
        }
        return ProductosPuntoVentaDaoFactory.create();
    }

    /**
     * Metodo que retorna todos los puntos de Venta creados en sistema
     * 
     * @return List<PuntoVenta>
     */
    @Override
    public List<PuntoVenta> getAllPuntosVenta() {
        try {
            this.puntoVentaDao = getPuntoVentaDao();
            return Arrays.asList(this.puntoVentaDao.findAll());
        }
        catch (PuntoVentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el arreglo de Puntos de Venta en la BD",
                    ex);
        }
    }

    /**
     * Metodo que retorna un PuntoVenta de acuerdo al id
     * 
     * @param idPuntoVenta
     * @return PuntoVenta
     */
    @Override
    public PuntoVenta getPuntoVentaById(Integer idPuntoVenta) {
        this.puntoVentaDao = getPuntoVentaDao();
        try {
            return this.puntoVentaDao.findByPrimaryKey(idPuntoVenta);
        }
        catch (PuntoVentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Punto de Venta con id: "
                            + idPuntoVenta, ex);
        }
    }

    /**
     * Metodo que retorna un listado de Puntos de Venta de acuerdo al nombre
     * 
     * @param nombre
     * @return List<PuntoVenta>
     */
    @Override
    public List<PuntoVenta> getPuntoVentaByName(String nombre) {
        this.puntoVentaDao = getPuntoVentaDao();
        try {
            return Arrays.asList(this.puntoVentaDao.findByName(nombre));
        }
        catch (PuntoVentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener los Puntos de Venta con nombre: "
                            + nombre, ex);
        }
    }

    /**
     * Metodo que retorna un listado de Puntos de Venta de acuerdo al nombre
     * 
     * @param usuario
     * @return List<PuntoVenta>
     */
    @Override
    public PuntoVenta getPuntoVentaByUser(String usuario) {
        this.puntoVentaDao = getPuntoVentaDao();
        try {
            UsuarioDao usuarioDao = UsuarioDaoFactory.create();
            String idAdmin = usuarioDao.findByFullName(usuario).getIdPersona();
            return this.puntoVentaDao.findByAdmin(idAdmin);
        }
        catch (PuntoVentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener los Puntos de Venta con nombre: "
                            + usuario, ex);
        }
        catch (UsuarioDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el usuario con nombre: "
                            + usuario, ex);
        }
    }

    /**
     * Metodo que elimina un grupo de Puntos de Venta del sistema de acuerdo al
     * paramtero idPuntosVenta
     * 
     * @param idPuntosVenta
     * @return boolean
     */
    @Override
    public boolean eliminarPuntosVenta(List<Integer> idPuntosVenta) {
        Connection conn = null;
        this.puntoVentaDao = getPuntoVentaDao();
        try {
            // Comenzamos la transaccion, si algun elemento no se elimina
            // entonces ninguno se elmina
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.puntoVentaDao.setUserConn(conn);
            // Eliminamos registro a registro
            for (Integer puntoVenta : idPuntosVenta) {
                this.puntoVentaDao.delete(new PuntoVentaPk(puntoVenta));
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (PuntoVentaDaoException ex) {
            try {
                if (!conn.isClosed()) {
                    conn.rollback();
                }
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException(
                    "Existe un problema para eliminar los elementos: "
                            + idPuntosVenta, ex);
        }
        catch (SQLException ex) {
            try {
                if (!conn.isClosed()) {
                    conn.rollback();
                }
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
            // Cerramos la conexion para regresala al pool
            try {
                if (!conn.isClosed()) {
                    ResourceManager.close(conn);
                }
            }
            catch (SQLException e) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        e);
            }
        }
    }

    /**
     * Metodo que guarda un Punto de Venta en el sistema
     * 
     * @param PuntoVenta
     * @return boolean
     */
    @Override
    public boolean guardarPuntoVenta(PuntoVenta puntoVenta) {
        Connection conn = null;
        this.puntoVentaDao = getPuntoVentaDao();
        // Comenzamos la transaccion
        try {
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.puntoVentaDao.setUserConn(conn);
            // Revisamos si es un insert o un update, dependiendo si se tiene o
            // no el valor de la PK
            if (puntoVenta.getIdPuntoVenta() == null) {
                this.puntoVentaDao.insert(puntoVenta);
            }
            else {
                this.puntoVentaDao.update(puntoVenta.createPk(), puntoVenta);
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se guardaron los registros
            return true;
        }
        catch (PuntoVentaDaoException ex) {
            try {
                if (!conn.isClosed()) {
                    conn.rollback();
                }
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException("No se puedo agregar el PuntoVenta:"
                    + puntoVenta.toString() + " a la BD", ex);
        }
        catch (SQLException ex) {
            try {
                if (!conn.isClosed()) {
                    conn.rollback();
                }
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
            // Cerramos la conexion para regresala al pool
            try {
                if (!conn.isClosed()) {
                    ResourceManager.close(conn);
                }
            }
            catch (SQLException e) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        e);
            }
        }
    }

    /**
     * Metodo que retorna todos los Productos por punto de Venta creados en
     * sistema
     * 
     * @return List<ProductoPuntoVenta>
     */
    @Override
    public List<ProductosPuntoVenta> getAllProductosPuntoVenta() {
        this.productosPuntoVentaDao = getProductosPuntoVentaDao();
        try {
            return Arrays.asList(this.productosPuntoVentaDao.findAll());
        }
        catch (ProductosPuntoVentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el arreglo de Productos por Punto de Venta en la BD",
                    ex);
        }
    }

    /**
     * Metodo que retorna un registro de Productos por Punto de Venta de acuerdo
     * a sus ids
     * 
     * @param idPuntoVenta
     * @param idProducto
     * @return ProductoPuntoVenta
     */
    @Override
    public ProductosPuntoVenta getProductoPuntoVentaById(Integer idPuntoVenta,
            Integer idProducto) {
        this.productosPuntoVentaDao = getProductosPuntoVentaDao();
        try {
            return this.productosPuntoVentaDao.findByPrimaryKey(idPuntoVenta,
                    idProducto);
        }
        catch (ProductosPuntoVentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener los Productos por PDV con  id Punto de Venta: "
                            + idPuntoVenta + " y id de Producto: " + idProducto,
                    ex);
        }
    }

    /**
     * Metodo que retorna una lista de Productos por Punto de Venta de acuerdo
     * al parametro idPuntoVenta
     * 
     * @param idPuntoVenta
     * @return List<ProductosPuntoVenta>
     */
    @Override
    public List<ProductosPuntoVenta> getProductoPuntoVentaByPuntoVenta(
            Integer idPuntoVenta) {
        this.productosPuntoVentaDao = getProductosPuntoVentaDao();
        try {
            return Arrays.asList(this.productosPuntoVentaDao.findByPuntoVenta(idPuntoVenta));
        }
        catch (ProductosPuntoVentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el arreglo de Productos por Punto de Venta en la BD",
                    ex);
        }
    }

    /**
     * Metodo que retorna una lista de Productos por Punto de Venta de acuerdo
     * al parametro idPuntoVenta
     * 
     * @param idProducto
     * @return List<ProductosPuntoVenta>
     */
    @Override
    public List<ProductosPuntoVenta> getProductoPuntoVentaByProducto(
            Integer idProducto) {
        this.productosPuntoVentaDao = getProductosPuntoVentaDao();
        try {
            return Arrays.asList(this.productosPuntoVentaDao.findByProducto(idProducto));
        }
        catch (ProductosPuntoVentaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el arreglo de Productos por Punto de Venta en la BD",
                    ex);
        }
    }

    /**
     * Metodo que elimina un grupo de Puntos de Venta del sistema de acuerdo al
     * paramtero idPuntosVenta
     * 
     * @param idPuntosVenta
     * @param idProductos
     * @return boolean
     */
    @SuppressWarnings("null")
    @Override
    public boolean eliminarProductosPuntoVenta(List<Integer> idPuntosVenta,
            List<Integer> idProductos) {
        Connection conn = null;
        this.productosPuntoVentaDao = getProductosPuntoVentaDao();
        try {
            // Comenzamos la transaccion, si algun elemento no se elimina
            // entonces ninguno se elmina, Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.productosPuntoVentaDao.setUserConn(conn);
            // Eliminamos registro a registro
            int index = 1;
            for (Integer puntoVenta : idPuntosVenta) {
                this.productosPuntoVentaDao.delete(new ProductosPuntoVentaPk(
                        puntoVenta, idProductos.get(index++)));
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (ProductosPuntoVentaDaoException ex) {
            try {
                if (!conn.isClosed()) {
                    conn.rollback();
                }
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException(
                    "Existe un problema para eliminar los elementos: "
                            + idPuntosVenta + " - " + idProductos, ex);
        }
        catch (SQLException ex) {
            try {
                if (!conn.isClosed()) {
                    conn.rollback();
                }
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
            // Cerramos la conexion para regresala al pool
            try {
                if (!conn.isClosed()) {
                    ResourceManager.close(conn);
                }
            }
            catch (SQLException e) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        e);
            }
        }
    }

    /**
     * Metodo que guarda Productos por Punto de Venta en el sistema
     * 
     * @param productosPuntoVenta
     * @return boolean
     */
    @Override
    public boolean guardarProductoPuntoVenta(
            ProductosPuntoVenta productosPuntoVenta) {
        Connection conn = null;
        this.productosPuntoVentaDao = getProductosPuntoVentaDao();
        try {
            ProductosPuntoVenta productosPuntoVentaAux = this.productosPuntoVentaDao.findByPrimaryKey(productosPuntoVenta.createPk());
            // Comenzamos la transaccion
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.productosPuntoVentaDao.setUserConn(conn);
            // Revisamos si es un insert o un update, dependiendo si se tiene o
            // no el valor de la PK
            if (productosPuntoVentaAux == null) {
                this.productosPuntoVentaDao.insert(productosPuntoVenta);
            }
            else {
                this.productosPuntoVentaDao.update(
                        productosPuntoVenta.createPk(), productosPuntoVenta);
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se guardaron los registros
            return true;
        }
        catch (ProductosPuntoVentaDaoException ex) {
            try {
                if (!conn.isClosed()) {
                    conn.rollback();
                }
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException(
                    "No se puedo agregar los Productos por PuntoVenta:"
                            + productosPuntoVenta.toString() + " a la BD", ex);
        }
        catch (SQLException ex) {
            try {
                if (!conn.isClosed()) {
                    conn.rollback();
                }
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
            // Cerramos la conexion para regresala al pool
            try {
                if (!conn.isClosed()) {
                    ResourceManager.close(conn);
                }
            }
            catch (SQLException e) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        e);
            }
        }
    }

}
