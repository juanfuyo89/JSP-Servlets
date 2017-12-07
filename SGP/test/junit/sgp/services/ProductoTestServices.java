package junit.sgp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sga.eis.dto.Descuento;
import sga.eis.dto.Marca;
import sga.eis.dto.Producto;
import sga.eis.dto.Tipo;
import sga.services.ProductoService;
import sga.services.impl.ProductoServiceImpl;
import sga.web.model.ProductoBean;

/**
 * Clase de prueba de tipo Producto de la capa de servicios
 * 
 * @author Juan Carlos Fuyo
 */
public class ProductoTestServices {

    /**
     * Atributo usuarioService para comunicarnos con la capa de servicios
     */
    ProductoService productoService;
    
    @Before
    public void setUp() {
        this.productoService = ProductoServiceImpl.getInstance();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }

    @Test
    public void insertarProducto() {
        Producto productoDto = new Producto();
        productoDto.setNombre("Producto Prueba Dos");
        productoDto.setDescripcion("Descripción de Prueba Dos");
        productoDto.setCosto(10000);
        productoDto.setPrecio(15000);
        productoDto.setMarca(1);
        productoDto.setTipo(1);
        productoDto.setDescuento(1);
        Assert.assertTrue(this.productoService.guardarProducto(productoDto));
    }

    @Test
    public void consultarProductos() {
        List<ProductoBean> productos = this.productoService.getAllProductos();
        if(!productos.isEmpty()){
            for(ProductoBean producto : productos){
                System.out.println(producto.getProducto().toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarProductoById() {
        Producto producto = this.productoService.getProductoById(1);
        if(producto != null){
            System.out.println(producto.toString());
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
        System.out.println("---------");
    }

    @Test
    public void consultarProductoByName() {
        List<ProductoBean> productos = this.productoService.getProductosByName("prueba");
        if(!productos.isEmpty()){
            for(ProductoBean producto : productos){
                System.out.println(producto.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
    }

    @Test
    public void modificarProducto() {
        Producto productoDto = new Producto();
        productoDto.setIdProducto(1);
        productoDto.setNombre("Descripción de Prueba Uno");
        productoDto.setDescripcion("");
        productoDto.setCosto(20000);
        productoDto.setPrecio(35000);
        productoDto.setMarca(1);
        productoDto.setTipo(1);
        productoDto.setDescuento(1);
        Assert.assertTrue(this.productoService.guardarProducto(productoDto));
    }

    @Test
    public void eliminarProducto() {
        List<Integer> productos = new ArrayList<Integer>();
        productos.add(1);
        Assert.assertTrue(this.productoService.eliminarProductos(productos));
    }

    @Test
    public void insertarTipo() {
        Tipo tipoDto = new Tipo();
        tipoDto.setNombre("CAMISA HOMBRE");
        Assert.assertTrue(this.productoService.guardarTipo(tipoDto));
    }

    @Test
    public void consultarTipos() {
        List<Tipo> tipos = this.productoService.getAllTipos();
        if(!tipos.isEmpty()){
            for(Tipo tipo : tipos){
                System.out.println(tipo.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarTipoById() {
        Tipo tipo = this.productoService.getTipoById(1);
        if(tipo != null){
            System.out.println(tipo.toString());
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
        System.out.println("---------");
    }

    @Test
    public void consultarTipoByName() {
        List<Tipo> tipos = this.productoService.getTiposByName("BALO");
        if(!tipos.isEmpty()){
            for(Tipo tipo : tipos){
                System.out.println(tipo.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
    }

    @Test
    public void modificarTipo() {
        Tipo tipoDto = new Tipo();
        tipoDto.setIdTipo(1);
        tipoDto.setNombre("PANTALON MUJER");
        Assert.assertTrue(this.productoService.guardarTipo(tipoDto));
    }

    @Test
    public void eliminarTipo() {
        List<Integer> tipos = new ArrayList<Integer>();
        tipos.add(1);
        Assert.assertTrue(this.productoService.eliminarTipos(tipos));
    }

    @Test
    public void insertarMarca() {
        Marca marcaDto = new Marca();
        marcaDto.setNombre("Jeff");
        marcaDto.setEsNacional(0);
        Assert.assertTrue(this.productoService.guardarMarca(marcaDto));
    }

    @Test
    public void consultarMarcas() {
        List<Marca> marcas = this.productoService.getAllMarcas();
        if(!marcas.isEmpty()){
            for(Marca marca : marcas){
                System.out.println(marca.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarMarcaById() {
        Marca marca = this.productoService.getMarcaById(1);
        if(marca != null){
            System.out.println(marca.toString());
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
        System.out.println("---------");
    }

    @Test
    public void consultarMarcaByName() {
        List<Marca> marcas = this.productoService.getMarcasByName("mika");
        if(!marcas.isEmpty()){
            for(Marca marca : marcas){
                System.out.println(marca.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
    }

    @Test
    public void modificarMarca() {
        Marca marcaDto = new Marca();
        marcaDto.setIdMarca(1);
        marcaDto.setNombre("Nike");
        marcaDto.setEsNacional(0);
        Assert.assertTrue(this.productoService.guardarMarca(marcaDto));
    }

    @Test
    public void eliminarMarca() {
        List<Integer> marcas = new ArrayList<Integer>();
        marcas.add(1);
        Assert.assertTrue(this.productoService.eliminarMarcas(marcas));
    }

    @Test
    public void insertarDescuento() {
        Descuento descuentoDto = new Descuento();
        descuentoDto.setDescripcion("Descuento de Prueba Uno");
        descuentoDto.setFechInicial(new Date(0));
        descuentoDto.setFechFinal(new Date(1));
        descuentoDto.setPorcentaje(25);
        Assert.assertTrue(this.productoService.guardarDescuento(descuentoDto));
    }

    @Test
    public void consultarDescuentos() {
        List<Descuento> descuentos = this.productoService.getAllDescuentos();
        if(!descuentos.isEmpty()){
            for(Descuento descuento : descuentos){
                System.out.println(descuento.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
    }

    @Test
    public void consultarDescuentoById() {
        Descuento descuento = this.productoService.getDescuentoById(1);
        if(descuento != null){
            System.out.println(descuento.toString());
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
        }
        System.out.println("---------");
    }

    @Test
    public void modificarDescuento() {
        Descuento descuentoDto = new Descuento();
        descuentoDto.setIdDcto(3);
        descuentoDto.setDescripcion("Descuento de Prueba Dos");
        descuentoDto.setFechInicial(new Date(0));
        descuentoDto.setFechFinal(new Date(1));
        descuentoDto.setPorcentaje(30);
        Assert.assertTrue(this.productoService.guardarDescuento(descuentoDto));
    }

    @Test
    public void eliminarDescuento() {
        List<Integer> descuentos = new ArrayList<Integer>();
        descuentos.add(1);
        Assert.assertTrue(this.productoService.eliminarDescuentos(descuentos));
    }

}
