package ar.utn.dds.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.ServicioExterno.CentroDTO;
import ar.utn.dds.buscador.BuscadorDeCGP;
import ar.utn.dds.buscador.buscadorDeBancos;

public class BusquedaDePuntos {
	
	public static BuscadorDeCGP buscador;
	public static buscadorDeBancos buscadorBanco;

	public static List<CentroGestionParticipacion> buscarCGPEnRepoExterno(String nombre){
		
		List<CentroDTO> listaDeCentroDTO = buscador.buscarPOI(nombre);
		
		List<CentroGestionParticipacion> listaCGP = new ArrayList<CentroGestionParticipacion>();
		
		listaDeCentroDTO.forEach(dto -> listaCGP.add(Conversor.getInstance().convertirDTOACGP(dto)));
		
		return listaCGP;
		
	}
	
	public static List<SucursalBanco> buscarBancoEnRepoExterno(String nombre){
		
		JSONArray bancosJson = buscadorBanco.buscarPOI(nombre);
		List<SucursalBanco> listaBancos = new ArrayList<SucursalBanco>();
		 for(int x=0; x<bancosJson.size(); x++) 
		  {      
			 listaBancos.add(Conversor.getInstance().jsonAbanco((JSONObject) bancosJson.get(x)));
		  }   
		 return listaBancos;
	}
	
	public static void setBuscadorDeCGP(BuscadorDeCGP buscadorDeCGP){
		buscador = buscadorDeCGP;
	}
	
	
	public static void setBuscadorDeBancos(buscadorDeBancos buscadorDeBanco){
		buscadorBanco = buscadorDeBanco;
	}
	
}
