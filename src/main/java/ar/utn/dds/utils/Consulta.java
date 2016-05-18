package ar.utn.dds.utils;


public class Consulta {

	private String fraseBuscada;
	private Integer cantidadDeResultados;
	private long tiempoDeEjecución;
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

	public long getTiempoDeEjecución() {
		return tiempoDeEjecución;
	}

	public void setTiempoDeEjecución(long tiempoDeEjecución) {
		this.tiempoDeEjecución = tiempoDeEjecución;
	}

	public String getUsuarioEjecutor() {
		return usuarioEjecutor;
	}

	public void setUsuarioEjecutor(String usuarioEjecutor) {
		this.usuarioEjecutor = usuarioEjecutor;
	}

}
