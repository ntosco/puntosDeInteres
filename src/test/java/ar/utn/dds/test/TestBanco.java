package ar.utn.dds.test;

import static org.junit.Assert.*;


import java.util.List;

import org.json.simple.JSONObject;
import org.junit.*;

import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.utils.BusquedaDePuntos;

import ar.utn.dds.buscador.StubBuscadorBanco;

import ar.utn.dds.utils.Conversor;


public class TestBanco extends JuegoDeDatos {
	public List<SucursalBanco> listaBancos;
	
	@Before

	public void SetUp(){
		setUpGeneral();
		setUpBanco();

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
		SucursalBanco banco = Conversor.getInstance().jsonAbanco(obj);
		//assertTrue(banco.getBarrio()=="Avellaneda");
		//assertTrue(banco.getDireccionNombre()== "Banco de la plaza");
		//assertTrue(banco.getUbicacionActual().latitude() == 35);
		assertTrue(banco.getListaServicios().size() == 3);
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
		SucursalBanco banco = Conversor.getInstance().jsonAbanco(obj);
		assertTrue(banco.getUbicacionActual().latitude() == 35);
		
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
		SucursalBanco banco = Conversor.getInstance().jsonAbanco(obj);
		assertTrue(banco.getDireccionNombre()== "Banco de la plaza");
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
		SucursalBanco banco = Conversor.getInstance().jsonAbanco(obj);
		assertTrue(banco.getBarrio()=="Avellaneda");
	}
	
	@Test
	public void conversionBancos(){
		BusquedaDePuntos.setBuscadorDeBancos(new StubBuscadorBanco());
		List<SucursalBanco> listaBancos = BusquedaDePuntos.buscarBancoEnRepoExterno("lalala");
		assertTrue(listaBancos.size() == 2);
	}
	
	@Test
	public void conversionBancosServicios(){
		BusquedaDePuntos.setBuscadorDeBancos(new StubBuscadorBanco());
		List<SucursalBanco> listaBancos = BusquedaDePuntos.buscarBancoEnRepoExterno("lalala");
		assertTrue(listaBancos.get(0).getListaServicios().size() == 3);
	}
	
	

	
	
} 
