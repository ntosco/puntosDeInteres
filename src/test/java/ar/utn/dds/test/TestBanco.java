package ar.utn.dds.test;

import static org.junit.Assert.*;

import java.util.List;

import org.json.simple.JSONObject;
import org.junit.*;

import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.juegoDeDatos.StubBuscadorBanco;
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
	
/*	
 * LOS POIS NO ENTIENDEN EL METODO getListaServicios()
 * 
 * @Test
	public void conversionBancosServicios(){
		List<POI> listaBancos = adapter.buscarPOI("lalala");
		assertTrue(listaBancos.get(0).getListaServicios().size() == 3);
	}
	
	
*/
	
	
} 
