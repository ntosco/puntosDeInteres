package ar.utn.dds.procesos;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;

public class ProcesoMultiple implements Proceso{

	List<Proceso> procesosAEjecutar = new ArrayList<Proceso>();
	String nombre;
	int idProcesoMultiple;
	EstrategiaPorFallo estrategiaPorFallo;
	
	@Override
	public void ejecutarse(EstrategiaPorFallo estrategia) {
		this.setEstrategiaPorFallo(estrategia);
		procesosAEjecutar.forEach(
				proceso -> proceso.ejecutarse(proceso.getEstrategiaPorFallo())
				);
		estrategiaPorFallo.ejecutarse(this);
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public EstrategiaPorFallo getEstrategiaPorFallo() {
		return this.estrategiaPorFallo;
	}
	
	private void setEstrategiaPorFallo(EstrategiaPorFallo estrategia) {
		estrategiaPorFallo = estrategia;
	}
	
	
}
