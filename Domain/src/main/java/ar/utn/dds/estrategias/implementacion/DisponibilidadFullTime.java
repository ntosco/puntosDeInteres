package ar.utn.dds.estrategias.implementacion;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.utn.dds.POI.*;
import ar.utn.dds.estrategias.*;
import ar.utn.dds.servicios.Servicio;

@Entity
@DiscriminatorValue("FullTime")
public class DisponibilidadFullTime extends EstrategiaDisponibilidad {

	public Boolean estaDisponible(POI poi, List<Servicio> servicios, String nombreServicio,
			LocalDateTime horarioConsultado) {
		return true;
	}
}
