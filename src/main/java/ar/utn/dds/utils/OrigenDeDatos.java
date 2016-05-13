package ar.utn.dds.utils;

import java.util.List;

import ar.utn.dds.POI.POI;

public abstract interface OrigenDeDatos {
	
	public List<POI> buscarPOI(String nombre);

}
