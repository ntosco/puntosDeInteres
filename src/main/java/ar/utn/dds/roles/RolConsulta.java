package ar.utn.dds.roles;

import ar.utn.dds.exceptions.InvalidPermissionsException;
import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.usuarios.Usuario;

public class RolConsulta implements Rol {


	
	@Override
	public void ejecutarProceso(Proceso proceso, EstrategiaPorFallo estrategiaPorFallo, Usuario usuarioEjecutor) {
		throw new InvalidPermissionsException("El usuario no contiene los permisos adecuados para la ejecución del proceso");
	}

}
