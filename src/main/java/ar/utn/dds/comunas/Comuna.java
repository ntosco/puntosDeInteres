package ar.utn.dds.comunas;

import org.uqbar.geodds.*;

public class Comuna {

	Polygon areaComuna = new Polygon();
	
	public void setAreaDeComuna(Point punto){
		areaComuna.add(punto);
	}	
	
	
	public Boolean estaCercaDe(Point unPunto){
		return areaComuna.isInside(unPunto);
	}
	
}
