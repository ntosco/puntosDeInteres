package ar.utn.dds.decorators;

import ar.utn.dds.managers.ManagerDeConsultas;
import ar.utn.dds.servicios.ServiceLocator;
import ar.utn.dds.utils.Consulta;
import ar.utn.dds.utils.Mail;

public class NotificarTiempoDeConsulta extends AccionDecorador{
	
	private long tiempoMaximo;
	private String admin;
	static ServiceLocator instance;

	public NotificarTiempoDeConsulta(Integer tiempoMaximo,ManagerDeConsultas decorado, String admin) {
		super(decorado);
		this.tiempoMaximo = tiempoMaximo;
		this.admin = admin;
	}


	@Override
	public void ejecutarse(Consulta consulta) {
		this.getDecorado().ejecutarse(consulta);
		this.notificarAdminSiEsNecesario(consulta);
	}


	private void notificarAdminSiEsNecesario(Consulta consulta) {
		
		if(consulta.getTiempoDeEjecución() > tiempoMaximo){
			instance.getMailSender().enviarMail(new Mail("Consulta excede el tiempo de ejecucion","Deberia mandar 'this'",this.admin)); //FIXME: Resolver el tipado del email
		}
			
		
	}

}
