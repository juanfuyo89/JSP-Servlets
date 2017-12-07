package junit.sgp.eis;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dao.ProductoDao;
import sga.eis.dto.Producto;
import sga.eis.dto.ProductoPk;
import sga.eis.exceptions.ProductoDaoException;
import sga.eis.factory.ProductoDaoFactory;
import sga.web.model.ProductoBean;

/**
 * Clase de prueba del DAO tipo Producto
 * 
 * @author Juan Carlos Fuyo
 */
public class ProductoTest {

    /**
     * Atributo usuarioDao para comunicarnos con la capa de datos
     */
    ProductoDao productoDao;
    
    @Before
    public void setUp() {
        this.productoDao = ProductoDaoFactory.create();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }
    
    @Test
    public void insertarProducto() {
        Producto productoDto = new Producto();
        productoDto.setNombre("PRUEBA_DOS");
        productoDto.setDescripcion("Prueba Descripcion Dos");
        productoDto.setCosto(25000.0);
        productoDto.setPrecio(35000.0);
        productoDto.setMarca(1);
        productoDto.setTipo(1);
        productoDto.setDescuento(1);
        try {
            ProductoPk ProductoPk = this.productoDao.insert(productoDto);
            if (ProductoPk.getIdProducto() != null) {
                Assert.assertTrue(true);
            }else{
                Assert.assertTrue(false);
            }
        }
        catch (ProductoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarProductos() {
        try {
            List<ProductoBean> productos =  this.productoDao.findAllBeans();
            for (ProductoBean producto : productos) {
                System.out.println(producto.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void consultarProductoById(){
        try {
            Producto producto = this.productoDao.findByPrimaryKey(1);
            System.out.println(producto.toString());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarProductoByName(){
        try {
            List<ProductoBean> productos = this.productoDao.findByName("EBA_UN");
            for (ProductoBean producto : productos) {
                System.out.println(producto.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarProductoByTipo(){
        try {
            List<ProductoBean> productos = this.productoDao.findByTipo(1);
            for (ProductoBean producto : productos) {
                System.out.println(producto.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarProductoByMarca(){
        try {
            List<ProductoBean> productos = this.productoDao.findByMarca(1);
            for (ProductoBean producto : productos) {
                System.out.println(producto.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarProductoByDescuento(){
        try {
            Producto[] productos = this.productoDao.findByDescuento(1);
            for (Producto producto : productos) {
                System.out.println(producto.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
   
    @Test
    public void modificarProducto(){
        Producto productoDto = new Producto();
        productoDto.setIdProducto(1);
        productoDto.setNombre("PRUEBA_UNO");
        productoDto.setDescripcion("Prueba Descripcion");
        productoDto.setCosto(35000.0);
        productoDto.setPrecio(45000.0);
        productoDto.setMarca(1);
        productoDto.setTipo(1);
        productoDto.setDescuento(1);
        try {
            this.productoDao.update(productoDto.createPk(), productoDto);
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void eliminarProducto(){
        Producto productoDto = new Producto();
        productoDto.setIdProducto(2);
        try {
            this.productoDao.delete(productoDto.createPk());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (ProductoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
}
