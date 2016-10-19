package ar.utn.dds.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.utn.dds.repositorio.RepositorioDeUsuarios;
import ar.utn.dds.usuarios.UsuarioConcreto;

public class TestBusquedaDeUsuario {

	@Test
	public void testCrearUsuario() {
		RepositorioDeUsuarios.getInstance().clean();
		RepositorioDeUsuarios.getInstance().setearUsuario(new UsuarioConcreto(), "user", "pass");
		assertTrue(RepositorioDeUsuarios.getInstance().getUsuarios().size() == 1);
	}
	
	@Test
	public void cleanRepoUsuarios() {
		RepositorioDeUsuarios.getInstance().clean();
		assertTrue(RepositorioDeUsuarios.getInstance().getUsuarios().size() == 0);
	}
	
	@Test
	public void testIngresar() {
		RepositorioDeUsuarios.getInstance().setearUsuario(new UsuarioConcreto(), "martin", "samo");
		RepositorioDeUsuarios.getInstance().setearUsuario(new UsuarioConcreto(), "nico", "tosco");
		
		assertTrue(RepositorioDeUsuarios.getInstance().ingresar("martin", "samo"));
	}

	@Test
	public void testIngresarMal() {
		RepositorioDeUsuarios.getInstance().setearUsuario(new UsuarioConcreto(), "martin", "samo");
		RepositorioDeUsuarios.getInstance().setearUsuario(new UsuarioConcreto(), "nico", "tosco");

		assertTrue(RepositorioDeUsuarios.getInstance().ingresar("martin", "passwordIncorrecta") == false);
	}

	@Test
	public void testBuscarUsuario() {
		RepositorioDeUsuarios.getInstance().setearUsuario(new UsuarioConcreto(), "martin", "samo");
		RepositorioDeUsuarios.getInstance().setearUsuario(new UsuarioConcreto(), "nico", "tosco");

		assertTrue(RepositorioDeUsuarios.getInstance().buscarUsuario("nico").getNombreUsuario() == "nico");
		assertTrue(RepositorioDeUsuarios.getInstance().buscarUsuario("nico").getPassword() == "tosco");
		assertTrue(RepositorioDeUsuarios.getInstance().buscarUsuario("nico").getFavoritos().size() == 1);
	}

	@Test
	public void testBuscarUsuarioQueNoExiste() {
		RepositorioDeUsuarios.getInstance().setearUsuario(new UsuarioConcreto(), "martin", "samo");
		RepositorioDeUsuarios.getInstance().setearUsuario(new UsuarioConcreto(), "nico", "tosco");
		
		assertTrue(RepositorioDeUsuarios.getInstance().buscarUsuario("dios") == null);

	}

}
