package ar.utn.dds.decorators;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import ar.utn.dds.managers.ManagerDeConsultas;
import ar.utn.dds.utils.Consulta;

public class GenerarReporteBusquedasxFechas extends AccionDecorador{

	Map<String, Integer> listaCantDeBusquedasxFecha = new HashMap<>();
	
	public GenerarReporteBusquedasxFechas(ManagerDeConsultas decorado) {
		super(decorado);
	}

	@Override
	public void ejecutarse(Consulta consulta) {
		this.getDecorado().ejecutarse(consulta);
		this.asignarCantResultadosAFecha(consulta.getFecha(),consulta.getCantidadDeResultados());
	}

	public void asignarCantResultadosAFecha(LocalDate fecha,int cantResultados){
		if(this.listaCantDeBusquedasxFecha.containsKey(fecha.toString())){
			cantResultados +=  this.listaCantDeBusquedasxFecha.get(fecha);
		}
		this.listaCantDeBusquedasxFecha.put(fecha.toString(), cantResultados);
	}
	
	public Map<String, Integer> getListaBusquedasxUsuario() {
		return listaCantDeBusquedasxFecha;
	}
}
