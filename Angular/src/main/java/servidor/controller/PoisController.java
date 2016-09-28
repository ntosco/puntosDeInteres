package servidor.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import servidor.controller.util.JsonTransformer;
import ar.utn.dds.POI.POI;

import java.util.ArrayList;
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

      Spark.get("/searchPoi/:listaPalabrasClave", (request,response) -> {
    	  String palabrasClave = request.params(":listaPalabrasClave");
    	  String[] arrayPalabrasClave = palabrasClave.split("!");
    	  List<POI> poisBuscado = buscarPorPalabrasClave(arrayPalabrasClave);
          poisBuscado = eliminarRepetidos(poisBuscado);
          response.type("application/json;charset=utf-8");
          return poisBuscado;
      }, this.jsonTransformer);
      

      
      /*
       *       Spark.get("/searchPoi", (request,response) -> {
    	  List<POI> poisBuscados = Repositorio.getInstance().search("cartuchera");
          response.type("application/json;charset=utf-8");
          return poisBuscados;
      }, this.jsonTransformer);
       *  */
    }
    
    public List<POI> eliminarRepetidos(List<POI> Lista){
  	  List<POI> sinRepetidos = new ArrayList<POI>();
  	  for(int a=0; a < Lista.size(); a++){
  		  if(sinRepetidos.contains(Lista.get(a))){
  			  
  		  }else{
  			sinRepetidos.add(Lista.get(a));
  		  }
  	  }
  	  return sinRepetidos;
    }
    
    public List<POI> buscarPorPalabrasClave(String[] PalabrasClave){
    	List<POI> poisBuscado = new ArrayList<POI>();
        for(int i=0; i < PalabrasClave.length; i++){
      	  poisBuscado.addAll(Repositorio.getInstance().search(PalabrasClave[i]));
       }
        return poisBuscado;
    }
    
    
}

