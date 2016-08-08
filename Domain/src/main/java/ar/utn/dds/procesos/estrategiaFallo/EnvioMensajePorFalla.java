package ar.utn.dds.procesos.estrategiaFallo;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.servicios.MailSender;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.utils.Mail;

public class EnvioMensajePorFalla implements EstrategiaPorFallo {


	private List<Usuario> usuariosANotificar ;

	public EnvioMensajePorFalla(List<Usuario> usuariosANotificar) {
		super();
		this.usuariosANotificar = usuariosANotificar;
	}
	
	@Override
	public void ejecutarse(Proceso procesoEnEstadoDeError) {
		//FIXME Se comenta hasta cambiar los test
	
		MailSender.enviarMail(new Mail("El proceso" + procesoEnEstadoDeError.getNombre() + "fallo en su ejecución", this.correoDeUsuariosANotificar() ));
	}
	
	private List<String> correoDeUsuariosANotificar(){
		List<String> correoDeUsuarios = new ArrayList<String>();
	    for ( Usuario usuario : this.usuariosANotificar ) {
	    	correoDeUsuarios.add(usuario.getEmail());
	    }
		return correoDeUsuarios;
	}
	
	

}
