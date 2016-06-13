package ar.utn.dds.roles;

import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.serviceLocator.ServiceLocatorReportes;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.utils.ResultadoDeProceso;

public class RolAdministrador implements Rol {

	@Override
	public void ejecutarProceso(Proceso proceso,EstrategiaPorFallo estrategiaPorFallo, Usuario usuarioEjecutor) {
			proceso.ejecutarse(estrategiaPorFallo);
			this.almacenarResultadoProceso(proceso,usuarioEjecutor);
	}

	private void almacenarResultadoProceso(Proceso proceso, Usuario usuarioEjecutor) {
		//TODO Decidir si se carga los datos de ejecución desde el proceso o por fuera;
		ResultadoDeProceso resultadoProcesoEjecutado = new ResultadoDeProceso();
		resultadoProcesoEjecutado.setUsuarioEjecutor(usuarioEjecutor);
		resultadoProcesoEjecutado.setProcesoEjecutado(proceso);
		
		ServiceLocatorReportes.getInstance().getHistorialProcesosEjecutados().almacenarResultado(resultadoProcesoEjecutado);
	}

}
