package ar.utn.dds.comunas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.uqbar.geodds.*;

@Entity
public class Comuna {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	private Polygon areaComuna = new Polygon();

	public void setAreaDeComuna(Point punto) {
		areaComuna.add(punto);
	}

	public Boolean estaCercaDe(Point unPunto) {
		return areaComuna.isInside(unPunto);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Polygon getAreaComuna() {
		return areaComuna;
	}

	public void setAreaComuna(Polygon areaComuna) {
		this.areaComuna = areaComuna;
	}
}
