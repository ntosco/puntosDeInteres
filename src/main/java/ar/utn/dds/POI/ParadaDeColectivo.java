package ar.utn.dds.POI;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadFullTime;

public class ParadaDeColectivo extends POI{
	
	static double DISTANCIA_MINIMA_DE_CERCANIA = 0.1;
	String linea; //Agrego linea tipo String para cada parada. "linea" es String porque puede incluir el ramal.
	
	
	public String getLinea() {
		return linea;
	}


	public static double getDISTANCIA_MINIMA_DE_CERCANIA() {
		return DISTANCIA_MINIMA_DE_CERCANIA;
	}


	public static void setDISTANCIA_MINIMA_DE_CERCANIA(double dISTANCIA_MINIMA_DE_CERCANIA) {
		DISTANCIA_MINIMA_DE_CERCANIA = dISTANCIA_MINIMA_DE_CERCANIA;
	}


	public void setLinea(String linea) {
		this.linea = linea;
	}


	public Boolean estaCercaDe(Point ubicacionTerminal){
		double d = ubicacionActual.distance(ubicacionTerminal);	
		return d < DISTANCIA_MINIMA_DE_CERCANIA;
	}
	

	public boolean cumpleCondicionBusqueda(String textoLibre){
		if (linea.toLowerCase().contains(textoLibre.toLowerCase())){
			return true;
		}else {	
			return contieneKeyword(textoLibre);
		}			
	}
	
	public boolean cumpleCondicionBusqueda2(String textoLibre){
		return (this.linea == textoLibre);
	}


	/**
	 * 
	 */
	public ParadaDeColectivo() {
		super();
		List<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadFullTime());
		this.setEstrategiasDisponibilidad(estrategias);
	}

}	


