package servidor.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import servidor.controller.util.JsonTransformer;
import ar.utn.dds.POI.POI;
import java.util.List;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.repositorio.RepositorioDeUsuarios;
import ar.utn.dds.usuarios.Usuario;
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
       

      Spark.get("/logUser", (request,response) -> {
    	  String nombreUsuario = "martin";
    	  String password = "samo";
          Usuario usuarioQueIngresa;

    	  boolean ingresoExitoso = RepositorioDeUsuarios.getInstance().ingresar(nombreUsuario, password);
          if(ingresoExitoso){//se loggea con exito
        	  usuarioQueIngresa = RepositorioDeUsuarios.getInstance().buscarUsuario(nombreUsuario);
          }else{
        	  usuarioQueIngresa = null;
          }
          response.type("application/json;charset=utf-8");
          return usuarioQueIngresa;

      }, this.jsonTransformer);

        
    }
}
