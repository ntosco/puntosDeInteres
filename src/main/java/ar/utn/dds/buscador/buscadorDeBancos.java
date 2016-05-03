package ar.utn.dds.buscador;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ar.utn.dds.POI.SucursalBanco;

public class buscadorDeBancos {
	public static JSONArray buscarBancos(String nombre) {
		JSONObject obj = new JSONObject();
		obj.put("banco", "Banco de la plaza");
		obj.put("x", "35");
		obj.put("y", "72");
		obj.put("sucursal", "Avellaneda");
		obj.put("gerente", "Pablo Perez");
		obj.put("servicios", "[cobrocheques,depósitos,extracciones]");
		
		JSONObject obj1 = new JSONObject();
		obj1.put("banco", "Banco de la plaza");
		obj1.put("x", "35");
		obj1.put("y", "72");
		obj1.put("sucursal", "Caballito");
		obj1.put("gerente", "Javier Garcia");
		obj1.put("servicios", "[cobrocheques,depósitos,extracciones,transferencias,seguros]");
		
		JSONArray list = new JSONArray();
		list.add(obj);
		list.add(obj1);
	
		return list;
	}
}
