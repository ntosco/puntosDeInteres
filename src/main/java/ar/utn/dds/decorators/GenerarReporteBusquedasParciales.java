package ar.utn.dds.decorators;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.managers.ManagerDeConsultas;
import ar.utn.dds.utils.Consulta;

public class GenerarReporteBusquedasParciales extends AccionDecorador{
	
	List<Integer> cantParciales = new ArrayList<Integer>();
	

	public GenerarReporteBusquedasParciales(ManagerDeConsultas decorado) {
		super(decorado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutarse(Consulta consulta) {
		this.getDecorado().ejecutarse(consulta);
		this.cantParciales.add(consulta.getCantidadDeResultados());
	}

	public List<Integer> getCantParciales() {
		return cantParciales;
	}
	

}
