package ar.utn.dds.disponibilidad;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;


public class TestLocalComercial {

	private LocalComercial cafeMartinez;
	private Point puntoTerminal;
	private Point ubicacionLocalCafeMartinez;
	private Point ubicacionLocalNike;
	
	private LocalComercial nike;//solo un rubro: indumentaria
	private LocalComercial falabella; //varios rubros: indumentaria, muebleria, perfumeria, etc
	private Rubro indumentaria;
	private Rubro muebleria;
	private Rubro perfumeria;
	
	private Servicio asesoramientoLegal;
	private Servicio pagoDeFacturas;
	private Servicio asesoramientoTecnico;
	
	private LocalDateTime lunes1210hs ;
	private LocalDateTime lunes23hs ;

	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(10,20);
 
		//Local comercial
		cafeMartinez = new LocalComercial();
		ubicacionLocalCafeMartinez = new Point(10.003,20);
		cafeMartinez.setUbicacionActual(ubicacionLocalCafeMartinez);
		cafeMartinez.setRadioDeCercania(0.4);
		
		//
		RangoHorario rangolaboral = new RangoHorario(100000, 200000);
		Jornada jornadaLaboral = new Jornada(DayOfWeek.FRIDAY, rangolaboral);
		Jornada jornadaLaboral2 = new Jornada(DayOfWeek.TUESDAY, rangolaboral);
		RangoHorario rangolaboral2 = new RangoHorario(130000, 150000);
		ArrayList<Jornada> jornadas = new ArrayList<>();
		jornadas.add(jornadaLaboral);
		jornadas.add(jornadaLaboral2);
		//
		
		cafeMartinez.setJornadaDisponible(jornadas);
		
		lunes1210hs =LocalDateTime.of(2016,4,11,12,10,00);
		lunes23hs= LocalDateTime.of(2016,4,11,23,10,00);
		
	}
	

	@Test
	public void estoyDisponibleHorarioOut(){
		assertFalse(cafeMartinez.estaDisponible(cafeMartinez,null,lunes23hs));
	}
	
	@Test
	public void estoyDisponibleHorarioIn(){
		assertFalse(cafeMartinez.estaDisponible(cafeMartinez,null, lunes1210hs));
	}
	
	@Test
	public void estoyDisponibleConServicioHorarioOut(){
		assertFalse(cafeMartinez.estaDisponible(cafeMartinez,"Rentas", lunes23hs));
	}
	
	@Test
	public void estoyDisponibleConServicioHorarioIn(){
		assertFalse(cafeMartinez.estaDisponible(cafeMartinez,"Rentas", lunes1210hs));
	}

	

	
}