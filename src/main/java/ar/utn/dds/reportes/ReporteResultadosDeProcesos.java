package ar.utn.dds.reportes;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.utils.ResultadoDeProceso;

public class ReporteResultadosDeProcesos {
	
	private List<ResultadoDeProceso> historialDeResultados = new ArrayList<ResultadoDeProceso>();
	
	public void agregarResultado(ResultadoDeProceso resultado){
		this.historialDeResultados.add(resultado);
	}
	

}
