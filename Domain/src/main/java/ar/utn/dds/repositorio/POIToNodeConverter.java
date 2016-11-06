package ar.utn.dds.repositorio;

import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.neo4j.graphdb.Node;
import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.SucursalBanco;

public class POIToNodeConverter {

	public static POI convertToPOI(Node nodePoi) {
		final Procedure1<POI> function = (POI it) -> {
			int id = (int) nodePoi.getId();
			it.setId(id);
			String nombreDePoi = (String) nodePoi.getProperty("nombre", "");
			it.setNombre(nombreDePoi);
			String tipoDePoi = (String) nodePoi.getProperty("tipo", "");	
			it.setTipo(tipoDePoi);
		};
			String tipoDePoi = (String) nodePoi.getProperty("tipo", "");
			POI unPoi = POIToNodeConverter.getTipoDePoi(tipoDePoi);
	return ObjectExtensions.<POI>operator_doubleArrow(unPoi, function);
	
	}
	
	public static POI getTipoDePoi(String tipoDePoi){
		switch (tipoDePoi) {
		case "paradaColectivo":
			POI parada = new ParadaDeColectivo(); return parada;
		case "localComercial":
			POI local = new LocalComercial(); return local;
		case "cgp":
			POI cgp = new CentroGestionParticipacion(); return cgp;
		case "sucursalBanco":
			POI banco = new SucursalBanco(); return banco;
		default:
			throw new RuntimeException("El tipo del Poi no se encuentra");
		}	
	}
}
