package ar.utn.dds.busquedaDePuntos;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

public class TestLocalComercial extends JuegoDeDatos {

	@Before
	public void SetUp() {
		setUpGeneral();
		setUpLocalComercial();
		
	}

	@Test
	public void testPerteneceAUnRubroQueEstaEnSuLista() {
		assertTrue(cafeMartinez.cumpleCondicionBusqueda("cafeteria"));
	}
	
	
	//Revisar test que rompe cuando se le pone una palabra que no esta en su lista
//	@Test
	//public void testNoPerteneceAUnRubroQueEstaEnSuLista(){
		// assertFalse(cafeMartinez.cumpleCondicionBusqueda("perfumeria"));
//	}

}
