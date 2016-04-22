package ar.utn.dds.disponibilidad;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;


public class TestBanco extends JuegoDeDatos {
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		
	}

	@Test
	public void estoyDisponibleconServicioHorarioIN(){
		assertTrue(sucursalRetiro.estaDisponible("CP", lunes12hs));
	}	
	
	// el horario ingresado no se encuentra en el servicio seleccionado
		// pero se encuentra en rango horario banco
	@Test
	public void estoyDisponibleconServicioHorarioINBancoOUTServicio(){
		assertTrue(sucursalRetiro.estaDisponible("Rentas", jueves11hs));
	}
	
	// el horario ingresado  se encuentra en el servicio seleccionado
	// pero no se encuentra en rango horario banco
	@Test
	public void estoyDisponibleconServicioInexisitenteHorarioINServicioOUTBanco(){
		assertFalse(sucursalRetiro.estaDisponible("Rentas", sabado1210hs));
	}
	
	// el horario ingresado no se encuentra en el servicio seleccionado
	// y no se encuentra en rango horario banco
	@Test
	public void estoyDisponibleconServicioHorarioOUTBancoOUTServicio(){
		assertFalse(sucursalRetiro.estaDisponible("CP", sabado23hs));
	}
	

	


} 
