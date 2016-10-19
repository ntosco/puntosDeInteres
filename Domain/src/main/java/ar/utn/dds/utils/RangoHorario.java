package ar.utn.dds.utils;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RangoHorario {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private LocalTime horarioInicial;
	
	@Column
	private LocalTime horarioFinal;
	
	public RangoHorario(LocalTime horarioInicial, LocalTime horarioFinal) {
		super();
		
		this.horarioInicial=horarioInicial;
		this.horarioFinal=horarioFinal;
		
	}

	public Boolean DentroRangoHorario(LocalTime horarioConsultado){
		
		return (horarioConsultado.isAfter(this.getHorarioInicial()) && horarioConsultado.isBefore(this.getHorarioFinal()));
		
	}

	public LocalTime getHorarioInicial() {
		return horarioInicial;
	}

	public void setHorarioInicial(LocalTime horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	public LocalTime getHorarioFinal() {
		return horarioFinal;
	}

	public void setHorarioFinal(LocalTime horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
