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
	private RangoHorario horarioDeEjecucion = new RangoHorario(null, null);
	private Usuario usuarioEjecutor;
	public Estado estado = new Estado();
	private int idProceso;
	private EstrategiaPorFallo estrategiaPorFallo ;	
	
	public void ejecutarse( Usuario usuario){
		this.fechaComienzo = LocalDate.now();
		this.usuarioEjecutor = usuario;
		this.horarioDeEjecucion.setHorarioInicial(LocalTime.now());	
		this.ejecutarse();
		this.horarioDeEjecucion.setHorarioFinal(LocalTime.now());

	}
	
	public abstract void ejecutarse();


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
	
	public int getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(int idProceso) {
		this.idProceso = idProceso;
	}

	public Boolean enEstadoErroneo() {
		return this.estado.esUnEstadoError();
	}

	public EstrategiaPorFallo getEstrategiaPorFallo() {
		return estrategiaPorFallo;
	}

	public void setEstrategiaPorFallo(EstrategiaPorFallo estrategiaPorFallo) {
		this.estrategiaPorFallo = estrategiaPorFallo;
	}

}