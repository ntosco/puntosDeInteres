package ar.utn.dds.utils;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.POI.POI;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.serviceLocator.ServiceLocator;

public class BusquedaDePuntos {
	
	public List<OrigenDeDatos> origenesDeDatos;
	public static ServiceLocator serviceLocator;
	public static BusquedaDePuntos instance;
	
	public static BusquedaDePuntos getInstance(){
		if (instance == null) {
			instance = new BusquedaDePuntos();
		}	
		return instance;
	}
	
	public BusquedaDePuntos(){
		origenesDeDatos.add(new AdapterCGP());
		origenesDeDatos.add(new AdapterBancos());
		origenesDeDatos.add(Repositorio.getInstance());
	}

	public List<POI> busquedaGeneral(String nombre){
		

// Ver por regla de negocio o por enunciado si es necesario actualizar cada vez que se haga una busqueda en el repositorio.
		
//		listaPorExterno.addAll(buscador.buscarBancoEnRepoExterno(nombre));
//		listaPorExterno.addAll(buscador.buscarCGPEnRepoExterno(nombre));
//		if (!(listaPorExterno.isEmpty())){
//			listaPorExterno.forEach(poi -> actualizarContraElRepo(poi));
//		}
//		return repositorioPois.search(nombre);   
		
		List<POI> Auxiliar = new ArrayList<POI>();
		
		origenesDeDatos.forEach(serv -> Auxiliar.addAll(serv.buscarPOI(nombre)));
		
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