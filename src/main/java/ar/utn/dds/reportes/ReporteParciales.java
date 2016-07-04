package ar.utn.dds.reportes;


import java.util.Hashtable;
import ar.utn.dds.utils.Consulta;

public class ReporteParciales implements Reporte{
	
	private ReporteParciales(){
		super();
	}
	
	private Hashtable<String,Integer> ResultadosParciales = new Hashtable<String,Integer>();
	public static ReporteParciales instance;
		
	public static ReporteParciales getInstance(){
		if (instance == null) {
			instance = new ReporteParciales();
		}	
		return instance;
	}
	
	
	@Override
	public void emitirse() {
		// Implementar a futuro.
		
	}

	@Override
	public void procesarConsulta(Consulta consulta) {
		
		ResultadosParciales.put(consulta.getUsuarioEjecutor().toString(),consulta.getCantidadDeResultados());
		
	}
	

	public Hashtable<String,Integer> getCantidadResultadosParciales() {
		return ResultadosParciales;
	}

	public void setCantidadResultadosParciales(Hashtable<String,Integer>cantidadResultadosParciales) {
		ResultadosParciales = cantidadResultadosParciales;
	}

	
}
