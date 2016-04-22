package ar.utn.dds.calculoCercania;

import static org.junit.Assert.*;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;


public class TestBanco extends JuegoDeDatos {

	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();

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
