package AppModel;

import org.uqbar.commons.utils.Observable;

import ar.utn.dds.POI.POI;

@Observable
public class VistaPOIAppModel {

	public String nombre;
	public String direccion;
	public POI poiSeleccionado; 
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public POI getPoiSeleccionado() {
		return poiSeleccionado;
	}
	public void setPoiSeleccionado(POI poiSeleccionado) {
		this.poiSeleccionado = poiSeleccionado;
	}
	
	
	
		
}
