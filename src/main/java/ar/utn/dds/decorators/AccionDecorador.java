package ar.utn.dds.decorators;

import ar.utn.dds.managers.ManagerDeConsultas;
import ar.utn.dds.utils.Consulta;

public abstract class AccionDecorador implements ManagerDeConsultas {
	
	private ManagerDeConsultas decorado;


	public AccionDecorador(ManagerDeConsultas decorado) {
		super();
		this.decorado = decorado;
	}
	
	
	public abstract void ejecutarse(Consulta consulta);

	public ManagerDeConsultas getDecorado() {
		return decorado;
	}


	public void setDecorado(ManagerDeConsultas decorado) {
		this.decorado = decorado;
	}

}
