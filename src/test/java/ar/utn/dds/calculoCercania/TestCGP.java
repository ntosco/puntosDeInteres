package ar.utn.dds.calculoCercania;

import static org.junit.Assert.*;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;

public class TestCGP extends JuegoDeDatos {

	
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpCGP();
		
	}
	
	@Test
	public void estoyCercaDeUnCGPCercano(){
		assertFalse(cgpAlmagro.estaCercaDe(puntoTerminal)); // hacer que de true
	}
	
	@Test 
	public void estoyLejosDeUnCGPLejano(){
		assertFalse(cgpCaballito.estaCercaDe(puntoTerminal));
		
	}
	
}
