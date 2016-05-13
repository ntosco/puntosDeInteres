package ar.utn.dds.utils;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.POI.POI;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.serviceLocator.ServiceLocator;

public class BusquedaDePuntos {
	
	public List<OrigenDeDatos> origenesDeDatos;
	public static BusquedaDePuntos instance;
	
	
	
	public static BusquedaDePuntos getInstance(){
		if (instance == null) {
			instance = new BusquedaDePuntos();
		}	
		return instance;
	}
	
//	public BusquedaDePuntos(){
//		origenesDeDatos.add(new AdapterCGP());
//		origenesDeDatos.add(new AdapterBancos());
//		origenesDeDatos.add(Repositorio.getInstance());
//	}

	public List<POI> busquedaGeneral(String nombre){
		
		List<POI> Auxiliar = new ArrayList<POI>();
		
		origenesDeDatos.forEach(serv -> Auxiliar.addAll(serv.buscarPOI(nombre)));
		
		return Auxiliar;
		
	}
	
	     // ********************************************************
		// ** Getters and Setters
		// ********************************************************
		
		public List<OrigenDeDatos> getServicios() {
			return origenesDeDatos;
		}
		
		public void setServicio(OrigenDeDatos servicio) {
			this.origenesDeDatos.add(servicio);
		}
	
	
}