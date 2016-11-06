package ar.utn.dds.repositorio;

import java.util.Iterator;
import java.util.List;

import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

import ar.utn.dds.POI.POI;
import ar.utn.dds.usuarios.UsuarioConcreto;

public class RepoPoisNeo4j extends AbstractRepoNeo4J {
	
	public static RepoPoisNeo4j instance;
	
	public static RepoPoisNeo4j getInstace(){
		if(RepoPoisNeo4j.instance == null){
			RepoPoisNeo4j.instance = new RepoPoisNeo4j();
		}
		return RepoPoisNeo4j.instance;
	}
	
	public List<POI> getPois(final String valor) {
	    List<POI> listaPois = null;
	    {
	      GraphDatabaseService graphService = this.getGraphDb();
	      final Transaction transaction = graphService.beginTx();
	      List<POI> tryListaPois = null;
	      try {
	        Iterator<Node> nodosUsuarios = this.getNodosPois(valor);
	        final Function1<Node, POI> function = (Node node) -> {
	          return POIToNodeConverter.convertToPOI(node);
	        };
	        Iterator<POI> map = IteratorExtensions.<Node, POI>map(nodosUsuarios, function);
	        tryListaPois = IteratorExtensions.<POI>toList(map);
	      } finally {
	        this.cerrarTransaccion(transaction);
	      }
	      listaPois = tryListaPois;
	    }
	    return listaPois;
	}
	
	private Iterator<Node> getNodosPois(final String valor) {
		GraphDatabaseService db = this.getGraphDb();
		String query = "MATCH (p)";
		Result result = db.execute(query);
		Iterator<Node> poi_column = result.<Node>columnAs("usuario");
		return poi_column;
	  //  return this.basicSearch((("Poi.nombre =~ \'(?i).*" + valor) + ".*\'"));
	  }
	
/*	private Iterator<Node> basicSearch(String valor) {
	    GraphDatabaseService graphDb = this.getGraphDb();
	    String query = "MATCH (n:Label) where n.username =~ ".*foo regex.*" RETURN n;"
	    Result  result = graphDb.execute((("match (poi:Poi) where " + valor) + " return usuario"));
	    Result result1 = graphDb.execute((("match (p:Poi)-[a]->(x)" + valor) + " return usuario"));
	    Iterator<Node> poi_column = result.<Node>columnAs("usuario");
	    return poi_column;
	  } */

}
