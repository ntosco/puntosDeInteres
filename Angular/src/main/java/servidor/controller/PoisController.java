package servidor.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import servidor.controller.util.JsonTransformer;
import ar.utn.dds.POI.POI;
import java.util.List;
import ar.utn.dds.repositorio.Repositorio;
import spark.Spark;

public class PoisController {

    private JsonTransformer jsonTransformer;
    private Gson gson;

    public PoisController(JsonTransformer jsonTransformer, Gson gson) {
        this.jsonTransformer = jsonTransformer;
        this.gson = gson;
    }

    public void register(){
//        Gson gson2 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Spark.get("/pois", (request,response) -> {
            List<POI> todosLosPois = Repositorio.getInstance().allInstances();
            response.type("application/json;charset=utf-8");
            return todosLosPois;
        }, this.jsonTransformer);
    }
}
