package ar.utn.dds.buscador;

import java.util.List;

import ar.utn.dds.ServicioExterno.CentroDTO;

public interface BuscadorDeCGP {
	
	public List<CentroDTO> buscarPOI(String nombre);
}
