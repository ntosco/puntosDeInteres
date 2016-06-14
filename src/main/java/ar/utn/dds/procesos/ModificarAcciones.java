package ar.utn.dds.procesos;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.observers.Observador;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.repositorio.RepositorioDeUsuarios;
import ar.utn.dds.usuarios.Usuario;

public class ModificarAcciones implements Proceso {

	private String nombre;
	private EstrategiaPorFallo fallo;
	private List<Observador> acciones = new ArrayList<Observador>();
	private RepositorioDeUsuarios usuarios = RepositorioDeUsuarios.getInstance();
	

	@Override
	public void ejecutarse(EstrategiaPorFallo fallo) {
		usuarios().forEach(usuario -> usuario.actualizarAcciones(acciones));
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
