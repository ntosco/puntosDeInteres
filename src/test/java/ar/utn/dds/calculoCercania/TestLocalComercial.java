package ar.utn.dds.calculoCercania;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.servicios.Servicio;


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


	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(10,20);
 
		//Local comercial
		cafeMartinez = new LocalComercial();
		ubicacionLocalCafeMartinez = new Point(10.003,20);
		cafeMartinez.setUbicacionActual(ubicacionLocalCafeMartinez);
		cafeMartinez.setRadioDeCercania(0.4);
		
		nike = new LocalComercial();//esta cerca de la terminal
		ubicacionLocalNike = new Point(150,200);
		nike.setUbicacionActual(ubicacionLocalNike);		
		nike.setRadioDeCercania(0.2);
		
	}
	
	@Test
	public void estoyCercaDeUnLocalComercial(){
		assertTrue(cafeMartinez.estaCercaDe(puntoTerminal));
	}
	
	@Test
	public void estoyLejosDeUnLocalComercialLejano(){
		assertFalse(nike.estaCercaDe(puntoTerminal));
	}
	
}
