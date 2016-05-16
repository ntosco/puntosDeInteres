package ar.utn.dds.decorators;

import ar.utn.dds.managers.ManagerDeConsultas;
import ar.utn.dds.utils.Consulta;

public class NotificarTiempoDeConsulta extends AccionDecorador{
	
	private Integer tiempoMaximo;

	public NotificarTiempoDeConsulta(Integer tiempoMaximo,ManagerDeConsultas decorado) {
		super(decorado);
		this.tiempoMaximo = tiempoMaximo;
	}


	@Override
	public void ejecutarse(Consulta consulta) {
		this.getDecorado().ejecutarse(consulta);
		
	}

}
