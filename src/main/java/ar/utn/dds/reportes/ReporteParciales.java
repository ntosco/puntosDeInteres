package ar.utn.dds.reportes;


import java.util.Hashtable;
import ar.utn.dds.utils.Consulta;

public class ReporteParciales implements Reporte{
	
	public static ReporteParciales instance;
	
	private Hashtable<String,Integer> ResultadosParciales = new Hashtable<String,Integer>();
	
		
	//	private List<Integer> CantidadResultadosParciales = new ArrayList<Integer>();
	
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
	public void validarConsulta(Consulta consulta) {
		
		ResultadosParciales.put(consulta.getUsuarioEjecutor().toString(),consulta.getCantidadDeResultados());
		
	}
	

	public Hashtable<String,Integer> getCantidadResultadosParciales() {
		return ResultadosParciales;
	}

	public void setCantidadResultadosParciales(Hashtable<String,Integer>cantidadResultadosParciales) {
		ResultadosParciales = cantidadResultadosParciales;
	}

	
}
