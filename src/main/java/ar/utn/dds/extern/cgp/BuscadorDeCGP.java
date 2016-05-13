package ar.utn.dds.extern.cgp;

import java.util.List;

public interface BuscadorDeCGP {
	
	public List<CentroDTO> buscarPOI(String nombre);
}
