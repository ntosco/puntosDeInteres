package ar.utn.dds.juegoDeDatos;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ar.utn.dds.extern.banco.buscadorDeBancos;

public class StubBuscadorBanco implements buscadorDeBancos {


	private static StubBuscadorBanco stubBuscador;

	public static StubBuscadorBanco getInstance() {
		if (stubBuscador == null) {

			stubBuscador = new StubBuscadorBanco();
		}
		return stubBuscador;
	}

	
	@Override
	public JSONArray buscarPOI(String nombre) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		obj.put("banco", "Banco de la plaza");
		obj.put("x", "35");
		obj.put("y", "72");
		obj.put("sucursal", "Avellaneda");
		obj.put("gerente", "Pablo Perez");
		obj.put("servicios", "[cobrocheques,depositos,extracciones]");
		
		JSONObject obj1 = new JSONObject();
		obj1.put("banco", "Banco de la plaza");
		obj1.put("x", "35");
		obj1.put("y", "72");
		obj1.put("sucursal", "Caballito");
		obj1.put("gerente", "Javier Garcia");
		obj1.put("servicios", "[cobrocheques,depositos,extracciones,transferencias,seguros]");
		
		JSONArray list = new JSONArray();
		list.add(obj);
		list.add(obj1);
		return list;
	}


}
