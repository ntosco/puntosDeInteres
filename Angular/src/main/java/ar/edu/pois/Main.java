package ar.edu.pois;

import ar.edu.pois.controller.util.JsonTransformer;
import ar.edu.pois.controller.PoisController;
import com.google.gson.Gson;
import spark.Spark;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        JsonTransformer jsonTransformer = new JsonTransformer(gson);

        Spark.port(9000);
        Spark.staticFileLocation("/webapp");

        new PoisController(jsonTransformer).register();
    }
}

