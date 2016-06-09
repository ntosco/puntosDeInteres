package ar.utn.dds.observers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import ar.utn.dds.utils.BusquedaDePuntos;
import ar.utn.dds.utils.Consulta;

public class ObservadorPorFecha implements Observador{

	//private List<Integer> BusquedasPorFecha = new ArrayList<Integer>();
	private Hashtable<String,Integer> BusquedasPorFecha = new Hashtable<String,Integer>();

	public static ObservadorPorFecha instance;
	
	public static ObservadorPorFecha getInstance(){
		if (instance == null) {
			instance = new ObservadorPorFecha();
		}	
		return instance;
	}
	
	
	@Override
	public void actualizar(Consulta consulta) {
		if(getBusquedasPorFecha().containsKey(consulta.getFecha())){
			 BusquedasPorFecha.put(consulta.getFecha().toString(), BusquedasPorFecha.get(consulta.getFecha()) + consulta.getCantidadDeResultados());
		}else{
			 getBusquedasPorFecha().put(consulta.getFecha().toString(), consulta.getCantidadDeResultados());
		}
	}


	public Hashtable<String,Integer> getBusquedasPorFecha() {
		return BusquedasPorFecha;
	}


	public void setBusquedasPorFecha(Hashtable<String,Integer> busquedasPorFecha) {
		BusquedasPorFecha = busquedasPorFecha;
	}

}
