package ar.utn.dds.disponibilidad;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;

public class TestsColectivo {
	
	private Point puntoTerminal;
	private ParadaDeColectivo parada114;//si yo busco 11, me devuelve las paradas del 11 y las del 114, 115, ...?
	private ParadaDeColectivo parada11;
	
	private Point ubicacionParada114;
	private Point ubicacionParada11;
	
	private LocalDateTime lunes1210hs ;
	private LocalDateTime lunes23hs ;
	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(10,20);

		//Parada de colectivo - Parada 114
		parada114 = new ParadaDeColectivo();
		parada114.setJornadaDisponible(null);
		
		parada11 = new ParadaDeColectivo();
		ubicacionParada11 = new Point(150,200);
		parada11.setUbicacionActual(ubicacionParada11);
		
		lunes1210hs =LocalDateTime.of(2016,4,11,12,10,00);
		lunes23hs= LocalDateTime.of(2016,4,11,23,10,00);	
			
	}

	@Test
	public void estoyDisponibleAhoraSinServicio(){
		assertTrue(parada114.estaDisponible(parada11,null, LocalDateTime.now()));
	}
	
	@Test
	public void estoyDisponibleAhoraConServicio(){
		assertTrue(parada11.estaDisponible(parada11,"Rentas", LocalDateTime.now()));
	}
	
}


