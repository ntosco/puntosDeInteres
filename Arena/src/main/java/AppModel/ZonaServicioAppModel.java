package AppModel;

import java.util.List;

import ar.utn.dds.servicios.Servicio;

public class ZonaServicioAppModel extends VistaPOIAppModel {


	public String zona;
	public List<Servicio> servicios;

	public String getZona() {
	return zona;	
	}
	public void setZona(String zona) {
	this.zona = zona;
	}
	public List<Servicio> getServicios() {
	return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
	this.servicios = servicios;
	}
	
}
