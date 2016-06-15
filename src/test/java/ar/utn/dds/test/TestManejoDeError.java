package ar.utn.dds.test;

import org.junit.After;
import org.junit.Before;

import static org.mockito.Mockito.mock;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.estrategiaFallo.EnvioMensajePorFalla;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.procesos.estrategiaFallo.NoRealizarAccionPorFalla;
import ar.utn.dds.procesos.estrategiaFallo.ReplicaPorFallo;

public class TestManejoDeError extends JuegoDeDatos{
	
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		setUpCGP();
		setUpLocalComercial();
		setUpColectivos();
		
		EstrategiaPorFallo estrategiaEnviarMail = new EnvioMensajePorFalla();
		EstrategiaPorFallo estrategiaNoRealizaAccion = new NoRealizarAccionPorFalla();
		EstrategiaPorFallo estrategiaReplica3VecesPorFallo = new ReplicaPorFallo(3);
		
		
	}


}
