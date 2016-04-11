package ar.utn.dds.POI;

import java.util.ArrayList;
import ar.utn.dds.servicios.Servicio;

public class CentroGestionParticipacion extends POI{

	ArrayList <Servicio> listaServicios = new ArrayList <Servicio>();
		
	
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
	
	
	/*
	private Boolean estaCercaDe(Point otroPOI){
		return ubicacionActual.distance(otroPOI) * 100 < DISTANCIA_MINIMA_DE_CERCANIA;
	}
	
	*/
	
	
}
