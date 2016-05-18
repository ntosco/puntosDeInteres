package ar.utn.dds.decorators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.utn.dds.POI.POI;
import ar.utn.dds.managers.Buscador;
import ar.utn.dds.utils.Consulta;

public class GenerarReporteBusquedasxUsuario extends AccionDecorador {
	
	Map<String, Integer> listaBusquedasxUsuario = new HashMap<>();
	

	public GenerarReporteBusquedasxUsuario(Buscador decorado) {
		super(decorado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<POI> busquedaGeneral(String fraseBuscada) {
		
		List<POI> poisEncontrados = new ArrayList<POI>();
		
		poisEncontrados = this.getDecorado().busquedaGeneral(fraseBuscada);

		//FIXME Ver cuando se cambie usuario
		this.asignarCantResultadosAUsuario(null , poisEncontrados.size());
		
		return poisEncontrados;
		
	}

	private void asignarCantResultadosAUsuario(String usuarioEjecutor, Integer cantidadDeResultados) {
		if(this.listaBusquedasxUsuario.containsKey(usuarioEjecutor)){
			cantidadDeResultados +=  this.listaBusquedasxUsuario.get(usuarioEjecutor);
		}
		this.listaBusquedasxUsuario.put(usuarioEjecutor, cantidadDeResultados);

	}

	public Map<String, Integer> getListaBusquedasxUsuario() {
		return listaBusquedasxUsuario;
	}





}
