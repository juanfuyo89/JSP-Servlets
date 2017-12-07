package junit.sgp.eis;

import java.util.Date;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dao.VentaDao;
import sga.eis.dto.Venta;
import sga.eis.dto.VentaPk;
import sga.eis.exceptions.VentaDaoException;
import sga.eis.factory.VentaDaoFactory;

/**
 * Clase de prueba del DAO tipo Venta
 * 
 * @author Juan Carlos Fuyo
 */
public class VentaTest {

    /**
     * Atributo usuarioDao para comunicarnos con la capa de datos
     */
    VentaDao ventaDao;
    
    @Before
    public void setUp() {
        this.ventaDao = VentaDaoFactory.create();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }
    
    @Test
    public void insertarVenta() {
        Venta ventaDto = new Venta();
        ventaDto.setIdPersona("1033718747");
        ventaDto.setIdProducto(1);
        ventaDto.setFecVenta(new Date(0));
        ventaDto.setPrecioVenta(10000.00);
        ventaDto.setCantidad(5);
        try {
            VentaPk VentaPk = this.ventaDao.insert(ventaDto);
            if (VentaPk.getIdVenta() != null) {
                System.out.println(VentaPk.toString());
                Assert.assertTrue(true);
            }else{
                Assert.assertTrue(false);
            }
        }
        catch (VentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarVentas() {
        try {
            Venta[] ventas =  this.ventaDao.findAll();
            for (Venta venta : ventas) {
                System.out.println(venta.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (VentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void consultarVentaById(){
        try {
            Venta venta = this.ventaDao.findByPrimaryKey(1);
            System.out.println(venta.toString());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (VentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarVentaByPersona(){
        try {
            Venta[] ventas = this.ventaDao.findByPersona("1033718747");
            for (Venta venta : ventas) {
                System.out.println(venta.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (VentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarVentaByProducto(){
        try {
            Venta[] ventas = this.ventaDao.findByProducto(1);
            for (Venta venta : ventas) {
                System.out.println(venta.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (VentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void modificarVenta(){
        Venta ventaDto = new Venta();
        ventaDto.setIdVenta(1);
        ventaDto.setIdPersona("1033718747");
        ventaDto.setIdProducto(1);
        ventaDto.setFecVenta(new Date(0));
        ventaDto.setPrecioVenta(45000.00);
        ventaDto.setCantidad(10);
        try {
            this.ventaDao.update(ventaDto.createPk(), ventaDto);
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (VentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void eliminarVenta(){
        Venta ventaDto = new Venta();
        ventaDto.setIdVenta(4);
        try {
            this.ventaDao.delete(ventaDto.createPk());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (VentaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
}
