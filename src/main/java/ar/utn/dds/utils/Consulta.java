package ar.utn.dds.utils;


public class Consulta {

	private String fraseBuscada;
	private Integer cantidadDeResultados;
	private long tiempoDeEjecucion;
	private String usuarioEjecutor;

	public Consulta() {
		super();

	}

	// ********************************************************
	// ** Getters and Setters
	// ********************************************************
	
	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public void setFraseBuscada(String fraseBuscada) {
		this.fraseBuscada = fraseBuscada;
	}

	public Integer getCantidadDeResultados() {
		return cantidadDeResultados;
	}

	public void setCantidadDeResultados(Integer cantidadDeResultados) {
		this.cantidadDeResultados = cantidadDeResultados;
	}

	public long getTiempoDeEjecucion() {
		return tiempoDeEjecucion;
	}

	public void setTiempoDeEjecucion(long tiempoDeEjecucion) {
		this.tiempoDeEjecucion = tiempoDeEjecucion;
	}

	public String getUsuarioEjecutor() {
		return usuarioEjecutor;
	}

	public void setUsuarioEjecutor(String usuarioEjecutor) {
		this.usuarioEjecutor = usuarioEjecutor;
	}

}
