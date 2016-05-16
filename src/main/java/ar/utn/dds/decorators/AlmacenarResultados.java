package ar.utn.dds.decorators;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.managers.ManagerDeConsultas;
import ar.utn.dds.utils.Consulta;

public class AlmacenarResultados extends AccionDecorador {
	
	List<Consulta> consultasAlmacenadas = new ArrayList<Consulta>();


	public AlmacenarResultados(ManagerDeConsultas decorado) {
		super(decorado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutarse(Consulta consulta) {
		this.getDecorado().ejecutarse(consulta);
		this.almacenarConsulta(consulta);
		
		
	}

	private void almacenarConsulta(Consulta consulta) {
		this.consultasAlmacenadas.add(consulta);
		
	}

	@Override
	public void ejecutarse() {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	
	

}
