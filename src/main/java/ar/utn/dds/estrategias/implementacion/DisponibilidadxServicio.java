package ar.utn.dds.estrategias.implementacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import ar.utn.dds.POI.POI;
import ar.utn.dds.estrategias.*;
import ar.utn.dds.servicios.Servicio;

public class DisponibilidadxServicio implements EstrategiaDisponibilidad {

	public Boolean estaDisponible(POI poi, List<Servicio> _totalidadDeservicios, String _nombreServicio,
			LocalDateTime _horarioConsultado) {

		Stream<Servicio> servicios;

		if (_nombreServicio == null) {
			servicios = _totalidadDeservicios.stream();

		} else {
			servicios = this.tieneServicio(_totalidadDeservicios, _nombreServicio);
		}

		return servicios.anyMatch((servicio) -> servicio.estaDisponible(_horarioConsultado));

	}

	public Stream<Servicio> tieneServicio(List<Servicio> servicios, String nombreServicio) {
		return servicios.stream().filter((servicio) -> servicio.nombre().equals(nombreServicio));

	}

}
