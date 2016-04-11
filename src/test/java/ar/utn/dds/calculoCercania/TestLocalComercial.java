package ar.utn.dds.calculoCercania;

import static org.junit.Assert.assertTrue;

import org.junit.*;
import org.uqbar.geodds.Point;
import ar.utn.dds.POI.LocalComercial;


public class TestLocalComercial {

	private LocalComercial cafeMartinez;
	private Point puntoTerminal;
	private Point ubicacionLocalCafeMartinez;

	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(10,20);
 
		//Local comercial - cafeMartinez
		cafeMartinez = new LocalComercial();
		ubicacionLocalCafeMartinez = new Point(10.003,20);
		cafeMartinez.setUbicacionActual(ubicacionLocalCafeMartinez);
		cafeMartinez.setRadioDeCercania(0.4);
	
	}
	
	@Test
	public void estoyCercaDeUnLocalComercial(){
		assertTrue(cafeMartinez.estaCercaDe(puntoTerminal));
	}
	
}
