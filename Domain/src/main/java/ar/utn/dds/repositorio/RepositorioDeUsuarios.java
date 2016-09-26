package ar.utn.dds.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.uqbar.geodds.Point;

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
		Repoinstance = null;
	}
	

	public Usuario crearUsuario(String nombreUsuario, String password){
		Usuario usuarioNuevo = new UsuarioConcreto();
		usuarioNuevo.setNombreUsuario(nombreUsuario);
		usuarioNuevo.setPassword(password);
		usuarioNuevo.setFavoritos(new ArrayList<POI>());
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
