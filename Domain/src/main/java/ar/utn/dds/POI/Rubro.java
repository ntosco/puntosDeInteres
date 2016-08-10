package ar.utn.dds.POI;

import org.uqbar.commons.utils.Observable;

@Observable
public class Rubro {
	private String nombre;
	private double radioCercania;

	public double getRadioCercania() {
		return radioCercania;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public double radioCercania(){
		return this.radioCercania;
	}

	public void setRadioCercania(double radio) {
		radioCercania = radio;
	}

	public Rubro(String nombre,double cercania) {
		this.nombre = nombre;
		this.radioCercania = cercania;
	}
	
}
