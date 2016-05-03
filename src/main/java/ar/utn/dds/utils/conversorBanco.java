package ar.utn.dds.utils;


import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.servicios.Servicio;

public class conversorBanco extends Conversor{

	public SucursalBanco jsonAbanco(JSONObject obj) {
		SucursalBanco banco = new SucursalBanco();
		
		banco.setBarrio(obj.get("sucursal").toString()); 
		banco.setDireccionNombre(obj.get("banco").toString());
		
		Point ubcicacionActual = new Point(Integer.parseInt(obj.get("x").toString()), Integer.parseInt(obj.get("y").toString()));
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
	
	public static SucursalBanco jsonAsucursalBanco(JSONObject obj) {
		SucursalBanco banco = new SucursalBanco();
		return banco;
	}
}