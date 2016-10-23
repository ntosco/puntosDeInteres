package ar.utn.dds.procesos.estrategiaFallo;

import javax.persistence.Entity;

import ar.utn.dds.procesos.Proceso;

@Entity
public class NoRealizarAccionPorFalla implements EstrategiaPorFallo{
	
	@Override
	public void ejecutarse(Proceso procesoEnEstadoDeError) {
		
	}

}
