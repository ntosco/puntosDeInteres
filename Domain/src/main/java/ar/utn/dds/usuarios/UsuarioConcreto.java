package ar.utn.dds.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.uqbar.commons.utils.Observable;

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

@Entity
@Observable
public class UsuarioConcreto implements Usuario {
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(fetch=FetchType.LAZY)
	private List<Observador> accionesObservers = new ArrayList<Observador>();
	
	@Column(length=150)
	@Expose private String nombreUsuario;
	
	@Column(length=150)
	@Expose private String password;
	
	private Rol rol;
	
	@Column(length=150)
	private String email;
	
	private EstrategiaPorFallo estrategiaPorFallo = new NoRealizarAccionPorFalla();
	
	@ManyToMany(fetch=FetchType.LAZY)
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
			Review opinion = new Review();
				opinion.setComentario(comentario);
				opinion.setNombreUsuario(this.getNombreUsuario());
				opinion.setValoracion(valoracion);
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
	
	@OneToMany(fetch=FetchType.LAZY)
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

	@Override
	public void agregarFavorito(POI poiFavorito){
		List<POI> favoritosActuales = this.getFavoritos();
		favoritosActuales.add(poiFavorito);
		this.setFavoritos(favoritosActuales);
	}
	
	@Override
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
