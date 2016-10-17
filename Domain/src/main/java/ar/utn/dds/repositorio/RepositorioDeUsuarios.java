package ar.utn.dds.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.usuarios.UsuarioConcreto;
import ar.utn.dds.utils.Jornada;

public class RepositorioDeUsuarios{
	//TODO Ver si es conveniente extender de CollectionBaseRepo
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	static RepositorioDeUsuarios Repoinstance;

	public static RepositorioDeUsuarios getInstance(){
		if (Repoinstance == null) {
			Repoinstance = new RepositorioDeUsuarios();
		}	
		return Repoinstance;
	}
	
	private RepositorioDeUsuarios(){
		this.crearUsuario("martin", "samo");
		this.crearUsuario("nico", "tosco");
		this.crearUsuario("maca", "lepera");
		this.crearUsuario("fer", "haspert");
	}

	public void clean(){
		this.usuarios.clear();
		//Repoinstance = null;
	}
	

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
