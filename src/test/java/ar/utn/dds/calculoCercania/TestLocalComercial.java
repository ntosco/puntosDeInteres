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
	private Point ubicacionLibreriaAlmagro;
	private Point ubicacionPuestoDiariosJose;
	
	private LocalComercial nike;//solo un rubro: indumentaria
	private LocalComercial falabella; //varios rubros: indumentaria, muebleria, perfumeria, etc
	private LocalComercial libreriaAlmagro;
	private LocalComercial puestoDiariosJose;
	private Rubro indumentaria;
	private Rubro muebleria;
	private Rubro perfumeria;
	private Rubro libreriaEscolar;
	private Rubro puestoDiarios;


	
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
		
		libreriaAlmagro = new LocalComercial();
		ubicacionLibreriaAlmagro = new Point(10,20.2);
		libreriaAlmagro.setUbicacionActual(ubicacionLibreriaAlmagro);
		libreriaAlmagro.setRadioDeCercania(10);
		
		puestoDiariosJose = new LocalComercial();
		ubicacionPuestoDiariosJose = new Point(10,20.2);
		puestoDiariosJose.setUbicacionActual(ubicacionPuestoDiariosJose);
		puestoDiariosJose.setRadioDeCercania(0.2);
		
		
		
	}
	
	@Test
	public void estoyCercaDeUnLocalComercial(){
		assertTrue(cafeMartinez.estaCercaDe(puntoTerminal));
	}
	
	@Test
	public void estoyLejosDeUnLocalComercialLejano(){
		assertFalse(nike.estaCercaDe(puntoTerminal));
	}
	
	@Test
	public void estoyLejosDeUnaLibreriaPeroCercaDeUnKioscoDeDiarios(){
		assertFalse(puestoDiariosJose.estaCercaDe(puntoTerminal));
		//assertTrue(libreriaAlmagro.estaCercaDe(puntoTerminal));

	}
}
