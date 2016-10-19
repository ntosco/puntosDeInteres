package ar.utn.dds.POI;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.uqbar.commons.utils.Observable;
import com.google.gson.annotations.Expose;

@Observable
@Entity
public class Review {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=100)
	@Expose private String comentario;
	
	
	@Expose private int valoracion;// de 1 a 5
	
	@Column(length=100)
	@Expose private String nombreUsuario;
	
	public Review() {
		
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	

	
}
