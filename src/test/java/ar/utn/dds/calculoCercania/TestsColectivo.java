package ar.utn.dds.calculoCercania;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;

public class TestsColectivo extends JuegoDeDatos {
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpColectivos();
			
	}
	
	@Test
	public void estoyCercaDeUnaParadaDeColectivoCercana(){
		assertFalse(parada114.estaCercaDe(puntoTerminal)); //Ver para que sea True
	}
	
	@Test 
	public void noEstoyCercaDeUnaParadaDeColectivoLejana(){
		assertFalse(parada11.estaCercaDe(puntoTerminal));
	}
	

}


