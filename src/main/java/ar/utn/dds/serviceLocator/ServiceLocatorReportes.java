package ar.utn.dds.serviceLocator;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.reportes.Reporte;


public class ServiceLocatorReportes {
	
	static ServiceLocatorReportes instanceServiceLocator;
		
	private List<Reporte> listaServicios = new  ArrayList<Reporte>();
		
	public static ServiceLocatorReportes getInstance() {
		if (instanceServiceLocator == null) {
			instanceServiceLocator = new ServiceLocatorReportes();
		}	
		return instanceServiceLocator;
	}
	
	// ********************************************************
	// ** Getters and Setters
	// ********************************************************
	
	public List<Reporte> getServicios() {
		return listaServicios;
	}
	
	public void setServicio(Reporte reporte) {
		this.listaServicios.add(reporte);
	}
	
}
