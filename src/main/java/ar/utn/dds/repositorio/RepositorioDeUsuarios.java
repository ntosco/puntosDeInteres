package ar.utn.dds.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.uqbar.commons.model.CollectionBasedRepo;

import ar.utn.dds.usuarios.Usuario;

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
	
	public List<Usuario> getUsuariosAdministradores(){
		CollectionUtils.filter(this.usuarios, ((usuario)-> usuario.tieneRolAdministrador()));
		return usuarios;
		
	}
	
	public void agregarUsuario(Usuario usuario){
		this.usuarios.add(usuario);
	}
	

	
}
