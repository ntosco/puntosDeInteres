package ar.utn.dds.utils;

import java.time.LocalTime;

public class RangoHorario {
	

	LocalTime horarioInicial;
	LocalTime horarioFinal;
	
	public RangoHorario(LocalTime horarioInicial, LocalTime horarioFinal) {
		super();
		
		this.horarioInicial=horarioInicial;
		this.horarioFinal=horarioFinal;
		
	}

	public Boolean DentroRangoHorario(LocalTime _horarioConsultado){
		
		return (_horarioConsultado.isAfter(this.getHorarioInicial()) && _horarioConsultado.isBefore(this.getHorarioFinal()));
		
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
