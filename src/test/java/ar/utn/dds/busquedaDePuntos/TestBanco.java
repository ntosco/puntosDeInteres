package ar.utn.dds.busquedaDePuntos;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

public class TestBanco extends JuegoDeDatos {

	@Before
	public void SetUp() {
		setUpGeneral();
		setUpBanco();
	}

	
	@Test
	public void testElBancoPrestaAlgunoDeLosServiciosBuscados(){
		assertTrue(sucursalRetiro.cumpleCondicionBusqueda("Rentas"));
	}
	
	@Test
	public void testElBancoNoPRestaAlgunoDeLosServiciosBuscados(){
		assertFalse(sucursalMartinez.cumpleCondicionBusqueda("CP"));
	}
	
}
