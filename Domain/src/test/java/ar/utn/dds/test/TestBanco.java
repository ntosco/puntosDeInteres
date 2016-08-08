package ar.utn.dds.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.juegoDeDatos.StubBuscadorBanco;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.AdapterBancos;


public class TestBanco extends JuegoDeDatos {
	public List<SucursalBanco> listaBancos;
	public AdapterBancos adapter;
	
	@Before

	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		adapter = new AdapterBancos();
		adapter.setServicioBanco(new StubBuscadorBanco());

	}
	
	@Test
	public void estoyCercaDeUnaSucursalCercana(){
		assertTrue(sucursalRetiro.estaCercaDe(puntoTerminal));
	}
	
	@Test
	public void noEstoyCercaDeUnaSucursalLejana(){
		assertFalse(sucursalMartinez.estaCercaDe(puntoTerminal));
	}
	
	@Test
	public void testElBancoPrestaAlgunoDeLosServiciosBuscados(){
		assertTrue(sucursalRetiro.buscarPOI("Rentas"));
	}
	
	@Test
	public void testElBancoNoPRestaAlgunoDeLosServiciosBuscados(){
		assertFalse(sucursalMartinez.buscarPOI("CP"));
	}
	
	@Test
	public void estoyDisponibleconServicioHorarioIN(){
		assertTrue(sucursalRetiro.estaDisponible("CP", lunes12hs));
	}	
	
	// el horario ingresado no se encuentra en el servicio seleccionado
		// pero se encuentra en rango horario banco
	@Test
	public void estoyDisponibleconServicioHorarioINBancoOUTServicio(){
		assertTrue(sucursalRetiro.estaDisponible("Rentas", jueves11hs));
	}
	
	// el horario ingresado  se encuentra en el servicio seleccionado
	// pero no se encuentra en rango horario banco
	@Test
	public void estoyDisponibleconServicioInexisitenteHorarioINServicioOUTBanco(){
		assertFalse(sucursalRetiro.estaDisponible("Rentas", sabado1210hs));
	}
	
	// el horario ingresado no se encuentra en el servicio seleccionado
	// y no se encuentra en rango horario banco
	@Test
	public void estoyDisponibleconServicioHorarioOUTBancoOUTServicio(){
		assertFalse(sucursalRetiro.estaDisponible("CP", sabado23hs));
	}
	
	@Test
	public void conversorJsonBancoServicios(){
		JSONObject obj = new JSONObject();
		obj.put("banco", "Banco de la plaza");
		obj.put("x", "35");
		obj.put("y", "72");
		obj.put("sucursal", "Avellaneda");
		obj.put("gerente", "Pablo Perez");
		obj.put("servicios", "[cobrocheques,dep�sitos,extracciones]");
		POI banco = adapter.jsonAbanco(obj);
		
		assertEquals("Avellaneda",banco.getBarrio());
		assertEquals(35,(int)banco.getUbicacionActual().latitude());

	}
	
	
	@Test
	public void conversorJsonBancoUbicacion(){
		JSONObject obj = new JSONObject();
		obj.put("banco", "Banco de la plaza");
		obj.put("x", "35");
		obj.put("y", "72");
		obj.put("sucursal", "Avellaneda");
		obj.put("gerente", "Pablo Perez");
		obj.put("servicios", "[cobrocheques,dep�sitos,extracciones]");
		POI banco = adapter.jsonAbanco(obj);
		
		assertEquals(35,(int) banco.getUbicacionActual().latitude());
	}

	@Test
	public void conversorJsonBancoDireccionNombre(){
		JSONObject obj = new JSONObject();
		obj.put("banco", "Banco de la plaza");
		obj.put("x", "35");
		obj.put("y", "72");
		obj.put("sucursal", "Avellaneda");
		obj.put("gerente", "Pablo Perez");
		obj.put("servicios", "[cobrocheques,dep�sitos,extracciones]");
		POI banco = adapter.jsonAbanco(obj);
		
		assertEquals("Banco de la plaza",banco.getNombre());

	}
	
	@Test
	public void conversorJsonBancoBarrio(){
		JSONObject obj = new JSONObject();
		obj.put("banco", "Banco de la plaza");
		obj.put("x", "35");
		obj.put("y", "72");
		obj.put("sucursal", "Avellaneda");
		obj.put("gerente", "Pablo Perez");
		obj.put("servicios", "[cobrocheques,dep�sitos,extracciones]");
		POI banco = adapter.jsonAbanco(obj);
		
		assertEquals("Avellaneda",banco.getBarrio());

	}
	
	@Test
	public void conversionBancos(){
		
		int sizeAnterior = 0;
		
		List<POI> listaBancos = adapter.buscarPOI("lalala");
		assertEquals(sizeAnterior + 2 , listaBancos.size());

	}
	
	@Test
	public void pruebaBuilder(){
		assertEquals(bancoGalicia.getNombre(), "Banco Galicia");
	}

	public void buscoPOIsConAdaptadorYMeDevuelvePOIS(){
		
		//Creo los POIS correspondientes
		
		SucursalBanco bancoDeLaPlaza = new SucursalBanco();
		
		bancoDeLaPlaza.setNombre("Banco de la plaza");
		bancoDeLaPlaza.setUbicacionActual(new Point(35,72));
		bancoDeLaPlaza.setBarrio("Avellaneda");
		List<Servicio> listaServ = new ArrayList<Servicio>();
		
		Servicio servCobro = new Servicio("cobrocheques",null);
		Servicio servDepo = new Servicio("depositos",null);
		Servicio servExtracciones = new Servicio("extracciones",null);
		Servicio servTransferencias = new Servicio("transferencias",null);
		Servicio servSeguros = new Servicio("seguros",null);
		
		listaServ.add(servCobro);
		listaServ.add(servDepo);
		listaServ.add(servExtracciones);
		
		bancoDeLaPlaza.setListaServicios(listaServ);
		
		//Finalizo el primer POI
		
		//Creo otro POI
		
		SucursalBanco bancoDeLaPlaza2 = new SucursalBanco();
		
		bancoDeLaPlaza2.setNombre("Banco de la plaza");
		bancoDeLaPlaza2.setUbicacionActual(new Point(35,72));
		bancoDeLaPlaza2.setBarrio("Caballito");
		List<Servicio> listaServ2 = new ArrayList<Servicio>();
		
		listaServ2.add(servCobro);
		listaServ2.add(servDepo);
		listaServ2.add(servExtracciones);
		listaServ2.add(servTransferencias);
		listaServ2.add(servSeguros);
		
		bancoDeLaPlaza2.setListaServicios(listaServ2);
		
		//Finalizo el segundo POI
		
		List<POI> listaResultadoEsperado = new ArrayList<POI>();
		listaResultadoEsperado.add(bancoDeLaPlaza);
		listaResultadoEsperado.add(bancoDeLaPlaza2);
		
		assertEquals(listaResultadoEsperado.get(0).getBarrio(), adapter.buscarPOI("15").get(0).getBarrio());
		assertEquals(listaResultadoEsperado.get(0).getNombre(), adapter.buscarPOI("15").get(0).getNombre());
		assertEquals((int)listaResultadoEsperado.get(0).getUbicacionActual().longitude(), (int)adapter.buscarPOI("15").get(0).getUbicacionActual().longitude());
		assertEquals((int)listaResultadoEsperado.get(0).getUbicacionActual().latitude(), (int)adapter.buscarPOI("15").get(0).getUbicacionActual().latitude());
		
		assertEquals(listaResultadoEsperado.get(1).getBarrio(), adapter.buscarPOI("15").get(1).getBarrio());
		assertEquals(listaResultadoEsperado.get(1).getNombre(), adapter.buscarPOI("15").get(1).getNombre());
		assertEquals((int)listaResultadoEsperado.get(1).getUbicacionActual().longitude(), (int)adapter.buscarPOI("15").get(1).getUbicacionActual().longitude());
		assertEquals((int)listaResultadoEsperado.get(1).getUbicacionActual().latitude(), (int)adapter.buscarPOI("15").get(1).getUbicacionActual().latitude());

		assertEquals(listaResultadoEsperado.size(), adapter.buscarPOI("15").size());



	}
	

} 
