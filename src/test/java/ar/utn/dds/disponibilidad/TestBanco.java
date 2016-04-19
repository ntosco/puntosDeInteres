package ar.utn.dds.disponibilidad;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.*;
import org.uqbar.geodds.Point;
import ar.utn.dds.POI.*;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;


public class TestBanco {

	private SucursalBanco sucursalRetiro;
	private SucursalBanco sucursalMartinez;
	private Point ubicacionSucursal;
	private Point ubicacionSucursalLejana;
	private Point puntoTerminal;
	private Point puntoTerminal2;
	
	private LocalDateTime sabado1210hs ;
	private LocalDateTime lunes12hs ;
	private LocalDateTime jueves11hs ;
	private LocalDateTime sabado23hs;
	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(10,20);
		puntoTerminal2 = new Point(1000,2000);

		
		//
		RangoHorario rangolaboral_10a20 = new RangoHorario(LocalTime.of(10, 0, 0),LocalTime.of(20, 0, 0));
		RangoHorario rangolaboral_13a15 = new RangoHorario(LocalTime.of(13, 0, 0),LocalTime.of(15, 0, 0));
		Jornada jornadaLaboral_Lunes_10a20 = new Jornada(DayOfWeek.MONDAY, rangolaboral_10a20);
		Jornada jornadaLaboral_Jueves_13a15 = new Jornada(DayOfWeek.TUESDAY, rangolaboral_13a15);
		ArrayList<Jornada> jornadas = new ArrayList<>();
		jornadas.add(jornadaLaboral_Lunes_10a20);
		jornadas.add(jornadaLaboral_Jueves_13a15);
		//
		
		lunes12hs =LocalDateTime.of(2016,4,11,12,00,00);
		jueves11hs = LocalDateTime.of(2016,4,7,11,00,00);
		sabado1210hs = LocalDateTime.of(2016,4,2,12,10,00);
		sabado23hs = LocalDateTime.of(2016,4,2,23,00,00);
		
		//
		RangoHorario rangolaboral_17a20 = new RangoHorario(LocalTime.of(17, 0, 0),LocalTime.of(20, 0, 0));
		Jornada jornadaLaboral_Sabado_13a15 = new Jornada(DayOfWeek.SATURDAY, rangolaboral_13a15);
		Jornada jornadaLaboral_Jueves_17a20 = new Jornada(DayOfWeek.TUESDAY, rangolaboral_17a20);
		ArrayList<Jornada> jornadasInversas = new ArrayList<>();
		jornadasInversas.add(jornadaLaboral_Sabado_13a15);
		jornadasInversas.add(jornadaLaboral_Jueves_17a20);
		//
		
		Servicio servicioRentas =new Servicio("Rentas", jornadasInversas);
		Servicio serivcioCP = new Servicio("CP", jornadas);
		

		//Sucursal Banco
		sucursalRetiro = new SucursalBanco();
		ArrayList<Servicio> servicios = new ArrayList<>();
		servicios.add(serivcioCP);
		servicios.add(servicioRentas);
		sucursalRetiro.setListaServicios(servicios);
		
		sucursalMartinez = new SucursalBanco();
		ubicacionSucursalLejana = new Point(800, 200);
		sucursalMartinez.setUbicacionActual(ubicacionSucursalLejana);
	}
	
	
	
	
	// el horario ingresado se encuentra en el servicio seleccionado
	// y se encuentra en rango horario banco
	@Test
	public void estoyDisponibleconServicioHorarioIN(){
		assertTrue(sucursalRetiro.estaDisponible(sucursalRetiro,"CP", lunes12hs));
	}	
	
	// el horario ingresado no se encuentra en el servicio seleccionado
		// pero se encuentra en rango horario banco
	@Test
	public void estoyDisponibleconServicioHorarioINBancoOUTServicio(){
		assertTrue(sucursalRetiro.estaDisponible(sucursalRetiro,"Rentas", jueves11hs));
	}
	
	// el horario ingresado  se encuentra en el servicio seleccionado
	// pero no se encuentra en rango horario banco
	@Test
	public void estoyDisponibleconServicioInexisitenteHorarioINServicioOUTBanco(){
		assertFalse(sucursalRetiro.estaDisponible(sucursalRetiro,"Rentas", sabado1210hs));
	}
	
	// el horario ingresado no se encuentra en el servicio seleccionado
	// y no se encuentra en rango horario banco
	@Test
	public void estoyDisponibleconServicioHorarioOUTBancoOUTServicio(){
		assertFalse(sucursalRetiro.estaDisponible(sucursalRetiro,"CP", sabado23hs));
	}
	

	


} 
