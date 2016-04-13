package ar.utn.dds.busquedaDePuntos;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
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

	private LocalComercial nike;// solo un rubro: indumentaria
	private LocalComercial falabella; // varios rubros: indumentaria, muebleria,
										// perfumeria, etc
	private Rubro indumentaria;
	private Rubro muebleria;
	private Rubro perfumeria;

	private Servicio asesoramientoLegal;
	private Servicio pagoDeFacturas;
	private Servicio asesoramientoTecnico;

	private LocalDateTime lunes1210hs;
	private LocalDateTime lunes23hs;

	@Before
	public void SetUp() {

		// Terminal de consulta
		puntoTerminal = new Point(10, 20);

		// Local comercial
		cafeMartinez = new LocalComercial();
		ubicacionLocalCafeMartinez = new Point(10.003, 20);
		cafeMartinez.setUbicacionActual(ubicacionLocalCafeMartinez);
		cafeMartinez.setRadioDeCercania(0.4);
		ArrayList<Rubro> listaRubros = new ArrayList<Rubro>();
		Rubro cafeteria = new Rubro();
		cafeteria.setNombre("cafeteria");
		listaRubros.add(cafeteria);
		
		cafeMartinez.setListaRubros(listaRubros);

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

		lunes1210hs = LocalDateTime.of(2016, 4, 11, 12, 10, 00);
		lunes23hs = LocalDateTime.of(2016, 4, 11, 23, 10, 00);

	}

	@Test
	public void testPerteneceAUnRubroQueEstaEnSuLista() {
		assertTrue(cafeMartinez.cumpleCondicionBusqueda("cafeteria"));
	}
	
	@Test
	public void testNoPerteneceAUnRubroQueEstaEnSuLista(){
		assertFalse(cafeMartinez.cumpleCondicionBusqueda("perfumeria"));
	}

}
