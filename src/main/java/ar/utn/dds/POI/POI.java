package ar.utn.dds.POI;
import java.util.ArrayList;

import org.uqbar.geodds.*;

import ar.utn.dds.servicios.Servicio;

public abstract class POI {
	
	String direccionNombre;
	int direccionNumero;
	Point ubicacionActual;
	
	
	ArrayList <String> listaPalabrasClave = new ArrayList <String>(); //Creo lista de palabras clave
	
	
	public void setUbicacionActual(Point unPunto){
		ubicacionActual = unPunto;
	}

	public boolean cumpleCondicionBusqueda(String textoLibre) {
		return false;
		
	}
	
	public boolean contieneKeyword(String palabraClave){
		return listaPalabrasClave.equals(palabraClave);
		
	}
}
