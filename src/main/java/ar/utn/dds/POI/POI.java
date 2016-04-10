package ar.utn.dds.POI;
import org.uqbar.geodds.*;

public abstract class POI {
	
	String direccionNombre;
	int direccionNumero;
	Point ubicacionActual;
	static double DISTANCIA_MINIMA_DE_CERCANIA = 0.5;
	
	public void setUbicacionActual(Point unPunto){
		ubicacionActual = unPunto;
	}
	
	public Boolean estaCercaDe(Point ubicacionTerminal){
		double d = ubicacionActual.distance(ubicacionTerminal);	
		return d < DISTANCIA_MINIMA_DE_CERCANIA;
	}
}
