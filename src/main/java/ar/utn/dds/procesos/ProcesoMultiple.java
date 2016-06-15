package ar.utn.dds.procesos;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;

public class ProcesoMultiple extends Proceso{

	List<Proceso> procesosAEjecutar = new ArrayList<Proceso>();
	String nombre;
	int idProcesoMultiple;
	
	
	@Override
	public void ejecutarse(EstrategiaPorFallo estrategiaDeFallo) {
		procesosAEjecutar.forEach( 
			proceso -> {
				proceso.ejecutarse(estrategiaDeFallo);
				this.actualizacionDeEstado(proceso);
		}
		);
	}

	public void setProcesos(List<Proceso> procesos) {
		procesosAEjecutar = procesos;
	}
	
	public void actualizacionDeEstado(Proceso proceso){
		
			if(this.falloEnSuEjecucion(proceso)){
				this.getEstado().setValor(1);
			}else
				this.getEstado().setValor(2);		

	}
	
	public boolean falloEnSuEjecucion(Proceso proceso){
		return proceso.getEstado().getValor() == 1;
	}
}
