package ar.utn.dds.reportes;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.utils.Consulta;

public class ReporteAlmacenamientoConsultas implements Reporte{
	
	private List<Consulta> consultasAlmacenadas = new ArrayList<Consulta>();

	public static ReporteAlmacenamientoConsultas instance;
	
	public static ReporteAlmacenamientoConsultas getInstance(){
		if (instance == null) {
			instance = new ReporteAlmacenamientoConsultas();
		}
		return instance;
	}
	
	private ReporteAlmacenamientoConsultas() {
		super();
	}

	
	@Override
	public void emitirse() {
		// Implementarse a futuro
		
	}

	@Override
	public void procesarConsulta(Consulta consulta) {		
		this.consultasAlmacenadas.add(consulta);
	}
	
	public List<Consulta> getConsultasAlmacenadas() {
		return consultasAlmacenadas;
	}


	public void setConsultasAlmacenadas(List<Consulta> consultasAlmacenadas) {
		this.consultasAlmacenadas = consultasAlmacenadas;
	}

}
