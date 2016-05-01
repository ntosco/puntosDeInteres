package ar.utn.dds.ServicioExterno;

import java.util.List;


public class CentroDTO {
	
	private int numeroComuna;
	private String zonasIncluidas;
	private String nombreDirector;
	private String domicilio;
	private String telefono;
	private List<ServicioDTO> servicios;
	
	
	public int getComuna() {
		return numeroComuna;
	}


	public String getZonasIncluidas() {
		return zonasIncluidas;
	}


	public String getDomicilio() {
		return domicilio;
	}
	
	public List<ServicioDTO> getServicios(){
		return servicios;
	}
		
	}

