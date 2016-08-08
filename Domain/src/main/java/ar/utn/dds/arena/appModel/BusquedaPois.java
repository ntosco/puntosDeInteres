package ar.utn.dds.arena.appModel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;
import ar.utn.dds.POI.POI;

@Observable
public class BusquedaPois {

	POI pois;
	List<String> listaPalabrasClaves = new ArrayList<String>();
	String palabraClave;
	POI poiSeleccionado;
	
	
	
	//********************************
	// Getters & Setters
	//********************************
	
	public POI getPois() {
		return pois;
	}
	public void setPois(POI pois) {
		this.pois = pois;
	}
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
		
}
