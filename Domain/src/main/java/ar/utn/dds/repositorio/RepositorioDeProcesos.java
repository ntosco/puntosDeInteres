package ar.utn.dds.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;

import ar.utn.dds.procesos.Proceso;


public class RepositorioDeProcesos {
	
	protected List<Proceso> procesos = new ArrayList<Proceso>();
	int idCounter = 0;
	Proceso proceso;
	
	static RepositorioDeProcesos Repoinstance;

	public static RepositorioDeProcesos getInstance(){
		if (Repoinstance == null) {
			Repoinstance = new RepositorioDeProcesos();
		}	
		return Repoinstance;
	}

	public void clean(){
		Repoinstance = null;
	}
	
	public void agregarProceso(Proceso proceso){
		this.procesos.add(proceso);
	}
	
//	public List<Proceso> buscarProcesoPorID(int idProceso){
//		CollectionUtils.filter(this.procesos, ((proceso)-> proceso ));
//		return procesos;
//	}
	
	private void setID(){
		proceso.setIdProceso(idCounter);
		this.actualizarID();
	}

	private void actualizarID() {
		this.idCounter++;
	}
}
