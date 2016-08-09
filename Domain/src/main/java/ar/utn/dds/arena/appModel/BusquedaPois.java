package ar.utn.dds.arena.appModel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;
import ar.utn.dds.POI.POI;
import ar.utn.dds.utils.BusquedaDePuntos;

@Observable
public class BusquedaPois {

	private List<POI> pois = new ArrayList<POI>();
	private List<String> listaPalabrasClaves = new ArrayList<String>();
	private String palabraClave;
	private POI poiSeleccionado;
	
	
	//********************************
	// Acciones
	//********************************
	
	//TODO Definir quien es el que hace la busqueda (Usuario - Busqueda de puntos)
	public void buscarPOI(){
		this.pois = BusquedaDePuntos.getInstance().busquedaGeneralPorPalabrasClaves(listaPalabrasClaves);
	}
	
	public void agregarPalabrasClave(){
		this.listaPalabrasClaves.add(palabraClave);
		
	}
	
	public Object visualizarPOI() {
		//TODO: Ver como abrir una nueva venta de dialogo para cada tipo de POI
		return null;
	}
	//********************************
	// Getters & Setters
	//********************************

	public List<String> getListaPalabrasClaves() {
		return listaPalabrasClaves;
	}
	public void setListaPalabrasClaves(List<String> listaPalabrasClaves) {
		this.listaPalabrasClaves = listaPalabrasClaves;
	}
	public String getPalabraClave() {
		return palabraClave;
	}
	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}

	public POI getPoiSeleccionado() {
		return poiSeleccionado;
	}

	public void setPoiSeleccionado(POI poiSeleccionado) {
		this.poiSeleccionado = poiSeleccionado;
	}

	public List<POI> getPois() {
		return pois;
	}

	public void setPois(List<POI> pois) {
		this.pois = pois;
	}


		
}
