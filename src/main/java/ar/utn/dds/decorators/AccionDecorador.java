package ar.utn.dds.decorators;

import ar.utn.dds.managers.ManagerDeAcciones;

public abstract class AccionDecorador implements ManagerDeAcciones {
	
	private ManagerDeAcciones Decorado;


	public AccionDecorador(ManagerDeAcciones decorado) {
		super();
		Decorado = decorado;
	}
	
	
	public abstract void ejecutarse();

}
