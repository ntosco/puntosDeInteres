package ar.utn.dds.reportes;


import java.util.Hashtable;
import ar.utn.dds.repositorio.AbstractRepoMongo;
import ar.utn.dds.utils.Consulta;

public class ReporteParciales extends AbstractRepoMongo{
	
	private Hashtable<String,Integer> ResultadosParciales = new Hashtable<String,Integer>();

	public void procesarConsulta(Consulta consulta) {
		ResultadosParciales.put(consulta.getUsuarioEjecutor().toString(),consulta.getCantidadDeResultados());
		this.getDatastore().save(consulta);
	}
	
	//// Singleton

	public static ReporteParciales instance;
	
	public static ReporteParciales getInstance(){
		if (instance == null) {
			instance = new ReporteParciales();
		}	
		return instance;
	}

	private ReporteParciales(){
		super();
	}

	////Getters and Setters

	public void setCantidadResultadosParciales(Hashtable<String,Integer>cantidadResultadosParciales) {
		ResultadosParciales = cantidadResultadosParciales;
	}

	public Hashtable<String,Integer> getCantidadResultadosParciales() {
		return ResultadosParciales;
	}
	
}
