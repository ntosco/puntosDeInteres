package ar.utn.dds.procesos;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;

public class ProcesoMultiple extends Proceso{


	List<Proceso> procesosAEjecutar = new ArrayList<Proceso>();
		

	@Override
	public void ejecutarse() {
		this.getEstado().setEstadoComoOK();
		procesosAEjecutar.forEach( 
			proceso -> {
				proceso.ejecutarse();
				this.actualizacionDeEstado(proceso);
			}
		);
	}
	
	public void actualizacionDeEstado(Proceso proceso){
		if(this.getEstado().esUnEstadoOk()){
			if( proceso.getEstado().esUnEstadoError()){
				this.getEstado().setEstadoComoErroneo();
//				throw new ProcessIncorrectExecutionException("El proceso multiple con id ",this.getIdProceso()," con descripcion ", this.getNombre(),"se ejecutó con errores");
			}
		}
	}
	
	public void setProcesos(List<Proceso> procesos) {
		procesosAEjecutar = procesos;
	}	
	@Override
	public void setEstrategiaPorFallo(EstrategiaPorFallo estrategiaPorFallo) {
		super.setEstrategiaPorFallo(estrategiaPorFallo);
		procesosAEjecutar.forEach(proceso->{proceso.setEstrategiaPorFallo(estrategiaPorFallo);});
	}
} 
