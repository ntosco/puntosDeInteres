package ar.utn.dds.managers;

import ar.utn.dds.utils.Consulta;

public class ManagerDeAccionesImpl implements ManagerDeAcciones{

	@Override
	public void ejecutarse() {
		
		
	}
	
	public void ejecutarConsulta(String fraseAConsultar,String usuarioEjecutor){
		
		Consulta consulta = new Consulta(fraseAConsultar, usuarioEjecutor);
		consulta.ejecutarse();
		
	}

	
}
