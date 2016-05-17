package ar.utn.dds.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.POI.POI;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.juegoDeDatos.StubBuscadorBanco;
import ar.utn.dds.juegoDeDatos.StubBuscadorCGP;
import ar.utn.dds.observers.ObservadorParciales;
import ar.utn.dds.observers.ObservadorPorFecha;
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
		
		unUsuario = new UsuarioConsulta();
		unUsuario.setNombreUsuario("Samo");
		
		

	}
	
	
	
	@Test
	public void ultimaConsultaUsuario() {
		unUsuario.agregarObservador(observerXfecha);
		unUsuario.buscarPuntos("15");
		assertTrue(unUsuario.getUltimaConsulta().getPalabraBuscada() == "15");
		assertTrue(unUsuario.getUltimaConsulta().getNombreUsuario() == "Samo");
		assertTrue(unUsuario.getUltimaConsulta().getCantidadDeResultados() == 4);
	}
	
	
	@Test
	//Cuando agregan un nuevo regritro el hashtable debe crecer en tamaño
	public void observadorParcialesAgregaRegistroACantidadResultadosParciales() {
		unUsuario.agregarObservador(observerParciales);
		unUsuario.buscarPuntos("15");
		assertTrue(observerParciales.getCantidadResultadosParciales().size()==1);

	}
	@Test
	public void observadorPorFechaAgregaRegistroABusquedasPorFecha() {
		unUsuario.agregarObservador(observerXfecha);
		List<POI> listaResultado = unUsuario.buscarPuntos("15");
		//assertTrue(observerXfecha.getBusquedasPorFecha().get(unUsuario.getUltimaConsulta().getFecha())== listaResultado.size());
		assertTrue(observerXfecha.getBusquedasPorFecha().size()==1);

	}
	
	@Test
	public void observadorTotalesAgregaRegistroATotalesPorUsuario() {
		unUsuario.agregarObservador(observerTotales);
		List<POI> listaResultado = unUsuario.buscarPuntos("15");
		assertTrue(observerTotales.getTotalesPorUsuario().size()==1);
		assertTrue(observerTotales.getTotalesPorUsuario().get(unUsuario.getNombreUsuario())== listaResultado.size());
	}
	
	//Cuando se actualiza un registro no crece en tamaño el hashtable, solo cambia la info del registro
	
	
	@Test
	public void observadorPorFechaActualizaBusquedasPorFecha() {
		observerXfecha.setBusquedasPorFecha(new Hashtable<String,Integer>());
		unUsuario.agregarObservador(observerXfecha);
		List<POI> listaResultado = unUsuario.buscarPuntos("15");
		unUsuario.buscarPuntos("15");
		assertTrue(observerXfecha.getBusquedasPorFecha().size()==1);
		assertTrue(observerXfecha.getBusquedasPorFecha().get(unUsuario.getUltimaConsulta().getFecha())== (listaResultado.size() + listaResultado.size()));		
	}
	
	@Test
	public void observadorTotalesActualizaTotalesPorUsuario() {
		observerTotales.setTotalesPorUsuario(new Hashtable<String,Integer>());
		unUsuario.agregarObservador(observerTotales);
		List<POI> listaResultado = unUsuario.buscarPuntos("15");
		unUsuario.buscarPuntos("15");
		assertTrue(observerTotales.getTotalesPorUsuario().size()==1);
		assertTrue(observerTotales.getTotalesPorUsuario().get(unUsuario.getNombreUsuario())== (listaResultado.size() + listaResultado.size()));		
	}
	

	
	//si la busqueda tarda mas de X cantidad de tiempo se notifica al admin
	public void observadorTiempoBusquedaNotificaAlAdministrador() {
		fail("Not yet implemented");
	}
	
	public void observadorTiempoBusquedaNooooooNotificaAlAdministrador() {
		fail("Not yet implemented");
	}
}
