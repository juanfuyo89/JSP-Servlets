package sga.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import sga.eis.dao.DescuentoDao;
import sga.eis.dao.MarcaDao;
import sga.eis.dao.ProductoDao;
import sga.eis.dao.TipoDao;
import sga.eis.dto.Descuento;
import sga.eis.dto.DescuentoPk;
import sga.eis.dto.Marca;
import sga.eis.dto.MarcaPk;
import sga.eis.dto.Producto;
import sga.eis.dto.ProductoPk;
import sga.eis.dto.Tipo;
import sga.eis.dto.TipoPk;
import sga.eis.exceptions.DescuentoDaoException;
import sga.eis.exceptions.MarcaDaoException;
import sga.eis.exceptions.ProductoDaoException;
import sga.eis.exceptions.TipoDaoException;
import sga.eis.factory.DescuentoDaoFactory;
import sga.eis.factory.MarcaDaoFactory;
import sga.eis.factory.ProductoDaoFactory;
import sga.eis.factory.TipoDaoFactory;
import sga.eis.jdbc.connect.ResourceManager;
import sga.services.ProductoService;
import sga.services.exceptions.BusinessException;
import sga.web.model.ProductoBean;

/**
 * Clase que implementa los metodos de la capa de servicios para manipular un
 * Producto
 * 
 * @author Juan Carlos Fuyo
 */
public class ProductoServiceImpl implements ProductoService {

    /**
     * Objeto de la clase para implementar el patron singleton
     */
    private static ProductoService productoServiceInstance;

    /**
     * Atributo productoDao para comunicarnos con la capa de datos
     */
    ProductoDao                    productoDao;

    /**
     * Atributo TipoDao para comunicarnos con la capa de datos
     */
    TipoDao                        tipoDao;

    /**
     * Atributo MarcaDao para comunicarnos con la capa de datos
     */
    MarcaDao                       marcaDao;

    /**
     * Atributo DescuentoDao para comunicarnos con la capa de datos
     */
    DescuentoDao                   descuentoDao;

    /**
     * Constructor sin argumentos privado, para implementar el patron singleton
     */
    private ProductoServiceImpl() {
    }

    /**
     * Metodo para crear una nueva y unica instancia si es que no existe
     * 
     * @return productoServiceInstance
     */
    public static ProductoService getInstance() {
        if (productoServiceInstance == null) {
            productoServiceInstance = new ProductoServiceImpl();
        }
        return productoServiceInstance;
    }

    /**
     * Metodo encargado de recuperar una instancia de ProductoDao haciendo uso
     * del patrón Factory
     * 
     * @return ProductoDao
     */
    public ProductoDao getProductoDao() {
        if (this.productoDao != null) {
            return this.productoDao;
        }
        return ProductoDaoFactory.create();
    }

    /**
     * Metodo encargado de recuperar una instancia de TipoDao haciendo uso
     * del patrón Factory
     * 
     * @return ProductoDao
     */
    public TipoDao getTipoDao() {
        if (this.tipoDao != null) {
            return this.tipoDao;
        }
        return TipoDaoFactory.create();
    }

    /**
     * Metodo encargado de recuperar una instancia de MarcaDao haciendo uso
     * del patrón Factory
     * 
     * @return ProductoDao
     */
    public MarcaDao getMarcaDao() {
        if (this.marcaDao != null) {
            return this.marcaDao;
        }
        return MarcaDaoFactory.create();
    }

    /**
     * Metodo encargado de recuperar una instancia de DescuentoDao haciendo uso
     * del patrón Factory
     * 
     * @return ProductoDao
     */
    public DescuentoDao getDescuentoDao() {
        if (this.descuentoDao != null) {
            return this.descuentoDao;
        }
        return DescuentoDaoFactory.create();
    }

    /**
     * Metodo que retorna un Producto de acuerdo al id
     * 
     * @param idProducto
     * @return Producto
     */
    @Override
    public Producto getProductoById(Integer idProducto) {
        this.productoDao = getProductoDao();
        try {
            return this.productoDao.findByPrimaryKey(idProducto);
        }
        catch (ProductoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Producto con id: "
                            + idProducto, ex);
        }
    }

    /**
     * Metodo que retorna todos los Productos creados en sistema
     * 
     * @return List<Producto>
     */
    @Override
    public List<ProductoBean> getAllProductos() {
        try {
            this.productoDao = getProductoDao();
            return this.productoDao.findAllBeans();
        }
        catch (ProductoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el arreglo de Productos en la BD",
                    ex);
        }
    }

    /**
     * Metodo que retorna los Productos de acuerdo al nombre completo
     * 
     * @param nombre
     * @return List<Producto>
     */
    @Override
    public Producto getProductoByFullName(String nombre) {
        this.productoDao = getProductoDao();
        try {
            return this.productoDao.findByFullName(nombre);
        }
        catch (ProductoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Producto con nombre: "
                            + nombre, ex);
        }
    }

    /**
     * Metodo que retorna una lista de Productos de acuerdo a conicidencia de
     * nombre
     * 
     * @param nombre
     * @return Producto
     */
    @Override
    public List<ProductoBean> getProductosByName(String nombre) {
        this.productoDao = getProductoDao();
        try {
            return this.productoDao.findByName(nombre);
        }
        catch (ProductoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener los Productos con nombre: "
                            + nombre, ex);
        }
    }
    
    /**
     * Metodo que retorna una lista de Productos de acuerdo al Tipo de Producto
     * 
     * @param tipo
     * @return List<Producto>
     */
    @Override
    public List<ProductoBean> getProductosByTipo(Integer tipo) {
        this.productoDao = getProductoDao();
        try {
            return this.productoDao.findByTipo(tipo);
        }
        catch (ProductoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener los Productos con Tipo: "
                            + tipo, ex);
        }
    }

    /**
     * Metodo que retorna una lista de Productos de acuerdo a la Marca
     * 
     * @param marca
     * @return List<Producto>
     */
    @Override
    public List<ProductoBean> getProductosByMarca(Integer marca) {
        this.productoDao = getProductoDao();
        try {
            return this.productoDao.findByMarca(marca);
        }
        catch (ProductoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener los Productos con Marca: "
                            + marca, ex);
        }
    }

    /**
     * Metodo que elimina un grupo de Productos del sistema de acuerdo al
     * paramtero idProductos
     * 
     * @param idProductos
     * @return boolean
     */
    @SuppressWarnings("null")
    @Override
    public boolean eliminarProductos(List<Integer> idProductos) {
        Connection conn = null;
        this.productoDao = getProductoDao();
        try {
            // Comenzamos la transaccion, si algun elemento no se elimina
            // entonces ninguno se elmina
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.productoDao.setUserConn(conn);
            // Eliminamos registro a registro
            for (Integer producto : idProductos) {
                this.productoDao.delete(new ProductoPk(producto));
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (ProductoDaoException ex) {
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
                            + idProductos, ex);
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
            // Cerramos la conexion para regresala al pool
            ResourceManager.close(conn);
        }
    }

    /**
     * Metodo que guarda un Producto en el sistema
     * 
     * @param Producto
     * @return boolean
     */
    @Override
    public boolean guardarProducto(Producto producto) {
        Connection conn = null;
        this.productoDao = getProductoDao();
        // Comenzamos la transaccion
        try {
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.productoDao.setUserConn(conn);
            // Revisamos si es un insert o un update, dependiendo si se tiene o
            // no el valor de la PK
            if (producto.getIdProducto() == null) {
                this.productoDao.insert(producto);
            }
            else {
                this.productoDao.update(producto.createPk(), producto);
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se guardaron los registros
            return true;
        }
        catch (ProductoDaoException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException("No se puedo agregar el Producto:"
                    + producto.toString() + " a la BD", ex);
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

    /**
     * Metodo que retorna todos los tipos creados en sistema
     * 
     * @return List<Tipo>
     */
    @Override
    public List<Tipo> getAllTipos() {
        try {
            this.tipoDao = getTipoDao();
            return Arrays.asList(this.tipoDao.findAll());
        }
        catch (TipoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el arreglo de Tipos en la BD",
                    ex);
        }
    }

    /**
     * Metodo que retorna un Tipo de acuerdo al id
     * 
     * @param idTipo
     * @return Tipo
     */
    @Override
    public Tipo getTipoById(Integer idTipo) {
        this.tipoDao = getTipoDao();
        try {
            return this.tipoDao.findByPrimaryKey(idTipo);
        }
        catch (TipoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Tipo con id: "
                            + idTipo, ex);
        }
    }

    /**
     * Metodo que retorna los Tipos de acuerdo al nombre
     * 
     * @param nombre
     * @return List<Tipo>
     */
    @Override
    public List<Tipo> getTiposByName(String nombre) {
        this.tipoDao = getTipoDao();
        try {
            return Arrays.asList(this.tipoDao.findByName(nombre));
        }
        catch (TipoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener los Tipos con nombre: "
                            + nombre, ex);
        }
    }

    /**
     * Metodo que retorna los Tipos de acuerdo al nombre completo
     * 
     * @param nombre
     * @return Tipo
     */
    @Override
    public Tipo getTipoByFullName(String nombre) {
        this.tipoDao = getTipoDao();
        try {
            return this.tipoDao.findByFullName(nombre);
        }
        catch (TipoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Tipo con Nombre: "
                            + nombre, ex);
        }
    }

    /**
     * Metodo que elimina un grupo de Tipos del sistema de acuerdo al paramtero
     * idTipos
     * 
     * @param idTipos
     * @return boolean
     */
    @SuppressWarnings("null")
    @Override
    public boolean eliminarTipos(List<Integer> idTipos) {
        Connection conn = null;
        this.tipoDao = getTipoDao();
        try {
            // Comenzamos la transaccion, si algun elemento no se elimina
            // entonces ninguno se elmina
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.tipoDao.setUserConn(conn);
            // Eliminamos registro a registro
            for (Integer tipo : idTipos) {
                this.tipoDao.delete(new TipoPk(tipo));
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (TipoDaoException ex) {
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
                            + idTipos, ex);
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
            // Cerramos la conexion para regresala al pool
            ResourceManager.close(conn);
        }
    }

    /**
     * Metodo que guarda un Tipo en el sistema
     * 
     * @param Tipo
     * @return boolean
     */
    @Override
    public boolean guardarTipo(Tipo tipo) {
        Connection conn = null;
        this.tipoDao = getTipoDao();
        // Comenzamos la transaccion
        try {
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.tipoDao.setUserConn(conn);
            // Revisamos si es un insert o un update, dependiendo si se tiene o
            // no el valor de la PK
            if (tipo.getIdTipo() == null) {
                this.tipoDao.insert(tipo);
            }
            else {
                this.tipoDao.update(tipo.createPk(), tipo);
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (TipoDaoException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException("No se puedo agregar el Tipo:"
                    + tipo.toString() + " a la BD", ex);
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

    /**
     * Metodo que retorna todas las marcas creadas en sistema
     * 
     * @return List<Marca>
     */
    @Override
    public List<Marca> getAllMarcas() {
        try {
            this.marcaDao = getMarcaDao();
            return Arrays.asList(this.marcaDao.findAll());
        }
        catch (MarcaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el arreglo de Marcas en la BD",
                    ex);
        }
    }

    /**
     * Metodo que retorna una Marca de acuerdo al id
     * 
     * @param idMarca
     * @return Marca
     */
    @Override
    public Marca getMarcaById(Integer idMarca) {
        this.marcaDao = getMarcaDao();
        try {
            return this.marcaDao.findByPrimaryKey(idMarca);
        }
        catch (MarcaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Marca con id: "
                            + idMarca, ex);
        }
    }

    /**
     * Metodo que retorna las Marcas de acuerdo al nombre
     * 
     * @param nombre
     * @return List<Marca>
     */
    @Override
    public List<Marca> getMarcasByName(String nombre) {
        this.marcaDao = getMarcaDao();
        try {
            return Arrays.asList(this.marcaDao.findByName(nombre));
        }
        catch (MarcaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener los Marcas con nombre: "
                            + nombre, ex);
        }
    }

    /**
     * Metodo que retorna las Marcas de acuerdo al nombre completo
     * 
     * @param nombre
     * @return Marca
     */
    @Override
    public Marca getMarcaByFullName(String nombre) {
        this.marcaDao = getMarcaDao();
        try {
            return this.marcaDao.findByFullName(nombre);
        }
        catch (MarcaDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Marca con id: "
                            + nombre, ex);
        }
    }

    /**
     * Metodo que elimina un grupo de Marcas del sistema de acuerdo al paramtero
     * idMarcas
     * 
     * @param idMarcas
     * @return boolean
     */
    @SuppressWarnings("null")
    @Override
    public boolean eliminarMarcas(List<Integer> idMarcas) {
        Connection conn = null;
        this.marcaDao = getMarcaDao();
        try {
            // Comenzamos la transaccion, si algun elemento no se elimina
            // entonces ninguno se elmina
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.marcaDao.setUserConn(conn);
            // Eliminamos registro a registro
            for (Integer marca : idMarcas) {
                this.marcaDao.delete(new MarcaPk(marca));
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (MarcaDaoException ex) {
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
                            + idMarcas, ex);
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
            // Cerramos la conexion para regresala al pool
            ResourceManager.close(conn);
        }
    }

    /**
     * Metodo que guarda una Marca en el sistema
     * 
     * @param Marca
     * @return boolean
     */
    @Override
    public boolean guardarMarca(Marca marca) {
        Connection conn = null;
        this.marcaDao = getMarcaDao();
        // Comenzamos la transaccion
        try {
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.marcaDao.setUserConn(conn);
            // Revisamos si es un insert o un update, dependiendo si se tiene o
            // no el valor de la PK
            if (marca.getIdMarca() == null) {
                this.marcaDao.insert(marca);
            }
            else {
                this.marcaDao.update(marca.createPk(), marca);
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (MarcaDaoException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException("No se puedo agregar el Marca:"
                    + marca.toString() + " a la BD", ex);
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

    /**
     * Metodo que retorna todos los descuentos creados en sistema
     * 
     * @return List<Descuento>
     */
    @Override
    public List<Descuento> getAllDescuentos() {
        try {
            this.descuentoDao = getDescuentoDao();
            return Arrays.asList(this.descuentoDao.findAll());
        }
        catch (DescuentoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el arreglo de Descuentos en la BD",
                    ex);
        }
    }

    /**
     * Metodo que retorna un Descuento de acuerdo al id
     * 
     * @param idDescuento
     * @return Descuento
     */
    @Override
    public Descuento getDescuentoById(Integer idDescuento) {
        this.descuentoDao = getDescuentoDao();
        try {
            return this.descuentoDao.findByPrimaryKey(idDescuento);
        }
        catch (DescuentoDaoException ex) {
            throw new BusinessException(
                    "Existe un problema al obtener el Descuento con id: "
                            + idDescuento, ex);
        }
    }

    /**
     * Metodo que elimina un grupo de Descuentos del sistema de acuerdo al
     * paramtero idDescuentos
     * 
     * @param idDescuentos
     * @return boolean
     */
    @SuppressWarnings("null")
    @Override
    public boolean eliminarDescuentos(List<Integer> idDescuentos) {
        Connection conn = null;
        this.descuentoDao = getDescuentoDao();
        try {
            // Comenzamos la transaccion, si algun elemento no se elimina
            // entonces ninguno se elmina
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.descuentoDao.setUserConn(conn);
            // Eliminamos registro a registro
            for (Integer descuento : idDescuentos) {
                this.descuentoDao.delete(new DescuentoPk(descuento));
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (DescuentoDaoException ex) {
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
                            + idDescuentos, ex);
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
            // Cerramos la conexion para regresala al pool
            ResourceManager.close(conn);
        }
    }

    /**
     * Metodo que guarda un Descuento en el sistema
     * 
     * @param Descuento
     * @return boolean
     */
    @Override
    public boolean guardarDescuento(Descuento descuento) {
        Connection conn = null;
        this.descuentoDao = getDescuentoDao();
        // Comenzamos la transaccion
        try {
            // Obtenemos una conexion del pool
            conn = ResourceManager.getConnection();
            // Activamos el manejo transaccional
            conn.setAutoCommit(false);
            this.descuentoDao.setUserConn(conn);
            // Revisamos si es un insert o un update, dependiendo si se tiene o
            // no el valor de la PK
            if (descuento.getIdDcto() == null) {
                this.descuentoDao.insert(descuento);
            }
            else {
                this.descuentoDao.update(descuento.createPk(), descuento);
            }
            // Guardamos los cambios en la BD
            conn.commit();
            // Regresamos la bandera indicando que se eliminaron los registros
            return true;
        }
        catch (DescuentoDaoException ex) {
            try {
                conn.rollback();
            }
            catch (SQLException ex1) {
                throw new BusinessException(
                        "No se pudo reestablecer el estado de la Base de Datos",
                        ex1);
            }
            throw new BusinessException("No se puedo agregar el Descuento:"
                    + descuento.toString() + " a la BD", ex);
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
