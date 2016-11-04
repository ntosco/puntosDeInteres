package ar.utn.dds.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.repositorio.RepoUsuariosNeo4J;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.usuarios.UsuarioConcreto;

public class TestNeo4JRepoUsuarios {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetInstance() {
		RepoUsuariosNeo4J _instance = RepoUsuariosNeo4J.getInstance();
		assertEquals(_instance.getClass(), RepoUsuariosNeo4J.class);
	}

	@Test
	public void testGetUsuarios() {
		RepoUsuariosNeo4J _instance = RepoUsuariosNeo4J.getInstance();
		assertEquals(_instance.getUsuarios("").size(), 0);
	}

	@Test
	public void testGetNodoUsuarioById() {
		fail("Not yet implemented");
	}
	
	@Test
	public void agregarOactualizarUsuario() {
		RepoUsuariosNeo4J _instance = RepoUsuariosNeo4J.getInstance();
		
		UsuarioConcreto usuario = new UsuarioConcreto();
	    usuario.setNombreUsuario("juan");
		usuario.setPassword("qwerty");
		
		_instance.saveOrUpdateUsuario(usuario);
		assertEquals(_instance.getUsuarios("").size(), 1);
	}
	
}
