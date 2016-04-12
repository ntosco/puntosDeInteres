package ar.utn.dds.calculoCercania;

import static org.junit.Assert.*;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;

public class TestsColectivo {
	
	private Point puntoTerminal;
	private ParadaDeColectivo parada114;//si yo busco 11, me devuelve las paradas del 11 y las del 114, 115, ...?
	private ParadaDeColectivo parada11;
	
	private Point ubicacionParada114;
	private Point ubicacionParada11;
	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(10,20);

		//Parada de colectivo - Parada 114
		parada114 = new ParadaDeColectivo();
		ubicacionParada114 = new Point(10.0008,20);
		parada114.setUbicacionActual(ubicacionParada114);
		
		parada11 = new ParadaDeColectivo();
		ubicacionParada11 = new Point(150,200);
		parada11.setUbicacionActual(ubicacionParada11);
			
	}
	
	@Test
	public void estoyCercaDeUnaParadaDeColectivoCercana(){
		assertTrue(parada114.estaCercaDe(puntoTerminal));
	}
	
	@Test 
	public void noEstoyCercaDeUnaParadaDeColectivoLejana(){
		assertFalse(parada11.estaCercaDe(puntoTerminal));
	}
}


