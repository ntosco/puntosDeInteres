package ar.utn.dds.POI;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.uqbar.commons.utils.Observable;

import com.google.gson.annotations.Expose;

import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxRangoHorario;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxServicio;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;
@Observable
@Entity
@DiscriminatorValue("SucursalBanco")
public class SucursalBanco extends POI{
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Expose private List<Servicio> listaServicios = new ArrayList<Servicio>();	
	
	@ElementCollection(targetClass = DayOfWeek.class)
	@Column(name = "diasDeLaSemana")
	@Enumerated(EnumType.STRING)
	@Expose private final List<DayOfWeek> DIAS_LABORABLES = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY));	

	public boolean cumpleCondicionBusqueda(String textoLibre){
		return false;		
		
	}
	
	public Boolean estaDisponible(String nombreServicio, LocalDateTime horarioConsultado) {
		return this.getEstrategiasDisponibilidad().stream()
				.anyMatch((estrategiaDisponibilidad) -> estrategiaDisponibilidad.estaDisponible(this,
						this.listaServicios, nombreServicio, horarioConsultado));

	}

	public SucursalBanco() {
		/* super();
		
		List<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadxServicio());
		estrategias.add(new DisponibilidadxRangoHorario());
		this.setEstrategiasDisponibilidad(estrategias); 

		RangoHorario rangoAtencion = new RangoHorario(LocalTime.of(10, 0, 0),LocalTime.of(15, 0, 0));
		List<Jornada> jornadasSemanales = new ArrayList<Jornada>();
		for (DayOfWeek dia : DIAS_LABORABLES) {
			Jornada jornada = new Jornada();
			jornada.setDiaSemanal(dia);
			jornada.setHorarioDeAtencion(rangoAtencion);
			jornadasSemanales.add(jornada);
		}

		this.setJornadaDisponible(jornadasSemanales); */

	}


	public List<Servicio> getListaServicios() {
		return listaServicios;
	}


	public void setListaServicios(List<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	
	
	
}
