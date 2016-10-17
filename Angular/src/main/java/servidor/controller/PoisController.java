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
import java.util.Properties;
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
      
      Spark.put("/pois/:id", (request, response) -> {
			//Tarea tarea = this.gson.fromJson(request.body(), Tarea.class);
	  
	  	int idDelPoi = Integer.parseInt(request.params(":id"));
	  	
	  	gson = new Gson();
	  	String json = request.body().toString();
	  	Properties properties = gson.fromJson(json, Properties.class);
	  	
	  	boolean esFavorito;
	  	String nombreUsuario = properties.getProperty("usuario");

	  	
	  	if (properties.getProperty("favorito").equals("true")){
	  		esFavorito =  true;
	  	}else{
	  		esFavorito = false;
	  	}	
	  	POI poiBuscado = Repositorio.getInstance().searchById(idDelPoi);
	  	RepositorioDeUsuarios.getInstance().actualizarFavoritos(poiBuscado,esFavorito,nombreUsuario);
			
		response.type("application/json;charset=utf-8");
			
		return "null";
		//return "{\"status\": \"OK\"}";
		},this.jsonTransformer);
      
      Spark.put("/comentario/:idPoi", (request, response) -> {
    	  int idDelPoi = Integer.parseInt(request.params(":idPoi"));
    	  gson = new Gson();
    	  String json = request.body().toString();
    	  Properties properties = gson.fromJson(json, Properties.class);
    	  String comentario = properties.getProperty("comentario");
    	  int valoracion = Integer.parseInt(properties.getProperty("valoracion"));
    	  String unUsuario = properties.getProperty("user");
    	  
    	  POI poiBuscado = Repositorio.getInstance().searchById(idDelPoi);
    	   Review unaReview = new Review(comentario, unUsuario, valoracion);
    	  	if(poiBuscado.elUsuarioYaOpino(unUsuario)){
    	  		Spark.halt(400, "El usuario Ya opinio");
    	  	} 
    	  	poiBuscado.agregarReview(unaReview);    	  		
    	  	return poiBuscado;
      }, this.jsonTransformer);      
    }
    
    
    public List<POI> buscarPorPalabrasClave(String[] PalabrasClave){
    	List<POI> poisBuscado = new ArrayList<POI>();
        for(int i=0; i < PalabrasClave.length; i++){
      	  poisBuscado.addAll(Repositorio.getInstance().search(PalabrasClave[i]));
       }
        return poisBuscado;
    }
    
    
}

