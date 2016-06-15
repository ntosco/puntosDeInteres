package ar.utn.dds.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.exceptions.InvalidPermissionsException;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.serviceLocator.ServiceLocator;

public class TestIntegracionProcesos extends JuegoDeDatos {
	

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
	@Test 
	public void usuarioConEstrategiaAsignaProcesoEstadoError(){
		usuarioAdmin.setEstrategiaPorFallo(estrategiaPorFalloMock);
		usuarioAdmin.ejecutarProceso(procesoActualizarLocalesComercialesSinTXT);
		assertTrue(procesoActualizarLocalesComercialesSinTXT.enEstadoErroneo());
	}
		
	@Test 
	public void usuarioConEstrategiaAsignaProcesoEstadoOK(){
		usuarioAdmin.setEstrategiaPorFallo(estrategiaPorFalloMock);
		usuarioAdmin.ejecutarProceso(procesoActualizarLocalesComerciales);
		assertFalse(procesoActualizarLocalesComercialesSinTXT.enEstadoErroneo());
	}
	
	@Test(expected = InvalidPermissionsException.class)
	public void usuarioConsultaNoEjecutaProcesoConError(){
		usuarioConsulta.ejecutarProceso(procesoErroneoMock);
	}
	@Test(expected = InvalidPermissionsException.class)
	public void usuarioConsultaNoEjecutaProcesoOK(){
		usuarioConsulta.ejecutarProceso(procesoOKMock);
	}
	
	@Test
	public void usuarioEjecutaProcesoErrorYEstrategiaPorFalloSeEjecuta(){
		usuarioAdmin.setEstrategiaPorFallo(estrategiaPorFalloMock);
		usuarioAdmin.ejecutarProceso(procesoActualizarLocalesComercialesSinTXT);
		verify(estrategiaPorFalloMock,times(1)).ejecutarse(procesoActualizarLocalesComercialesSinTXT);
	}
	
	@Test
	public void usuarioEjecutaProcesoDeFormaOKYEstrategiaPorFalloNoSeEjecuta(){
		usuarioAdmin.setEstrategiaPorFallo(estrategiaPorFalloMock);
		usuarioAdmin.ejecutarProceso(procesoActualizarLocalesComerciales);
		verify(estrategiaPorFalloMock,times(0)).ejecutarse(procesoActualizarLocalesComercialesSinTXT);
	}
		
	
	
	
}
