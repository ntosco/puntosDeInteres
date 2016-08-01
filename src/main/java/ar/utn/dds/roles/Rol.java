package ar.utn.dds.roles;

import ar.utn.dds.exceptions.InvalidPermissionsException;
import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.usuarios.Usuario;

public interface Rol {

	void ejecutarProceso(Proceso proceso,Usuario usuarioEjecutor) throws InvalidPermissionsException;

}
