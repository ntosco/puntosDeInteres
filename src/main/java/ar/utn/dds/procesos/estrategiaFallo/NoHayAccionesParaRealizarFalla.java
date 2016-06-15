package ar.utn.dds.procesos.estrategiaFallo;

import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.repositorio.RepositorioDeUsuarios;

public class NoHayAccionesParaRealizarFalla implements EstrategiaPorFallo{

	@Override
	public void ejecutarse(Proceso procesoEnEstadoDeError) {
		RepositorioDeUsuarios.getInstance().getUsuariosAdministradores().forEach(
				usr -> usr.notificarFalla());		
	}
}
