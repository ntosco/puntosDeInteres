package ar.utn.dds.POI;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
	private final List<DayOfWeek> DIAS_LABORABLES = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY));	

	public boolean cumpleCondicionBusqueda(String textoLibre){
		return false;		
		
	}
	
	public Boolean estaDisponible(String nombreServicio, LocalDateTime horarioConsultado) {
		return this.getEstrategiasDisponibilidad().stream()
				.anyMatch((estrategiaDisponibilidad) -> estrategiaDisponibilidad.estaDisponible(this,
						this.listaServicios, nombreServicio, horarioConsultado));

	}

	public SucursalBanco() {
		super();
		
		List<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadxServicio());
		estrategias.add(new DisponibilidadxRangoHorario());
		this.setEstrategiasDisponibilidad(estrategias);

		RangoHorario rangoAtencion = new RangoHorario(LocalTime.of(10, 0, 0),LocalTime.of(15, 0, 0));
		List<Jornada> jornadasSemanales = new ArrayList<Jornada>();
		for (DayOfWeek dia : DIAS_LABORABLES) {
			Jornada jornada = new Jornada(dia, rangoAtencion);
			jornadasSemanales.add(jornada);
		}

		this.setJornadaDisponible(jornadasSemanales);

	}


	public List<Servicio> getListaServicios() {
		return listaServicios;
	}


	public void setListaServicios(List<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	
	
	
}
