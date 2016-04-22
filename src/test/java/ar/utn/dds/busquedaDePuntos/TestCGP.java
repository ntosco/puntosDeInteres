package ar.utn.dds.busquedaDePuntos;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

public class TestCGP extends JuegoDeDatos {

	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpCGP();
		
	}
	
	@Test
	public void testContieneServicioDelTextoLibre(){
		assertTrue(cgpAlmagro.cumpleCondicionBusqueda("CP"));
	}
	
	@Test
	public void testNoContieneServicioDelTextoLibre(){
		assertFalse(cgpCaballito.cumpleCondicionBusqueda("Rentas"));
	}

}
