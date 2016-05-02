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

	public void setNumeroComuna(int numero) {
		numeroComuna = numero;
	}

	public void setZonasIncluidas(String zonas) {
		zonasIncluidas = zonas;
	}

	public void setNombreDirector(String nombre) {
		nombreDirector = nombre;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setServicios(List<ServicioDTO> servicios) {
		this.servicios = servicios;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public List<ServicioDTO> getServicios() {
		return servicios;
	}

	public CentroDTO(int numeroDeComuna, String zonasIncluidas,
			String nombreDirector, String domicilio, String telefono,
			List<ServicioDTO> servicios) {
		setNumeroComuna(numeroDeComuna);
		setZonasIncluidas(zonasIncluidas);
		setNombreDirector(nombreDirector);
		setDomicilio(domicilio);
		setTelefono(telefono);
		setServicios(servicios);

	}

}
