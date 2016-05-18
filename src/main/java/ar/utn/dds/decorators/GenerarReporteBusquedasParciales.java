package ar.utn.dds.decorators;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.POI.POI;
import ar.utn.dds.managers.Buscador;
import ar.utn.dds.utils.Consulta;

public class GenerarReporteBusquedasParciales extends AccionDecorador{
	
	List<Integer> cantParciales = new ArrayList<Integer>();
	

	public GenerarReporteBusquedasParciales(Buscador decorado) {
		super(decorado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<POI> busquedaGeneral(String fraseBuscada) {
		List<POI> poisEncontrados = new ArrayList<POI>();
		poisEncontrados = this.getDecorado().busquedaGeneral(fraseBuscada);
		this.cantParciales.add(poisEncontrados.size());
		return poisEncontrados;
	}

	public List<Integer> getCantParciales() {
		return cantParciales;
	}


	

}
