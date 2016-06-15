package ar.utn.dds.procesos;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.utils.Estado;
import ar.utn.dds.utils.RangoHorario;

public abstract class Proceso {
	
	private String Nombre;
	private LocalDate fechaComienzo;
	private RangoHorario horarioDeEjecucion;
	private Usuario usuarioEjecutor;
	public Estado estado;
	private int idProcesoActualizacion;
	
	public void ejecutarse(EstrategiaPorFallo estrategiaPorFallo, Usuario usuario){
		this.fechaComienzo = LocalDate.now();
		this.usuarioEjecutor = usuario;
		this.horarioDeEjecucion.setHorarioInicial(LocalTime.now());	
		this.ejecutarse(estrategiaPorFallo);
		this.horarioDeEjecucion.setHorarioFinal(LocalTime.now());
	}
	
	abstract void ejecutarse(EstrategiaPorFallo estrategiaPorFallo);


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public LocalDate getFechaComienzo() {
		return fechaComienzo;
	}


	public void setFechaComienzo(LocalDate fechaComienzo) {
		this.fechaComienzo = fechaComienzo;
	}


	public RangoHorario getHorarioDeEjecucion() {
		return horarioDeEjecucion;
	}


	public void setHorarioDeEjecucion(RangoHorario horarioDeEjecucion) {
		this.horarioDeEjecucion = horarioDeEjecucion;
	}


	public Usuario getUsuarioEjecutor() {
		return usuarioEjecutor;
	}


	public void setUsuarioEjecutor(Usuario usuarioEjecutor) {
		this.usuarioEjecutor = usuarioEjecutor;
	}


	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public int getIdProcesoActualizacion() {
		return idProcesoActualizacion;
	}

	public void setIdProcesoActualizacion(int idProcesoActualizacion) {
		this.idProcesoActualizacion = idProcesoActualizacion;
	}

}
