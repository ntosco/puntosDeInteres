package ar.utn.dds.POI;

import java.util.ArrayList;

import org.uqbar.geodds.Point;

import ar.utn.dds.servicios.Servicio;

public class LocalComercial extends POI{
	
	ArrayList <Rubro> listaRubros = new ArrayList <Rubro>();
	
	int radioDeCercania;

	public boolean estaCercaDe(Point puntoTerminal) {
		return false;
	}
	
	public boolean perteneceAlRubro(String textoLibre){
		for (Rubro rubro : listaRubros){
			if (textoLibre.equals(rubro))
				return true;
		}
		return false;
	}

	public boolean cumpleCondicionBusqueda(String textoLibre){
		if (perteneceAlRubro(textoLibre)){
			return true;	
			
		}else {		
			return contieneKeyword(textoLibre);		
		}
	}
	
	
	
	
}
