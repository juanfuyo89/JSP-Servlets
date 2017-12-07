package junit.sgp.eis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dao.ProductosPuntoVentaDao;
import sga.eis.dto.ProductosPuntoVenta;
import sga.eis.dto.ProductosPuntoVentaPk;
import sga.eis.exceptions.ProductosPuntoVentaDaoException;
import sga.eis.factory.ProductosPuntoVentaDaoFactory;

/**
 * Clase de prueba del DAO tipo ProductosPuntoProductosPuntoVenta
 * 
 * @author Juan Carlos Fuyo
 */
public class ProductosPuntoVentaTest {

    /**
     * Atributo usuarioDao para comunicarnos con la capa de datos
     */
    ProductosPuntoVentaDao productosPuntoVentaDao;

    @Before
    public void setUp() {
        this.productosPuntoVentaDao = ProductosPuntoVentaDaoFactory.create();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }

    @Test
    public void insertarProductosPuntoVenta() {
        ProductosPuntoVenta productosPuntoVentaDto = new ProductosPuntoVenta();
        productosPuntoVentaDto.setIdPuntoVenta(3);
        productosPuntoVentaDto.setIdProducto(1);
        productosPuntoVentaDto.setCantidad(40);
        try {
            ProductosPuntoVentaPk productosPuntoVentaPk = this.productosPuntoVentaDao.insert(productosPuntoVentaDto);
                System.out.println(productosPuntoVentaPk.toString());
                Assert.assertTrue(true);
            System.out.println("---------");
        }
        catch (ProductosPuntoVentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarProductosPuntoVentas() {
        try {
            ProductosPuntoVenta[] productosPuntoVentas = this.productosPuntoVentaDao.findAll();
            for (ProductosPuntoVenta productosPuntoVenta : productosPuntoVentas) {
                System.out.println(productosPuntoVenta.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductosPuntoVentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarProductosPuntoVentaById() {
        try {
            ProductosPuntoVenta productosPuntoVenta = this.productosPuntoVentaDao.findByPrimaryKey(
                    1, 1);
            System.out.println(productosPuntoVenta.toString());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductosPuntoVentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarProductosPuntoVentaByPersona() {
        try {
            ProductosPuntoVenta[] productosPuntoVentas = this.productosPuntoVentaDao.findByPuntoVenta(1);
            for (ProductosPuntoVenta productosPuntoVenta : productosPuntoVentas) {
                System.out.println(productosPuntoVenta.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductosPuntoVentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarProductosPuntoVentaByProducto() {
        try {
            ProductosPuntoVenta[] productosPuntoVentas = this.productosPuntoVentaDao.findByProducto(1);
            for (ProductosPuntoVenta productosPuntoVenta : productosPuntoVentas) {
                System.out.println(productosPuntoVenta.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductosPuntoVentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void modificarProductosPuntoVenta() {
        ProductosPuntoVenta productosPuntoVentaDto = new ProductosPuntoVenta();
        productosPuntoVentaDto.setIdPuntoVenta(1);
        productosPuntoVentaDto.setIdProducto(1);
        productosPuntoVentaDto.setCantidad(45);
        try {
            this.productosPuntoVentaDao.update(
                    productosPuntoVentaDto.createPk(), productosPuntoVentaDto);
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductosPuntoVentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarProductosPuntoVenta() {
        ProductosPuntoVenta productosPuntoVentaDto = new ProductosPuntoVenta();
        productosPuntoVentaDto.setIdPuntoVenta(3);
        productosPuntoVentaDto.setIdProducto(1);
        try {
            this.productosPuntoVentaDao.delete(productosPuntoVentaDto.createPk());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductosPuntoVentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

}
