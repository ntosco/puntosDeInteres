package ar.utn.dds.procesos.estrategiaFallo;


public class EnvioMensajePorFalla implements EstrategiaPorFallo {

	@Override
	public void ejecutarse() {
	
//		FIXME Ver si el correo se envia a el usuario ejecutor o a todos los Administradores
//		List<String> destinatarios = new ArrayList<String>();
//		destinatarios.add(usuarioEjecutor.getEmail());
//		
//		MailSender.enviarMail(new Mail("El proceso" + procesoEnEstadoDeError.getNombre() + "fallo en su ejecución", destinatarios ));
	}
	

}
