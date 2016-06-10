package ar.utn.dds.test;

import static org.junit.Assert.*;

import org.junit.*;

import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.creacionales.LocalComercialBuilder;
import ar.utn.dds.exceptions.*;

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

	@Test(expected = InvalidModelException.class)
	public void noCreaPOICuandoEsteEsInvalido(){
		LocalComercialBuilder builderCafeMartinez = new LocalComercialBuilder();
		builderCafeMartinez.crearListaRubros(cafeteriaYComidas);
		builderCafeMartinez.setBarrio("Pompeya").
							setDireccion("Cobo")
							.setNumero(5620)
							.setPalabrasClave(palabrasClaveCafeMartinez);
		cafeMartinez = builderCafeMartinez.build();
	}
	
	
	@Test(expected = RepositoryException.class)
	public void createPoiExistenteEnListaTest(){
		repositorio.create(this.parada15);
		repositorio.create(this.parada15);
	}

	@Test
	public void creoUnPoiYVerificoQueFuncioneCorrectamenteElID(){
		repositorio.create(this.parada15);
		int id = parada15.getId();
		int idPoiAgregado = repositorio.allInstances().get(0).getId();
		assertEquals(id,idPoiAgregado);
	}

	// ********************************************************
	// ** Tests: Borrado de POIs en el repositorio
	// ********************************************************
	
	@Test
	public void eliminoPoiValido(){
		repositorio.create(this.parada15);
		int size = repositorio.allInstances().size();
		repositorio.delete(this.parada15);
		assertEquals(--size,repositorio.allInstances().size());
	}
	
	@Test(expected = RepositoryException.class)
	public void noEliminoCuandoPoiEsInexistente(){
		repositorio.delete(this.parada15);
	}
	
		
	// ********************************************************
	// ** Tests: Busqueda de POIs dentro del repositorio
	// ********************************************************
	
	//*** Por ID
	
	@Test
	public void buscoUnPOIPorID(){
		repositorio.create(this.parada114);
		repositorio.create(this.parada15);
		assertTrue(parada15.esIgualA(repositorio.searchById(2)));
	}

	
	@Test(expected = RuntimeException.class)
	public void buscoUnPOIPorUnIDQueNoExiste(){
		repositorio.searchById(20);
	}

	//*** Por match.
	
	@Test
	public void buscoUnPOIExistente(){
		repositorio.create(this.parada114);
		repositorio.create(this.parada15);
		assertNotEquals(null, repositorio.search("114"));
	}	
	@Test
	public void buscoUnPOIInexistente(){
		repositorio.create(this.parada114);
		repositorio.create(this.parada15);
		assertNotEquals(null, repositorio.search("martinez"));
	}	
	
	// ********************************************************
	// ** Tests: Actualizacion de POIs dentro del repositorio
	// ********************************************************
	
	@Test(expected = BusinessException.class)
	public void quieroActualizarUnPOIInvalidoYLanzaExcepcion(){
		repositorio.create(this.parada114);
		parada114.setId(null);
		repositorio.update(parada114);
	}
	
	@Test(expected = RuntimeException.class)
	public void quieroActualizarUnPOIQueYaFueBorradoYLanzaExcepcion(){
		repositorio.create(this.parada114);
		repositorio.delete(parada114);
		repositorio.update(parada114);
	}
	
	@Test
	public void actualizoUnPOI(){
		repositorio.create(this.parada114);
		parada114.setLinea("1144");
		repositorio.update(parada114);
		
		ParadaDeColectivo poiActualizado = (ParadaDeColectivo) repositorio.searchById(parada114.getId());
		assertEquals("1144",poiActualizado.getLinea());
	}	
	
	
	
	
}
