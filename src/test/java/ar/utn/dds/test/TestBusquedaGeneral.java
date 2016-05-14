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
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.utils.AdapterBancos;
import ar.utn.dds.utils.AdapterCGP;
import ar.utn.dds.utils.BusquedaDePuntos;


public class TestBusquedaGeneral extends JuegoDeDatos {

	public AdapterBancos adapterBanco;
	public AdapterCGP adapterCGP;
	
	Repositorio repositorio = Repositorio.getInstance();
	BusquedaDePuntos buscadorPuntos = BusquedaDePuntos.getInstance();
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		setUpCGP();
		setUpDTO();
		setUpLocalComercial();
		setUpColectivos();
		
		adapterBanco = new AdapterBancos();
		adapterBanco.setServicioBanco(new StubBuscadorBanco());
		
		adapterCGP = new AdapterCGP();
		adapterCGP.setServicioCGP(new StubBuscadorCGP());	
		
		buscadorPuntos.setServicio(adapterBanco);
		buscadorPuntos.setServicio(adapterCGP);
		buscadorPuntos.setServicio(repositorio);

	}
	
	@After
	public void TearDown(){
		repositorio.clean();
		buscadorPuntos.borrarListaServicios();	
	}
	
		
	@Test
	public void agregoPOIsDeCadaOrigenDeDatosSinRepo(){
		
		List<POI> listaAuxiliar = new ArrayList<POI>();
		int sizeAnterior = 0;
		
		listaAuxiliar = buscadorPuntos.busquedaGeneral("15");
		
		assertEquals(sizeAnterior + 4, listaAuxiliar.size());
		
	}
	
	@Test
	public void agregoPOIsDeCadaOrigenJuntoConUnPOIDelRepo(){

		List<POI> listaAuxiliar = new ArrayList<POI>();
		int sizeAnterior = 0;
		
		repositorio.create(parada15);
		
		listaAuxiliar = buscadorPuntos.busquedaGeneral("15");
		
		assertEquals(sizeAnterior + 5, listaAuxiliar.size());
	
	}
	
	@Test
	public void verificarQueLosServiciosExternosBuscanPois(){

		buscadorPuntos.borrarListaServicios();
		
		AdapterBancos adaptadorBanco = mock(AdapterBancos.class);
		adaptadorBanco.setServicioBanco(new StubBuscadorBanco());

		AdapterCGP adaptadorCGP = mock(AdapterCGP.class);
		adaptadorCGP.setServicioCGP(new StubBuscadorCGP());
		
		buscadorPuntos.setServicio(adaptadorBanco);
		buscadorPuntos.setServicio(adaptadorCGP);
		
		buscadorPuntos.busquedaGeneral("15");
		
		verify(adaptadorBanco).buscarPOI("15");
		verify(adaptadorCGP).buscarPOI("15");
				
	}
	
}
	

	