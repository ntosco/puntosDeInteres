package ar.utn.dds.observers;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.reportes.ReporteParciales;
import ar.utn.dds.reportes.ReportePorFecha;
import ar.utn.dds.utils.Consulta;

public class ObservadorParciales implements Observador {

	private ReporteParciales reporte = new ReporteParciales();
	
	@Override
	public void actualizar(Consulta consulta) {
		
		reporte.validarConsulta(consulta);
				
	}


}
