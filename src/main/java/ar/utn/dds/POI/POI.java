package ar.utn.dds.POI;
import org.uqbar.geodds.*;

public abstract class POI {
	
	String direccionNombre;
	int direccionNumero;
	Point ubicacionActual;
	
	public void setUbicacionActual(Point unPunto){
		ubicacionActual = unPunto;
	}
}
