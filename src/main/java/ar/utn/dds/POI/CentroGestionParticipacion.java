package ar.utn.dds.POI;

import java.util.ArrayList;

import org.uqbar.geodds.*;

import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.servicios.Servicio;

public class CentroGestionParticipacion extends POI{

	ArrayList <Servicio> listaServicios = new ArrayList <Servicio>();
	Comuna comuna;	
			
	public boolean contieneServicio(String textoLibre){
		for (Servicio servicio : listaServicios){
			if (servicio.nombre().toLowerCase().contains(textoLibre))
				return true;
		}
		return false;
	}
	
	public boolean cumpleCondicionBusqueda(String textoLibre){
		if (contieneServicio(textoLibre)){
			return true;
		}else {		
			return contieneKeyword(textoLibre);		
		}
	}
	
	
	public Boolean estaCercaDe(Point unPunto){
		return comuna.estaCercaDe(unPunto);
	}

	public void setComuna(Comuna comunaNueva) {
		comuna = comunaNueva;
	}	
}
