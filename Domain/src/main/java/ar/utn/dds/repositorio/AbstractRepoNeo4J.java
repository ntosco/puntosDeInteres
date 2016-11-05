package ar.utn.dds.repositorio;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import com.google.common.base.Objects;


@Accessors
@SuppressWarnings("all")
public class AbstractRepoNeo4J {
  private GraphDatabaseService graphDb;
  
  protected AbstractRepoNeo4J() {
    GraphDatabaseProvider _instance = GraphDatabaseProvider.instance();
    GraphDatabaseService _graphDb = _instance.getGraphDb();
    this.graphDb = _graphDb;
  }
  
  public void cerrarTransaccion(final Transaction transaction) {
    boolean _notEquals = (!Objects.equal(transaction, null));
    if (_notEquals) {
      transaction.close();
    }
  }
  
  @Pure
  public GraphDatabaseService getGraphDb() {
    return this.graphDb;
  }
  
  public void setGraphDb(final GraphDatabaseService graphDb) {
    this.graphDb = graphDb;
  }
}

