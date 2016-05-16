package ar.utn.dds.creacionales;

import org.uqbar.geodds.Point;

import ar.utn.dds.utils.Jornada;

import java.util.ArrayList;
import java.util.List;

public class PoiBuilder {

	protected String nombre;
	protected String direccion;
	protected String barrio;
	protected int numero;
	protected Point ubicacionActual;
	protected List<String> palabrasClave;
	protected List<Jornada> jornada = new ArrayList<Jornada>();
	

	public void setearDatosComunes(String nombre, String direccion,
			String barrio, int numero, Point ubicacion, List<String> palabrasClave, List<Jornada> jornada) {

		this.nombre = nombre;
		this.direccion = direccion;
		this.barrio = barrio;
		this.numero = numero;
		this.ubicacionActual = ubicacion;
		this.palabrasClave = palabrasClave;
		this.jornada = jornada;
		
	}

	
}
