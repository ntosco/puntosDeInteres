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
