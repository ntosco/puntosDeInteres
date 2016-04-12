package ar.utn.dds.POI;
import java.util.ArrayList;
import java.time.LocalDateTime;

import java.util.List;

import org.uqbar.geodds.*;

import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.utils.Jornada;

public abstract class POI {
	
	String direccionNombre;
	int direccionNumero;
	Point ubicacionActual;
	static double DISTANCIA_MINIMA_DE_CERCANIA = 0.5;
	private List<Jornada> JornadaDisponible = new ArrayList<Jornada>();
	private List<EstrategiaDisponibilidad> EstrategiasDisponibilidad = new ArrayList<EstrategiaDisponibilidad>();

	
	
	ArrayList <String> listaPalabrasClave = new ArrayList <String>(); //Creo lista de palabras clave
	
	
	public ArrayList<String> getListaPalabrasClave() {
		return listaPalabrasClave;
	}

	public void setListaPalabrasClave(ArrayList<String> listaPalabrasClave) {
		this.listaPalabrasClave = listaPalabrasClave;
	}

	public void setUbicacionActual(Point unPunto){
		ubicacionActual = unPunto;
	}

	public Boolean estaCercaDe(Point ubicacionTerminal){
		double d = ubicacionActual.distance(ubicacionTerminal);	
		return d < DISTANCIA_MINIMA_DE_CERCANIA;

	}
	
	public boolean cumpleCondicionBusqueda(String textoLibre) {
		return false;
		
	}
	
	public boolean contieneKeyword(String palabraClave){
		return listaPalabrasClave.equals(palabraClave);
	}
	
	public Boolean estaDisponible(POI poi, String _nombreServicio,
			LocalDateTime _horarioConsultado) {
		 return this.getEstrategiasDisponibilidad().stream().anyMatch((estrategiaDisponibilidad)->estrategiaDisponibilidad.estaDisponible(this,null, _nombreServicio, _horarioConsultado));
		
	}

	public List<Jornada> getJornadaDisponible() {
		return JornadaDisponible;
	}

	public void setJornadaDisponible(List<Jornada> jornadaDisponible) {
		JornadaDisponible = jornadaDisponible;
	}

	public List<EstrategiaDisponibilidad> getEstrategiasDisponibilidad() {
		return EstrategiasDisponibilidad;
	}

	public void setEstrategiasDisponibilidad(List<EstrategiaDisponibilidad> estrategiasDisponibilidad) {
		EstrategiasDisponibilidad = estrategiasDisponibilidad;
	}



}
