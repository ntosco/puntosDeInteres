package ar.utn.dds.procesos;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.observers.Observador;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.repositorio.RepositorioDeUsuarios;
import ar.utn.dds.usuarios.Usuario;

public class ModificarAcciones implements Proceso {

	String nombre;
	List<Observador> acciones = new ArrayList<Observador>();
	List<Observador> undoList;
	RepositorioDeUsuarios usuarios = RepositorioDeUsuarios.getInstance();
	int idProcesoMultiple;
	static int id = 1;
	String estado;
	String undoEstado;
	
	
	
	public ModificarAcciones(){
		setEstado("Ok");
		idProcesoMultiple = ModificarAcciones.id++;
	}
	
	@Override
	public void ejecutarse(EstrategiaPorFallo fallo) {
		if(puedeEjecutarse()){
			copiarLista(usuarios().get(0).getAccionesObservers());
			copiarEstado();
			usuarios().forEach(usuario -> usuario.actualizarAcciones(acciones));
		}
		else{
			fallo.ejecutarse(this);
		}
	}

	public void copiarLista(List<Observador> accionesObservers) {
		undoList = new ArrayList<Observador>();
		accionesObservers.forEach(obs -> undoList.add(obs));
		
	}
	
	public void copiarEstado(){
		undoEstado = new String(estado);
	}

	public boolean puedeEjecutarse() {
		if(acciones.size() == 0){
			this.setEstado("Error");
			return false;
		}
		else{
			return true;
		}
	}
	
	public void undo(){
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

}
