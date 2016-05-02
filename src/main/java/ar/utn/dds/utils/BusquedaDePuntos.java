package ar.utn.dds.utils;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.ServicioExterno.CentroDTO;
import ar.utn.dds.buscador.BuscadorDeCGP;

public class BusquedaDePuntos {
	
	public static BuscadorDeCGP buscador;

	public static List<CentroGestionParticipacion> buscarCGPEnRepoExterno(String nombre){
		List<CentroDTO> listaDeCentroDTO = buscador.buscarCGP(nombre);
		List<CentroGestionParticipacion> listaCGP = new ArrayList<CentroGestionParticipacion>();
		listaDeCentroDTO.forEach(dto -> listaCGP.add(Conversor.getInstance().convertirDTOACGP(dto)));
		return listaCGP;
		
	}
	
	public static void setBuscadorDeCGP(BuscadorDeCGP buscadorDeCGP){
		buscador = buscadorDeCGP;
	}
	
}
