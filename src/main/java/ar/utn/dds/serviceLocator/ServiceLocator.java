package ar.utn.dds.serviceLocator;

import java.util.ArrayList;
import java.util.List;


import ar.utn.dds.utils.OrigenDeDatos;

public class ServiceLocator {
	
	static ServiceLocator instanceServiceLocator;
		
	private List<OrigenDeDatos> listaServicios = new  ArrayList<OrigenDeDatos>();
		
	public static ServiceLocator getInstance() {
		if (instanceServiceLocator == null) {
			instanceServiceLocator = new ServiceLocator();
		}	
		return instanceServiceLocator;
	}
	
	// ********************************************************
	// ** Getters and Setters
	// ********************************************************
	
	public List<OrigenDeDatos> getServicios() {
		return listaServicios;
	}
	
	public void setServicio(OrigenDeDatos servicio) {
		this.listaServicios.add(servicio);
	}
	
}
