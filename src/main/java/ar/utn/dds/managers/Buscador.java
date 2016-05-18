package ar.utn.dds.managers;

import java.util.List;
import ar.utn.dds.POI.POI;

public interface Buscador {

	public List<POI> busquedaGeneral(String fraseBuscada);
	
}
