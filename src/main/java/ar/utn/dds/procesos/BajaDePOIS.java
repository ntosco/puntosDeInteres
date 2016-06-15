package ar.utn.dds.procesos;

import java.util.ArrayList;
import java.util.List;


import org.json.simple.JSONObject;

import ar.utn.dds.POI.POI;
import ar.utn.dds.extern.servicioREST.ServicioREST;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.utils.Estado;


public class BajaDePOIS extends Proceso {

	private ServicioREST servicioREST;
	Repositorio repositorioLocal = Repositorio.getInstance();
	String nombre;
	int idProcesoBajas;
	
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
			
			if (listaAux.get(i).getFechaBaja() != null){
			
				i = listaAux.size();
				estrategia.ejecutarse(this);
				
				//Cambio el Estado.
				
				Estado estado = new Estado();
				estado.setEstadoComoErroneo();
				estado.setDescripcion("El proceso fallo ya que el POI ya fue dado de baja");
				this.setEstado(estado);
				
								
				return; // Cierro el metodo ejecutarse
				
			}
			
		}
	
		listaAux.forEach(poi -> this.actualizar(poi, (informacionBaja.get("fecha").toString())));
		listaAux.forEach(poi -> repositorioLocal.update(poi));
		
		Estado estado = new Estado();
		estado.setEstadoComoOK();
		estado.setDescripcion("El proceso se completo exitosamente");
		this.setEstado(estado);
		
		
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
