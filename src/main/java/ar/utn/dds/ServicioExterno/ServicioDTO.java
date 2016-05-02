package ar.utn.dds.ServicioExterno;

import java.util.List;

public class ServicioDTO {
	
	private String nombreServicio;
	private List<RangoServicioDTO> rangosHorarios;
	
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public void setRangosHorarios(List<RangoServicioDTO> rangosHorarios) {
		this.rangosHorarios = rangosHorarios;
	}

	
	public List<RangoServicioDTO> getRangosHorarios(){
		return rangosHorarios;
	}
	
	public String getNombreServicio(){
		return nombreServicio;
	}
	
	public ServicioDTO(String nombreServicio, List<RangoServicioDTO> rangosHorarios){
		setNombreServicio(nombreServicio);
		setRangosHorarios(rangosHorarios);
	}

}
