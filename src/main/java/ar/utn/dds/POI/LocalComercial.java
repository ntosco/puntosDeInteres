package ar.utn.dds.POI;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxRangoHorario;


public class LocalComercial extends POI {

	double cercania = 0;
	List<Rubro> listaRubros = new ArrayList<Rubro>();
	
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

//	public void setRadioDeCercania(double d) {
//		cercania = d;
//	}

	public Boolean estaCercaDe(Point ubicacionTerminal) {
		//me fijo en la lista de rubros cual es la distancia. tomo la más amplia
		listaRubros.forEach(rubro -> {	if( cercania < rubro.radioCercania)
											cercania = rubro.radioCercania;
									 }
							);
		return ubicacionActual.distance(ubicacionTerminal) < cercania;
	}

	public boolean perteneceAlRubro(String textoLibre) {
		for (Rubro rubro : listaRubros) {
			if (textoLibre.equals(rubro.getNombre()))
				return true;
		}
		return false;
	}

	public boolean cumpleCondicionBusqueda(String textoLibre) {
		if (perteneceAlRubro(textoLibre)) {
			return true;

		} else {
			return contieneKeyword(textoLibre);
		}
	}

	public LocalComercial() {
		super();
		List<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadxRangoHorario());
		this.setEstrategiasDisponibilidad(estrategias);
	}

}
