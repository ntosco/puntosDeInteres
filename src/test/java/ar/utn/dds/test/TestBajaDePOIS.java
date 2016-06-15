package ar.utn.dds.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import ar.utn.dds.POI.POI;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.juegoDeDatos.StubBuscadorBanco;
import ar.utn.dds.juegoDeDatos.StubBuscadorCGP;
import ar.utn.dds.juegoDeDatos.StubServicioREST;
import ar.utn.dds.procesos.BajaDePOIS;
import ar.utn.dds.procesos.estrategiaFallo.EnvioMensajePorFalla;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.utils.AdapterBancos;
import ar.utn.dds.utils.AdapterCGP;


public class TestBajaDePOIS extends JuegoDeDatos {

	Repositorio repositorio = Repositorio.getInstance();
	public BajaDePOIS procesoBajas;
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		setUpCGP();
		setUpLocalComercial();
		setUpColectivos();
		
		procesoBajas = new BajaDePOIS();
		procesoBajas.setServicioREST(new StubServicioREST());
		
	}
	
	@After
	public void TearDown(){
		repositorio.clean();
	}
	
	@Test
	public void seDaDeBajaUnPOIEfectivamente(){
		
		repositorio.create(parada15);
		
		procesoBajas.ejecutarse(estrategiaEnvioMensaje);
				
		List<POI> listaAuxiliar = new ArrayList<POI>();
		
		listaAuxiliar = repositorio.search("15");
		
		assertTrue(listaAuxiliar.get(0).getFechaBaja().toString() == "01/01/2015");
	
	}
	
	@Test
	public void elProcesoFallaPorPOIYaDadoDeBaja(){
		
		EnvioMensajePorFalla estrategia = mock(EnvioMensajePorFalla.class);
		
		repositorio.create(parada15);
		
		procesoBajas.ejecutarse(estrategia);
		procesoBajas.ejecutarse(estrategia);
		
		verify(estrategia).ejecutarse(procesoBajas);
		
		
	}
	
}
	
