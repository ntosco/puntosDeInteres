package ar.utn.dds.observers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.utn.dds.repositorio.RepositorioDeUsuarios;
import ar.utn.dds.servicios.MailSender;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.utils.Consulta;
import ar.utn.dds.utils.Mail;

public class ObservadorTiempoBusqueda implements Observador {

	private Integer tiempoDeBusquedaMaximo;
	private List<Consulta> listaConsultasNotificar = new ArrayList<Consulta>();
	
	public List<Consulta> getListaConsultasNotificar() {
		return listaConsultasNotificar;
	}
	
	public void setListaConsultasNotificar(List<Consulta> listaConsultas) {
		this.listaConsultasNotificar = listaConsultas;
	}
	@Override
	public void actualizar(Consulta consulta) {
		if(consulta.getTiempoTranscurrido() > getTiempoDeBusquedaMaximo()){
			
			//Ver si le corresponde al Observer tener toda la "lógica" para sacar la lista de correos de los usuarios
			List<Usuario> listaDeAdministradores = RepositorioDeUsuarios.getInstance().getUsuariosAdministradores();
			List<String> listaCorreosDeDestinatarios = listaDeAdministradores.stream().map((administrador)-> administrador.getEmail()).collect(Collectors.toList()); 
			MailSender.enviarMail(new Mail("Consulta excede el tiempo de ejecucion", listaCorreosDeDestinatarios));
			listaConsultasNotificar.add(consulta);
		}		
	}
	

	public Integer getTiempoDeBusquedaMaximo() {
		return tiempoDeBusquedaMaximo;
	}

	public void setTiempoDeBusquedaMaximo(Integer tiempoDeBusquedaMaximo) {
		this.tiempoDeBusquedaMaximo = tiempoDeBusquedaMaximo;
	}

}
