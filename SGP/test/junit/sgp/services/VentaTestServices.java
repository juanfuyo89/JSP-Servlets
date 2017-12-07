package junit.sgp.services;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dto.ProductosPuntoVenta;
import sga.eis.dto.Venta;
import sga.services.PuntoVentaService;
import sga.services.VentaService;
import sga.services.impl.PuntoVentaServiceImpl;
import sga.services.impl.VentaServiceImpl;

/**
 * Clase de prueba de tipo Venta de la capa de servicios
 * 
 * @author Juan Carlos Fuyo
 */
public class VentaTestServices {

    /**
     * Atributo usuarioService para comunicarnos con la capa de servicios
     */
    VentaService ventaService;

    @Before
    public void setUp() {
        this.ventaService = VentaServiceImpl.getInstance();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }

    @Test
    public void consultarVentas(){
        List<Venta> ventas = this.ventaService.getAllVentas();
        if (!ventas.isEmpty()) {
            for (Venta venta : ventas) {
                System.out.println(venta.toString());
            }
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarVentaById() {
        Venta venta = this.ventaService.getVentaById(1);
        if (venta != null) {
            System.out.println(venta.toString());
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarVentaByPersona(){
        List<Venta> ventas = this.ventaService.getVentaByPersona("");
        if (!ventas.isEmpty()) {
            for (Venta venta : ventas) {
                System.out.println(venta.toString());
            }
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarVentaByProducto(){
        List<Venta> ventas = this.ventaService.getVentaByProducto(1);
        if (!ventas.isEmpty()) {
            for (Venta venta : ventas) {
                System.out.println(venta.toString());
            }
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarVentaByPuntoVenta(){
        List<Venta> ventas = this.ventaService.getVentaByPuntoVenta(1);
        if (!ventas.isEmpty()) {
            for (Venta venta : ventas) {
                System.out.println(venta.toString());
            }
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void eliminarVenta(){
        Assert.assertTrue(this.ventaService.eliminarVenta(5));
    }

    @Test
    public void guardarVenta(){
        Venta venta = new Venta();
        venta.setIdPuntoVenta(1);
        venta.setIdProducto(1);
        venta.setIdPersona("");
        venta.setFecVenta(new Date());
        venta.setPrecioVenta(1000);
        venta.setCantidad(5);
        PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
        ProductosPuntoVenta productosPuntoVenta = puntoVentaService.getProductoPuntoVentaById(
                venta.getIdPuntoVenta(), venta.getIdProducto());
        Assert.assertTrue(this.ventaService.guardarVenta(venta, productosPuntoVenta.getCantidad()));
    }
}
