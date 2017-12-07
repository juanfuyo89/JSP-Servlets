package junit.sgp.eis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dao.MarcaDao;
import sga.eis.dto.Marca;
import sga.eis.dto.MarcaPk;
import sga.eis.exceptions.MarcaDaoException;
import sga.eis.factory.MarcaDaoFactory;

/**
 * Clase de prueba del DAO tipo Marca
 * 
 * @author Juan Carlos Fuyo
 */
public class MarcaTest {

    /**
     * Atributo usuarioDao para comunicarnos con la capa de datos
     */
    MarcaDao marcaDao;
    
    @Before
    public void setUp() {
        this.marcaDao = MarcaDaoFactory.create();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }
    
    @Test
    public void insertarMarca() {
        Marca marcaDto = new Marca();
        marcaDto.setNombre("PRUEBA_DOS");
        marcaDto.setEsNacional(0);
        try {
            MarcaPk MarcaPk = this.marcaDao.insert(marcaDto);
            if (MarcaPk.getIdMarca() != null) {
                Assert.assertTrue(true);
            }else{
                Assert.assertTrue(false);
            }
        }
        catch (MarcaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarmarcas() {
        try {
            Marca[] marcas =  this.marcaDao.findAll();
            for (Marca marca : marcas) {
                System.out.println(marca.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (MarcaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void consultarMarcaById(){
        try {
            Marca marca = this.marcaDao.findByPrimaryKey(1);
            System.out.println(marca.toString());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (MarcaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void consultarMarcaByName(){
        try {
            Marca[] marcas = this.marcaDao.findByName("PRUEBA");
            for (Marca marca : marcas) {
                System.out.println(marca.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (MarcaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void modificarMarca(){
        Marca marcaDto = new Marca();
        marcaDto.setIdMarca(1);
        marcaDto.setNombre("PRUEBA_UNO");
        marcaDto.setEsNacional(1);
        try {
            this.marcaDao.update(marcaDto.createPk(), marcaDto);
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (MarcaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void eliminarMarca(){
        Marca marcaDto = new Marca();
        marcaDto.setIdMarca(2);
        try {
            this.marcaDao.delete(marcaDto.createPk());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (MarcaDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
}
