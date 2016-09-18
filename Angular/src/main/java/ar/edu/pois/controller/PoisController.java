package ar.edu.pois.controller;

import ar.edu.pois.controller.util.JsonTransformer;
import ar.utn.dds.POI.POI;
import java.util.List;

import ar.utn.dds.repositorio.Repositorio;
import spark.Spark;

public class PoisController {

    private JsonTransformer jsonTransformer;

    public PoisController(JsonTransformer jsonTransformer) {
        this.jsonTransformer = jsonTransformer;
    }

    public void register(){
        Spark.get("/pois", (request,response) -> {
           List<POI> puntosDeInteres = Repositorio.getInstance().getPuntosDeInteres();
            response.type("application/json;charset=utf-8");
            return puntosDeInteres;
        }, this.jsonTransformer);
    }

}
