package ar.utn.dds.calculoCercania;

import static org.junit.Assert.*;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;

public class TestsColectivo {

	private ParadaDeColectivo parada114;
	
	private Point ubicacionParada114;
	private Point puntoTerminal;
	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(10,20);

		//Parada de colectivo - Parada 114
		parada114 = new ParadaDeColectivo();
		ubicacionParada114 = new Point(10.0008,20);
		parada114.setUbicacionActual(ubicacionParada114);
			
	}
	
	@Test
	public void estoyCercaDeUnaParadaDeColectivo(){
		assertTrue(parada114.estaCercaDe(puntoTerminal));
	}
}


