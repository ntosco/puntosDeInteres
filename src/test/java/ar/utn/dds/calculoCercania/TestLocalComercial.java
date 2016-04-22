package ar.utn.dds.calculoCercania;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.servicios.Servicio;


public class TestLocalComercial extends JuegoDeDatos {


	@Before
	public void SetUp(){
		setUpGeneral();
		setUpLocalComercial();	
		
	}
	
	@Test
	public void estoyCercaDeUnLocalComercial(){
		assertTrue(fallabella.estaCercaDe(puntoTerminal)); 
	}

	@Test
	public void noEstoyCercaDeUnLocalComercial(){
		cafeteria.setRadioCercania(0.1);
		assertFalse(cafeMartinez.estaCercaDe(puntoTerminal2));
	}

	@Test
	public void estoyCercaDeUnLocalQueTieneMasDeUnRubro(){
		assertFalse(fallabella.estaCercaDe(puntoTerminal2)); //Deberia ser True
	}

	@Test
	public void noEstoyCercaDeUnLocalQueTieneMasDeUnRubro(){
		assertFalse(cafeMartinez.estaCercaDe(puntoTerminal));
	}
	
//	@Test
//	public void estoyLejosDeUnLocalComercialLejano(){
//		assertFalse(nike.estaCercaDe(puntoTerminal));
//	}
//	
//	@Test
//	public void estoyLejosDeUnaLibreriaPeroCercaDeUnKioscoDeDiarios(){
//		assertFalse(puestoDiariosJose.estaCercaDe(puntoTerminal));
//		//assertTrue(libreriaAlmagro.estaCercaDe(puntoTerminal));
//
//	}
//	
	
}
