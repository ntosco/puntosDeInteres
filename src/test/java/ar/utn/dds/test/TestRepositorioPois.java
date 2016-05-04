package ar.utn.dds.test;

import static org.junit.Assert.*;

import java.nio.channels.ShutdownChannelGroupException;

import org.junit.*;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.POI.POI;
import ar.utn.dds.exceptions.BusinessException;

public class TestRepositorioPois extends JuegoDeDatos {

	Repositorio repositorio = Repositorio.getInstance();
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		setUpCGP();
		setUpLocalComercial();
		setUpColectivos();
	}

	@After
	public void TearDown(){
		repositorio.clean();
	}
	
	// ********************************************************
	// ** Tests: Cración de POIs dentro del repositorio
	// ********************************************************
	
	@Test
	public void creaPoiCuandoEsteEsValido(){
		int size = repositorio.allInstances().size();
		repositorio.create(this.parada15);
		assertEquals(repositorio.allInstances().size(),++size);
	}

	@Test(expected = BusinessException.class)
	public void noCreaPOICuandoEsteEsInvalido(){
		int size = repositorio.allInstances().size();
		repositorio.create(this.cafeMartinez);
		assertEquals(repositorio.allInstances().size(),++size);
	}
	
	
	@Test(expected = BusinessException.class)
	public void createPoiExistenteEnListaTest(){
		repositorio.create(this.parada15);
		repositorio.create(this.parada15);
	}

	@Test
	public void creoUnPoiYVerificoQueAndeElID(){
		repositorio.create(this.parada15);
		int id = parada15.getId();
		int idPoiAgregado = repositorio.allInstances().get(0).getId();
		assertEquals(id,idPoiAgregado);
	}

	
	// ********************************************************
	// ** Tests: Busqueda de POIs dentro del repositorio
	// ********************************************************
		
	@Test
	public void buscoUnPOIPorID(){
		repositorio.create(this.parada114);
		repositorio.create(this.parada15);
		assertTrue(parada15.esIgualA(repositorio.searchById(2)));
	}

}
