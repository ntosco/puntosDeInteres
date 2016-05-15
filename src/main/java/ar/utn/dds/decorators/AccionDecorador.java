package ar.utn.dds.decorators;

import ar.utn.dds.managers.ManagerDeAcciones;

public abstract class AccionDecorador implements ManagerDeAcciones {
	
	private ManagerDeAcciones decorado;


	public AccionDecorador(ManagerDeAcciones decorado) {
		super();
		this.decorado = decorado;
	}
	
	
	public abstract void ejecutarse();

}
