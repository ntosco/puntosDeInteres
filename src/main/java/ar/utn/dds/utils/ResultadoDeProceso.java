package ar.utn.dds.utils;

import java.time.LocalDate;

import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.usuarios.Usuario;

public class ResultadoDeProceso {

	private LocalDate fecha;
	private RangoHorario rangoHorarioDeEjecucion;
	private Proceso proceso;
	private Usuario usuarioEjecutor;
	private Estado estado;
	
	
	public ResultadoDeProceso(Proceso proceso) {
		this.fecha = proceso.getFechaComienzo();
		this.rangoHorarioDeEjecucion = proceso.getHorarioDeEjecucion();
		this.usuarioEjecutor = proceso.getUsuarioEjecutor();
		this.estado = proceso.getEstado();
		this.proceso = proceso; 
	}
	



}
