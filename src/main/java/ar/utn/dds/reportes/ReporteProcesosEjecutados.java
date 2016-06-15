package ar.utn.dds.reportes;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.utils.ResultadoDeProceso;

public class ReporteProcesosEjecutados {
	
	private List<Proceso> historialResultados = new ArrayList<Proceso>();
	
	public void almacenarResultado(Proceso proceso){
		this.historialResultados.add(proceso);
		}

}
