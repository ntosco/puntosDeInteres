package ar.utn.dds.decorators;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.POI.POI;
import ar.utn.dds.managers.Buscador;
import ar.utn.dds.servicios.MailSender;
import ar.utn.dds.servicios.ServiceLocator;
import ar.utn.dds.utils.Mail;

public class NotificarTiempoDeConsulta extends AccionDecorador{
	
	private long tiempoMaximo;
	private String admin;
	static ServiceLocator instance;

	public NotificarTiempoDeConsulta(Integer tiempoMaximo,Buscador decorado, String admin) {
		super(decorado);
		this.tiempoMaximo = tiempoMaximo;
		this.admin = admin;
	}


	@Override
	public List<POI> busquedaGeneral(String fraseBuscada){
		long tiempoInicial = System.currentTimeMillis();
		
		List<POI> poisEncontrados = new ArrayList<POI>();
		
		poisEncontrados = this.getDecorado().busquedaGeneral(fraseBuscada);
		
		long tiempoFinal = System.currentTimeMillis();

		this.notificarAdminSiEsNecesario(tiempoFinal - tiempoInicial);
		
		return poisEncontrados;
	}


	private void notificarAdminSiEsNecesario(long tiempoDeEjecucion) {
		
		if(tiempoDeEjecucion > tiempoMaximo){
			MailSender.enviarMail(new Mail("Consulta excede el tiempo de ejecucion","Deberia mandar 'this'",this.admin)); //FIXME: Resolver el tipado del email
		}
			
		
	}


}
