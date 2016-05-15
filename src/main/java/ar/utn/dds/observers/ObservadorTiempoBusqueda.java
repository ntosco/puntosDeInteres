package ar.utn.dds.observers;

import ar.utn.dds.utils.Consulta;

public class ObservadorTiempoBusqueda implements Observador {

	@Override
	public void actualizar(Consulta consulta) {
		if(consulta.getTiempoTranscurrido() > 30){
			//notificar
		}		
	}
	
	public void notificarAdministrador(Consulta consulta) {
	
	}

}
