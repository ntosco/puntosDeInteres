package ar.utn.dds.reportes;

import ar.utn.dds.utils.Consulta;

public interface Reporte {
	
	public void emitirse();
	public void validarConsulta(Consulta consulta);

}
