package ar.utn.dds.observers;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import ar.utn.dds.reportes.ReporteTotales;
import ar.utn.dds.utils.Consulta;

public class ObservadorTotales implements Observador{
	
	private ReporteTotales reporte = new ReporteTotales();
	
	public static ObservadorTotales instance;
	
	public static ObservadorTotales getInstance(){
		if (instance == null) {
			instance = new ObservadorTotales();
		}
		return instance;
	}
	
	
	@Override
	public void actualizar(Consulta consulta) {
		
		reporte.procesarConsulta(consulta);
		
	}


	public ReporteTotales getReporte() {
		return reporte;
	}


	public void setReporte(ReporteTotales reporte) {
		this.reporte = reporte;
	}

	
}
