package ar.utn.dds.roles;

import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.serviceLocator.ServiceLocator;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.utils.ResultadoDeProceso;

public class RolAdministrador implements Rol {

	@Override
	public void ejecutarProceso(Proceso proceso, Usuario usuarioEjecutor) {
			proceso.ejecutarse(usuarioEjecutor);
			this.almacenarResultadoProceso(proceso);
	}

	private void almacenarResultadoProceso(Proceso proceso) {
		ResultadoDeProceso resultadoProcesoEjecutado = new ResultadoDeProceso(proceso);
		ServiceLocator.getInstance().getHistorialProcesosEjecutados().almacenarResultado(resultadoProcesoEjecutado);
	}
	
	

}
