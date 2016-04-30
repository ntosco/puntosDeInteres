package ar.utn.dds.utils;

import java.util.List;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.ServicioExterno.CentroDTO;
import ar.utn.dds.buscador.BuscadorDeCGP;

public class BusquedaDePuntos {

	public void buscarCGPEnRepoExterno(String nombre){
		List<CentroDTO> listaDeCentroDTO = BuscadorDeCGP.buscarCGP(nombre);
		listaDeCentroDTO.forEach(dto -> Conversor.convertirDTOACGP(dto));
	}
	
}
