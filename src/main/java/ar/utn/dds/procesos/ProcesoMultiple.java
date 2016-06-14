package ar.utn.dds.procesos;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;

public class ProcesoMultiple implements Proceso{

	List<Proceso> procesosAEjectutar = new ArrayList<Proceso>();
	String nombre;
	int idProcesoMultiple;
	
	@Override
	public void ejecutarse(EstrategiaPorFallo estrategiaPorFallo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	
	
}
