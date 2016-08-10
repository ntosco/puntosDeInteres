package ar.utn.dds.POI;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;
import org.uqbar.geodds.Point;

import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadFullTime;
@Observable
public class ParadaDeColectivo extends POI{
	
	final double DISTANCIA_MINIMA_DE_CERCANIA = 0.1;
	private String linea; //Agrego linea tipo String para cada parada. "linea" es String porque puede incluir el ramal.
	
	
	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}


	public Boolean estaCercaDe(Point ubicacionTerminal){
		double d = this.getUbicacionActual().distance(ubicacionTerminal);	
		return d < DISTANCIA_MINIMA_DE_CERCANIA;
	}
	

	public boolean cumpleCondicionBusqueda(String textoLibre){
		return(linea.toLowerCase().contains(textoLibre.toLowerCase()));
	}
	
	
	public boolean cumpleCondicionBusqueda2(String textoLibre){
		return (this.linea == textoLibre);
	}


	public ParadaDeColectivo() {
		super();
		List<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadFullTime());
		this.setEstrategiasDisponibilidad(estrategias);
	}

}	


