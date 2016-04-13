package ar.utn.dds.busquedaDePuntos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.ParadaDeColectivo;

public class TestColectivo {
	
	private ParadaDeColectivo parada114;//si yo busco 11, me devuelve las paradas del 11 y las del 114, 115, ...?
	private ParadaDeColectivo parada11;
	private ParadaDeColectivo parada7Rojo;// si yo busco 7, me deberia devolver 7 ramal medina, 7 ramal samore, etc
	private ParadaDeColectivo parada7Azul;
	private ArrayList<ParadaDeColectivo> paradas;
	
	@Before
	public void SetUp(){
		
		ParadaDeColectivo parada114 = new ParadaDeColectivo();
		parada114.setLinea("114");
		
		parada11 = new ParadaDeColectivo();
		Point ubicacionParada11 = new Point(10,15);
		parada11.setLinea("11");
		
		parada7Rojo = new ParadaDeColectivo();
		Point ubicacionParada7Rojo = new Point(11,5);
		parada7Rojo.setLinea("7 Barrio Samore");
		
		parada7Azul = new ParadaDeColectivo();
		Point ubicacionParada7Azul = new Point(15,5);
		parada7Azul.setLinea("7 Medina");
		
		ArrayList<ParadaDeColectivo> paradas = null;
		paradas.add(parada7Azul);
		paradas.add(parada7Rojo);

	}
	
	@Test
	public void busquedaDeParadaIngresandoTexto(){
		assertTrue(parada114.cumpleCondicionBusqueda("114"));
	}
	
	@Test
	public void noEncuentraParadaQueNoCoincideConLaBusqueda(){
		assertFalse(parada114.cumpleCondicionBusqueda("7"));
		
	}
	
	@Test
	public void aversifunca(){
		assertEquals(parada11.getLinea(),"11");
	}
	


}


