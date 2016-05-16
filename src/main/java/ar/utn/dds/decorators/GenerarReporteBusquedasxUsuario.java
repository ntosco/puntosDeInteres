package ar.utn.dds.decorators;

import java.util.HashMap;
import java.util.Map;

import ar.utn.dds.managers.ManagerDeConsultas;
import ar.utn.dds.utils.Consulta;

public class GenerarReporteBusquedasxUsuario extends AccionDecorador {
	
	Map<String, Integer> listaBusquedasxUsuario = new HashMap<>();
	

	public GenerarReporteBusquedasxUsuario(ManagerDeConsultas decorado) {
		super(decorado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutarse(Consulta consulta) {
		this.getDecorado().ejecutarse(consulta);
		this.asignarCantResultadosAUsuario(consulta.getUsuarioEjecutor(),consulta.getCantidadDeResultados());
		
	}

	private void asignarCantResultadosAUsuario(String usuarioEjecutor, Integer cantidadDeResultados) {
		if(this.listaBusquedasxUsuario.containsKey(usuarioEjecutor)){
			cantidadDeResultados +=  this.listaBusquedasxUsuario.get(usuarioEjecutor);
		}
		this.listaBusquedasxUsuario.put(usuarioEjecutor, cantidadDeResultados);

	}

	public Map<String, Integer> getListaBusquedasxUsuario() {
		return listaBusquedasxUsuario;
	}




}
