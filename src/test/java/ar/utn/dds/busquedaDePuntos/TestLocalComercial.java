package ar.utn.dds.busquedaDePuntos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.Rubro;

public class TestLocalComercial {
	
	private LocalComercial fallabella;
	
	private Rubro indumentaria;
	private Rubro muebleria;
	private Rubro perfumeria;
	
	@Before
	public void setUp(){
		
		fallabella = new LocalComercial();
		
		ArrayList<Rubro> listaRubrosDeFallabella = new ArrayList<Rubro>();
		listaRubrosDeFallabella.add(indumentaria);
		listaRubrosDeFallabella.add(muebleria);
		listaRubrosDeFallabella.add(perfumeria);
		fallabella.setListaRubros(listaRubrosDeFallabella);
		
		indumentaria.setNombre("indumentaria");
		muebleria.setNombre("muebleria");
		perfumeria.setNombre("perfumeria");
		
	}

		@Test
		public void perteneceAUnRubroQueEstaEnSuLista(){
			assertTrue(fallabella.perteneceAlRubro("perfumeria"));
		}
		
}

