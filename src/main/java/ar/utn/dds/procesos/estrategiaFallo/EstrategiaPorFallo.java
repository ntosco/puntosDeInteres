package ar.utn.dds.procesos.estrategiaFallo;

import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.utils.Estado;

public interface EstrategiaPorFallo {
	
	void ejecutarse(Proceso procesoEnEstadoDeError);
	
	

}
