package ar.utn.dds.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.utn.dds.repositorio.RepositorioDeUsuarios;

public class TestBusquedaDeUsuario {

	@Test
	public void testCrearUsuario() {
		RepositorioDeUsuarios.getInstance().clean();
		RepositorioDeUsuarios.getInstance().crearUsuario("user", "pass");
		assertTrue(RepositorioDeUsuarios.getInstance().getUsuarios().size() == 1);
	}
	
	@Test
	public void cleanRepoUsuarios() {
		RepositorioDeUsuarios.getInstance().clean();
		assertTrue(RepositorioDeUsuarios.getInstance().getUsuarios().size() == 0);
	}
	
	@Test
	public void testIngresar() {
		RepositorioDeUsuarios.getInstance().crearUsuario("martin", "samo");
		RepositorioDeUsuarios.getInstance().crearUsuario("nico", "tosco");
		
		assertTrue(RepositorioDeUsuarios.getInstance().ingresar("martin", "samo"));
	}

	@Test
	public void testIngresarMal() {
		RepositorioDeUsuarios.getInstance().crearUsuario("martin", "samo");
		RepositorioDeUsuarios.getInstance().crearUsuario("nico", "tosco");

		assertTrue(RepositorioDeUsuarios.getInstance().ingresar("martin", "passwordIncorrecta") == false);
	}

	@Test
	public void testBuscarUsuario() {
		RepositorioDeUsuarios.getInstance().crearUsuario("martin", "samo");
		RepositorioDeUsuarios.getInstance().crearUsuario("nico", "tosco");

		assertTrue(RepositorioDeUsuarios.getInstance().buscarUsuario("nico").getNombreUsuario() == "nico");
		assertTrue(RepositorioDeUsuarios.getInstance().buscarUsuario("nico").getPassword() == "tosco");
		assertTrue(RepositorioDeUsuarios.getInstance().buscarUsuario("nico").getFavoritos().size() == 1);
	}

	@Test
	public void testBuscarUsuarioQueNoExiste() {
		RepositorioDeUsuarios.getInstance().crearUsuario("martin", "samo");
		RepositorioDeUsuarios.getInstance().crearUsuario("nico", "tosco");

		assertTrue(RepositorioDeUsuarios.getInstance().buscarUsuario("dios") == null);

	}

}
