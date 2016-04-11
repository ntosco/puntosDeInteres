package ar.utn.dds.POI;

import org.uqbar.geodds.Point;

public class ParadaDeColectivo extends POI{
	
	static float DISTANCIA_MINIMA_DE_CERCANIA = 100;
	
	String linea; //Agrego linea tipo String para cada parada. "linea" es String porque puede incluir el ramal.
	
	
	
	public Boolean estaCercaDe(Point ubicacionTerminal){
		return ubicacionActual.distance(ubicacionTerminal) * 100 < DISTANCIA_MINIMA_DE_CERCANIA;
	}
	
	public boolean CumpleCondicionBusqueda(String textoLibre){
		if (linea.toLowerCase().contains(textoLibre.toLowerCase())){
			return true;
		}else {	
			return contieneKeyword(textoLibre);
		}
			
			
	}
}	


