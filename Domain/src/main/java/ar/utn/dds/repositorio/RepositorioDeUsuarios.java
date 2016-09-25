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
		if(usuarioBuscado.getPassword() == password){
			return true;
		}
		return false;
	}
	
	public Usuario buscarUsuario(String nombreUsuario){
        for(int i=0; i<this.usuarios.size(); i++){
        	if(this.usuarios.get(i).getNombreUsuario() == nombreUsuario){
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
