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
	private RangoHorario horarioDeEjecución = new RangoHorario(null, null);
	private Usuario usuarioEjecutor;
	private Estado estado = new Estado();
	private int idProceso;
	
	public void ejecutarse(EstrategiaPorFallo estrategiaPorFallo, Usuario usuario){
		this.fechaComienzo = LocalDate.now();
		this.usuarioEjecutor = usuario;
		horarioDeEjecución.setHorarioInicial(LocalTime.now());	
		this.ejecutarse(estrategiaPorFallo);
		this.horarioDeEjecución.setHorarioFinal(LocalTime.now());

	}
	
	public abstract void ejecutarse(EstrategiaPorFallo estrategiaPorFallo);


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


	public RangoHorario getHorarioDeEjecución() {
		return horarioDeEjecución;
	}


	public void setHorarioDeEjecución(RangoHorario horarioDeEjecución) {
		this.horarioDeEjecución = horarioDeEjecución;
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
		// TODO Auto-generated method stub
		return null;
	}

}
