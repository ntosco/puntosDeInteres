package ar.utn.dds.utils;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Jornada {
	
	private DayOfWeek diaSemanal;
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

	
}
