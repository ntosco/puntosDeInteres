package ar.utn.dds.managers;


import ar.utn.dds.utils.Consulta;

public class ManagerDeConsultasImpl implements ManagerDeConsultas{

	@Override
	public void ejecutarse(Consulta consulta) {
		consulta.activarse();
	}
	

	
}
