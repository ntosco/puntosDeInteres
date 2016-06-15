package ar.utn.dds.procesos;

import java.util.ArrayList;
import java.util.List;


import org.json.simple.JSONObject;

import ar.utn.dds.POI.POI;
import ar.utn.dds.extern.servicioREST.ServicioREST;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.repositorio.Repositorio;


public class BajaDePOIS extends Proceso {

	private ServicioREST servicioREST;
	Repositorio repositorioLocal = Repositorio.getInstance();
	
	// Se modela el objeto JSON con los siguientes campos:
	// valorDebusqueda, "palabra "
	// fecha , XX/XX/XXXX
	
	
	@Override
	public void ejecutarse(EstrategiaPorFallo estrategia) {
		

		JSONObject informacionBaja = servicioREST.buscarPOIS();

		List<POI> listaAux= new ArrayList<POI>();
		listaAux = repositorioLocal.search(informacionBaja.get("valorDeBusqueda").toString());
		
		//Validacion de fallo.
		
		for(int i = 0 ; i < listaAux.size(); i++){
			
			if (listaAux.get(i).getFechaBaja() == null){
				//No action.
			}else{
				i = listaAux.size();
				estrategia.ejecutarse(this);
				return ;
				
				// Debo cambiar el estado??? - Consultar
				
				//Fin de proceso.
			}
			
		}
	
		listaAux.forEach(poi -> this.actualizar(poi, (informacionBaja.get("fecha").toString())));
		listaAux.forEach(poi -> repositorioLocal.update(poi));
		
	}
	
	private POI actualizar(POI poiActualizable, String fechaBaja){
		
		poiActualizable.setFechaBaja(fechaBaja);
		return poiActualizable;		
		
	}

	public ServicioREST getServicioREST() {
		return servicioREST;
	}

	public void setServicioREST(ServicioREST servicioREST) {
		this.servicioREST = servicioREST;
	}

	

}
