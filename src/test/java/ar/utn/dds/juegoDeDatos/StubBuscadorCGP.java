package ar.utn.dds.juegoDeDatos;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.extern.cgp.BuscadorDeCGP;
import ar.utn.dds.extern.cgp.CentroDTO;
import ar.utn.dds.extern.cgp.RangoServicioDTO;
import ar.utn.dds.extern.cgp.ServicioDTO;

public class StubBuscadorCGP implements BuscadorDeCGP {

	private static StubBuscadorCGP stubBuscador;

	public static StubBuscadorCGP getInstance() {
		if (stubBuscador == null) {

			stubBuscador = new StubBuscadorCGP();
		}
		return stubBuscador;
	}
	
	public List<CentroDTO> buscarPOI(String nombre) {
		
		
		
		
		List<CentroDTO> centrosDTO = new ArrayList<CentroDTO>();
		List<ServicioDTO> serviciosDTO = new ArrayList<ServicioDTO>();
		List<ServicioDTO> serviciosDTOPalermo = new ArrayList<ServicioDTO>();
		List<RangoServicioDTO> rangosDTO = new ArrayList<RangoServicioDTO>();
		
		RangoServicioDTO unRango = new RangoServicioDTO(1,9,0,18,0);
		RangoServicioDTO otroRango = new RangoServicioDTO(1,9,0,13,0);
		
		rangosDTO.add(unRango);
		rangosDTO.add(otroRango);
		
		ServicioDTO unServicio = new ServicioDTO("rentas", rangosDTO);
		ServicioDTO serviciosPalermo = new ServicioDTO("pagos", rangosDTO);
		
		serviciosDTO.add(unServicio);
		serviciosDTOPalermo.add(serviciosPalermo);
		
		CentroDTO unCentro = new CentroDTO(1, "Recoleta", "Juan Perez", "Jujuy 998", "45647898", serviciosDTO);
		CentroDTO centroPalermo = new CentroDTO(1, "Palermo", "Miguelito", "Santa Fe 556", "6546532", serviciosDTOPalermo);
		centrosDTO.add(unCentro);
		centrosDTO.add(centroPalermo);
		
		return centrosDTO;
	}

}
