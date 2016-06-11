package ar.utn.dds.usuarios;

import java.util.ArrayList;
import java.util.List;
import ar.utn.dds.POI.POI;
import ar.utn.dds.observers.Observador;
import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.roles.Rol;
import ar.utn.dds.roles.RolAdministrador;
import ar.utn.dds.utils.Consulta;

public class UsuarioConcreto implements Usuario {
	
	private List<Observador> accionesObservers = new ArrayList<Observador>();
	private String nombreUsuario;
	private Rol rol;
	private String email;
	

	@Override
	public List<POI> buscarPuntos(String pablabraBuscada) {
		
		Consulta consulta = new Consulta(this,pablabraBuscada);
		List<POI> poisResultantes = new ArrayList<POI>();
		
		poisResultantes = consulta.evaluarse();
		
		this.notificarObservadores(consulta);
		
		return poisResultantes;
	}

//	El método ejecutar proceso no va a quedar de esta forma parametrizado,
//	Ver si pongo closure , un command o expresiones lambda para que se 
//	ejecute luego de la ejecución del proceso
	@Override
	public void ejecutarProceso(Proceso proceso) {
		this.rol.ejecutarProceso(proceso);
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
		return (this.rol instanceof RolAdministrador);
	}

	public List<Observador> getAccionesObservers() {
		return accionesObservers;
	}

	public void setAccionesObservers(List<Observador> accionesObservers) {
		this.accionesObservers = accionesObservers;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}





}
