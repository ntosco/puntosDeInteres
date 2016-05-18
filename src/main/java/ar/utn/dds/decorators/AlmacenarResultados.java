package ar.utn.dds.decorators;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.POI.POI;
import ar.utn.dds.managers.Buscador;
import ar.utn.dds.utils.Consulta;

public class AlmacenarResultados extends AccionDecorador {
	
	List<Consulta> consultasAlmacenadas = new ArrayList<Consulta>();


	public AlmacenarResultados(Buscador decorado) {
		super(decorado);
	}

	@Override
	public List<POI> busquedaGeneral(String fraseBuscada){
		
		//FIXME Ver como traerse el usuario que ejecuta la consulta
		long tiempoInicial = System.currentTimeMillis();
		
		Consulta consulta = new Consulta();
		List<POI> poisEncontrados = new ArrayList<POI>();
		
		poisEncontrados = this.getDecorado().busquedaGeneral(fraseBuscada);
		
		consulta.setCantidadDeResultados(poisEncontrados.size());
		consulta.setFraseBuscada(fraseBuscada);
		
		long tiempoFinal = System.currentTimeMillis();
		consulta.setTiempoDeEjecucion(tiempoFinal - tiempoInicial);
		
		this.almacenarConsulta(consulta);
		
		return poisEncontrados;
		
	}

	private void almacenarConsulta(Consulta consulta) {
		this.consultasAlmacenadas.add(consulta);
		
	}

	public List<Consulta> getConsultasAlmacenadas() {
		return consultasAlmacenadas;
	}

	public void setConsultasAlmacenadas(List<Consulta> consultasAlmacenadas) {
		this.consultasAlmacenadas = consultasAlmacenadas;
	}




	

}
