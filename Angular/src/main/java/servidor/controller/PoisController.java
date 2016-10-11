package servidor.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import javax.servlet.http.HttpServletResponse;



import servidor.controller.util.JsonTransformer;
import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.Review;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.repositorio.RepositorioDeUsuarios;
import spark.HaltException;
import spark.Spark;import spark.http.matching.Halt;


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
          poiBuscado.setValoracionPromedio(poiBuscado.calcularValoracionPromedio());
          return poiBuscado;
      }, this.jsonTransformer);

      Spark.get("/searchPoi/:palabraclave", (request,response) -> {
    	  String palabrasClave = request.params(":palabraclave");
    	  String[] arrayPalabrasClave = palabrasClave.split(",");
    	  List<POI> poisBuscado = buscarPorPalabrasClave(arrayPalabrasClave);
    	  Set<POI> setListPoisBuscado = new HashSet<POI>(poisBuscado);
          response.type("application/json;charset=utf-8");
          return setListPoisBuscado;
      }, this.jsonTransformer);
      
      Spark.put("/comentario/:idPoi/:comentario/:valoracion/:user", (request, response) -> {
    	  int idDelPoi = Integer.parseInt(request.params(":idPoi"));
    	  String comentario = request.params(":comentario");
    	  int valoracion = Integer.parseInt(request.params(":valoracion"));
    	  String unUsuario = request.params(":user");
    	  POI poiBuscado = Repositorio.getInstance().searchById(idDelPoi);
    	  Review unaReview = new Review(comentario, unUsuario, valoracion);
    	  	if(poiBuscado.elUsuarioYaOpino(unUsuario)){
    	  		Spark.halt(400, "El usuario Ya opinio");
    	  	}
    	  	poiBuscado.agregarReview(unaReview);    	  		
    	  	return poiBuscado;
      }, this.jsonTransformer);      

      
      /*
       *       Spark.get("/searchPoi", (request,response) -> {
    	  List<POI> poisBuscados = Repositorio.getInstance().search("cartuchera");
          response.type("application/json;charset=utf-8");
          return poisBuscados;
      }, this.jsonTransformer);
       *  */
    }
    
    
    public List<POI> buscarPorPalabrasClave(String[] PalabrasClave){
    	List<POI> poisBuscado = new ArrayList<POI>();
        for(int i=0; i < PalabrasClave.length; i++){
      	  poisBuscado.addAll(Repositorio.getInstance().search(PalabrasClave[i]));
       }
        return poisBuscado;
    }
    
    
}

