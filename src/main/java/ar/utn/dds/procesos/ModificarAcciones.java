package ar.utn.dds.procesos;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.observers.Observador;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.repositorio.RepositorioDeUsuarios;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.utils.Estado;

public class ModificarAcciones extends Proceso {


	String nombre;
	List<Observador> acciones = new ArrayList<Observador>();
	List<Observador> undoList;
	RepositorioDeUsuarios usuarios = RepositorioDeUsuarios.getInstance();
	int idProcesoMultiple;
	static int id = 1;
	Estado undoEstado;
	
	public ModificarAcciones() {
		this.estado = new Estado();
		idProcesoMultiple = ModificarAcciones.id++;
	}	
	

	
	@Override
	public void ejecutarse() {
		Usuario usuario = usuarios().get(0);
		RepositorioDeUsuarios.getInstance().agregarUsuario(usuario);
		copiarLista(usuario.getAccionesObservers());
		copiarEstado();		
		if(puedeEjecutarse()){
			usuarios().forEach(usr -> usr.actualizarAcciones(acciones));
		}
		else{
			this.getEstrategiaPorFallo().ejecutarse(this);
		}
	}

	public void copiarLista(List<Observador> accionesObservers) {
		undoList = new ArrayList<Observador>();
		accionesObservers.forEach(obs -> undoList.add(obs));
		
	}
	
	public void copiarEstado(){
		int estadoAnterior = estado.getValor();
		this.undoEstado = new Estado();
		undoEstado.setValor(estadoAnterior);
		
	}

	public boolean puedeEjecutarse() {
		if(acciones.size() == 0){
			this.estado.setDescripcion("No hay acciones para ejecutar");
			this.estado.setEstadoComoErroneo();
			return false;
		}
		else{
			this.estado.setDescripcion("Ok");
			this.estado.setEstadoComoOK();
			return true;
		}
	}
	
	public void undo(){
		undoEstado();
		undoListaDeAcciones();
	}
	
	public void undoEstado(){
		int estadoAnterior = undoEstado.getValor();
		String descripcion = undoEstado.getDescripcion();
		this.estado = new Estado();
		estado.setValor(estadoAnterior);
		estado.setDescripcion(descripcion);
	}
	
	public void undoListaDeAcciones(){
		usuarios().forEach(usuario -> usuario.actualizarAcciones(undoList));
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
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void activar(List<Observador> lista){
		lista.forEach(accion -> agregarSiNoExiste(accion));
	}
	
	public void desactivar(List<Observador> acciones){
		acciones.forEach(accion -> this.acciones.remove(accion));
	}

	public void agregarSiNoExiste(Observador accion) {
		if (acciones.contains(accion)){}
		else{
			acciones.add(accion);
		} 
	}
	
	public void setAcciones(List<Observador> acciones){
		this.acciones = acciones;
	}
	
	public List<Observador> getUndoList() {
		return undoList;
	}

	public void setUndoList(List<Observador> undoList) {
		this.undoList = undoList;
	}

	public int getIdProcesoMultiple() {
		return idProcesoMultiple;
	}

	public void setIdProcesoMultiple(int idProcesoMultiple) {
		this.idProcesoMultiple = idProcesoMultiple;
	}

	public List<Observador> getAcciones() {
		return acciones;
	}

}
