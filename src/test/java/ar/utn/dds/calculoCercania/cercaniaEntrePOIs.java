package ar.utn.dds.calculoCercania;

import static org.junit.Assert.*;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;

public class cercaniaEntrePOIs {

	private ParadaDeColectivo parada114;
	private LocalComercial cafeMartinez;

	
	private Point ubicacionPOI;
	private Point puntoTerminal;
	
	@Before
	public void SetUp(){
		//Parada de colectivo - Parada 114
		parada114 = new ParadaDeColectivo();
		ubicacionPOI = new Point(10,20);
		parada114.setUbicacionActual(ubicacionPOI);
	
		//Local comercial - cafeMartinez
		cafeMartinez = new LocalComercial();
		cafeMartinez.setUbicacionActual(ubicacionPOI);
		
		// Terminal de consulta
		puntoTerminal = new Point(11,20);
	}
	
	@Test
	public void estoyCercaDeUnaParadaDeColectivo(){
		assertFalse(parada114.estaCercaDe(puntoTerminal));
	}
	
//	@Test
//	public void estoyCercaDeUnLocalComercial(){
//		assertFalse(cafeMartinez.estaCercaDe(puntoTerminal));
//	}

}
