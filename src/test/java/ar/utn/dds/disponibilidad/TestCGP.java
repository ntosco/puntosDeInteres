package ar.utn.dds.disponibilidad;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

public class TestCGP {

	private Point puntoTerminal;
	
	private CentroGestionParticipacion cgpAlmagro;
	private CentroGestionParticipacion cgpCaballito;
	private Comuna comuna2;
	private Comuna comuna1;

	private Point punto1comuna;
	private Point punto2comuna;
	private Point punto3comuna;
	
	private Point punto4comuna;
	private Point punto5comuna;
	private Point punto6comuna;
	
	
	private LocalDateTime lunes1210hs ;
	private LocalDateTime lunes23hs ;

	
	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(15,25);
		
		//Comuna
		comuna1 = new Comuna();
		comuna2 = new Comuna();
	
		punto1comuna = new Point(10,20);
		punto2comuna = new Point(20,20);
		punto3comuna = new Point(15,30);
		
		punto4comuna = new Point(10,20);
		punto5comuna = new Point(20,20);
		punto6comuna = new Point(15,10);

		comuna1.setAreaDeComuna(punto1comuna);
		comuna1.setAreaDeComuna(punto2comuna);
		comuna1.setAreaDeComuna(punto3comuna);
		
		comuna2.setAreaDeComuna(punto4comuna);
		comuna2.setAreaDeComuna(punto5comuna);
		comuna2.setAreaDeComuna(punto6comuna);
	
		
		//
		RangoHorario rangolaboral_10a20 = new RangoHorario(100000, 200000);
		RangoHorario rangolaboral_13a15 = new RangoHorario(130000, 150000);
		Jornada jornadaLaboral_Lunes_10a20 = new Jornada(DayOfWeek.MONDAY, rangolaboral_10a20);
		Jornada jornadaLaboral_Jueves_13a15 = new Jornada(DayOfWeek.TUESDAY, rangolaboral_13a15);
		ArrayList<Jornada> jornadas = new ArrayList<>();
		jornadas.add(jornadaLaboral_Lunes_10a20);
		jornadas.add(jornadaLaboral_Jueves_13a15);
		//
		
		lunes1210hs =LocalDateTime.of(2016,4,11,12,10,00);
		lunes23hs= LocalDateTime.of(2016,4,11,23,10,00);
		
		//
		Jornada jornadaLaboral_Lunes_13a15 = new Jornada(DayOfWeek.MONDAY, rangolaboral_13a15);
		Jornada jornadaLaboral_Jueves_10a20 = new Jornada(DayOfWeek.TUESDAY, rangolaboral_10a20);
		ArrayList<Jornada> jornadasInversas = new ArrayList<>();
		jornadasInversas.add(jornadaLaboral_Lunes_13a15);
		jornadasInversas.add(jornadaLaboral_Jueves_10a20);
		//
		
		Servicio servicioRentas =new Servicio("Rentas", jornadasInversas);
		Servicio serivcioCP = new Servicio("CP", jornadas);
		
		// Centro de Gestion y Participación
		cgpAlmagro = new CentroGestionParticipacion();
		cgpAlmagro.setComuna(comuna1);
		ArrayList<Servicio> servicios = new ArrayList<>();
		servicios.add(serivcioCP);
		servicios.add(servicioRentas);
		cgpAlmagro.setListaServicios(servicios);
		
		
		cgpCaballito = new CentroGestionParticipacion();
		cgpCaballito.setComuna(comuna2);
		
		ArrayList<Servicio> servicio = new ArrayList<>();
		servicio.add(serivcioCP);
		cgpAlmagro.setListaServicios(servicio);
	
		
	}
	
	
	// el horario ingresado se encuentra en el servicio seleccionado
	@Test
	public void estoyDisponibleconServicioHorarioIN(){
		assertTrue(cgpAlmagro.estaDisponible(cgpAlmagro,"CP", lunes1210hs));
	}	
	
	// el horario ingresado no se encuentra en el servicio seleccionado
	@Test
	public void estoyDisponibleconServicioHorarioOUT(){
		assertFalse(cgpAlmagro.estaDisponible(cgpAlmagro,"CP", lunes23hs));
	}
	
	// el nombre del Servicio no existe
	@Test
	public void estoyDisponibleconServicioInexisitenteHorarioIN(){
		assertFalse(cgpAlmagro.estaDisponible(cgpAlmagro,"NombreInexistente", lunes1210hs));
	}
	
	// No se ingresa nombre del servicio ,pero horario es del servicio CP
	@Test
	public void estoyDisponibleSinServicioHorarioIN(){
		assertTrue(cgpAlmagro.estaDisponible(cgpAlmagro,null,lunes1210hs));
	}
	
	// No se ingresa nombre del servicio ,el horario no es de ningun servicio
	@Test
	public void estoyDisponibleSinServicioHorarioOUT(){
		assertFalse(cgpAlmagro.estaDisponible(cgpCaballito,null,lunes23hs));
	}
	
	

	
	
}
