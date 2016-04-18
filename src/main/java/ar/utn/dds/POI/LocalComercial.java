package ar.utn.dds.POI;

import java.util.ArrayList;

import org.uqbar.geodds.Point;

import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxRangoHorario;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxServicio;
import ar.utn.dds.servicios.Servicio;

public class LocalComercial extends POI {

	double cercania = 0;
	ArrayList<Rubro> listaRubros = new ArrayList<Rubro>();
	
	public ArrayList<Rubro> getListaRubros() {
		return listaRubros;
	}

	public void setListaRubros(ArrayList<Rubro> listaRubros) {
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
		ArrayList<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadxRangoHorario());
		this.setEstrategiasDisponibilidad(estrategias);
	}

}
