package ar.utn.dds.POI;

import org.uqbar.geodds.Point;

public class LocalComercial extends POI{
	
	String rubro;
	int radioDeCercania;

	public boolean estaCercaDe(Point puntoTerminal) {
		
		return false;
	}

}
