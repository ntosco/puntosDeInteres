package ar.utn.dds.observers;

import ar.utn.dds.reportes.ReporteParciales;
import ar.utn.dds.utils.Consulta;

public class ObservadorParciales implements Observador {

	private ReporteParciales reporte = new ReporteParciales();
	
	@Override
	public void actualizar(Consulta consulta) {
		
		reporte.procesarConsulta(consulta);
				
	}

	public ReporteParciales getReporte() {
		return reporte;
	}

	public void setReporte(ReporteParciales reporte) {
		this.reporte = reporte;
	}

	

}
