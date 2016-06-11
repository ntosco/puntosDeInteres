package ar.utn.dds.reportes;

import java.util.Hashtable;

import ar.utn.dds.utils.Consulta;

public class ReporteTotales implements Reporte{

	private Hashtable<String,Integer> TotalesPorUsuario = new Hashtable<String,Integer>();
	
	public static ReporteTotales instance;
	
	public static ReporteTotales getInstance(){
		if (instance == null) {
			instance = new ReporteTotales();
		}
		return instance;
	}
		
	
	
	@Override
	public void emitirse() {
		// Implementar a futuro
		
	}

	@Override
	public void procesarConsulta(Consulta consulta) {
		
		if(getTotalesPorUsuario().containsKey(consulta.getUsuarioEjecutor().getNombreUsuario())){
			String nombreUsuario = consulta.getUsuarioEjecutor().getNombreUsuario();
			int cantidadDeResultados = getTotalesPorUsuario().get(nombreUsuario);
			int cantidadDeResultadosConsulta = consulta.getCantidadDeResultados();
			int nuevaCantidadDeResultados = cantidadDeResultados + cantidadDeResultadosConsulta;
			
			getTotalesPorUsuario().put(nombreUsuario, nuevaCantidadDeResultados);
		}else{
			String nombreUsuario = consulta.getUsuarioEjecutor().getNombreUsuario();
			int cantidadDeResultadosConsulta = consulta.getCantidadDeResultados();
			
			getTotalesPorUsuario().put(nombreUsuario, cantidadDeResultadosConsulta);
		}
				
		
		
	}
	
	public Hashtable<String,Integer> getTotalesPorUsuario() {
		return TotalesPorUsuario;
	}


	public void setTotalesPorUsuario(Hashtable<String,Integer> totalesPorUsuario) {
		TotalesPorUsuario = totalesPorUsuario;
	}

}
