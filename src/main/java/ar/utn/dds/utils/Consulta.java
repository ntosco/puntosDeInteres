package ar.utn.dds.utils;

import java.time.DayOfWeek;
import java.util.Date;

public class Consulta {
	// atributos predeterminados. Esperar respuesta de macarena.
	
	private String nombreUsuario;
	
	private int cantidadDeResultados;
	
	private String fecha;
	
	private String palabraBuscada;
	
	private int	tiempoTranscurrido; //En segundos
	
	
	public Consulta (String nombreUser, int cantidadResultados, String fechaConsulta, String palabraBusqueda , int tiempo){
		this.nombreUsuario = nombreUser;
		this.cantidadDeResultados = cantidadResultados;
		this.fecha = fechaConsulta;
		this.palabraBuscada = palabraBusqueda;
		this.tiempoTranscurrido = tiempo;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}

	public void setCantidadDeResultados(int cantidadDeResultados) {
		this.cantidadDeResultados = cantidadDeResultados;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPalabraBuscada() {
		return palabraBuscada;
	}

	public void setPalabraBuscada(String palabraBuscada) {
		this.palabraBuscada = palabraBuscada;
	}

	public int getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}

	public void setTiempoTranscurrido(int tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}
	
	

	
	
	
	

}
