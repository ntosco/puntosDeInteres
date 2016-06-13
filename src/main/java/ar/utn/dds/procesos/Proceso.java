package ar.utn.dds.procesos;

import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;

public interface Proceso {
	
	//TODO: Ver si lo que devuelve es void o el estado de ejecución
	public void ejecutarse(EstrategiaPorFallo estrategiaPorFallo);
	
	public String getNombre();
	

}
