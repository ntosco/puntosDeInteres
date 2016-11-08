package ar.utn.dds.repositorio;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.CentroGestionParticipacion;

import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;
import java.time.LocalTime;

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
			
	public static CentroGestionParticipacion convertToCGP(Node nodeCGP, boolean deep){
			CentroGestionParticipacion unCGP = new CentroGestionParticipacion();
			Procedure1<CentroGestionParticipacion> function = (CentroGestionParticipacion cgp) -> {
					int id = (int) nodeCGP.getId();
					cgp.setId(id);
					String nombreDePoi = (String) nodeCGP.getProperty("nombre", "");
					cgp.setNombre(nombreDePoi);
					String tipoDePoi = (String) nodeCGP.getProperty("tipo", "");	
					cgp.setTipo(tipoDePoi);
					String direccionDePoi = (String) nodeCGP.getProperty("direccionNombre", "");
					cgp.setDireccionNombre(direccionDePoi);
					int numeroDelPoi = Integer.parseInt((String) nodeCGP.getProperty("direccionNumero", ""));
					cgp.setDireccionNumero(numeroDelPoi);
					if(deep){
						List<Servicio> servicios = new ArrayList<Servicio>();
						for(Relationship relationship : nodeCGP.getRelationships(RelacionesPoi.DaServicio)){
							Node servicio1 = relationship.getOtherNode(nodeCGP);
							String unNombre1 = (String)servicio1.getProperty("nombre", null);
							Servicio unServicio = new Servicio(unNombre1, null);
							servicios.add(unServicio);	
						}
						unCGP.setListaServicios(servicios);
					}
				};
			
			return ObjectExtensions.<CentroGestionParticipacion>operator_doubleArrow(unCGP, function);
	}
	
	public static SucursalBanco convertToBanco(Node nodeBanco, boolean deep){
		SucursalBanco unBanco = new SucursalBanco();
		Procedure1<SucursalBanco> function = (SucursalBanco banco) -> {
				int id = (int) nodeBanco.getId();
				banco.setId(id);
				String nombreDePoi = (String) nodeBanco.getProperty("nombre", "");
				banco.setNombre(nombreDePoi);
				String tipoDePoi = (String) nodeBanco.getProperty("tipo", "");	
				banco.setTipo(tipoDePoi);
				String direccionDePoi = (String) nodeBanco.getProperty("direccionNombre", "");
				banco.setDireccionNombre(direccionDePoi);
				int numeroDelPoi = Integer.parseInt((String) nodeBanco.getProperty("direccionNumero", ""));
				banco.setDireccionNumero(numeroDelPoi);
				if(deep){
					List<Jornada> jornadas = new ArrayList<Jornada>();
					for(Relationship relationship : nodeBanco.getRelationships(RelacionesPoi.atiende)){
						Node jornada1 = relationship.getOtherNode(nodeBanco);
						String dia = (String)jornada1.getProperty("dia", null);
						String desde = (String)jornada1.getProperty("horaDesde", null);
						String hasta = (String)jornada1.getProperty("horaHasta", null);
						DayOfWeek diaJornada = null;
						switch (dia) {
						case "Monday":
							diaJornada = DayOfWeek.MONDAY;
						case "Tuesday":
							diaJornada = DayOfWeek.TUESDAY;
						case "Wednesday":
							diaJornada = DayOfWeek.WEDNESDAY;
						case "Thursday":
							 diaJornada = DayOfWeek.THURSDAY;
						case "Friday":
							 diaJornada = DayOfWeek.FRIDAY;
						case "Saturday":
							 diaJornada = DayOfWeek.SATURDAY;
						case "Sunday":
							 diaJornada = DayOfWeek.SUNDAY;
						}
							
							LocalTime horaDesde = LocalTime.of(Integer.parseInt(desde.substring(0, 2)),Integer.parseInt(desde.substring(3, 5)),0);
							LocalTime horaHasta = LocalTime.of(Integer.parseInt(hasta.substring(0, 2)),Integer.parseInt(hasta.substring(3, 5)),0);
							RangoHorario horario = new RangoHorario(horaDesde, horaHasta);
						Jornada unaJornada = new Jornada(diaJornada, horario);
						jornadas.add(unaJornada);	
					}
					unBanco.setJornadaDisponible(jornadas);
				}
			};
		return ObjectExtensions.<SucursalBanco>operator_doubleArrow(unBanco, function);
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
