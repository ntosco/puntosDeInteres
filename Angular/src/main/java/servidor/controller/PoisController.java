package servidor.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import servidor.controller.util.JsonTransformer;
import ar.utn.dds.POI.POI;
import java.util.List;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.repositorio.RepositorioDeUsuarios;
import spark.Spark;

public class PoisController {

    private JsonTransformer jsonTransformer;
    private Gson gson;

    public PoisController(JsonTransformer jsonTransformer, Gson gson) {
        this.jsonTransformer = jsonTransformer;
        this.gson = gson;
    }

    public void register(){
        Spark.get("/pois", (request,response) -> {
            List<POI> todosLosPois = Repositorio.getInstance().allInstances();
            response.type("application/json;charset=utf-8");
            return todosLosPois;
        }, this.jsonTransformer);
        
        Spark.get("/favoritos/:user", (request,response) -> {
        	List<POI> favoritos = RepositorioDeUsuarios.getInstance().favoritosPorUsuario(request.params(":user"));
            response.type("application/json;charset=utf-8");
            return favoritos;
        }, this.jsonTransformer);       

      Spark.get("/logUser/:user/:pass", (request,response) -> {
    	  boolean ingresoExitoso = RepositorioDeUsuarios.getInstance().ingresar(request.params(":user"), request.params(":pass"));
          response.type("application/json;charset=utf-8");
          return ingresoExitoso;
      }, this.jsonTransformer);

      Spark.get("/buscarUnPoi/:idPoi", (request,response) -> {
    	  int idDelPoi = Integer.parseInt(request.params(":idPoi"));
    	  POI poiBuscado = Repositorio.getInstance().searchById(idDelPoi);
          response.type("application/json;charset=utf-8");
          return poiBuscado;
      }, this.jsonTransformer);
    }
}
