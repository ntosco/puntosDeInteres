package ar.utn.dds.POI;

public class Rubro {
	String nombre;
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
