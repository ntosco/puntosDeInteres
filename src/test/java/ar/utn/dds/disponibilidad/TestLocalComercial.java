package ar.utn.dds.disponibilidad;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;


public class TestLocalComercial extends JuegoDeDatos {

	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpLocalComercial();
		
	}
	
	@Test
	public void estoyDisponibleHorarioOut(){
		assertFalse(cafeMartinez.estaDisponible(null,lunes23hs));
	}
	
	@Test
	public void estoyDisponibleHorarioIn(){
		assertTrue(cafeMartinez.estaDisponible(null, lunes1210hs));
	}
	
	@Test
	public void estoyDisponibleConServicioHorarioOut(){
		assertFalse(cafeMartinez.estaDisponible("Rentas", lunes23hs));
	}
	
	@Test
	public void estoyDisponibleConServicioHorarioIn(){
		assertTrue(cafeMartinez.estaDisponible("Rentas", lunes1210hs));
	}
		
}
