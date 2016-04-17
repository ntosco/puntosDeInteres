package ar.utn.dds.calculoCercania;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;

import juegoDeDatos.TestPrueba;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;
import juegoDeDatos.JuegoDeDatos;


public class TestBanco extends TestPrueba {


	@Before
	public void SetUp(){
		
		super.setUp();

	}
	 
	@Test
	public void estoyCercaDeUnaSucursalCercana(){
		assertTrue(sucursalRetiro.estaCercaDe(puntoTerminal));
	}
	
	@Test
	public void noEstoyCercaDeUnaSucursalLejana(){
		assertFalse(sucursalMartinez.estaCercaDe(puntoTerminal));
	}
	
} 
