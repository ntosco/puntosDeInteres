package ar.utn.dds.estrategias;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import ar.utn.dds.POI.POI;
import ar.utn.dds.servicios.Servicio;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="estrategiatype",discriminatorType=DiscriminatorType.STRING)
public abstract class EstrategiaDisponibilidad {
	
	@Id
	@GeneratedValue
	private Long id;
	

	public abstract Boolean estaDisponible(POI poi, List<Servicio> servicios, String nombreServicio,
			LocalDateTime horarioConsultado);


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
