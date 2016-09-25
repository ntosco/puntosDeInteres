package ar.utn.dds.POI;

import java.math.BigDecimal;

public class Review {
	private String comentario;
	private int valoracion;// de 1 a 5
	private String nombreUsuario;
	
	public Review(String comentario, String nombre, int valoracion) {
		this.setComentario(comentario);
		this.setNombreUsuario(nombre);
		this.setValoracion(valoracion);
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	

	
}
