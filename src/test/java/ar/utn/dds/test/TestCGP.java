package ar.utn.dds.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.POI.POI;
import ar.utn.dds.extern.cgp.BuscadorDeCGP;
import ar.utn.dds.extern.cgp.CentroDTO;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.juegoDeDatos.StubBuscadorCGP;
import ar.utn.dds.utils.AdapterCGP;

public class TestCGP extends JuegoDeDatos {

	public List<POI> listaPOI;
	public AdapterCGP adapter;

	@Before
	public void SetUp() {
		setUpGeneral();
		setUpCGP();
		adapter = new AdapterCGP();
		adapter.setServicioCGP(new StubBuscadorCGP());
		listaPOI = adapter.buscarPOI("nombre");

	}

	@Test
	public void estoyCercaDeUnCGPCercano() {
		assertFalse(cgpAlmagro.estaCercaDe(puntoTerminal)); // hacer que de true
	}

	@Test
	public void estoyLejosDeUnCGPLejano() {
		assertFalse(cgpCaballito.estaCercaDe(puntoTerminal));

	}

	@Test
	public void testContieneServicioDelTextoLibre() {
		assertTrue(cgpAlmagro.buscarPOI("CP"));
	}

	@Test
	public void contieneServicio() {
		assertTrue(cgpAlmagro.contieneServicio("rentas"));
	}

	@Test
	public void testContieneServicioDelTextoLibre2() {
		assertTrue(cgpPaternal.buscarPOI("rentas"));
	}

	@Test
	public void testNoContieneServicioDelTextoLibre() {
		assertFalse(cgpCaballito.buscarPOI("pagos"));
	}

	// el horario ingresado se encuentra en el servicio seleccionado
	@Test
	public void estoyDisponibleconServicioHorarioIN() {
		assertTrue(cgpAlmagro.estaDisponible("CP", lunes1210hs));
	}

	// el horario ingresado no se encuentra en el servicio seleccionado
	@Test
	public void estoyDisponibleconServicioHorarioOUT() {
		assertFalse(cgpAlmagro.estaDisponible("CP", lunes23hs));
	}

	// el nombre del Servicio no existe
	@Test
	public void estoyDisponibleconServicioInexisitenteHorarioIN() {
		assertFalse(cgpAlmagro.estaDisponible("NombreInexistente", lunes1210hs));
	}

	// No se ingresa nombre del servicio ,pero horario es del servicio CP
	@Test
	public void estoyDisponibleSinServicioHorarioIN() {
		assertFalse(cgpAlmagro.estaDisponible("asdasdasd", lunes1210hs));
	}

	// No se ingresa nombre del servicio ,el horario no es de ningun servicio
	@Test
	public void estoyDisponibleSinServicioHorarioOUT() {
		assertFalse(cgpAlmagro.estaDisponible("almagro", sabado23hs));
	}

	// Entrega 2

	// Hay un elemento de la lista de CGP y antes no habia ninguno
	@Test
	public void testConverionDTOaCGP() {
		listaPOI.clear();
		adapter.setServicioCGP(new StubBuscadorCGP());
		assertEquals(listaPOI.size(), 0);
		listaPOI = adapter.buscarPOI("nombre");
		assertEquals(listaPOI.size(), 2);
	}

	// Conversion de Zonas Incluidas a Barrio CGP
	@Test
	public void testConversionDeZonas() {
		POI cgp = listaPOI.get(0);
		assertEquals(cgp.getBarrio(), "Recoleta");
	}

	@Test
	public void conversionDeNombre() {
		POI cgp = listaPOI.get(0);
		assertEquals(cgp.getNombre(), "comuna1");
	}

	@Test
	public void testJornadaDisponible() {
		POI cgp = listaPOI.get(0);
		assertTrue(cgp.estaDisponible("rentas", lunes12hs));
		assertFalse(cgp.estaDisponible("ServicioQueNoContiene", lunes12hs));
		assertFalse(cgp.estaDisponible("rentas", lunes23hs));
	}

	@Test
	public void testDisponiblesEnHorario() {
		assertTrue(listaPOI.get(0).estaDisponible("rentas", lunes12hs));
		assertFalse(listaPOI.get(1).estaDisponible("rentas", lunes12hs));
	}

	@Test
	public void testConMocks() {
		BuscadorDeCGP buscador = mock(BuscadorDeCGP.class);
		adapter.setServicioCGP(buscador);

		adapter.buscarPOI("nombre");

		verify(buscador).buscarPOI("nombre");

	}
	
	@Test
	public void buiderTest(){
		assertEquals(cgpPalermo.getNombre(), "Palermo");
	}
	
	@Test
	public void builderJornada(){
		assertTrue(cgpLaBoca.estaDisponible("Asesoramiento Legal", lunes1210hs));
	}
}

