package ar.utn.dds.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.serviceLocator.ServiceLocator;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.BusquedaDePuntos;
import ar.utn.dds.buscador.BuscadorDeCGP;
import ar.utn.dds.buscador.StubBuscadorBanco;
import ar.utn.dds.buscador.StubBuscadorCGP;
import ar.utn.dds.buscador.StubNuevoBanco;
import ar.utn.dds.buscador.StubNuevoCGP;
import ar.utn.dds.buscador.buscadorDeBancos;
//import ar.utn.dds.serviceLocator;
import ar.utn.dds.exceptions.BusinessException;
import ar.utn.dds.utils.Conversor;


public class TestBusquedaGeneral extends JuegoDeDatos {
	
	Repositorio repositorio = Repositorio.getInstance();
	ServiceLocator serviceLocator = ServiceLocator.getInstance();
	
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		buscadorDeBancos buscadorBanco = new StubNuevoBanco();
		BuscadorDeCGP buscadorCGP = new StubNuevoCGP();
		
		
	}
	
	
	@Test 
	public void agregoUnBancoDelServicioExternoQueEstaDuplicadoEnElRepo(){
		
		StubNuevoBanco buscadorBanco = new StubNuevoBanco();
		StubNuevoCGP buscadorCGP = new StubNuevoCGP();
		
//		Repositorio repositorio = Repositorio.getInstance();
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		
				
		JSONObject bancoFrances2 = new JSONObject();
		
		bancoFrances2.put("banco", "Banco Frances");
		bancoFrances2.put("x", "45");
		bancoFrances2.put("y", "20");
		bancoFrances2.put("sucursal", "Avellaneda");
		bancoFrances2.put("gerente", "Pablo Perez");
		bancoFrances2.put("servicios", "[cobrocheques,dep�sitos,extracciones]");
		
		JSONObject bancoGalicia2 = new JSONObject();
		
		bancoGalicia2.put("banco", "Banco Galicia");
		bancoGalicia2.put("x", "45");
		bancoGalicia2.put("y", "20");
		bancoGalicia2.put("sucursal", "Avellaneda");
		bancoGalicia2.put("gerente", "Pablo Perez");
		bancoGalicia2.put("servicios", "[cobrocheques,dep�sitos,extracciones]");
		
		JSONArray listaBanco = new JSONArray();
		listaBanco.add(bancoFrances2);
		
		
		buscadorBanco.setListaAdevolver(listaBanco);
		//buscadorCGP.setListaAdevolver(null);
		
		repositorio.create(bancoFrances);

		
		serviceLocator.setBuscadorDeBancos(buscadorBanco);
		//serviceLocator.setBuscadorDeCGP(buscadorCGP);
		
		serviceLocator.busquedaGeneral("banco");
		assertEquals(serviceLocator.getRepositorioPois().allInstances().size(),1);
	}
	
	
//	public void conversionBancosServicios(){
//		BusquedaDePuntos.setBuscadorDeBancos(new StubBuscadorBanco());
//		List<SucursalBanco> listaBancos = BusquedaDePuntos.buscarBancoEnRepoExterno("lalala");
//		assertTrue(listaBancos.get(0).getListaServicios().size() == 3);
//	}
	
	
}
	

	