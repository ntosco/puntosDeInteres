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
	
	
	public PoiBuilder setNombre(String nombre){
		this.nombre = nombre;
		return this;
	}
	
	public PoiBuilder setBarrio(String barrio){
		this.barrio = barrio;
		return this;
	}
	
	public PoiBuilder setDireccion(String calle){
		this.direccion = calle;
		return this;
	}
	
	public PoiBuilder setNumero(int numero){
		this.numero = numero;
		return this;
	}

	public PoiBuilder setUbicacion(Point punto){
		ubicacionActual = punto;
		return this;
	}
	
	public PoiBuilder setPalabrasClave(List<String> palabrasClave){
		this.palabrasClave = palabrasClave;
		return this;
	}
	
	public PoiBuilder setJornada(List<Jornada> jornada){
		this.jornada = jornada;
		return this;
	}
	
}
