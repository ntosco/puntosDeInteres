package ar.utn.dds.estrategias.implementacion;

import java.time.LocalDateTime;
import java.util.List;
import ar.utn.dds.POI.*;
import ar.utn.dds.estrategias.*;
import ar.utn.dds.servicios.Servicio;

public class DisponibilidadFullTime implements EstrategiaDisponibilidad {

	public Boolean estaDisponible(POI poi, List<Servicio> servicios, String _nombreServicio,
			LocalDateTime _horarioConsultado) {
		return true;
	}
}
