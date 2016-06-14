package ar.utn.dds.procesos;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.observers.Observador;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.procesos.estrategiaFallo.NoHayAccionesParaRealizarFalla;
import ar.utn.dds.procesos.estrategiaFallo.NoRealizarAccionPorFalla;
import ar.utn.dds.repositorio.RepositorioDeUsuarios;
import ar.utn.dds.usuarios.Usuario;

public class ModificarAcciones implements Proceso {

	String nombre;
	EstrategiaPorFallo fallo;
	List<Observador> acciones = new ArrayList<Observador>();
	RepositorioDeUsuarios usuarios = RepositorioDeUsuarios.getInstance();
	int idProcesoMultiple;
	String estado;
	
	
	public ModificarAcciones(){
		setEstado("Ok");
		fallo = new NoRealizarAccionPorFalla();		
	}
	
	@Override
	public void ejecutarse(EstrategiaPorFallo fallo) {
		if(puedeEjecutarse()){
			usuarios().forEach(usuario -> usuario.actualizarAcciones(acciones));
		}
		else{
			fallo.ejecutarse(this);
		}
	}

	private boolean puedeEjecutarse() {
		if(acciones.size() == 0){
			this.fallo = new NoHayAccionesParaRealizarFalla();
			this.setEstado("Error");
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public String getNombre() {
		return nombre;
	}
	
	public List<Usuario> usuarios(){
		return usuarios.getUsuarios();
	}
	
	public void activar(Observador accion){
		acciones.add(accion);
	}
	
	public void desactivar(Observador accion){
		acciones.remove(accion);
	}
	
	public EstrategiaPorFallo getFallo() {
		return fallo;
	}

	public void setFallo(EstrategiaPorFallo fallo) {
		this.fallo = fallo;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void activar(List<Observador> lista){
		lista.forEach(accion -> agregarSiNoExiste(accion));
	}
	
	public void desactivar(List<Observador> acciones){
		acciones.forEach(accion -> this.acciones.remove(accion));
	}

	private void agregarSiNoExiste(Observador accion) {
		if (acciones.contains(accion)){}
		else{
			acciones.add(accion);
		} 
	}
		

}
