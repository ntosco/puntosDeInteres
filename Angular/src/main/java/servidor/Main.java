package servidor;

import com.google.gson.GsonBuilder;
import servidor.controller.PoisController;
import servidor.controller.util.JsonTransformer;
import com.google.gson.Gson;
import spark.Spark;

public class Main {

    public static void main(String[] args) {
        Gson gson2 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        JsonTransformer jsonTransformer = new JsonTransformer(gson2);

        Spark.port(9000);
        Spark.staticFileLocation("/webapp");

        new PoisController(jsonTransformer, gson2).register();
    }
}