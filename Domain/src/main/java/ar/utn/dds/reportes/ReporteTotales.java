package ar.utn.dds.reportes;

import java.util.Hashtable;

import ar.utn.dds.repositorio.AbstractRepoMongo;
import ar.utn.dds.utils.Consulta;

public class ReporteTotales extends AbstractRepoMongo{

	private Hashtable<String,Integer> TotalesPorUsuario = new Hashtable<String,Integer>();
	
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
		
		this.getDatastore().save(consulta);
	}
		
	//// Singleton
	
	private ReporteTotales (){
		super();
	}
	
	public static ReporteTotales instance;
	
	public static ReporteTotales getInstance(){
		if (instance == null) {
			instance = new ReporteTotales();
		}
		return instance;
	}
		
	//// Getters and Setters
	
	public Hashtable<String,Integer> getTotalesPorUsuario() {
		return TotalesPorUsuario;
	}

	public void setTotalesPorUsuario(Hashtable<String,Integer> totalesPorUsuario) {
		TotalesPorUsuario = totalesPorUsuario;
	}

}
