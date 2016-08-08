package ar.utn.dds.observers;

import ar.utn.dds.reportes.ReporteTotales;
import ar.utn.dds.utils.Consulta;

public class ObservadorTotales implements Observador{
	
	
	public static ObservadorTotales instance;
	
	public static ObservadorTotales getInstance(){
		if (instance == null) {
			instance = new ObservadorTotales();
		}
		return instance;
	}
	
	
	@Override
	public void actualizar(Consulta consulta) {
		
		ReporteTotales.getInstance().procesarConsulta(consulta);
		
	}
}
