package ar.utn.dds.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.google.gson.annotations.Expose;

import ar.utn.dds.estrategias.implementacion.DisponibilidadxRangoHorario;
import ar.utn.dds.utils.Jornada;
@Observable
public class Servicio {
	@Expose private String nombre;
	@Expose private List<Jornada> jornadaDisponible = new ArrayList<Jornada>();
	@Expose private String nombreDeJornada;
	@Expose private DisponibilidadxRangoHorario estrategiaDisponibilidad = new DisponibilidadxRangoHorario();

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

	public Boolean estaDisponible(LocalDateTime horarioConsultado) {
		 return estrategiaDisponibilidad.estaDisponible(this,horarioConsultado);
	}

	public List<Jornada> getJornadaDisponible() {
		return jornadaDisponible;
	}


	public void setJornadaDisponible(List<Jornada> jornadaDisponible) {
		this.jornadaDisponible = jornadaDisponible;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreDeJornada() {
		return nombreDeJornada;
	}

	public void setNombreDeJornada(String nombreDeJornada) {
		this.nombreDeJornada = nombreDeJornada;
	}
	
	
	
	//Agregar contructor
		
}
