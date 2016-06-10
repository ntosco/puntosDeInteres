package ar.utn.dds.observers;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.utils.Consulta;

public class ObservadorAlmacenamientoDeConsultas implements Observador{

	private List<Consulta> consultasAlmacenadas = new ArrayList<Consulta>();
	
	@Override
	public void actualizar(Consulta consulta) {
		this.consultasAlmacenadas.add(consulta);
	}
	

}
