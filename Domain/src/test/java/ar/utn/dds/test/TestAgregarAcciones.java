package ar.utn.dds.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
import ar.utn.dds.procesos.estrategiaFallo.NoHayAccionesParaRealizarFalla;
import ar.utn.dds.repositorio.RepositorioDeUsuarios;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.usuarios.UsuarioConcreto;
import ar.utn.dds.utils.Estado;

public class TestAgregarAcciones extends JuegoDeDatos {
	
	private ModificarAcciones procesoModificarAcciones = new ModificarAcciones();
	private List<Observador> acciones = new ArrayList<Observador>();
	private List<Observador> acciones2 = new ArrayList<Observador>();
	private Observador tiempoDeBusqueda = new ObservadorTiempoBusqueda();
	private Observador porFecha = new ObservadorPorFecha();
	private Observador almacenamientoDeConsultas = new ObservadorAlmacenamientoDeConsultas();
	private Usuario juan;
	private UsuarioConcreto pedro;
	private UsuarioConcreto martin;
	private EstrategiaPorFallo fallo;
	private Estado estadoOK;
	private Estado estadoError;
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		setUpCGP();
		setUpLocalComercial();
		setUpColectivos();
		juan = new UsuarioConcreto();
		martin = new UsuarioConcreto();
		pedro = new UsuarioConcreto();
		RepositorioDeUsuarios.getInstance().agregarUsuario(juan);
		RepositorioDeUsuarios.getInstance().agregarUsuario(pedro);
		RepositorioDeUsuarios.getInstance().agregarUsuario(martin);
		acciones.add(almacenamientoDeConsultas);
		acciones.add(porFecha);
		acciones.add(tiempoDeBusqueda);
		acciones2.add(tiempoDeBusqueda);
		pedro.setAccionesObservers(acciones2);
		estadoOK = new Estado();
		estadoOK.setDescripcion("Ok");
		estadoOK.setEstadoComoOK();
		estadoError = new Estado();
		estadoError.setDescripcion("No hay acciones para ejecutar");
		estadoError.setEstadoComoErroneo();
		fallo = new NoHayAccionesParaRealizarFalla();
		
	}
	
	@Test
	public void testAgregarAccionesALaLista(){
		
		procesoModificarAcciones.activar(tiempoDeBusqueda);
		procesoModificarAcciones.activar(porFecha);
		procesoModificarAcciones.setEstrategiaPorFallo(new NoHayAccionesParaRealizarFalla());
		procesoModificarAcciones.ejecutarse();
		assertEquals(juan.getAccionesObservers().size(),2);
	}
	
	@Test 
	public void testQuitarAcciones(){
		
		procesoModificarAcciones.activar(tiempoDeBusqueda);
		procesoModificarAcciones.activar(porFecha);
		procesoModificarAcciones.desactivar(tiempoDeBusqueda);
		procesoModificarAcciones.desactivar(porFecha);
		procesoModificarAcciones.setEstrategiaPorFallo(new NoHayAccionesParaRealizarFalla());
		procesoModificarAcciones.ejecutarse();
		assertEquals(juan.getAccionesObservers().size(),0);
		
	}
	
	@Test 
	public void testAgregarListaDeAcciones(){
		procesoModificarAcciones.activar(acciones);
		procesoModificarAcciones.setEstrategiaPorFallo(new NoHayAccionesParaRealizarFalla());
		procesoModificarAcciones.ejecutarse();
		assertEquals(juan.getAccionesObservers().size(),3);
	}
	
	@Test
	public void testDesactivarListaDeAcciones(){
		procesoModificarAcciones.activar(acciones);
		procesoModificarAcciones.desactivar(acciones);
		procesoModificarAcciones.setEstrategiaPorFallo(new NoHayAccionesParaRealizarFalla());
		procesoModificarAcciones.ejecutarse();
		assertEquals(juan.getAccionesObservers().size(),0);
	}
	
	@Test
	public void testSetDeUnaListaDeAcciones(){
		procesoModificarAcciones.setAcciones(acciones);
		procesoModificarAcciones.setEstrategiaPorFallo(new NoHayAccionesParaRealizarFalla());
		procesoModificarAcciones.ejecutarse();
		assertEquals(juan.getAccionesObservers().size(),3);
	}
	
	@Test
	public void testProcesoFallaYEnviaMensajeDeError(){
			
			EstrategiaPorFallo fallo = mock(EstrategiaPorFallo.class);
			procesoModificarAcciones = new ModificarAcciones();
			
			procesoModificarAcciones.setAcciones(new ArrayList<Observador>());
			procesoModificarAcciones.setEstrategiaPorFallo(fallo);
			procesoModificarAcciones.ejecutarse();

			verify(fallo).ejecutarse(procesoModificarAcciones);

		}
	
	@Test
	public void copiarUnaListaDeObservers(){
		
		procesoModificarAcciones.copiarLista(acciones2);
		pedro.setAccionesObservers(acciones);
		assertEquals(1, procesoModificarAcciones.getUndoList().size());
	
	}
	
	@Test
	public void testundoEstado(){

		procesoModificarAcciones.setEstado(estadoOK);
		procesoModificarAcciones.setAcciones(new ArrayList<Observador>());
		procesoModificarAcciones.setEstrategiaPorFallo(new NoHayAccionesParaRealizarFalla());
		procesoModificarAcciones.ejecutarse();
		assertEquals(procesoModificarAcciones.estado.getValor(), 2);
		assertEquals(procesoModificarAcciones.estado.getDescripcion(), "No hay acciones para ejecutar");
		
		procesoModificarAcciones.undoEstado();
		assertEquals(procesoModificarAcciones.estado.getValor(), 1);
	}
	
	@Test
	public void testUndoAcciones(){
		
		juan.setAccionesObservers(acciones2);
		assertEquals(1, juan.getAccionesObservers().size());
		
		procesoModificarAcciones.setAcciones(acciones);
		procesoModificarAcciones.setEstrategiaPorFallo(new NoHayAccionesParaRealizarFalla());
		procesoModificarAcciones.ejecutarse();
		assertEquals(3, juan.getAccionesObservers().size());
		
		procesoModificarAcciones.undoListaDeAcciones();
		assertEquals(1, juan.getAccionesObservers().size());
		
	}
	
	@Test
	public void testUndo(){
		
		// acciones -> 3 observers
		// acciones 2 -> 1 observer
		
		juan.setAccionesObservers(acciones2);
		procesoModificarAcciones.setEstado(estadoError);
		assertEquals(juan.getAccionesObservers().size(),1);
		assertEquals(procesoModificarAcciones.estado.getValor(), 2);
		
		procesoModificarAcciones.setAcciones(acciones);
		procesoModificarAcciones.setEstrategiaPorFallo(new NoHayAccionesParaRealizarFalla());
		procesoModificarAcciones.ejecutarse();
		assertEquals(procesoModificarAcciones.estado.getValor(), 1);
		assertEquals(juan.getAccionesObservers().size(),3);
		
		
		procesoModificarAcciones.undo();
		assertEquals(juan.getAccionesObservers().size(),1);
		assertEquals(procesoModificarAcciones.estado.getValor(), 2);
	}
	
	@Test
	public void testEsErrorYSeteaComoErrorElEstado(){
		
		procesoModificarAcciones.setAcciones(new ArrayList<Observador>());
		procesoModificarAcciones.setEstrategiaPorFallo(fallo);
		procesoModificarAcciones.ejecutarse();
		assertEquals(procesoModificarAcciones.getEstado().getValor(),2);
	}
}
