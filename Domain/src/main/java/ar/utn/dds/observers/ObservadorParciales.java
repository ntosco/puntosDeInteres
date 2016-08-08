package ar.utn.dds.observers;

import ar.utn.dds.reportes.ReporteParciales;
import ar.utn.dds.utils.Consulta;

public class ObservadorParciales implements Observador {
	
	@Override
	public void actualizar(Consulta consulta) {
		ReporteParciales.getInstance().procesarConsulta(consulta);				
	}

}
