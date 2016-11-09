package ar.utn.dds.reportes;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.repositorio.AbstractRepoMongo;
import ar.utn.dds.utils.Consulta;

public class ReporteAlmacenamientoConsultas extends AbstractRepoMongo{
	
	private List<Consulta> consultasAlmacenadas = new ArrayList<Consulta>();

	public void procesarConsulta(Consulta consulta) {
		this.consultasAlmacenadas.add(consulta);
		this.getDatastore().save(consulta);
	}

	//// Singleton

	public static ReporteAlmacenamientoConsultas instance;
	
	public static ReporteAlmacenamientoConsultas getInstance(){
		if (instance == null) {
			instance = new ReporteAlmacenamientoConsultas();
		}
		return instance;
	}

	//// Getters and Setters

	private ReporteAlmacenamientoConsultas() {super();}

	public List<Consulta> getConsultasAlmacenadas() {
		return consultasAlmacenadas;
	}

	public void setConsultasAlmacenadas(List<Consulta> consultasAlmacenadas) {
		this.consultasAlmacenadas = consultasAlmacenadas;
	}

}
