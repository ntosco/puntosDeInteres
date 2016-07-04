package ar.utn.dds.observers;

import ar.utn.dds.reportes.ReportePorFecha;
import ar.utn.dds.utils.Consulta;

public class ObservadorPorFecha implements Observador{

	// Mantengo al Observer como Singleton.
	
	public static ObservadorPorFecha instance;

	public static ObservadorPorFecha getInstance(){
		if (instance == null) {
			instance = new ObservadorPorFecha();
		}	
		return instance;
	}
	
	@Override
	public void actualizar(Consulta consulta) {
		ReportePorFecha.getInstance().procesarConsulta(consulta);		
	}

}
