package ar.utn.dds.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ar.utn.dds.POI.POI;

import ar.utn.dds.POI.SucursalBanco; //SACAR

import ar.utn.dds.buscador.buscadorDeBancos;

public class AdapterBancos extends OrigenDeDatos{
	
	private buscadorDeBancos servicioBanco;
	
	@Override
	public List<POI> buscarPOI(String nombre) {
		
		JSONArray bancosJson = servicioBanco.buscarPOI(nombre);
		
		if(bancosJson.isEmpty()){
			return new ArrayList<POI>();
		}else{
			
			List<POI> listaBancos = new ArrayList<POI>(); //Cambio a POI para que compile
														  // Podria castearse para SucursalBanco
			 for(int x=0; x<bancosJson.size(); x++) 
			  {      
				 listaBancos.add(Conversor.getInstance().jsonAbanco((JSONObject) bancosJson.get(x)));
				 
				 //Eliminar la clase 'Conversor' y pasar los metodos necesarios para la conversion aqui
			  }   
			 return listaBancos;
		}
	}

	// ********************************************************
	// ** Getters and Setters
	// ********************************************************
	
	public buscadorDeBancos getServicioBanco() {
		return servicioBanco;
	}

	public void setServicioBanco(buscadorDeBancos servicioBanco) {
		this.servicioBanco = servicioBanco;
	}
	
}
	
	
	

