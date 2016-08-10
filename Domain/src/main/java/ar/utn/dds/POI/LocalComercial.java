package ar.utn.dds.POI;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;
import org.uqbar.geodds.Point;

import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxRangoHorario;

@Observable
public class LocalComercial extends POI {

	private double cercania = 0;
	private List<Rubro> listaRubros = new ArrayList<Rubro>();
	
	public List<Rubro> getListaRubros() {
		return listaRubros;
	}
	
	public List<String> getNombresRubros(){
		
		List<String> lista = new ArrayList<String>();
		listaRubros.forEach(rubro -> lista.add(rubro.getNombre()));
		return lista;
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

	public Boolean estaCercaDe(Point ubicacionTerminal) {
		//me fijo en la lista de rubros cual es la distancia. tomo la más amplia
		listaRubros.forEach(rubro -> {	if( cercania < rubro.getRadioCercania())
											cercania = rubro.getRadioCercania();
									 }
							);
		return this.getUbicacionActual().distance(ubicacionTerminal) < cercania;
	}

	public boolean perteneceAlRubro(String textoLibre) {
		for (Rubro rubro : listaRubros) {
			if (textoLibre.equals(rubro.getNombre()))
				return true;
		}
		return false;
	}

	public boolean cumpleCondicionBusqueda(String textoLibre) {
		return (perteneceAlRubro(textoLibre));
	}
	

	public LocalComercial() {
		super();
		List<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadxRangoHorario());
		this.setEstrategiasDisponibilidad(estrategias);
	}

}
