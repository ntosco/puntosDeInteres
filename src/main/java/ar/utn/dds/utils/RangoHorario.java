package ar.utn.dds.utils;

import java.time.LocalTime;

public class RangoHorario {
	

	private LocalTime horarioInicial;
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
	
}
