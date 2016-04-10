package ar.utn.dds.POI;

import org.uqbar.geodds.Point;

public class ParadaDeColectivo extends POI{
	
	static float DISTANCIA_MINIMA_DE_CERCANIA = 100;
	
	public Boolean estaCercaDe(Point ubicacionTerminal){
		return ubicacionActual.distance(ubicacionTerminal) * 100 < DISTANCIA_MINIMA_DE_CERCANIA;
	}
	
	
}	


