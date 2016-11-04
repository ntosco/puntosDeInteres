package ar.utn.dds.repositorio;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

import com.google.common.base.Objects;


import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.usuarios.UsuarioConcreto;

@SuppressWarnings("all")
public class RepoUsuariosNeo4J  extends AbstractRepoNeo4J{
	private static RepoUsuariosNeo4J instance;
	  
	  public static RepoUsuariosNeo4J getInstance() {
		  RepoUsuariosNeo4J _xblockexpression = null;
	    {
	      boolean _equals = Objects.equal(RepoUsuariosNeo4J.instance, null);
	      if (_equals) {
	    	  RepoUsuariosNeo4J _repoUsuarios = new RepoUsuariosNeo4J();
	    	  RepoUsuariosNeo4J.instance = _repoUsuarios;
	      }
	      _xblockexpression = RepoUsuariosNeo4J.instance;
	    }
	    return _xblockexpression;
	  }
	  
	  public List<UsuarioConcreto> getUsuarios(final String valor) {
	    List<UsuarioConcreto> _xblockexpression = null;
	    {
	      GraphDatabaseService _graphDb = this.getGraphDb();
	      final Transaction transaction = _graphDb.beginTx();
	      List<UsuarioConcreto> _xtrycatchfinallyexpression = null;
	      try {
	        Iterator<Node> _nodosUsuarios = this.getNodosUsuarios(valor);
	        final Function1<Node, UsuarioConcreto> _function = (Node node) -> {
	          return UsuarioToNodeConverter.convertToUsuario(node);
	        };
	        Iterator<UsuarioConcreto> _map = IteratorExtensions.<Node, UsuarioConcreto>map(_nodosUsuarios, _function);
	        _xtrycatchfinallyexpression = IteratorExtensions.<UsuarioConcreto>toList(_map);
	      } finally {
	        this.cerrarTransaccion(transaction);
	      }
	      _xblockexpression = _xtrycatchfinallyexpression;
	    }
	    return _xblockexpression;
	  }
	  
	  private Iterator<Node> getNodosUsuarios(final String valor) {
	    return this.basicSearch((("usuario.nombreUsuario =~ \'(?i).*" + valor) + ".*\'"));
	  }
	  
	  public Node getNodoUsuarioById(final int id) {
	    Iterator<Node> _basicSearch = this.basicSearch(("ID(usuario) = " + id));
	    return IteratorExtensions.<Node>head(_basicSearch);
	  }
	  
	  private Iterator<Node> basicSearch(final String where) {
	    GraphDatabaseService _graphDb = this.getGraphDb();
	    final Result result = _graphDb.execute((("match (usuario:Person) where " + where) + " return usuario"));
	    final Iterator<Node> peli_column = result.<Node>columnAs("usuario");
	    return peli_column;
	  }
	  
	  private void actualizarUsuario(final Usuario usuario, final Node nodeUsuario) {
		    final Procedure1<Node> _function = (Node it) -> {
		      String _nombreUsuario = usuario.getNombreUsuario();
		      it.setProperty("nombreUsuario", _nombreUsuario);
		      String _password = usuario.getPassword();
		      it.setProperty("password", _password);
		      Iterable<Relationship> _relationships = it.getRelationships();
		      final Consumer<Relationship> _function_1 = (Relationship it_1) -> {
		        it_1.delete();
		      };
		    };
		    ObjectExtensions.<Node>operator_doubleArrow(nodeUsuario, _function);
		  }
	  
	  public void saveOrUpdateUsuario(final Usuario usuario) {
		    GraphDatabaseService _graphDb = this.getGraphDb();
		    final Transaction transaction = _graphDb.beginTx();
		    try {
		      Node nodoUsuario = null;
		      int _id = usuario.getId();
		      boolean _equals = Objects.equal(_id, null);
		      if (_equals) {
		        GraphDatabaseService _graphDb_1 = this.getGraphDb();
		        Node _createNode = _graphDb_1.createNode();
		        nodoUsuario = _createNode;
		        Label _labelUsuario = this.labelUsuario();
		        nodoUsuario.addLabel(_labelUsuario);
		      } else {
		        int _id_1 = usuario.getId();
		        Node _nodoUsuario = this.getNodoUsuarioById(_id_1);
		        nodoUsuario = _nodoUsuario;
		      }
		      this.actualizarUsuario(usuario, nodoUsuario);
		      transaction.success();
		      long _id_2 = nodoUsuario.getId();
		      usuario.setId((int)_id_2);
		    } finally {
		      this.cerrarTransaccion(transaction);
		    }
		  }
	  
	  private Label labelUsuario() {
		    return Label.label("Usuario");
		  }
	  
	  
	  
}
