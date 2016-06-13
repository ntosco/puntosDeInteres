package ar.utn.dds.utils;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.usuarios.Usuario;

public class ResultadoDeProceso {
	
	private LocalDate fecha;
	private LocalTime horarioInicial;
	private LocalTime horarioFinal;
	private Proceso procesoEjecutado;
	private Usuario usuarioEjecutor;
	private Estado estado;
	
	public Proceso getProcesoEjecutado() {
		return procesoEjecutado;
	}
	public void setProcesoEjecutado(Proceso procesoEjecutado) {
		this.procesoEjecutado = procesoEjecutado;
	}
	public Usuario getUsuarioEjecutor() {
		return usuarioEjecutor;
	}
	public void setUsuarioEjecutor(Usuario usuarioEjecutor) {
		this.usuarioEjecutor = usuarioEjecutor;
	}

}
