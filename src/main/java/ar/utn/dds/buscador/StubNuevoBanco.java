package ar.utn.dds.buscador;

import org.json.simple.JSONArray;

public class StubNuevoBanco implements buscadorDeBancos{
	
	private JSONArray listaAdevolver = new JSONArray();

	
	public JSONArray buscarPOI(String nombre) {
		return listaAdevolver;
	}
	
	public void setListaAdevolver (JSONArray listaEntrante){
		this.listaAdevolver = listaEntrante;
	}

}
