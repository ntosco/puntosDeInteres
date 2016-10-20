package ar.utn.dds.comunas;

import java.time.DayOfWeek;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.uqbar.geodds.*;

import ar.utn.dds.POI.PointJava;
import ar.utn.dds.POI.PolygonJava;

@Entity
public class Comuna {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private PolygonJava areaComuna = new PolygonJava();

	public void setAreaDeComuna(PointJava punto) {
		areaComuna.add(punto);
	}

	public Boolean estaCercaDe(PointJava unPunto) {
		return areaComuna.isInside(unPunto);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PolygonJava getAreaComuna() {
		return areaComuna;
	}

	public void setAreaComuna(PolygonJava areaComuna) {
		this.areaComuna = areaComuna;
	}
}
