package ar.utn.dds.POI;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxRangoHorario;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxServicio;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

public class SucursalBanco extends POI{
	
	private List<Servicio> listaServicios = new ArrayList<Servicio>();
	private static ArrayList<DayOfWeek> DIAS_LABORABLES = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY));	

	public boolean cumpleCondicionBusqueda(String textoLibre){
		return contieneKeyword(textoLibre);		
		
	}
	

	public Boolean estaDisponible(POI poi, String _nombreServicio,
			LocalDateTime _horarioConsultado) {
		 return this.getEstrategiasDisponibilidad().stream().anyMatch((estrategiaDisponibilidad)->estrategiaDisponibilidad.estaDisponible(this,this.listaServicios, _nombreServicio, _horarioConsultado));
		
	}


	/**
	 * 
	 */
	public SucursalBanco() {
		super();
		ArrayList<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadxServicio());
		estrategias.add(new DisponibilidadxRangoHorario());
		this.setEstrategiasDisponibilidad(estrategias);

		RangoHorario rangoAtencion = new RangoHorario(100000, 150000);
		ArrayList<Jornada> jornadasSemanales = new ArrayList<>();
		for (DayOfWeek dia : DIAS_LABORABLES) {
			Jornada jornada = new Jornada(dia, rangoAtencion);
			jornadasSemanales.add(jornada);
		}

		this.setJornadaDisponible(jornadasSemanales);

	}
	
	
	
}
