package ar.utn.dds.observers;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import ar.utn.dds.utils.Consulta;

public class ObservadorTotales implements Observador{
	private Hashtable<String,Integer> TotalesPorUsuario = new Hashtable<String,Integer>();
	
	public static ObservadorTotales instance;
	
	public static ObservadorTotales getInstance(){
		if (instance == null) {
			instance = new ObservadorTotales();
		}	
		return instance;
	}
	
	
	@Override
	public void actualizar(Consulta consulta) {
		if(TotalesPorUsuario.containsKey(consulta.getNombreUsuario())){
			TotalesPorUsuario.put(consulta.getNombreUsuario(), consulta.getCantidadDeResultados());
		}else{
			TotalesPorUsuario.put(consulta.getNombreUsuario(), TotalesPorUsuario.get(consulta.getNombreUsuario()) + consulta.getCantidadDeResultados());
		}
		// TODO Auto-generated method stub
		
	}


}
