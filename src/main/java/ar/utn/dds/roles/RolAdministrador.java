package ar.utn.dds.roles;

import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;

public class RolAdministrador implements Rol {

	@Override
	public void ejecutarProceso(Proceso proceso,EstrategiaPorFallo estrategiaPorFallo) {
			proceso.ejecutarse(estrategiaPorFallo);
	}

}
