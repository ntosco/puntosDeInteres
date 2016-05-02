package ar.utn.dds.serviceLocator;

import ar.utn.dds.repositorio.Repositorio;

public class ServiceLocator {
	
	static ServiceLocator instanceServiceLocator;
	
//	Todo servicio que se desee agregar al ServiceLocator debe ser expuesto como atributo del mismo
//	y necesita que se genere su getter para poder ser accedido
	private Repositorio repositorioPois;

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
	

}
