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
