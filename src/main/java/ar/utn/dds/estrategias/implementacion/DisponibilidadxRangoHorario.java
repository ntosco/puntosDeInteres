package ar.utn.dds.estrategias.implementacion;

import java.time.LocalDateTime;
import java.util.List;

import ar.utn.dds.POI.POI;
import ar.utn.dds.estrategias.*;
import ar.utn.dds.servicios.Servicio;


public class DisponibilidadxRangoHorario implements EstrategiaDisponibilidad {

	public Boolean estaDisponible(POI poi,List<Servicio> servicios, String _nombreServicio,LocalDateTime _horarioConsultado) {
		
		Integer HHMMSS = _horarioConsultado.getHour() * 10000 + _horarioConsultado.getMinute()* 100 + _horarioConsultado.getSecond(); 
		
		return poi.getJornadaDisponible().stream().anyMatch((jornada)->jornada.DentroHorarioDeRango(_horarioConsultado.getDayOfWeek(), HHMMSS));
		
	}
	
	public Boolean estaDisponible(Servicio servicio,LocalDateTime _horarioConsultado) {
		
		Integer HHMMSS = _horarioConsultado.getHour() * 10000 + _horarioConsultado.getMinute()* 100 + _horarioConsultado.getSecond(); 
		
		return servicio.getJornadaDisponible().stream().anyMatch((jornada)->jornada.DentroHorarioDeRango(_horarioConsultado.getDayOfWeek(), HHMMSS));
		
	}

	
	
	

}
