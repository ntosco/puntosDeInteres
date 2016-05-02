package ar.utn.dds.ServicioExterno;

public class RangoServicioDTO {
	
	private int numeroDia;
	private int horarioDesde;
	private int minutosDesde;
	private int horarioHasta;
	private int minutosHasta;
	
	public RangoServicioDTO(int numDia, int horaDesde, int minDesde, int horaHasta, int minHasta){
		setNumeroDia(numDia);
		setHorarioDesde(horaDesde);
		setMinutosDesde(minDesde);
		setHorarioHasta(horaHasta);
		setMinutosHasta(minHasta);
	}
	
	public void setNumeroDia(int numeroDia) {
		this.numeroDia = numeroDia;
	}

	public void setHorarioDesde(int horarioDesde) {
		this.horarioDesde = horarioDesde;
	}

	public void setMinutosDesde(int minutosDesde) {
		this.minutosDesde = minutosDesde;
	}

	public void setHorarioHasta(int horarioHasta) {
		this.horarioHasta = horarioHasta;
	}

	public void setMinutosHasta(int minutosHasta) {
		this.minutosHasta = minutosHasta;
	}
	
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

