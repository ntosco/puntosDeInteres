package ar.utn.dds.POI;

import org.uqbar.geodds.Point;

public class LocalComercial extends POI{
	
	String rubro;
	double DISTANCIA_MINIMA_DE_CERCANIA =  0.5; 
	
	public void setRadioDeCercania(double d){
		DISTANCIA_MINIMA_DE_CERCANIA = d;
	}
	
	public Boolean estaCercaDe(Point ubicacionTerminal){
		double d = ubicacionActual.distance(ubicacionTerminal);	
		return d < DISTANCIA_MINIMA_DE_CERCANIA;
	}
	
}
