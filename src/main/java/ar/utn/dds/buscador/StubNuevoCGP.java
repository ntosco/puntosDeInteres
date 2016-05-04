package ar.utn.dds.buscador;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.ServicioExterno.CentroDTO;

public class StubNuevoCGP implements BuscadorDeCGP{
	
	private List<CentroDTO> listaAdevolver = new ArrayList<CentroDTO>();
	

	@Override
	public List<CentroDTO> buscarPOI(String nombre) {
		return listaAdevolver;
	}
	
	public void setListaAdevolver(List<CentroDTO> listaEntrante){
		this.listaAdevolver = listaEntrante;
	}

}
