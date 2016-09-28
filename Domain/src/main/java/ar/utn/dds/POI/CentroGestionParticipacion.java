package ar.utn.dds.POI;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;
import org.uqbar.geodds.*;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.estrategias.implementacion.DisponibilidadxServicio;
import ar.utn.dds.servicios.Servicio;
@Observable
public class CentroGestionParticipacion extends POI{

	@Expose private List<Servicio> listaServicios = new ArrayList <Servicio>();
	@Expose private Comuna comuna;	
	@Expose private List<String> listaServiciosString = new ArrayList<String>();
			
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
	
	public void actualizarListaServicios(){
		List<String> lista = new ArrayList<String>();
		this.listaServicios.forEach(servicio -> lista.add(servicio.getNombre()));
		listaServiciosString = lista;
	}

	public CentroGestionParticipacion() {
		super();
		List<EstrategiaDisponibilidad> estrategias = new ArrayList<EstrategiaDisponibilidad>();
		estrategias.add(new DisponibilidadxServicio());
		this.setEstrategiasDisponibilidad(estrategias);
	}

	public List<String> getListaServiciosString() {
		this.actualizarListaServicios();
		return listaServiciosString;
	}

	public void setListaServiciosString(List<String> listaServiciosString) {
		this.listaServiciosString = listaServiciosString;
	}
	
	

}
