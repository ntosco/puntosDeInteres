package ar.utn.dds.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.servicios.Servicio;

public class POIToNodeConverter {

	public static POI convertToPOI(Node nodePoi, boolean deep) {
			Procedure1<POI> function = (POI it) -> {
			int id = (int) nodePoi.getId();
			it.setId(id);
			String nombreDePoi = (String) nodePoi.getProperty("nombre", "");
			it.setNombre(nombreDePoi);
			String tipoDePoi = (String) nodePoi.getProperty("tipo", "");	
			it.setTipo(tipoDePoi);
			String direccionDePoi = (String) nodePoi.getProperty("direccionNombre", "");
			it.setDireccionNombre(direccionDePoi);
			int numeroDelPoi = Integer.parseInt((String) nodePoi.getProperty("direccionNumero", ""));
			it.setDireccionNumero(numeroDelPoi);
			String numeroDeLinea = (String) nodePoi.getProperty("linea", null);
			it.setLinea(numeroDeLinea);
			
			
		};
			String tipoDePoi2 = (String) nodePoi.getProperty("tipo", "");
			POI unPoi = POIToNodeConverter.getTipoDePoi(tipoDePoi2);
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
