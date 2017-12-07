package junit.sgp.eis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dao.TipoDao;
import sga.eis.dto.Tipo;
import sga.eis.dto.TipoPk;
import sga.eis.exceptions.TipoDaoException;
import sga.eis.factory.TipoDaoFactory;

/**
 * Clase de prueba del DAO tipo Tipo
 * 
 * @author Juan Carlos Fuyo
 */
public class TipoTest {

    /**
     * Atributo usuarioDao para comunicarnos con la capa de datos
     */
    TipoDao tipoDao;
    
    @Before
    public void setUp() {
        this.tipoDao = TipoDaoFactory.create();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }
    
    @Test
    public void insertarTipo() {
        Tipo tipoDto = new Tipo();
        tipoDto.setNombre("PRUEBA_DOS");
        try {
            TipoPk tipoPk = this.tipoDao.insert(tipoDto);
            if (tipoPk.getIdTipo() != null) {
                Assert.assertTrue(true);
            }else{
                Assert.assertTrue(false);
            }
        }
        catch (TipoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void consultarTipos() {
        try {
            Tipo[] tipos =  this.tipoDao.findAll();
            for (Tipo tipo : tipos) {
                System.out.println(tipo.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (TipoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void consultarTipoById(){
        try {
            Tipo tipo = this.tipoDao.findByPrimaryKey(1);
            System.out.println(tipo.toString());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (TipoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void consultarTipoByName(){
        try {
            Tipo[] tipos = this.tipoDao.findByName("PRUEBA");
            for (Tipo tipo : tipos) {
                System.out.println(tipo.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (TipoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void modificarTipo(){
        Tipo tipoDto = new Tipo();
        tipoDto.setIdTipo(1);
        tipoDto.setNombre("PRUEBA_UNO");
        try {tipoDao.update(tipoDto.createPk(), tipoDto);
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (TipoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void eliminarTipo(){
        Tipo tipoDto = new Tipo();
        tipoDto.setIdTipo(2);
        try {
            this.tipoDao.delete(tipoDto.createPk());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (TipoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
}
