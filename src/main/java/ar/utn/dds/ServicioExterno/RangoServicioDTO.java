package ar.utn.dds.ServicioExterno;

public class RangoServicioDTO {
	
	private int numeroDia;
	private int horarioDesde;
	private int minutosDesde;
	private int horarioHasta;
	private int minutosHasta;
	
	public int getHorarioDesde(){
		return horarioDesde;
	}
	
	public int getHorarioHasta(){
		return horarioHasta;
	}
	
	public int getMinutosDesde(){
		return minutosDesde;
	}
	
	public int getMinutosHasta(){
		return minutosHasta;
	}
	
	public int getNumeroDia(){
		return numeroDia;
	}
		
}

