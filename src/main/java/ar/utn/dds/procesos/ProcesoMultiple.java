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
		procesosAEjecutar.forEach(proceso -> proceso.ejecutarse(estrategiaDeFallo));
	}

		
	
}
