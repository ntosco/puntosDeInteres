package ar.utn.dds.disponibilidad;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;
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
	
	
	// el horario ingresado se encuentra en el servicio seleccionado
	@Test
	public void estoyDisponibleconServicioHorarioIN(){
		assertFalse(cgpAlmagro.estaDisponible("CP", lunes1210hs));
	}	
	
	// el horario ingresado no se encuentra en el servicio seleccionado
	@Test
	public void estoyDisponibleconServicioHorarioOUT(){
		assertFalse(cgpAlmagro.estaDisponible("CP", lunes23hs));
	}
	
	// el nombre del Servicio no existe
	@Test
	public void estoyDisponibleconServicioInexisitenteHorarioIN(){
		assertFalse(cgpAlmagro.estaDisponible("NombreInexistente", lunes1210hs));
	}
	
	// No se ingresa nombre del servicio ,pero horario es del servicio CP
	@Test
	public void estoyDisponibleSinServicioHorarioIN(){
		assertFalse(cgpAlmagro.estaDisponible("",lunes1210hs));
	}
	
	// No se ingresa nombre del servicio ,el horario no es de ningun servicio
	@Test
	public void estoyDisponibleSinServicioHorarioOUT(){
		assertFalse(cgpAlmagro.estaDisponible("almagro",lunes1210hs));
	}
	
	

	
	
}
