package ar.utn.dds.usuarios;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.Review;
import ar.utn.dds.exceptions.InvalidPermissionsException;
import ar.utn.dds.observers.Observador;
import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.procesos.estrategiaFallo.NoRealizarAccionPorFalla;
import ar.utn.dds.roles.Rol;
import ar.utn.dds.roles.RolAdministrador;
import ar.utn.dds.utils.Consulta;

public class UsuarioConcreto implements Usuario {
	
	private List<Observador> accionesObservers = new ArrayList<Observador>();
	@Expose private String nombreUsuario;
	@Expose private String password;
	private Rol rol;
	private String email;
	private EstrategiaPorFallo estrategiaPorFallo = new NoRealizarAccionPorFalla();
	@Expose private List<POI> favoritos = new ArrayList<POI>();



	@Override
	public List<POI> buscarPuntos(String pablabraBuscada) {
		
		Consulta consulta = new Consulta(this,pablabraBuscada);

		return consulta.buscaPuntosYNotificaObservadores();
	}
	

	@Override
	public void opinar(POI punto, String comentario, int valoracion) {
		if(punto.elUsuarioYaOpino(this.getNombreUsuario())){
			
		}else{
			Review opinion = new Review(comentario,this.getNombreUsuario(),valoracion);
			punto.agregarReview(opinion);
		}
	}

	@Override
	public void ejecutarProceso(Proceso proceso) {
		proceso.setEstrategiaPorFallo(estrategiaPorFallo);
		this.rol.ejecutarProceso(proceso,this);
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	@Override
	public void actualizarAcciones(List<Observador> acciones){
		accionesObservers = acciones;
	}

	public EstrategiaPorFallo getEstrategiaPorFallo() {
		return estrategiaPorFallo;
	}

	public void setEstrategiaPorFallo(EstrategiaPorFallo estrategiaPorFallo) {
		this.estrategiaPorFallo = estrategiaPorFallo;
	}

	public List<POI> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<POI> favoritos) {
		this.favoritos = favoritos;
	}

	public void agregarFavorito(POI poiFavorito){
		List<POI> favoritosActuales = this.getFavoritos();
		favoritosActuales.add(poiFavorito);
		this.setFavoritos(favoritosActuales);
	}
	
	public void quitarFavorito(POI poiNoFavorito){
		List<POI> favoritosActuales = this.getFavoritos();
		favoritosActuales.remove(poiNoFavorito);
		this.setFavoritos(favoritosActuales);
	}	

	@Override
	public void notificarFalla() {
		this.setEmail(email);
		
	}




}
