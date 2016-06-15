package ar.utn.dds.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.serviceLocator.ServiceLocator;

public class TestIntegracionProcesos extends JuegoDeDatos {
	
	Repositorio repositorio = Repositorio.getInstance();

	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		setUpCGP();
		setUpLocalComercial();
		setUpColectivos();
		setUpEstrategiasXFallo();
		setUpProcesos();
		
		when(procesoErroneoMock.enEstadoErroneo()).thenReturn(true);
		when(procesoErroneoMock.getNombre()).thenReturn("Proceso Mock");
		when(procesoErroneoMock.enEstadoErroneo()).thenReturn(false);

	}
	
	@Test 
	public void usuarioConEstrategiaReplicar3VecesConProcesoEnError(){
		Integer cantidadProcesos = ServiceLocator.getInstance().getHistorialProcesosEjecutados().cantidadProcesosEjecutados();
		usuarioAdmin.setEstrategiaPorFallo(estrategiaReplica3veces);
		usuarioAdmin.ejecutarProceso(procesoErroneoMock);
		assertEquals(++cantidadProcesos,ServiceLocator.getInstance().getHistorialProcesosEjecutados().cantidadProcesosEjecutados());
	}

	
}
