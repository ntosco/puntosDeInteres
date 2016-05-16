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
		this.asignarCantBusquedasAFecha(consulta.getFecha());
	}

	public void asignarCantBusquedasAFecha(LocalDate fecha){
		int cantBusquedas = 1;
		if(this.listaCantDeBusquedasxFecha.containsKey(fecha.toString()))
			cantBusquedas =  this.listaCantDeBusquedasxFecha.get(fecha.toString()) + 1;
		this.listaCantDeBusquedasxFecha.put(fecha.toString(), cantBusquedas);
	}
	
	public Map<String, Integer> getListaBusquedasxFecha() {
		return listaCantDeBusquedasxFecha;
	}
}
