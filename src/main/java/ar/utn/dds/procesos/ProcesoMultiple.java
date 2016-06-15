package ar.utn.dds.procesos;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;

public class ProcesoMultiple extends Proceso{

	List<Proceso> procesosAEjectutar = new ArrayList<Proceso>();
	
	@Override
	public void ejecutarse(EstrategiaPorFallo estrategiaPorFallo) {
		// TODO Auto-generated method stub
		
	}


	
	
}
