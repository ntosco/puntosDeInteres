package ar.utn.dds.utils;

import java.util.List;
import ar.utn.dds.POI.POI;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.serviceLocator.ServiceLocator;

public class AdapterRepo extends OrigenDeDatos{
	
	private Repositorio repositorioLocal;
	
	@Override
	public List<POI> buscarPOI(String nombre) {
		return repositorioLocal.search(nombre);		
	}
	
	// ********************************************************
	// ** Getters and Setters
	// ********************************************************
	
	public Repositorio getRepositorioLocal() {
		return repositorioLocal;
	}

	public void setRepositorioLocal(Repositorio repositorioLocal) {
		this.repositorioLocal = repositorioLocal;
	}

}
