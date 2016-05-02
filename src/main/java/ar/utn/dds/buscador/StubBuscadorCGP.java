package ar.utn.dds.buscador;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.ServicioExterno.CentroDTO;
import ar.utn.dds.ServicioExterno.RangoServicioDTO;
import ar.utn.dds.ServicioExterno.ServicioDTO;

public class StubBuscadorCGP implements BuscadorDeCGP {

	private static StubBuscadorCGP stubBuscador;

	public static StubBuscadorCGP getInstance() {
		if (stubBuscador == null) {

			stubBuscador = new StubBuscadorCGP();
		}
		return stubBuscador;
	}
	
	public List<CentroDTO> buscarCGP(String nombre) {

		List<CentroDTO> centrosDTO = new ArrayList<CentroDTO>();
		List<ServicioDTO> serviciosDTO = new ArrayList<ServicioDTO>();
		List<RangoServicioDTO> rangosDTO = new ArrayList<RangoServicioDTO>();
		RangoServicioDTO unRango = new RangoServicioDTO(1,9,0,18,0);
		rangosDTO.add(unRango);
		ServicioDTO unServicio = new ServicioDTO("rentas", rangosDTO);
		serviciosDTO.add(unServicio);
		CentroDTO unCentro = new CentroDTO(1, "Recoleta", "Juan Perez", "Jujuy 998", "45647898", serviciosDTO);
		centrosDTO.add(unCentro);
		return centrosDTO;
	}

}
