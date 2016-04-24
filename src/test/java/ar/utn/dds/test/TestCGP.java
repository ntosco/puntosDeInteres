package ar.utn.dds.test;

import static org.junit.Assert.*;

import org.junit.*;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;

public class TestCGP extends JuegoDeDatos {

	
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpCGP();
		
	}
	
	@Test
	public void estoyCercaDeUnCGPCercano(){
		assertFalse(cgpAlmagro.estaCercaDe(puntoTerminal)); // hacer que de true
	}
	
	@Test 
	public void estoyLejosDeUnCGPLejano(){
		assertFalse(cgpCaballito.estaCercaDe(puntoTerminal));
		
	}
	
	@Test
	public void testContieneServicioDelTextoLibre(){
		assertTrue(cgpAlmagro.cumpleCondicionBusqueda("CP"));
	}
	
	@Test
	public void contieneServicio(){
		assertTrue(cgpAlmagro.contieneServicio("rentas"));
	}
	
	@Test
	public void testContieneServicioDelTextoLibre2(){
		assertTrue(cgpPaternal.cumpleCondicionBusqueda("rentas"));
	}
	
	@Test
	public void testNoContieneServicioDelTextoLibre(){
		assertFalse(cgpCaballito.cumpleCondicionBusqueda("Rentas"));
	}
	
	// el horario ingresado se encuentra en el servicio seleccionado
	@Test
	public void estoyDisponibleconServicioHorarioIN(){
		assertFalse(cgpAlmagro.estaDisponible("CP", lunes1210hs));
	}	
	
	// el horario ingresado no se encuentra en el servicio seleccionado
	@Test
	public void estoyDisponibleconServicioHorarioOUT(){
		assertFalse(cgpAlmagro.estaDisponible("CP", lunes23hs));
	}
	
	// el nombre del Servicio no existe
	@Test
	public void estoyDisponibleconServicioInexisitenteHorarioIN(){
		assertFalse(cgpAlmagro.estaDisponible("NombreInexistente", lunes1210hs));
	}
	
	// No se ingresa nombre del servicio ,pero horario es del servicio CP
	@Test
	public void estoyDisponibleSinServicioHorarioIN(){
		assertFalse(cgpAlmagro.estaDisponible("asdasdasd",lunes1210hs));
	}
	
	// No se ingresa nombre del servicio ,el horario no es de ningun servicio
	@Test
	public void estoyDisponibleSinServicioHorarioOUT(){
		assertFalse(cgpAlmagro.estaDisponible("almagro",sabado23hs));
	}
	
}
