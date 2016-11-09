package ar.utn.dds.reportes;

import java.util.Hashtable;

import ar.utn.dds.repositorio.AbstractRepoMongo;
import ar.utn.dds.utils.Consulta;

public class ReportePorFecha extends AbstractRepoMongo{
	
	private Hashtable<String,Integer> BusquedasPorFecha = new Hashtable<String,Integer>();
	
	public void procesarConsulta(Consulta consulta) {
		
		if(getBusquedasPorFecha().containsKey(consulta.getFecha())){
			 BusquedasPorFecha.put(consulta.getFecha().toString(), BusquedasPorFecha.get(consulta.getFecha()) + consulta.getCantidadDeResultados());
		}else{
			 getBusquedasPorFecha().put(consulta.getFecha().toString(), consulta.getCantidadDeResultados());
		}	
		
		this.getDatastore().save(consulta);
	}
	
	////Singleton
	
	public static ReportePorFecha instance;
		
	public static ReportePorFecha getInstance(){
		if (instance == null) {
			instance = new ReportePorFecha();
		}	
		return instance;
	}
	
	private ReportePorFecha(){
		super();
	}
	
	////Getters and Setters
		
	public Hashtable<String,Integer> getBusquedasPorFecha() {
		return BusquedasPorFecha;
	}

	public void setBusquedasPorFecha(Hashtable<String,Integer> busquedasPorFecha) {
		BusquedasPorFecha = busquedasPorFecha;
	}

}
