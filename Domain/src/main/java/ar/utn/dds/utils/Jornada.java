package ar.utn.dds.utils;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Jornada {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private DayOfWeek diaSemanal;
	
	@OneToOne(cascade=CascadeType.ALL)
	private RangoHorario horarioDeAtencion;
	
	/**
	 * @param diaSemanal
	 * @param horarioDeAtencion
	*/
	public Jornada(DayOfWeek diaSemanal, RangoHorario horarioDeAtencion) {
		super();
		this.diaSemanal = diaSemanal;
		this.horarioDeAtencion = horarioDeAtencion;
		
	}

	public DayOfWeek getDiaSemanal() {
		return diaSemanal;
		
	}

	public void setDiaSemanal(DayOfWeek diaSemanal) {
		this.diaSemanal = diaSemanal;
	}

	public RangoHorario getHorarioDeAtencion() {
		return horarioDeAtencion;
	}

	public void setHorarioDeAtencion(RangoHorario horarioDeAtencion) {
		this.horarioDeAtencion = horarioDeAtencion;
	}
	
	public Boolean DentroHorarioDeRango(DayOfWeek diaSemana, LocalTime horarioConsultado) {
		return (this.getHorarioDeAtencion().DentroRangoHorario(horarioConsultado) && this.getDiaSemanal().equals(diaSemana));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
