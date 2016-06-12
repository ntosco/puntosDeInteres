package ar.utn.dds.roles;

import ar.utn.dds.exceptions.InvalidPermissionsException;
import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;

public class RolConsulta implements Rol {

//	TODO :Controlar si es correcto que en que cada rol nuevo 
//	implemente este método o se solucione con un IF (DECISIÓN)
	
	@Override
	public void ejecutarProceso(Proceso proceso, EstrategiaPorFallo estrategiaPorFallo) {
		throw new InvalidPermissionsException("El usuario no contiene los permisos adecuados para la ejecución del proceso");
	}

}
