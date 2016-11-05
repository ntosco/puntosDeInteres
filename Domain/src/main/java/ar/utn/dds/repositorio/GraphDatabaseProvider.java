package ar.utn.dds.repositorio;

import java.io.File;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.google.common.base.Objects;


@Accessors
@SuppressWarnings("all")
public class GraphDatabaseProvider {
  private static String PATH = "C:/Users/Samo/Documents/Neo4j/default.graphdb/data/dbms";
  
  private static GraphDatabaseProvider instance;
  
  private GraphDatabaseService graphDb;
  
  private GraphDatabaseProvider() {
    final GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
    File _file = new File((GraphDatabaseProvider.PATH + "/Usuarios.db"));
    GraphDatabaseService _newEmbeddedDatabase = dbFactory.newEmbeddedDatabase(_file);
    this.graphDb = _newEmbeddedDatabase;
  }
  
  public static GraphDatabaseProvider instance() {
    GraphDatabaseProvider _xblockexpression = null;
    {
      boolean _equals = Objects.equal(GraphDatabaseProvider.instance, null);
      if (_equals) {
        GraphDatabaseProvider _graphDatabaseProvider = new GraphDatabaseProvider();
        GraphDatabaseProvider.instance = _graphDatabaseProvider;
      }
      _xblockexpression = GraphDatabaseProvider.instance;
    }
    return _xblockexpression;
  }
  
  @Pure
  public GraphDatabaseService getGraphDb() {
    return this.graphDb;
  }
  
  public void setGraphDb(final GraphDatabaseService graphDb) {
    this.graphDb = graphDb;
  }
}
