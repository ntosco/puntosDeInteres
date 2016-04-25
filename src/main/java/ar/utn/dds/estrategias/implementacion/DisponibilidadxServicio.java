package ar.utn.dds.estrategias.implementacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import ar.utn.dds.POI.POI;
import ar.utn.dds.estrategias.*;
import ar.utn.dds.servicios.Servicio;

public class DisponibilidadxServicio implements EstrategiaDisponibilidad {

	public Boolean estaDisponible(POI poi, List<Servicio> totalidadDeservicios, String nombreServicio,
			LocalDateTime horarioConsultado) {

		Stream<Servicio> servicios;

		if (nombreServicio == null) {
			servicios = totalidadDeservicios.stream();

		} else {
			servicios = this.tieneServicio(totalidadDeservicios, nombreServicio);
		}

		return servicios.anyMatch((servicio) -> servicio.estaDisponible(horarioConsultado));

	}

	public Stream<Servicio> tieneServicio(List<Servicio> servicios, String nombreServicio) {
		return servicios.stream().filter((servicio) -> servicio.getNombre().equals(nombreServicio));

	}

}
