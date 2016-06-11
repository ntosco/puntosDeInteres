package ar.utn.dds.roles;

import ar.utn.dds.procesos.Proceso;

public class RolAdministrador implements Rol {

	@Override
	public void ejecutarProceso(Proceso proceso) {
			proceso.ejecutarse();
	}

}
