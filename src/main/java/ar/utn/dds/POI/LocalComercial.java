package ar.utn.dds.POI;

import java.util.ArrayList;

import org.uqbar.geodds.Point;

import ar.utn.dds.servicios.Servicio;

public class LocalComercial extends POI{
	
	public ArrayList<Rubro> getListaRubros() {
		return listaRubros;
	}

	public void setListaRubros(ArrayList<Rubro> listaRubros) {
		this.listaRubros = listaRubros;
	}

	ArrayList <Rubro> listaRubros = new ArrayList <Rubro>();

	double DISTANCIA_MINIMA_DE_CERCANIA =  0.5; 
	
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
	
	public void setRadioDeCercania(double d){
		DISTANCIA_MINIMA_DE_CERCANIA = d;
	}
	
	public Boolean estaCercaDe(Point ubicacionTerminal){
		double d = ubicacionActual.distance(ubicacionTerminal);	
		return d < DISTANCIA_MINIMA_DE_CERCANIA;
	}

}
