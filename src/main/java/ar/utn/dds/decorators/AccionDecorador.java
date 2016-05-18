package ar.utn.dds.decorators;

import java.util.List;

import ar.utn.dds.POI.POI;
import ar.utn.dds.managers.Buscador;
import ar.utn.dds.utils.Consulta;

public abstract class AccionDecorador implements Buscador {
	
	private Buscador decorado;


	public AccionDecorador(Buscador decorado) {
		super();
		this.decorado = decorado;
	}
	
	
	public abstract List<POI> busquedaGeneral(String fraseBuscada);

	public Buscador getDecorado() {
		return decorado;
	}


	public void setDecorado(Buscador decorado) {
		this.decorado = decorado;
	}

}
