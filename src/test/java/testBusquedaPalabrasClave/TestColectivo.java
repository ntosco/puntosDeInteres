package testBusquedaPalabrasClave;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.ParadaDeColectivo;

public class TestColectivo {
	
	private ParadaDeColectivo parada114;//si yo busco 11, me devuelve las paradas del 11 y las del 114, 115, ...?
	private ParadaDeColectivo parada11;
	private ParadaDeColectivo parada7Rojo;// si yo busco 7, me deberia devolver 7 ramal medina, 7 ramal samore, etc
	private ParadaDeColectivo parada7Amarillo;
	private ParadaDeColectivo parada7Azul;
	
	@Before
	public void setUp(){
		
		parada114 = new ParadaDeColectivo();
		Point ubicacionParada114 = new Point(10,20);
		parada114.setLinea("114");
		
		parada11 = new ParadaDeColectivo();
		Point ubicacionParada11 = new Point(10,15);
		parada11.setLinea("11");
		
		
		parada7Rojo.setLinea("7 Barrio Samore");
		parada7Amarillo.setLinea("7 Avellaneda");
		parada7Azul.setLinea("7 Medina");
	}
	
	@Test
	public void testBusquedaDeParadaIngresandoTexto(){
		assertTrue(parada114.cumpleCondicionBusqueda("114"));
	}

}


