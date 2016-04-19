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
	public Jornada(DayOfWeek _diaSemanal, RangoHorario _horarioDeAtencion) {
		super();
		this.diaSemanal = _diaSemanal;
		this.horarioDeAtencion = _horarioDeAtencion;
		
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
	
	public Boolean DentroHorarioDeRango(DayOfWeek _diaSemana, LocalTime _horarioConsultado) {

		return (this.getHorarioDeAtencion().DentroRangoHorario(_horarioConsultado) && this.getDiaSemanal().equals(_diaSemana));
	}

	
}
