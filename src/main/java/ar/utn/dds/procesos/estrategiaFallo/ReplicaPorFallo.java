package ar.utn.dds.procesos.estrategiaFallo;

import ar.utn.dds.procesos.Proceso;

public class ReplicaPorFallo implements EstrategiaPorFallo{

	private Integer toleranciaDeFallo ;
	//FIXME Mantener hasta cambiar el command pasado por parametro por el closure "EstrategiaPorFallo.ejecutarse()"
	private EstrategiaPorFallo noRealizarAccionPorFalla = new NoRealizarAccionPorFalla();
	
	private ReplicaPorFallo(){}
	
	public ReplicaPorFallo(Integer toleranciaDeFallo) {
		super();
		this.toleranciaDeFallo = toleranciaDeFallo;
	}

	@Override
	public void ejecutarse(Proceso proceso) {

		for (int i = 0; i < toleranciaDeFallo; i++) {
		proceso.ejecutarse(this.noRealizarAccionPorFalla);
		if(!proceso.enEstadoErroneo())break;
		}
	}
	
	


}
