package ar.utn.dds.creacionales;

import java.util.List;
import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.ProcesoMultiple;


public class ProcesoMultipleBuilder {


	private List<Proceso> procesos;
	private String nombre;
	
	public ProcesoMultipleBuilder crearListaProcesos(List<Proceso> lista) {
		procesos = lista;
		return this;
	}
	
	public void setNombre(String nombreNuevo){
		nombre = nombreNuevo;
	}
	
	public ProcesoMultiple build(){
		
		ProcesoMultiple procesoMultiple = new ProcesoMultiple();
		procesoMultiple.setNombre(nombre);
		procesoMultiple.setProcesos(procesos);
		return procesoMultiple;
	}
	
}
