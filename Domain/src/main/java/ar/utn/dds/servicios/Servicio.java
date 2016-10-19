package ar.utn.dds.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.uqbar.commons.utils.Observable;
import com.google.gson.annotations.Expose;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxRangoHorario;
import ar.utn.dds.utils.Jornada;

@Observable
@Entity
public class Servicio {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=100)
	@Expose private String nombre;
	
	@OneToMany(fetch=FetchType.LAZY)
	@Expose private List<Jornada> jornadaDisponible = new ArrayList<Jornada>();
	
	@Column(length=100)
	@Expose private String nombreDeJornada;
	
	@OneToOne(fetch=FetchType.LAZY)
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
		
}
