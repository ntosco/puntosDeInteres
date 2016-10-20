package ar.utn.dds.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.POI.PointJava;
import ar.utn.dds.POI.PolygonJava;
import ar.utn.dds.POI.Review;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadFullTime;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxRangoHorario;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxServicio;
import ar.utn.dds.observers.Observador;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.usuarios.UsuarioConcreto;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

public class RepositorioDeUsuarios{
	//TODO Ver si es conveniente extender de CollectionBaseRepo
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	
	
	/* Singleton */
	private static RepositorioDeUsuarios Repoinstance;
	private static final SessionFactory sessionFactory = new Configuration()
														.configure()
														.addAnnotatedClass(UsuarioConcreto.class)
														.addAnnotatedClass(POI.class)
														.addAnnotatedClass(LocalComercial.class)
														.addAnnotatedClass(SucursalBanco.class)
														.addAnnotatedClass(CentroGestionParticipacion.class)
														.addAnnotatedClass(ParadaDeColectivo.class)
														.addAnnotatedClass(Review.class)
														.addAnnotatedClass(Rubro.class)
														.addAnnotatedClass(Jornada.class)
														.addAnnotatedClass(Comuna.class)
														.addAnnotatedClass(RangoHorario.class)
														.addAnnotatedClass(EstrategiaDisponibilidad.class)
														.addAnnotatedClass(DisponibilidadFullTime.class)
														.addAnnotatedClass(DisponibilidadxRangoHorario.class)
														.addAnnotatedClass(DisponibilidadxServicio.class)
														.addAnnotatedClass(PointJava.class)
														.addAnnotatedClass(Observador.class)
														.addAnnotatedClass(PolygonJava.class)
														.buildSessionFactory();
	
	@SuppressWarnings("unchecked")
	public List<Usuario> allInstances() {
		Session session = sessionFactory.openSession();
		try {
			return session.createCriteria(UsuarioConcreto.class).list();
		} finally {
			session.close();
		}
	}
	
	public static RepositorioDeUsuarios getInstance(){
		if (Repoinstance == null) {
			Repoinstance = new RepositorioDeUsuarios();
		}	
		return Repoinstance;
	}
	
	private RepositorioDeUsuarios(){
		this.setearUsuario(new UsuarioConcreto(),"martin", "samo");
		this.setearUsuario(new UsuarioConcreto(),"nico", "tosco");
		this.setearUsuario(new UsuarioConcreto(),"maca", "lepera");
		this.setearUsuario(new UsuarioConcreto(),"fer", "haspert");
	}

	public void clean(){
		this.usuarios.clear();
		//Repoinstance = null;
	}
	
	public Usuario setearUsuario(Usuario usuarioNuevo, String nombreUsuario, String password){
		usuarioNuevo.setNombreUsuario(nombreUsuario);
		usuarioNuevo.setPassword(password);
			
		//Agrego un ejemplo de POI Favorito.
		
		POI poi = new LocalComercial();
				
		poi.setNombre("Libreria");
		poi.setDireccionNombre("Av. Hipolito Yrigoyen 4276");				
			
		List<POI> aux2 = new ArrayList<POI>();	
		aux2.add(poi);
			
		usuarioNuevo.setFavoritos(aux2);
		
		this.agregarUsuario(usuarioNuevo);
		return usuarioNuevo;
	}
	
/*
	public Usuario crearUsuario(String nombreUsuario, String password){
		Usuario usuarioNuevo = new UsuarioConcreto();
		usuarioNuevo.setNombreUsuario(nombreUsuario);
		usuarioNuevo.setPassword(password);
			
		//Agrego un ejemplo de POI Favorito.
		
		POI poi = new LocalComercial();
				
		poi.setNombre("Libreria");
		poi.setDireccionNombre("Av. Hipolito Yrigoyen 4276");				
			
		List<POI> aux2 = new ArrayList<POI>();	
		aux2.add(poi);
			
		usuarioNuevo.setFavoritos(aux2);
		
		this.agregarUsuario(usuarioNuevo);
		return usuarioNuevo;
	}
*/
	public boolean ingresar(String nombreUsuario, String password){
		Usuario usuarioBuscado = this.buscarUsuario(nombreUsuario);
		return usuarioBuscado.getPassword().contains(password);
	}

	public Usuario buscarUsuario(String nombre){
		for(int i=0; i<this.usuarios.size(); i++){
			if(this.usuarios.get(i).getNombreUsuario().contains(nombre) ){
				return this.usuarios.get(i);
			}
		}
		return null;
	}
	
	public List<POI> favoritosPorUsuario(String user) {
			Usuario usuarioAux = this.buscarUsuario(user);
			return usuarioAux.getFavoritos();
	}
	
	public void actualizarFavoritos(POI poi,Boolean esFavorito,String user){
		Usuario usuario = RepositorioDeUsuarios.getInstance().buscarUsuario(user);
		if (esFavorito) {
			usuario.quitarFavorito(poi);		
		}else{
			usuario.agregarFavorito(poi);
		};
	}
	 
	public List<Usuario> getUsuariosAdministradores(){
		CollectionUtils.filter(this.usuarios, ((usuario)-> usuario.tieneRolAdministrador()));
		return usuarios;
		
	}
	
	public void agregarUsuario(Usuario usuario){
		this.usuarios.add(usuario);
	}
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	

	
}
