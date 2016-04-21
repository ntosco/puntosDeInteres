package ar.utn.dds.estrategias.implementacion;

import java.time.LocalDateTime;
import java.util.List;
import ar.utn.dds.POI.POI;
import ar.utn.dds.estrategias.*;
import ar.utn.dds.servicios.Servicio;

public class DisponibilidadxRangoHorario implements EstrategiaDisponibilidad {

	public Boolean estaDisponible(POI poi, List<Servicio> servicios, String _nombreServicio,
			LocalDateTime _horarioConsultado) {

		return poi.getJornadaDisponible().stream().anyMatch((jornada) -> jornada
				.DentroHorarioDeRango(_horarioConsultado.getDayOfWeek(), _horarioConsultado.toLocalTime()));

	}

	public Boolean estaDisponible(Servicio servicio, LocalDateTime _horarioConsultado) {

		return servicio.getJornadaDisponible().stream().anyMatch((jornada) -> jornada
				.DentroHorarioDeRango(_horarioConsultado.getDayOfWeek(), _horarioConsultado.toLocalTime()));

	}

}
