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
		if(horarioFinal>horarioInicial){
			this.horarioInicial = horarioInicial;
			this.horarioFinal = horarioFinal;
		}else{
			this.horarioInicial = horarioFinal;
			this.horarioFinal = horarioInicial;
		}
	}

	public Boolean DentroRangoHorario(Integer _horarioConsultado){
		
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
