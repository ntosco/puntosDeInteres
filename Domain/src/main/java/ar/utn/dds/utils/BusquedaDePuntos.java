package ar.utn.dds.utils;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.POI.POI;

public class BusquedaDePuntos {
	
	public List<OrigenDeDatos> origenesDeDatos = new ArrayList<OrigenDeDatos>();
	private List<POI> Auxiliar = new ArrayList<POI>();
	
	public static BusquedaDePuntos instance;
	
	
	
	public static BusquedaDePuntos getInstance(){
		if (instance == null) {
			instance = new BusquedaDePuntos();
		}	
		return instance;
	}
	
	
	public List<POI> busquedaGeneral(String nombre){
		Auxiliar.clear();
		origenesDeDatos.forEach(serv -> agregarAAuxiliar(serv.buscarPOI(nombre)));
		return Auxiliar;
		
	}
	
	//Metodo para la vista
	public List<POI> busquedaGeneralPorPalabrasClaves(List<String> palabrasClaves){
		List<POI> listaPois = new ArrayList<POI>();
		palabrasClaves.forEach(palabraClave ->listaPois.addAll(this.busquedaGeneral(palabraClave)));
		return listaPois;
	}
	
	private void agregarAAuxiliar(List<POI> lista){	
		if (!lista.isEmpty()){
			Auxiliar.addAll(lista);
		}		
	}
	
// ********************************************************
// ** Getters and Setters
// ********************************************************
		
	public List<OrigenDeDatos> getServicios() {
		return origenesDeDatos;
	}
		
	public void borrarListaServicios(){
		this.origenesDeDatos.clear();
	}
	
	public void setServicio(OrigenDeDatos servicio) {
		this.origenesDeDatos.add(servicio);
	}
	
	
}