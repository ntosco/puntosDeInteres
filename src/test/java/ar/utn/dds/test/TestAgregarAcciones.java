package ar.utn.dds.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.observers.Observador;
import ar.utn.dds.observers.ObservadorAlmacenamientoDeConsultas;
import ar.utn.dds.observers.ObservadorPorFecha;
import ar.utn.dds.observers.ObservadorTiempoBusqueda;
import ar.utn.dds.procesos.ModificarAcciones;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.repositorio.RepositorioDeUsuarios;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.usuarios.UsuarioConcreto;

public class TestAgregarAcciones extends JuegoDeDatos {
	
	private ModificarAcciones procesoModificarAcciones = new ModificarAcciones();
	private List<Observador> acciones = new ArrayList<Observador>();
	private Observador tiempoDeBusqueda = new ObservadorTiempoBusqueda();
	private Observador porFecha = new ObservadorPorFecha();
	private Observador almacenamientoDeConsultas = new ObservadorAlmacenamientoDeConsultas();
	private RepositorioDeUsuarios usuarios;
	private EstrategiaPorFallo fallo;
	private Usuario juan = new UsuarioConcreto();
	
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		setUpCGP();
		setUpLocalComercial();
		setUpColectivos();
		RepositorioDeUsuarios.getInstance().agregarUsuario(juan);
		acciones.add(almacenamientoDeConsultas);
		acciones.add(porFecha);
		acciones.add(tiempoDeBusqueda);
	}
	
	@Test
	public void testAgregarAccionesALaLista(){
		
		procesoModificarAcciones.activar(tiempoDeBusqueda);
		procesoModificarAcciones.activar(porFecha);
		procesoModificarAcciones.ejecutarse(fallo);
		assertEquals(juan.getAccionesObservers().size(),2);
	}
	
	@Test 
	public void testQuitarAcciones(){
		
		procesoModificarAcciones.activar(tiempoDeBusqueda);
		procesoModificarAcciones.activar(porFecha);
		procesoModificarAcciones.desactivar(tiempoDeBusqueda);
		procesoModificarAcciones.desactivar(porFecha);
		procesoModificarAcciones.ejecutarse(fallo);
		assertEquals(juan.getAccionesObservers().size(),0);
		
	}
	
	@Test 
	public void testAgregarListaDeAcciones(){
		procesoModificarAcciones.activar(acciones);
		procesoModificarAcciones.ejecutarse(fallo);
		assertEquals(juan.getAccionesObservers().size(),3);
	}
	
	@Test
	public void testDesactivarListaDeAcciones(){
		procesoModificarAcciones.activar(acciones);
		procesoModificarAcciones.desactivar(acciones);
		procesoModificarAcciones.ejecutarse(fallo);
		assertEquals(juan.getAccionesObservers().size(),0);
	}
}
