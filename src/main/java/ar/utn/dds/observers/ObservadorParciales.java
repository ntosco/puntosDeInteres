package ar.utn.dds.observers;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.utils.Consulta;

public class ObservadorParciales implements Observador {
	private List<Integer> CantidadResultadosParciales = new ArrayList<Integer>();
	
	@Override
	public void actualizar(Consulta consulta) {
		CantidadResultadosParciales.add(consulta.getCantidadDeResultados());
		
	}

}
