package ar.utn.dds.roles;

import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;

public interface Rol {

	void ejecutarProceso(Proceso proceso,EstrategiaPorFallo estrategiaPorFallo);

}
