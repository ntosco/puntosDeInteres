package ar.utn.dds.POI;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadFullTime;

public class ParadaDeColectivo extends POI{
	
	final double DISTANCIA_MINIMA_DE_CERCANIA = 0.1;
	private String linea;
	
	public ParadaDeColectivo() {
		super();
		List<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadFullTime());
		this.setEstrategiasDisponibilidad(estrategias);
	}
	
	
	public Boolean estaCercaDe(Point unPunto){
		return this.getUbicacionActual().distance(unPunto) < DISTANCIA_MINIMA_DE_CERCANIA;
	}
	
	public boolean cumpleCondicionBusqueda(String textoLibre){
		return(linea.toLowerCase().contains(textoLibre.toLowerCase()));
	}
	
	// ********************************************************
	// ** Getters and Setters
	// ********************************************************

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

}	


