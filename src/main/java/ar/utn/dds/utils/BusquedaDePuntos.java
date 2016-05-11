package ar.utn.dds.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.ServicioExterno.CentroDTO;
import ar.utn.dds.buscador.BuscadorDeCGP;
import ar.utn.dds.buscador.buscadorDeBancos;
import ar.utn.dds.exceptions.BusinessException;
import ar.utn.dds.serviceLocator.ServiceLocator;

public class BusquedaDePuntos {
	
	public static ServiceLocator serviceLocator;
	

	public List<POI> busquedaGeneral(String nombre){
		

// Ver por regla de negocio o por enunciado si es necesario actualizar cada vez que se haga una busqueda en el repositorio.
		
//		listaPorExterno.addAll(buscador.buscarBancoEnRepoExterno(nombre));
//		listaPorExterno.addAll(buscador.buscarCGPEnRepoExterno(nombre));
//		if (!(listaPorExterno.isEmpty())){
//			listaPorExterno.forEach(poi -> actualizarContraElRepo(poi));
//		}
//		return repositorioPois.search(nombre);   
		
		List<POI> Auxiliar = new ArrayList<POI>();
		
		serviceLocator.getInstance().getServicios().forEach(serv -> Auxiliar.addAll(serv.buscarPOI(nombre)));
		
		return Auxiliar;
		
	}
	
	
	
//	private void actualizarContraElRepo(POI poiEntrante){
//		try{
//			repositorioPois.create(poiEntrante);
//		}
//		catch(BusinessException excep){					//No es la misma excepcion.
//			repositorioPois.update(poiEntrante);
//		}
//	}
		
	
}