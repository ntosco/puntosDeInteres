package ar.utn.dds.POI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import ar.utn.dds.servicios.Servicio;

public class SucursalBanco extends POI{
	
	private List<Servicio> listaServicios = new ArrayList<Servicio>();

	public boolean cumpleCondicionBusqueda(String textoLibre){
		return contieneKeyword(textoLibre);		
		
	}
	

	public Boolean estaDisponible(POI poi, String _nombreServicio,
			LocalDateTime _horarioConsultado) {
		 return this.getEstrategiasDisponibilidad().stream().anyMatch((estrategiaDisponibilidad)->estrategiaDisponibilidad.estaDisponible(this,this.listaServicios, _nombreServicio, _horarioConsultado));
		
	}
	
}
