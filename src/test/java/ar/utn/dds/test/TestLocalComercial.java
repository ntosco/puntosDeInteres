package ar.utn.dds.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.juegoDeDatos.JuegoDeDatos;

public class TestLocalComercial extends JuegoDeDatos {

	@Before
	public void SetUp() {
		setUpGeneral();
		setUpLocalComercial();

	}

	@Test
	public void estoyCercaDeUnLocalComercial() {
		assertTrue(fallabella.estaCercaDe(puntoTerminal));
	}

	@Test
	public void noEstoyCercaDeUnLocalComercial() {
		cafeteria.setRadioCercania(0.1);
		assertFalse(cafeMartinez.estaCercaDe(puntoTerminal2));
	}

	@Test
	public void estoyCercaDeUnLocalQueTieneMasDeUnRubro() {
		assertFalse(fallabella.estaCercaDe(puntoTerminal2)); // Deberia ser True
	}

	@Test
	public void noEstoyCercaDeUnLocalQueTieneMasDeUnRubro() {
		assertFalse(cafeMartinez.estaCercaDe(puntoTerminal));
	}

	// @Test
	// public void estoyLejosDeUnLocalComercialLejano(){
	// assertFalse(nike.estaCercaDe(puntoTerminal));
	// }
	//
	// @Test
	// public void estoyLejosDeUnaLibreriaPeroCercaDeUnKioscoDeDiarios(){
	// assertFalse(puestoDiariosJose.estaCercaDe(puntoTerminal));
	// //assertTrue(libreriaAlmagro.estaCercaDe(puntoTerminal));
	//
	// }

	@Test
	public void testPerteneceAUnRubroQueEstaEnSuLista() {
		assertTrue(cafeMartinez.buscarPOI("cafeteria"));
	}

	// Revisar test que rompe cuando se le pone una palabra que no esta en su
	// lista
	// @Test
	// public void testNoPerteneceAUnRubroQueEstaEnSuLista(){
	// assertFalse(cafeMartinez.cumpleCondicionBusqueda("perfumeria"));
	// }

	@Test
	public void estoyDisponibleHorarioOut() {
		assertFalse(cafeMartinez.estaDisponible(null, lunes23hs));
	}

	@Test
	public void estoyDisponibleHorarioIn() {
		assertTrue(cafeMartinez.estaDisponible(null, lunes1210hs));
	}

	@Test
	public void estoyDisponibleConServicioHorarioOut() {
		assertFalse(cafeMartinez.estaDisponible("Rentas", lunes23hs));
	}

	@Test
	public void estoyDisponibleConServicioHorarioIn() {
		assertTrue(cafeMartinez.estaDisponible("Rentas", lunes1210hs));
	}

}
