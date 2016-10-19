package ar.utn.dds.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.PointJava;
import ar.utn.dds.POI.SucursalBanco; //SACAR
import ar.utn.dds.extern.banco.buscadorDeBancos;
import ar.utn.dds.servicios.Servicio;

public class AdapterBancos implements OrigenDeDatos{
	
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
				 listaBancos.add(jsonAbanco((JSONObject) bancosJson.get(x)));
			  }   
			 return listaBancos;
		}
	}

	// ********************************************************
	// ** Getters and Setters
	// ********************************************************
	
	public POI jsonAbanco(JSONObject obj) {
		SucursalBanco banco = new SucursalBanco();
		
		banco.setBarrio(obj.get("sucursal").toString()); 
		banco.setNombre(obj.get("banco").toString());
		
		PointJava ubcicacionActual = new PointJava().setXY(Integer.parseInt(obj.get("x").toString()), Integer.parseInt(obj.get("y").toString()));
		banco.setUbicacionActual(ubcicacionActual);
		
		String serviciosJson = obj.get("servicios").toString();
		String[] arrayServicios = serviciosJson.split(",");
		List<Servicio> listaServicios = new ArrayList<Servicio>();
		for(int i=0; i<arrayServicios.length; i++){
			Servicio servicioBanco = new Servicio(arrayServicios[i], null);
			listaServicios.add(servicioBanco);
        }
		banco.setListaServicios(listaServicios);
		return banco;
	}

	public buscadorDeBancos getServicioBanco() {
		return servicioBanco;
	}

	public void setServicioBanco(buscadorDeBancos servicioBanco) {
		this.servicioBanco = servicioBanco;
	}
	
}
	
	
	

