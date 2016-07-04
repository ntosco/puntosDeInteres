package ar.utn.dds.observers;
import ar.utn.dds.reportes.ReporteAlmacenamientoConsultas;
import ar.utn.dds.utils.Consulta;

public class ObservadorAlmacenamientoDeConsultas implements Observador{

	
	@Override
	public void actualizar(Consulta consulta) {
		ReporteAlmacenamientoConsultas.getInstance().procesarConsulta(consulta);		
	}
	
}