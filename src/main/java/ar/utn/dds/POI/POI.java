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

	
	
	List<String> listaPalabrasClave = new ArrayList <String>(); //Creo lista de palabras clave
	
	public String getDireccionNombre() {
		return direccionNombre;
	}

	public void setDireccionNombre(String direccionNombre) {
		this.direccionNombre = direccionNombre;
	}

	public int getDireccionNumero() {
		return direccionNumero;
	}

	public void setDireccionNumero(int direccionNumero) {
		this.direccionNumero = direccionNumero;
	}

	public static double getDISTANCIA_MINIMA_DE_CERCANIA() {
		return DISTANCIA_MINIMA_DE_CERCANIA;
	}

	public static void setDISTANCIA_MINIMA_DE_CERCANIA(double dISTANCIA_MINIMA_DE_CERCANIA) {
		DISTANCIA_MINIMA_DE_CERCANIA = dISTANCIA_MINIMA_DE_CERCANIA;
	}

	public Point getUbicacionActual() {
		return ubicacionActual;
	}
	
	public List<String> getListaPalabrasClave() {
		return listaPalabrasClave;
	}

	public void setListaPalabrasClave(List<String> listaPalabrasClave) {
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
		return listaPalabrasClave.contains(palabraClave);
	}
	
	public Boolean estaDisponible(String _nombreServicio, LocalDateTime _horarioConsultado) {
		return this.getEstrategiasDisponibilidad().stream()
				.allMatch((estrategiaDisponibilidad) -> estrategiaDisponibilidad.estaDisponible(this, null,
						_nombreServicio, _horarioConsultado));

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
