package ar.utn.dds.POI;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.commons.utils.Observable;

import com.google.gson.annotations.Expose;

@Observable
public class Rubro {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=150)
	@Expose private String nombre;
	
	
	@Expose private double radioCercania;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
