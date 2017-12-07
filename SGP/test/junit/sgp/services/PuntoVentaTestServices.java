package junit.sgp.services;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dto.ProductosPuntoVenta;
import sga.eis.dto.PuntoVenta;
import sga.services.PuntoVentaService;
import sga.services.impl.PuntoVentaServiceImpl;

/**
 * Clase de prueba de tipo PuntoVenta de la capa de servicios
 * 
 * @author Juan Carlos Fuyo
 */
public class PuntoVentaTestServices {

    /**
     * Atributo usuarioService para comunicarnos con la capa de servicios
     */
    PuntoVentaService puntoVentaService;

    @Before
    public void setUp() {
        this.puntoVentaService = PuntoVentaServiceImpl.getInstance();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }

    @Test
    public void consultarPuntosVenta() {
        List<PuntoVenta> puntosVenta = this.puntoVentaService.getAllPuntosVenta();
        if (!puntosVenta.isEmpty()) {
            for (PuntoVenta puntoVenta : puntosVenta) {
                System.out.println(puntoVenta.toString());
            }
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarPuntoVentaById() {
        PuntoVenta puntoVenta = this.puntoVentaService.getPuntoVentaById(1);
        if (puntoVenta != null) {
            System.out.println(puntoVenta.toString());
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarPuntoVentaByName() {
        List<PuntoVenta> puntosVenta = this.puntoVentaService.getPuntoVentaByName("");
        if (!puntosVenta.isEmpty()) {
            for (PuntoVenta puntoVenta : puntosVenta) {
                System.out.println(puntoVenta.toString());
            }
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarPuntoVentaByUser() {
        PuntoVenta puntoVenta = this.puntoVentaService.getPuntoVentaByUser("");
        if (puntoVenta != null) {
            System.out.println(puntoVenta.toString());
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void eliminarPuntoVenta() {
        List<Integer> puntosVenta = new ArrayList<Integer>();
        puntosVenta.add(1);
        Assert.assertTrue(this.puntoVentaService.eliminarPuntosVenta(puntosVenta));
    }

    @Test
    public void guardarPuntoVenta() {
        // Si enviamos el id del punto de Venta hace un Update, si no hace un
        // Insert
        PuntoVenta puntoVenta = new PuntoVenta();
        // puntoVenta.setIdPuntoVenta(idPuntoVenta);
        puntoVenta.setNombre("");
        puntoVenta.setDireccion("");
        puntoVenta.setTelefono("");
        puntoVenta.setIdAdmin("");
        Assert.assertTrue(this.puntoVentaService.guardarPuntoVenta(puntoVenta));
    }

    @Test
    public void consultarProductosPuntoVenta() {
        List<ProductosPuntoVenta> productosPuntoVenta = this.puntoVentaService.getAllProductosPuntoVenta();
        if (!productosPuntoVenta.isEmpty()) {
            for (ProductosPuntoVenta productoPuntoVenta : productosPuntoVenta) {
                System.out.println(productoPuntoVenta.toString());
            }
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarProductosPuntoVentaById() {
        ProductosPuntoVenta productosPuntoVenta = this.puntoVentaService.getProductoPuntoVentaById(
                1, 1);
        if (productosPuntoVenta != null) {
            System.out.println(productosPuntoVenta.toString());
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarProductosPuntoVentaByPDV() {
        List<ProductosPuntoVenta> productosPuntoVenta = this.puntoVentaService.getProductoPuntoVentaByPuntoVenta(1);
        if (!productosPuntoVenta.isEmpty()) {
            for (ProductosPuntoVenta productoPuntoVenta : productosPuntoVenta) {
                System.out.println(productoPuntoVenta.toString());
            }
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarProductosPuntoVentaByProducto() {
        List<ProductosPuntoVenta> productosPuntoVenta = this.puntoVentaService.getProductoPuntoVentaByProducto(1);
        if (!productosPuntoVenta.isEmpty()) {
            for (ProductosPuntoVenta productoPuntoVenta : productosPuntoVenta) {
                System.out.println(productoPuntoVenta.toString());
            }
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void eliminarProductosPuntoVenta() {
        List<Integer> puntosVenta = new ArrayList<Integer>();
        List<Integer> productos = new ArrayList<Integer>();
        puntosVenta.add(1);
        productos.add(1);
        Assert.assertTrue(this.puntoVentaService.eliminarProductosPuntoVenta(
                puntosVenta, productos));
    }

    @Test
    public void guardarProductosPuntoVenta() {
        // Si la Primary Key existe (idPuntoVenta + idProducto) se realiza un
        // Update, si no hace un Insert
        ProductosPuntoVenta productosPuntoVenta = new ProductosPuntoVenta();
        // puntoVenta.setIdPuntoVenta(idPuntoVenta);
        productosPuntoVenta.setIdPuntoVenta(1);
        productosPuntoVenta.setIdProducto(1);
        productosPuntoVenta.setCantidad(30);
        Assert.assertTrue(this.puntoVentaService.guardarProductoPuntoVenta(productosPuntoVenta));
    }

}
