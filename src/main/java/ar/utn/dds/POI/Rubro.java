package ar.utn.dds.POI;

public class Rubro {
	String nombre;
	public double getRadioCercania() {
		return radioCercania;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	double radioCercania;

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
