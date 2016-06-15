package ar.utn.dds.test;

import org.junit.Before;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.procesos.estrategiaFallo.ReplicaPorFallo;
import ar.utn.dds.repositorio.Repositorio;

public class TestEstrategiasPorFallo extends JuegoDeDatos {
	
	EstrategiaPorFallo estrategiaReplica3veces = new ReplicaPorFallo(3);
	Proceso procesoErroneoMock = mock(Proceso.class);
	Proceso procesoOKMock = mock(Proceso.class);
	Repositorio repositorio = Repositorio.getInstance();

	
	@Before
	public void SetUp(){
		when(procesoErroneoMock.enEstadoErroneo()).thenReturn(true);
		
		
	}

	public void replica3VecesElProceso(){
		estrategiaReplica3veces.ejecutarse(procesoErroneoMock);
		repositorio.create(parada15);
		
		
	}
}
