package ar.utn.dds.utils;

import java.time.LocalDate;

public class Consulta {

	private BusquedaDePuntos buscador = BusquedaDePuntos.getInstance();
	private String fraseBuscada;
	private Integer cantidadDeResultados;
	private long tiempoDeEjecución;
	private LocalDate fecha;
	// FIXME: ver hasta que se modele (o no) el usuario para nuestro sistemas
	// primeramente solo va a ser un string
	private String usuarioEjecutor;

	public Consulta(String fraseBuscada, String usuarioEjecutor) {
		super();
		this.fraseBuscada = fraseBuscada;
		this.usuarioEjecutor = usuarioEjecutor;
		this.fecha = LocalDate.now();
	}

	public void activarse() {
		
		long tiempoInicial = System.currentTimeMillis();

		this.cantidadDeResultados = buscador.busquedaGeneral(this.fraseBuscada).size();
		this.calcularTiempoDeRespuesta(tiempoInicial);

	}

	private void calcularTiempoDeRespuesta(long tiempoInicial) {
		this.tiempoDeEjecución = System.currentTimeMillis() - tiempoInicial;
	}

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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getUsuarioEjecutor() {
		return usuarioEjecutor;
	}

	public void setUsuarioEjecutor(String usuarioEjecutor) {
		this.usuarioEjecutor = usuarioEjecutor;
	}

}
