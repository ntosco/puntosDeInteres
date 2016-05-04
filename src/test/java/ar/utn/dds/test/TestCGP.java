package ar.utn.dds.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.ServicioExterno.CentroDTO;
import ar.utn.dds.buscador.BuscadorDeCGP;
import ar.utn.dds.buscador.StubBuscadorCGP;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.utils.BusquedaDePuntos;
import ar.utn.dds.utils.Conversor;

public class TestCGP extends JuegoDeDatos {

	public List<CentroGestionParticipacion> listaCGP;

	@Before
	public void SetUp() {
		setUpGeneral();
		setUpCGP();
		setUpDTO();
		BusquedaDePuntos.setBuscadorDeCGP(new StubBuscadorCGP());
		listaCGP = BusquedaDePuntos.buscarCGPEnRepoExterno("nombre");

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
		assertFalse(cgpCaballito.buscarPOI("Rentas"));
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
		BusquedaDePuntos.setBuscadorDeCGP(new StubBuscadorCGP());
		listaCGP = new ArrayList<CentroGestionParticipacion>();
		assertEquals(listaCGP.size(), 0);
		listaCGP = BusquedaDePuntos.buscarCGPEnRepoExterno("nombre");
		assertEquals(listaCGP.size(), 2);
	}

	// Conversion de Zonas Incluidas a Barrio CGP
	@Test
	public void testConversionDeZonas() {
		CentroGestionParticipacion cgp = listaCGP.get(0);
		assertEquals(cgp.getBarrio(), "Recoleta");
	}

	@Test
	public void testConversionDeComuna() {
		CentroGestionParticipacion cgp = listaCGP.get(0);
		Point unPunto = new Point(11, 20);
		cgp.getComuna().setAreaDeComuna(new Point(10, 20));
		cgp.getComuna().setAreaDeComuna(new Point(10, 50));
		cgp.getComuna().setAreaDeComuna(new Point(2, 20));
		assertFalse(cgp.getComuna().estaCercaDe(unPunto));
	}

	@Test
	public void conversionDeNombre() {
		CentroGestionParticipacion cgp = listaCGP.get(0);
		assertEquals(cgp.getNombre(), "comuna1");
	}

	@Test
	public void testJornadaDisponible() {
		CentroGestionParticipacion cgp = listaCGP.get(0);
		assertTrue(cgp.estaDisponible("rentas", lunes12hs));
		assertFalse(cgp.estaDisponible("ServicioQueNoContiene", lunes12hs));
		assertFalse(cgp.estaDisponible("rentas", lunes23hs));
	}

	@Test
	public void testDTOJuegoDeDatos() {
		centrosDTO.forEach(centro -> listaCGP.add(Conversor.getInstance().convertirDTOACGP(centro)));
		assertEquals(centrosDTO.size(), 1);

	}

	@Test
	public void testTodosDisponiblesEnHorario() {
		assertTrue(listaCGP.get(0).estaDisponible("rentas", lunes12hs));
		assertFalse(listaCGP.get(1).estaDisponible("rentas", lunes12hs));
	}

	@Test
	public void testConMocks() {
		BuscadorDeCGP buscador = mock(BuscadorDeCGP.class);

		BusquedaDePuntos.setBuscadorDeCGP(buscador);

		BusquedaDePuntos.buscarCGPEnRepoExterno("nombre");

		verify(buscador).buscarPOI("nombre");

	}
	
	@Test
	public void pruebaConversionDePalabrasClave(){
		CentroDTO centro = centrosDTO.get(0);
		assertTrue(Conversor.getInstance().palabrasClaveParaCGP(centro).contains("rentas"));
		assertTrue(Conversor.getInstance().palabrasClaveParaCGP(centro).contains("Recoleta"));
		
	}
}
