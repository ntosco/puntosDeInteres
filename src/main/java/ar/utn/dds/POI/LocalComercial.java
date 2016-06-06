package ar.utn.dds.POI;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxRangoHorario;


public class LocalComercial extends POI {

	private double cercania = 0;
	private List<Rubro> listaRubros = new ArrayList<Rubro>();
	
	public LocalComercial() {
		super();
		List<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadxRangoHorario());
		this.setEstrategiasDisponibilidad(estrategias);
	}
	
	public Boolean estaCercaDe(Point unPunto) {
		listaRubros.forEach(rubro -> {	if( cercania < rubro.getRadioCercania())
											cercania = rubro.getRadioCercania();
									 }
							);
		return this.getUbicacionActual().distance(unPunto) < cercania;
	}

	// ********************************************************
	// ** Criterios de búsqueda
	// ********************************************************
	
	public boolean cumpleCondicionBusqueda(String textoLibre) {
		return (perteneceAlRubro(textoLibre));
	}
	
	public boolean perteneceAlRubro(String textoLibre) {
		for (Rubro rubro : listaRubros) {
			if (textoLibre.equals(rubro.getNombre()))
				return true;
		}
		return false;
	}
	
	// ********************************************************
	// ** Getters and Setters
	// ********************************************************

	public List<Rubro> getListaRubros() {
		return listaRubros;
	}

	public double getCercania() {
		return cercania;
	}

	public void setCercania(double cercania) {
		this.cercania = cercania;
	}

	public void setListaRubros(List<Rubro> listaRubros) {
		this.listaRubros = listaRubros;
	}

}
