package ar.utn.dds.test;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.procesos.estrategiaFallo.ReplicaPorFallo;
import ar.utn.dds.repositorio.Repositorio;

public class TestEstrategiasPorFallo extends JuegoDeDatos {
	
	
	@Before
	public void SetUp(){
		setUpEstrategiasXFallo();
		when(procesoErroneoMock.enEstadoErroneo()).thenReturn(true);
		when(procesoErroneoMock.getNombre()).thenReturn("Proceso Mock");
		
	}

	@Test
	public void replica3VecesElProceso(){
		estrategiaReplica3veces.ejecutarse(procesoErroneoMock);
		verify(procesoErroneoMock,times(3)).ejecutarse(this.estrategiaReplica3veces.getNoRealizarAccionPorFalla());
	}

	@Test
	public void noRealizaNingunaAcción(){
		estrategiaNoHaceNada.ejecutarse(procesoErroneoMock);
		verify(procesoErroneoMock,times(0)).ejecutarse(estrategiaNoHaceNada);
	}
	
//	@Test
//	public void enviaElCorreoPorFalla(){
//		estrategiaEnvioMensaje.ejecutarse(procesoErroneoMock);
//		verify(procesoErroneoMock,times(0)).ejecutarse(estrategiaEnvioMensaje);
//		verify(ServiceLocator.getInstance().getMailSender(),times(1)).enviarMail(any(Mail.class));
//		
//	}

}	
