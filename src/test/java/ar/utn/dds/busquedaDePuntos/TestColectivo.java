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
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;

public class TestColectivo extends JuegoDeDatos{

	@Before
	public void SetUp() {
		setUpGeneral();
		setUpColectivos();

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
