package ar.utn.dds.utils;


public class RangoHorario {
	

	/**
	 * HHMMSS
	 * Ejemplo 23:12:00 -> 231200
	 */
	Integer horarioInicial;
	Integer horarioFinal;
	
	public RangoHorario(Integer horarioInicial, Integer horarioFinal) {
		super();
		// FIXME : controlar  que horario inicial sea menor que final
		this.horarioInicial = horarioInicial;
		this.horarioFinal = horarioFinal;
	}

	public Boolean DentroRangoHorario(Integer _horarioConsultado){
		
		//FIXME buscar operador para comparar horarios
		return (_horarioConsultado >= this.getHorarioInicial() && _horarioConsultado <= this.getHorarioFinal());
		
	}

	public Integer getHorarioInicial() {
		return horarioInicial;
	}

	public void setHorarioInicial(Integer horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	public Integer getHorarioFinal() {
		return horarioFinal;
	}

	public void setHorarioFinal(Integer horarioFinal) {
		this.horarioFinal = horarioFinal;
	}
	
}
