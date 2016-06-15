package ar.utn.dds.test;


import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.procesos.estrategiaFallo.NoRealizarAccionPorFalla;

public class TestProcesoMultiple extends JuegoDeDatos{

	@Before
	
	public void SetUp(){
		setUpProcesos();
		setUpUsuario();
		setUpEstrategiasXFallo();
	}
	
	@Test
	public void ejecutoProcesoMultipleOK(){
		procesoMultiplePruebaOK.ejecutarse(new NoRealizarAccionPorFalla());
	}
	
	@Test //FIXME: Arreglar el error feo que lanza por consola
	public void ejecutoProcesoMultiplePeroNoPuedeActualizarLocalesComercialesPorNoTenerTXT(){
		procesoMultiplePruebaERROR.ejecutarse(new NoRealizarAccionPorFalla());
	}
	
	
	
}
