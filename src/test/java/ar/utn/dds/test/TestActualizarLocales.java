package ar.utn.dds.test;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.POI;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.procesos.ActualizarLocalesComerciales;
import ar.utn.dds.procesos.estrategiaFallo.EnvioMensajePorFalla;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.repositorio.Repositorio;

public class TestActualizarLocales extends JuegoDeDatos {


	Repositorio repositorio = Repositorio.getInstance();
	EnvioMensajePorFalla estrategiaPorFallo = new EnvioMensajePorFalla();
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		setUpCGP();
		setUpLocalComercial();
		setUpColectivos();
	}

	@After
	public void TearDown(){
		repositorio.clean();
	}

	@Test
	public void actualizoUnLocalYsusPalabrasClaveCambian() {
		repositorio.create(libreria);
		ActualizarLocalesComerciales procesoActualizacion = new ActualizarLocalesComerciales();
		procesoActualizacion.ejecutarse(estrategiaPorFallo);
		
		List<POI> listaBusqueda = repositorio.search("lapiz");
		
		assertTrue(listaBusqueda.size()==1);
		assertTrue(listaBusqueda.get(0).getListaPalabrasClave().contains("lapiz"));
		assertTrue(listaBusqueda.get(0).getListaPalabrasClave().contains("escolar"));
		assertTrue(listaBusqueda.get(0).getListaPalabrasClave().contains("uniformes"));
		assertTrue(listaBusqueda.get(0).getListaPalabrasClave().contains("modas"));
		//assertTrue(listaBusqueda.get(0).getListaPalabrasClave().contains("cartuchera"));
		
	}
	
		@Test
		public void actualizoUnSoloLocal() {
			repositorio.create(libreria);
			
			ActualizarLocalesComerciales procesoActualizacion = new ActualizarLocalesComerciales();
			EstrategiaPorFallo fallo = mock(EstrategiaPorFallo.class);
			procesoActualizacion.setFallo(fallo);
			
			procesoActualizacion.actualizarLocal(procesoActualizacion.leerArchivo().get(0));
			
			List<POI> listaBusqueda = repositorio.search("lapiz");
			
			assertTrue(listaBusqueda.size()==1);
			assertTrue(listaBusqueda.get(0).getListaPalabrasClave().contains("lapiz"));
			assertTrue(listaBusqueda.get(0).getListaPalabrasClave().contains("colegio"));
			assertTrue(listaBusqueda.get(0).getListaPalabrasClave().contains("uniformes"));
			assertTrue(listaBusqueda.get(0).getListaPalabrasClave().contains("modas"));
		
		}

		@Test //el local que se quiere actualizar no esta en el repositorio
		
		public void falloAlActualizarLocal() {
			
			//el repo esta vacio asi que no va a encontrar nada
			EstrategiaPorFallo fallo = mock(EstrategiaPorFallo.class);
			ActualizarLocalesComerciales procesoActualizacion = new ActualizarLocalesComerciales();
			procesoActualizacion.ejecutarse(fallo);
			verify(fallo, atLeast(1)).ejecutarse(procesoActualizacion);
			
		}
		
}