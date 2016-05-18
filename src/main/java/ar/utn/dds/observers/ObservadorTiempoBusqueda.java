package ar.utn.dds.observers;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.utils.Consulta;

public class ObservadorTiempoBusqueda implements Observador {

	private Integer tiempoDeBusquedaMaximo;
	private List<Consulta> listaConsultasNotificar = new ArrayList<Consulta>();
	
	
	public List<Consulta> getListaConsultasNotificar() {
		return listaConsultasNotificar;
	}

	public void setListaConsultasNotificar(List<Consulta> listaConsultas) {
		this.listaConsultasNotificar = listaConsultas;
	}

	@Override
	public void actualizar(Consulta consulta) {
		if(consulta.getTiempoTranscurrido() > getTiempoDeBusquedaMaximo()){
			listaConsultasNotificar.add(consulta);
		}		
	}
	
	public void notificarAdministrador(Consulta consulta) {
	
	}

	public Integer getTiempoDeBusquedaMaximo() {
		return tiempoDeBusquedaMaximo;
	}

	public void setTiempoDeBusquedaMaximo(Integer tiempoDeBusquedaMaximo) {
		this.tiempoDeBusquedaMaximo = tiempoDeBusquedaMaximo;
	}

}
