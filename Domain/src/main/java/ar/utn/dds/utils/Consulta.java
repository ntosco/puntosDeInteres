package ar.utn.dds.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.POI.POI;
import ar.utn.dds.usuarios.Usuario;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.annotations.Entity;

@Entity("consultas")
public class Consulta {

	@Id ObjectId id;
	@Embedded private Usuario usuarioEjecutor;
	@Property private int cantidadDeResultados;
	@Transient private LocalDate fecha;

	@Property private String palabraBuscada;
	@Transient private long tiempoTranscurrido; //En segundosS
	
	
	public Consulta (Usuario usuario, String palabraBusqueda){
		this.usuarioEjecutor = usuario;
		this.palabraBuscada = palabraBusqueda;
	}
	
	public List<POI> buscaPuntosYNotificaObservadores(){
		
		List<POI> listaResultado = new ArrayList<POI>();	
		
		long startTime = System.nanoTime();
		listaResultado = BusquedaDePuntos.getInstance().busquedaGeneral(this.palabraBuscada);
		long endTime = System.nanoTime();
		this.tiempoTranscurrido = (endTime - startTime);
		this.fecha = LocalDate.now();
		this.cantidadDeResultados = listaResultado.size();
		
		usuarioEjecutor.notificarObservadores(this);


		return listaResultado;
	}

	
	// ********************************************************
	// ** Getters and Setters
	// ********************************************************
	
	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}

	public void setCantidadDeResultados(int cantidadDeResultados) {
		this.cantidadDeResultados = cantidadDeResultados;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getPalabraBuscada() {
		return palabraBuscada;
	}

	public void setPalabraBuscada(String palabraBuscada) {
		this.palabraBuscada = palabraBuscada;
	}

	public long getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}

	public void setTiempoTranscurrido(long tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}

	public Usuario getUsuarioEjecutor() {
		return usuarioEjecutor;
	}

	public void setUsuarioEjecutor(Usuario usuarioEjecutor) {
		this.usuarioEjecutor = usuarioEjecutor;
	}

}
