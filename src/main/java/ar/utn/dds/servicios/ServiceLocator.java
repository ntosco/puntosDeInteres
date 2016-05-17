package ar.utn.dds.servicios;

public class ServiceLocator {
	
	static ServiceLocator instance;
	MailSender mailSender;


	public static ServiceLocator getInstance() {
		if (instance == null)
			instance = new ServiceLocator();
		return instance;
	}
	
	public MailSender getMailSender() {
		return mailSender;
	}

}
