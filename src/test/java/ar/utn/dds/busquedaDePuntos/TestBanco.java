package ar.utn.dds.busquedaDePuntos;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

public class TestBanco {

	private SucursalBanco sucursalRetiro;
	private SucursalBanco sucursalMartinez;
	
	private Servicio asesoramientoLegal;
	private Servicio pagoDeFacturas;
	private Servicio Rentas;
	
	private LocalDateTime sabado1210hs ;
	private LocalDateTime lunes12hs ;
	private LocalDateTime jueves11hs ;
	private LocalDateTime sabado23hs;
	private Point ubicacionSucursalLejana;

	@Before
	public void SetUp() {
		
		RangoHorario rangolaboral_10a20 = new RangoHorario(100000, 200000);
		RangoHorario rangolaboral_13a15 = new RangoHorario(130000, 150000);
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
		RangoHorario rangolaboral_17a20 = new RangoHorario(170000, 200000);
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
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("Rentas");
		palabrasClave.add("CP");
		sucursalRetiro.setListaPalabrasClave(palabrasClave);
		
		sucursalMartinez = new SucursalBanco();
		ArrayList<Servicio> servicios2 = new ArrayList<>();
		servicios2.add(serivcioCP);
		ubicacionSucursalLejana = new Point(800, 200);
		sucursalMartinez.setUbicacionActual(ubicacionSucursalLejana);
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
