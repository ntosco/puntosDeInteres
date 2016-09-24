package ar.utn.dds.angular;

import ar.utn.dds.angular.controller.PoisController;
import ar.utn.dds.angular.controller.util.JsonTransformer;
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