package ar.utn.dds.juegoDeDatos;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ar.utn.dds.extern.servicioREST.ServicioREST;

public class StubServicioREST implements ServicioREST {


	private static StubServicioREST stubServicioRest;

	public static StubServicioREST getInstance() {
		if (stubServicioRest == null) {

			stubServicioRest = new StubServicioREST();
		}
		return stubServicioRest;
	}


	@Override
	public JSONObject buscarPOIS() {
		
		JSONObject obj = new JSONObject();
		obj.put("valorDeBusqueda", "15");
		obj.put("fecha", "01/01/2015");

		return obj;
	}
}