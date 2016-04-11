package ar.utn.dds.POI;
import org.uqbar.geodds.*;

public class Terminal{

	POI poiABuscar;
	Point ubicacion;
	
	public void setPoiABuscar (POI poiABuscar){
		this.poiABuscar = poiABuscar;
	}
	
	public void BuscarPoi(String textoLibre){
		if (textoLibre.length() > 0) 							// textoLibre no debe ser vacio.
			poiABuscar.cumpleCondicionBusqueda(textoLibre);
	
	}
	
}
