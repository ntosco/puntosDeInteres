package ar.utn.dds.reportes;

import java.util.Hashtable;

import ar.utn.dds.utils.Consulta;

public class ReportePorFecha implements Reporte {
	
	private Hashtable<String,Integer> BusquedasPorFecha = new Hashtable<String,Integer>();
	
	public static ReportePorFecha instance;
		
	public static ReportePorFecha getInstance(){
		if (instance == null) {
			instance = new ReportePorFecha();
		}	
		return instance;
	}
	
	@Override
	public void validarConsulta(Consulta consulta) {
	
		if(getBusquedasPorFecha().containsKey(consulta.getFecha())){
			 BusquedasPorFecha.put(consulta.getFecha().toString(), BusquedasPorFecha.get(consulta.getFecha()) + consulta.getCantidadDeResultados());
		}else{
			 getBusquedasPorFecha().put(consulta.getFecha().toString(), consulta.getCantidadDeResultados());
		}	
	}


	
	@Override
	public void emitirse() {
		// Implementar cuando realice el Actualizador de reportes.
		
	}
		
	
	
	public Hashtable<String,Integer> getBusquedasPorFecha() {
		return BusquedasPorFecha;
	}


	public void setBusquedasPorFecha(Hashtable<String,Integer> busquedasPorFecha) {
		BusquedasPorFecha = busquedasPorFecha;
	}


	

	
	

}
