package ar.utn.dds.POI;

import org.uqbar.geodds.Point;

public class ParadaDeColectivo extends POI{
	
	static double DISTANCIA_MINIMA_DE_CERCANIA = 0.1;
	String linea; //Agrego linea tipo String para cada parada. "linea" es String porque puede incluir el ramal.
	
	
	public String getLinea() {
		return linea;
	}


	public void setLinea(String linea) {
		this.linea = linea;
	}


	public Boolean estaCercaDe(Point ubicacionTerminal){
		double d = ubicacionActual.distance(ubicacionTerminal);	
		return d < DISTANCIA_MINIMA_DE_CERCANIA;
	}
	

	public boolean CumpleCondicionBusqueda(String textoLibre){
		if (linea.toLowerCase().contains(textoLibre.toLowerCase())){
			return true;
		}else {	
			return contieneKeyword(textoLibre);
		}		
	}

}	


