package ar.utn.dds.repositorio;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public abstract class AbstractRepoMongo {

    Datastore datastore;
	Morphia morphia;

    public AbstractRepoMongo(){
        if(datastore == null){
            morphia =  new Morphia();
            morphia.mapPackage("ar.utn.dds.utils");
            morphia.mapPackage("ar.utn.dds.usuarios");
            datastore = morphia.createDatastore(new MongoClient("localhost", 27017),"local-Pois");
            datastore.ensureIndexes();
        }
        System.out.println("Conectado a MongoDB. Bases: " + datastore.getDB().getCollectionNames().toString());
    }

    public Datastore getDatastore() {
		return datastore;
	}


}
