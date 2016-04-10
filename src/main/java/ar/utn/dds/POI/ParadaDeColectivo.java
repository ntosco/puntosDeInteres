package ar.utn.dds.POI;

import org.uqbar.geodds.Point;

public class ParadaDeColectivo extends POI{
	
	static double DISTANCIA_MINIMA_DE_CERCANIA = 0.1;

	public Boolean estaCercaDe(Point ubicacionTerminal){
		double d = ubicacionActual.distance(ubicacionTerminal);	
		return d < DISTANCIA_MINIMA_DE_CERCANIA;
	}
	
}	


