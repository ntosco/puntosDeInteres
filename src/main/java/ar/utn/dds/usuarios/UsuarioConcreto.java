package ar.utn.dds.usuarios;

import java.util.ArrayList;
import java.util.List;
import ar.utn.dds.POI.POI;
import ar.utn.dds.observers.Observador;
import ar.utn.dds.roles.Rol;
import ar.utn.dds.utils.Consulta;

public class UsuarioConcreto implements Usuario {
	
	private List<Observador> accionesObservers = new ArrayList<Observador>();
	private String nombreUsuario;
	private List<Rol> roles;
	private String email;
	

	@Override
	public List<POI> buscarPuntos(String pablabraBuscada) {
		
		Consulta consulta = new Consulta(this,pablabraBuscada);
		List<POI> poisResultantes = new ArrayList<POI>();
		
		poisResultantes = consulta.evaluarse();
		
		this.notificarObservadores(consulta);
		
		return poisResultantes;
	}

	@Override
	public void agregarObservador(Observador observerador) {
		accionesObservers.add(observerador);
	}

	@Override
	public void quitarObservador(Observador observerador) {
		accionesObservers.remove(observerador);
	}

	@Override
	public void notificarObservadores(Consulta consulta) {
		accionesObservers.forEach(observer -> observer.actualizar(consulta));
		
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public Boolean tieneRolAdministrador() {
		return null;
	}

	public List<Observador> getAccionesObservers() {
		return accionesObservers;
	}

	public void setAccionesObservers(List<Observador> accionesObservers) {
		this.accionesObservers = accionesObservers;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
