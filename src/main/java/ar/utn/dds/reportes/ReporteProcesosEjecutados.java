package ar.utn.dds.reportes;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.utils.ResultadoDeProceso;

public class ReporteProcesosEjecutados {
	
	private List<ResultadoDeProceso> historialResultados = new ArrayList<ResultadoDeProceso>();
	
	public void almacenarResultado(ResultadoDeProceso resultadoNuevo){
		this.historialResultados.add(resultadoNuevo);
		}
	public Integer cantidadProcesosEjecutados(){
		return this.historialResultados.size();
	}

}
