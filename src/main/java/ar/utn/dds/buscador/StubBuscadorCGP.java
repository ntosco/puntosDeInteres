package ar.utn.dds.buscador;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.ServicioExterno.CentroDTO;

public class StubBuscadorCGP implements BuscadorDeCGP {

	private static StubBuscadorCGP stubBuscador;

	public static StubBuscadorCGP getInstance() {
		if (stubBuscador == null) {

			stubBuscador = new StubBuscadorCGP();
		}
		return stubBuscador;
	}
	
	public static List<CentroDTO> buscarCGP(String nombre) {

		List<CentroDTO> centros = new ArrayList<CentroDTO>();
		return centros;
	}

}
