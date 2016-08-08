package ar.utn.dds.estrategias;

import java.time.LocalDateTime;
import java.util.List;
import ar.utn.dds.POI.*;
import ar.utn.dds.servicios.Servicio;

public interface EstrategiaDisponibilidad {

	public Boolean estaDisponible(POI poi, List<Servicio> servicios, String nombreServicio,
			LocalDateTime horarioConsultado);
}
