package ar.utn.dds.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.estrategias.implementacion.DisponibilidadxRangoHorario;
import ar.utn.dds.utils.Jornada;

public class Servicio {
	String nombre;
	private List<Jornada> jornadaDisponible = new ArrayList<Jornada>();
	private DisponibilidadxRangoHorario estrategiaDisponibilidad = new DisponibilidadxRangoHorario();

	public DisponibilidadxRangoHorario getEstrategiaDisponibilidad() {
		return estrategiaDisponibilidad;
	}

	public void setEstrategiaDisponibilidad(DisponibilidadxRangoHorario estrategiaDisponibilidad) {
		this.estrategiaDisponibilidad = estrategiaDisponibilidad;
	}

	public Servicio(String nombre, List<Jornada> jornadaDisponible) {
		this.nombre = nombre;
		setJornadaDisponible(jornadaDisponible);
	
	}

	public Boolean estaDisponible(LocalDateTime _horarioConsultado) {
		 return estrategiaDisponibilidad.estaDisponible(this,_horarioConsultado);
		
	}

	public List<Jornada> getJornadaDisponible() {
		return jornadaDisponible;
	}


	public void setJornadaDisponible(List<Jornada> _jornadaDisponible) {
		jornadaDisponible = _jornadaDisponible;
	}
	
	public String nombre(){
		return nombre;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//Agregar contructor
		
}
