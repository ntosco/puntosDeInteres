package ar.utn.dds.serviceLocator;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import ar.utn.dds.POI.POI;
import ar.utn.dds.buscador.BuscadorDeCGP;

import ar.utn.dds.buscador.buscadorDeBancos;
import ar.utn.dds.exceptions.BusinessException;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.utils.BusquedaDePuntos;
import ar.utn.dds.utils.Conversor;

public class ServiceLocator {
	
	static ServiceLocator instanceServiceLocator;
	
	
		
	
	
//	Todo servicio que se desee agregar al ServiceLocator debe ser expuesto como atributo del mismo
//	y necesita que se genere su getter para poder ser accedido
	private Repositorio repositorioPois;
	
	private buscadorDeBancos servicioBanco;
	private BuscadorDeCGP servicioCGT;
	
	private List<POI> listaPorExterno = new  ArrayList<POI>();
	

	private ServiceLocator(){
		this.repositorioPois = Repositorio.getInstance();
	}
	
	public static ServiceLocator getInstance() {
		if (instanceServiceLocator == null) {
			instanceServiceLocator = new ServiceLocator();
		}	
		return instanceServiceLocator;
	}
	
	// ********************************************************
	// ** Getters y Setters
	// ********************************************************
	

	public Repositorio getRepositorioPois() {
		return repositorioPois;
	}

	public void setRepositorioPois(Repositorio repositorioPois) {
		this.repositorioPois = repositorioPois;
	}
	
	public List<POI> busquedaGeneral(String nombre){
		
		BusquedaDePuntos buscador = new BusquedaDePuntos();
		
		buscador.setBuscadorDeBancos(servicioBanco);
		buscador.setBuscadorDeCGP(servicioCGT);
		
		listaPorExterno.addAll(buscador.buscarBancoEnRepoExterno(nombre));
		listaPorExterno.addAll(buscador.buscarCGPEnRepoExterno(nombre));
				
		listaPorExterno.forEach(poi -> actualizarContraElRepo(poi));
		
		return repositorioPois.search(nombre);      
		
	}	
	
	private void actualizarContraElRepo(POI poiEntrante){
		try{
			repositorioPois.create(poiEntrante);
		}
		catch(BusinessException excep){
			repositorioPois.update(poiEntrante);
		}
	}

}
