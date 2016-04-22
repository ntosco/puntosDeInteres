package ar.utn.dds.disponibilidad;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;

public class TestsColectivo extends JuegoDeDatos {
	
	
	@Before
	public void SetUp(){
		setUpColectivos();
			
	}

	@Test
	public void estoyDisponibleAhoraSinServicio(){
		assertTrue(parada114.estaDisponible(null, LocalDateTime.now()));
	}
	
	@Test
	public void estoyDisponibleAhoraConServicio(){
		assertTrue(parada11.estaDisponible("Rentas", LocalDateTime.now()));
	}
	
}


