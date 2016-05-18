package ar.utn.dds.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.POI.POI;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.juegoDeDatos.StubBuscadorBanco;
import ar.utn.dds.juegoDeDatos.StubBuscadorCGP;
import ar.utn.dds.observers.ObservadorParciales;
import ar.utn.dds.observers.ObservadorPorFecha;
import ar.utn.dds.observers.ObservadorTiempoBusqueda;
import ar.utn.dds.observers.ObservadorTotales;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.usuarios.UsuarioConsulta;
import ar.utn.dds.utils.AdapterBancos;
import ar.utn.dds.utils.AdapterCGP;
import ar.utn.dds.utils.BusquedaDePuntos;

public class TestObservers extends JuegoDeDatos{

	public AdapterBancos adapterBanco;
	public AdapterCGP adapterCGP;
	public UsuarioConsulta unUsuario;
	Repositorio repositorio = Repositorio.getInstance();
	BusquedaDePuntos buscadorPuntos = BusquedaDePuntos.getInstance();
	
	public ObservadorParciales observerParciales;
	public ObservadorTiempoBusqueda observadorTiempoBusqueda;
	ObservadorPorFecha observerXfecha = ObservadorPorFecha.getInstance();
	ObservadorTotales observerTotales = ObservadorTotales.getInstance();
	
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

		observerParciales = new ObservadorParciales();
		observadorTiempoBusqueda = new ObservadorTiempoBusqueda();
		
		unUsuario = new UsuarioConsulta();
		unUsuario.setNombreUsuario("Samo");
		
		

	}
	
	@After
	public void TearDown(){
		repositorio.clean();
		buscadorPuntos.borrarListaServicios();	
	}
	
	@Test
	public void ultimaConsultaUsuario() {
		unUsuario.agregarObservador(observerXfecha);
		unUsuario.buscarPuntos("15");
		assertEquals("Samo" ,unUsuario.getUltimaConsulta().getNombreUsuario());
		assertEquals("15" , unUsuario.getUltimaConsulta().getPalabraBuscada());
		assertEquals(4 , unUsuario.getUltimaConsulta().getCantidadDeResultados());
	}
	
	
	@Test
	//Cuando agregan un nuevo regritro el hashtable debe crecer en tamaño
	public void observadorParcialesAgregaRegistroACantidadResultadosParciales() {
		unUsuario.agregarObservador(observerParciales);
		unUsuario.buscarPuntos("15");
		assertEquals(1,observerParciales.getCantidadResultadosParciales().size());
	}
	
	@Test
	public void observadorPorFechaAgregaRegistroABusquedasPorFecha() {
		unUsuario.agregarObservador(observerXfecha);
		unUsuario.buscarPuntos("15");
		assertEquals(1,observerXfecha.getBusquedasPorFecha().size());

	}
	
	@Test
	public void observadorTotalesAgregaRegistroATotalesPorUsuario() {
		unUsuario.agregarObservador(observerTotales);
		List<POI> listaResultado = unUsuario.buscarPuntos("15");
		assertEquals(1,observerTotales.getTotalesPorUsuario().size());
		assertEquals(listaResultado.size(),(int)observerTotales.getTotalesPorUsuario().get(unUsuario.getNombreUsuario()));
	}
	
	//Cuando se actualiza un registro no crece en tamaño el hashtable, solo cambia la info del registro
	
	
	@Test
	public void observadorPorFechaActualizaBusquedasPorFecha() {
		observerXfecha.setBusquedasPorFecha(new Hashtable<String,Integer>());
		unUsuario.agregarObservador(observerXfecha);
		List<POI> listaResultado = unUsuario.buscarPuntos("15");
		unUsuario.buscarPuntos("15");
		assertEquals(1,observerXfecha.getBusquedasPorFecha().size());
		assertEquals(8, (int)observerXfecha.getBusquedasPorFecha().get(unUsuario.getUltimaConsulta().getFecha()));	
	}
	
	@Test
	public void observadorTotalesActualizaTotalesPorUsuario() {
		observerTotales.setTotalesPorUsuario(new Hashtable<String,Integer>());
		unUsuario.agregarObservador(observerTotales);
		List<POI> listaResultado = unUsuario.buscarPuntos("15");
		unUsuario.buscarPuntos("15");
		assertEquals(1,observerTotales.getTotalesPorUsuario().size());
		assertEquals(8, (int)observerTotales.getTotalesPorUsuario().get(unUsuario.getNombreUsuario()));		
	}
	

	
	//si la busqueda tarda mas de X cantidad de tiempo se notifica al admin
	@Test
	public void observadorTiempoBusquedaNotificaAlAdministrador() {
		observadorTiempoBusqueda.setTiempoDeBusquedaMaximo(0);
		unUsuario.agregarObservador(observadorTiempoBusqueda);
		unUsuario.buscarPuntos("15");
		assertEquals(1,observadorTiempoBusqueda.getListaConsultasNotificar().size());
		
	}
	
	@Test
	public void observadorTiempoBusquedaNooooooNotificaAlAdministrador() {
		observadorTiempoBusqueda.setTiempoDeBusquedaMaximo(99999999);
		unUsuario.agregarObservador(observadorTiempoBusqueda);
		unUsuario.buscarPuntos("15");
		assertEquals(0,observadorTiempoBusqueda.getListaConsultasNotificar().size());
	}
}
