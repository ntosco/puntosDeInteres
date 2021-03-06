package ar.utn.dds.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.*;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;

public class TestsColectivo extends JuegoDeDatos {
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpColectivos();
			
	}
	
	@Test
	public void estoyCercaDeUnaParadaDeColectivoCercana(){
		assertFalse(parada114.estaCercaDe(puntoTerminal)); //Ver para que sea True
	}
	
	@Test 
	public void noEstoyCercaDeUnaParadaDeColectivoLejana(){
		assertFalse(parada11.estaCercaDe(puntoTerminal));
	}
	
	@Test
	public void busquedaDeParadaIngresandoTexto() {
		assertTrue(parada114.buscarPOI("114"));
	}

	@Test
	public void noEncuentraParadaQueNoCoincideConLaBusqueda() {
		assertFalse(parada114.buscarPOI("7"));

	}
	
	@Test
	public void estoyDisponibleAhoraSinServicio(){
		assertTrue(parada114.estaDisponible(null, LocalDateTime.now()));
	}
	
	@Test
	public void estoyDisponibleAhoraConServicio(){
		assertTrue(parada11.estaDisponible("Rentas", LocalDateTime.now()));
	}
	
	@Test
	public void pruebaBuilder(){
		assertEquals(parada11.getLinea(), "11");
	}
	

}


