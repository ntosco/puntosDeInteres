package ar.utn.dds.POI;

import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.*;
import java.time.LocalDateTime;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxServicio;
import ar.utn.dds.servicios.Servicio;

public class CentroGestionParticipacion extends POI{

	private List<Servicio> listaServicios = new ArrayList <Servicio>();
	private Comuna comuna;	
			
	public boolean contieneServicio(String textoLibre){
		for (Servicio servicio : listaServicios){
			if (servicio.getNombre() == textoLibre)
				return true;
		}
		return false;
	}
	
	public Comuna getComuna() {
		return comuna;
	}

	public List<Servicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(List<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public boolean cumpleCondicionBusqueda(String textoLibre){
		return(contieneServicio(textoLibre));
	}
	
	
	public Boolean estaCercaDe(Point unPunto){
		return comuna.estaCercaDe(unPunto);
	}

	public void setComuna(Comuna comunaNueva) {
		comuna = comunaNueva;
	}
	
	public Boolean estaDisponible(String nombreServicio,
			LocalDateTime horarioConsultado) {
		 return this.getEstrategiasDisponibilidad().stream().anyMatch((estrategiaDisponibilidad)->estrategiaDisponibilidad.estaDisponible(this,this.listaServicios, nombreServicio, horarioConsultado));
		
	}

	public CentroGestionParticipacion() {
		super();
		List<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadxServicio());
		this.setEstrategiasDisponibilidad(estrategias);
	}

}
