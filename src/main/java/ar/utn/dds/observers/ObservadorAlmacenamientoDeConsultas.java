package ar.utn.dds.observers;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.reportes.ReporteAlmacenamientoConsultas;
import ar.utn.dds.utils.Consulta;

public class ObservadorAlmacenamientoDeConsultas implements Observador{

	private ReporteAlmacenamientoConsultas reporte = new ReporteAlmacenamientoConsultas();
	
	@Override
	public void actualizar(Consulta consulta) {
		
		reporte.procesarConsulta(consulta);
		
	}

	public ReporteAlmacenamientoConsultas getReporte() {
		return reporte;
	}

	public void setReporte(ReporteAlmacenamientoConsultas reporte) {
		this.reporte = reporte;
	}
	
	
}
