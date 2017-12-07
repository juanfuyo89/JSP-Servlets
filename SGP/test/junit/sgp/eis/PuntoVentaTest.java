package junit.sgp.eis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sga.eis.dao.PuntoVentaDao;
import sga.eis.dto.PuntoVenta;
import sga.eis.dto.PuntoVentaPk;
import sga.eis.exceptions.PuntoVentaDaoException;
import sga.eis.factory.PuntoVentaDaoFactory;

/**
 * Clase de prueba del DAO tipo PuntoVenta
 * 
 * @author Juan Carlos Fuyo
 */
public class PuntoVentaTest {

	/**
	 * Atributo usuarioDao para comunicarnos con la capa de datos
	 */
	PuntoVentaDao puntoVentaDao;

	@Before
	public void setUp() {
		this.puntoVentaDao = PuntoVentaDaoFactory.create();
	}

	@After
	public void tearDown() {
		// Tareas a realizar después de cada test
	}

	@Test
	public void insertarPuntoVenta() {
		PuntoVenta puntoVentaDto = new PuntoVenta();
		puntoVentaDto.setNombre("PRUEBA_DOS");
		puntoVentaDto.setDireccion("Calle 27 N° 22-08");
		puntoVentaDto.setTelefono("2222222");
		puntoVentaDto.setIdAdmin("1033718747");
		try {
			PuntoVentaPk PuntoVentaPk = this.puntoVentaDao.insert(puntoVentaDto);
			if (PuntoVentaPk.getIdPuntoVenta() != null) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (PuntoVentaDaoException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void consultarPuntosVenta() {
		try {
			PuntoVenta[] puntosVenta = this.puntoVentaDao.findAll();
			for (PuntoVenta puntoVenta : puntosVenta) {
				System.out.println(puntoVenta.toString());
			}
			System.out.println("---------");
			Assert.assertTrue(true);
		} catch (PuntoVentaDaoException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void consultarPuntoVentaById() {
		try {
			PuntoVenta puntoVenta = this.puntoVentaDao.findByPrimaryKey(1);
			System.out.println(puntoVenta.toString());
			System.out.println("---------");
			Assert.assertTrue(true);
		} catch (PuntoVentaDaoException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void consultarPuntoVentaByAdmin() {
		try {
			PuntoVenta puntoVenta = this.puntoVentaDao.findByAdmin("1033718747");
			System.out.println(puntoVenta.toString());
			System.out.println("---------");
			Assert.assertTrue(true);
		} catch (PuntoVentaDaoException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void modificarPuntoVenta() {
		PuntoVenta puntoVentaDto = new PuntoVenta();
		puntoVentaDto.setIdPuntoVenta(1);
		puntoVentaDto.setNombre("PRUEBA_UNO");
		puntoVentaDto.setDireccion("Calle 57 N° 45-12");
		puntoVentaDto.setTelefono("5555555");
		puntoVentaDto.setIdAdmin("1033718747");
		try {
			this.puntoVentaDao.update(puntoVentaDto.createPk(), puntoVentaDto);
			System.out.println("---------");
			Assert.assertTrue(true);
		} catch (PuntoVentaDaoException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void eliminarPuntoVenta() {
		PuntoVenta puntoVentaDto = new PuntoVenta();
		puntoVentaDto.setIdPuntoVenta(2);
		try {
			this.puntoVentaDao.delete(puntoVentaDto.createPk());
			System.out.println("---------");
			Assert.assertTrue(true);
		} catch (PuntoVentaDaoException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

}
