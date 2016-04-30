package ar.utn.dds.ServicioExterno;

import java.util.List;

public class ServicioDTO {
	
	private String nombreServicio;
	private List<RangoServicioDTO> rangosHorarios;
	
	public List<RangoServicioDTO> getRangosHorarios(){
		return rangosHorarios;
	}

}
