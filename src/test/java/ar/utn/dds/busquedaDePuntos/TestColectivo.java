package ar.utn.dds.busquedaDePuntos;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadFullTime;

public class TestColectivo {

	private Point puntoTerminal;
	private ParadaDeColectivo parada114;
	private ParadaDeColectivo parada11;

	private Point ubicacionParada114;
	private Point ubicacionParada11;

	private LocalDateTime lunes1210hs;
	private LocalDateTime lunes23hs;

	@Before
	public void SetUp() {

		// Terminal de consulta
		puntoTerminal = new Point(10, 20);

		// Parada de colectivo - Parada 114
		parada114 = new ParadaDeColectivo();
		parada114.setLinea("114");
		ubicacionParada114 = new Point(150, 200);
		parada114.setJornadaDisponible(null);

		parada11 = new ParadaDeColectivo();
		parada11.setLinea("11");
		ubicacionParada11 = new Point(150, 200);
		parada11.setUbicacionActual(ubicacionParada11);

		lunes1210hs = LocalDateTime.of(2016, 4, 11, 12, 10, 00);
		lunes23hs = LocalDateTime.of(2016, 4, 11, 23, 10, 00);

	}

	@Test
	public void busquedaDeParadaIngresandoTexto() {
		assertTrue(parada114.cumpleCondicionBusqueda("114"));
	}

	@Test
	public void noEncuentraParadaQueNoCoincideConLaBusqueda() {
		assertFalse(parada114.cumpleCondicionBusqueda("7"));

	}

}
