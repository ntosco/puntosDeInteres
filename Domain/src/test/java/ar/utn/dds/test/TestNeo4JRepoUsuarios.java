package ar.utn.dds.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import com.google.common.base.Objects;

import ar.utn.dds.repositorio.RepoUsuariosNeo4J;
import ar.utn.dds.repositorio.UsuarioToNodeConverter;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.usuarios.UsuarioConcreto;

public class TestNeo4JRepoUsuarios {
	RepoUsuariosNeo4J _instance;
	@Before
	public void setUp() throws Exception {
		_instance  = RepoUsuariosNeo4J.getInstance();
		UsuarioConcreto usuario = new UsuarioConcreto();
	    usuario.setNombreUsuario("nico");
		usuario.setPassword("tosco");
		_instance.saveOrUpdateUsuario(usuario);
		
		UsuarioConcreto usuarioJuan = new UsuarioConcreto();
		usuarioJuan.setNombreUsuario("juan");
		usuarioJuan.setPassword("qwrty");
		_instance.saveOrUpdateUsuario(usuarioJuan);
		
	}
	
	@Test
	public void converNodoToUsuario() {		
		Node nodoUsuario = _instance.getNodoUsuarioById(1);
		UsuarioConcreto nodoConvertidoToUsuario = UsuarioToNodeConverter.convertToUsuario(nodoUsuario);

		assertEquals(nodoConvertidoToUsuario.getNombreUsuario(), "juan");
		assertEquals(nodoConvertidoToUsuario.getPassword(), "qwrty");
	}
	@Test
	public void testGetInstance() {
		assertEquals(_instance.getClass(), RepoUsuariosNeo4J.class);
	}

	@Test
	public void testGetUsuarios() {

		//crea un usuario por primera vez en base de datos
		
		assertEquals(_instance.getUsuarios("juan").size(),  1);
	}

	@Test
	public void testCrearNodo(){

	    GraphDatabaseService _graphDb = _instance.getGraphDb();
	    final Transaction transaction = _graphDb.beginTx();
	      Node nodoUsuario = null;
	    try {
	
	        GraphDatabaseService _graphDb_1 = _instance.getGraphDb();
	        Node _createNode = _graphDb_1.createNode();
	        nodoUsuario = _createNode;
	        Label _labelUsuario = _instance.labelUsuario();
	        nodoUsuario.addLabel(_labelUsuario);
	    } finally {
	    	assertTrue(nodoUsuario.hasLabel(Label.label("Usuario")));
	    	_instance.cerrarTransaccion(transaction);
	    }
	}
	
	@Test
	public void testGetNodoUsuarioById() {
		Node nodoUsuario = _instance.getNodoUsuarioById(1);
		assertEquals(nodoUsuario.getId(), 1);
	}
	
	@Test
	public void agregarOactualizarUsuario() {
		
		UsuarioConcreto usuario = new UsuarioConcreto();
	    usuario.setNombreUsuario("martin");
		usuario.setPassword("samo");
		
		_instance.saveOrUpdateUsuario(usuario);
		assertEquals(_instance.getUsuarios("martin").size(), 1);
	}
	

}
