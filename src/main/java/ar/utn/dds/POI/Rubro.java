package ar.utn.dds.POI;

public class Rubro {
	private String nombre;
	private double radioCercania;
	
	public Rubro(String nombre,double cercania) {
		this.nombre = nombre;
		this.radioCercania = cercania;
	}

	// ********************************************************
	// ** Getters and Setters
	// ********************************************************
	
	public double getRadioCercania() {
		return radioCercania;
	}

	public void setRadioCercania(double radio) {
		radioCercania = radio;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

}
