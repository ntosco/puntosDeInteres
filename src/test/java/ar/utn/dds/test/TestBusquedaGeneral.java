package ar.utn.dds.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.juegoDeDatos.StubBuscadorBanco;
import ar.utn.dds.juegoDeDatos.StubBuscadorCGP;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.serviceLocator.ServiceLocator;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.AdapterBancos;
import ar.utn.dds.utils.AdapterCGP;
import ar.utn.dds.utils.BusquedaDePuntos;
import ar.utn.dds.exceptions.BusinessException;
import ar.utn.dds.extern.banco.buscadorDeBancos;
import ar.utn.dds.extern.cgp.BuscadorDeCGP;
import ar.utn.dds.extern.cgp.CentroDTO;



public class TestBusquedaGeneral extends JuegoDeDatos {

	public AdapterBancos adapterBanco;
	public AdapterCGP adapterCGP;
	
	public BusquedaDePuntos buscadorPuntos;
	
	public Repositorio repositorio;
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		
		adapterBanco = new AdapterBancos();
		adapterBanco.setServicioBanco(new StubBuscadorBanco());
		
		adapterCGP = new AdapterCGP();
		adapterCGP.setServicioCGP(new StubBuscadorCGP());
		
		
		repositorio = Repositorio.getInstance();
		buscadorPuntos = BusquedaDePuntos.getInstance();
		
		buscadorPuntos.setServicio(adapterBanco);
		buscadorPuntos.setServicio(adapterCGP);
		buscadorPuntos.setServicio(repositorio);

	}
	
	@After
	public void TearDown(){
		repositorio.clean();
	}
	
//	
//	@Test
//	public void testConMocks() {
//		BuscadorDeCGP buscador = mock(BuscadorDeCGP.class);
//		adapter.setServicioCGP(buscador);
//
//		adapter.buscarPOI("nombre");
//
//		verify(buscador).buscarPOI("nombre");
//
//	}
//
//	
//	
//	@Test 
//	public void agregoUnBancoDelServicioExternoPeroNoAgregoEnRepositorio(){
//		
//
//		buscadorBanco.setListaAdevolver(listaBanco);
//		
//		List<CentroDTO> aux = new ArrayList<CentroDTO>();
//		
//		buscadorCGP.setListaAdevolver(aux);
//				
//		serviceLocator.setBuscadorDeBancos(buscadorBanco);
//		serviceLocator.setBuscadorDeCGP(buscadorCGP);
//		
//		int sizeAnterior = serviceLocator.getRepositorioPois().allInstances().size();
//		
//		serviceLocator.busquedaGeneral("banco");
//		
//		assertEquals(sizeAnterior + 1,serviceLocator.getRepositorioPois().allInstances().size());
//	}
//	
////	@Test(expected = BusinessException.class)
////	public void agregoUnBancoDelServicioExternoQueYaExisteEnElRepositorio(){
////		
////		StubNuevoBanco buscadorBanco = new StubNuevoBanco();
////		StubNuevoCGP buscadorCGP = new StubNuevoCGP();
////		
////		ServiceLocator serviceLocator = ServiceLocator.getInstance();
////		
////				
////		JSONObject bancoFrances2 = new JSONObject();
////		
////		bancoFrances2.put("banco", "Banco Frances");
////		bancoFrances2.put("x", "45");
////		bancoFrances2.put("y", "20");
////		bancoFrances2.put("sucursal", "Avellaneda");
////		bancoFrances2.put("gerente", "Pablo Perez");
////		bancoFrances2.put("servicios", "[cobrocheques,dep�sitos,extracciones]");
////		
////		JSONObject bancoGalicia2 = new JSONObject();
////		
////		bancoGalicia2.put("banco", "Banco Galicia");
////		bancoGalicia2.put("x", "45");
////		bancoGalicia2.put("y", "20");
////		bancoGalicia2.put("sucursal", "Avellaneda");
////		bancoGalicia2.put("gerente", "Pablo Perez");
////		bancoGalicia2.put("servicios", "[cobrocheques,dep�sitos,extracciones]");
////		
////		JSONArray listaBanco = new JSONArray();
////		listaBanco.add(bancoFrances2);
////		
////		
////		buscadorBanco.setListaAdevolver(listaBanco);
////		
////		List<CentroDTO> aux = new ArrayList<CentroDTO>();
////		
////		buscadorCGP.setListaAdevolver(aux);
////		
////		serviceLocator.getRepositorioPois().create(bancoFrances);
////		
////		serviceLocator.setBuscadorDeBancos(buscadorBanco);
////		serviceLocator.setBuscadorDeCGP(buscadorCGP);
////		
////		int sizeAnterior = serviceLocator.getRepositorioPois().allInstances().size();
////		
////		List<POI> lista2 = serviceLocator.busquedaGeneral("nombre");
////		
////		//assertEquals(serviceLocator.getRepositorioPois().allInstances().size(),1);
////}
////	
//	@Test
//	public void agregoUnCGPDelServicioExternoPeroNoEnElRepositorio(){
//		
//		StubNuevoBanco buscadorBanco = new StubNuevoBanco();
//		StubBuscadorCGP buscadorCGP = new StubBuscadorCGP();
//		
//		ServiceLocator serviceLocator = ServiceLocator.getInstance();
//		
//				
//		JSONArray listaBanco = new JSONArray();
//				
//		buscadorBanco.setListaAdevolver(listaBanco);
//		
//				
//		serviceLocator.setBuscadorDeBancos(buscadorBanco);
//		serviceLocator.setBuscadorDeCGP(buscadorCGP);
//		
//		int sizeAnterior = serviceLocator.getRepositorioPois().allInstances().size();
//		
//		List<POI> lista2 = serviceLocator.busquedaGeneral("nombre");
//		
//		assertEquals(sizeAnterior + 2,serviceLocator.getRepositorioPois().allInstances().size());
//	}
//	
//	@Test
//	public void agregoUnCGPDelServicioExternoYAgregoUnPoiAlRepoLocalNoDuplicados(){
//		
//		StubNuevoBanco buscadorBanco = new StubNuevoBanco();
//		StubBuscadorCGP buscadorCGP = new StubBuscadorCGP();
//		
//		ServiceLocator serviceLocator = ServiceLocator.getInstance();
//		
//				
//		JSONArray listaBanco = new JSONArray();
//				
//		buscadorBanco.setListaAdevolver(listaBanco);
//		
//				
//		serviceLocator.setBuscadorDeBancos(buscadorBanco);
//		serviceLocator.setBuscadorDeCGP(buscadorCGP);
//		
//		int sizeAnterior = serviceLocator.getRepositorioPois().allInstances().size();
//		
//		serviceLocator.getRepositorioPois().create(bancoFrances);
//		
//		List<POI> lista2 = serviceLocator.busquedaGeneral("nombre");
//		
//		assertEquals(sizeAnterior + 3,serviceLocator.getRepositorioPois().allInstances().size());
//	}
	
	
}
	

	