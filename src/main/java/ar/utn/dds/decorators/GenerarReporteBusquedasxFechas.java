package ar.utn.dds.decorators;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.utn.dds.POI.POI;
import ar.utn.dds.managers.Buscador;
import ar.utn.dds.utils.Consulta;

public class GenerarReporteBusquedasxFechas extends AccionDecorador{

	Map<String, Integer> listaCantDeBusquedasxFecha = new HashMap<>();
	
	public GenerarReporteBusquedasxFechas(Buscador decorado) {
		super(decorado);
	}

	@Override
	public List<POI> busquedaGeneral(String fraseBuscada){
		List<POI> poisEncontrados = new ArrayList<POI>();
		poisEncontrados = this.getDecorado().busquedaGeneral(fraseBuscada);
		this.asignarCantBusquedasAFecha(LocalDate.now());
		return poisEncontrados;
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
