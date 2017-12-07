package junit.sgp.eis;

import java.util.Date;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dao.DescuentoDao;
import sga.eis.dto.Descuento;
import sga.eis.dto.DescuentoPk;
import sga.eis.exceptions.DescuentoDaoException;
import sga.eis.factory.DescuentoDaoFactory;

/**
 * Clase de prueba del DAO tipo Descuento
 * 
 * @author Juan Carlos Fuyo
 */
public class DescuentoTest {

    /**
     * Atributo usuarioDao para comunicarnos con la capa de datos
     */
    DescuentoDao descuentoDao;
    
    @Before
    public void setUp() {
        this.descuentoDao = DescuentoDaoFactory.create();
    }

    @After
    public void tearDown() {
        // Tareas a realizar después de cada test
    }
    
    @Test
    public void insertarDescuento() {
        Descuento descuentoDto = new Descuento();
        descuentoDto.setDescripcion("PRUEBA_DOS");
        descuentoDto.setFechInicial(new Date(0));
        descuentoDto.setFechFinal(new Date(0));
        descuentoDto.setPorcentaje(20);
        try {
            DescuentoPk DescuentoPk = this.descuentoDao.insert(descuentoDto);
            if (DescuentoPk.getIdDcto() != null) {
                Assert.assertTrue(true);
            }else{
                Assert.assertTrue(false);
            }
        }
        catch (DescuentoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }

    @Test
    public void consultarDescuentos() {
        try {
            Descuento[] descuentos =  this.descuentoDao.findAll();
            for (Descuento descuento : descuentos) {
                System.out.println(descuento.toString());
            }
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (DescuentoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void consultarDescuentoById(){
        try {
            Descuento descuento = this.descuentoDao.findByPrimaryKey(1);
            System.out.println(descuento.toString());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (DescuentoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void modificarDescuento(){
        Descuento descuentoDto = new Descuento();
        descuentoDto.setIdDcto(1);
        descuentoDto.setDescripcion("PRUEBA_UNO");
        descuentoDto.setFechInicial(new Date(0));
        descuentoDto.setFechFinal(new Date(0));
        descuentoDto.setPorcentaje(35);
        try {
            this.descuentoDao.update(descuentoDto.createPk(), descuentoDto);
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (DescuentoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void eliminarDescuento(){
        Descuento descuentoDto = new Descuento();
        descuentoDto.setIdDcto(2);
        try {
            this.descuentoDao.delete(descuentoDto.createPk());
            System.out.println("---------");
            Assert.assertTrue(true);
        }
        catch (DescuentoDaoException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }
    }
    
}
