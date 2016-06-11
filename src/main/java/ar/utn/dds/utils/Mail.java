package ar.utn.dds.utils;

import java.util.List;

public class Mail {
	  private final String titulo;
	  private final List<String> destinatarios; //admin

	  public Mail(String titulo,List<String> destinatarios) {
	    this.titulo = titulo;
	    this.destinatarios = destinatarios;
	  }


	public List<String> getDestinatario() {
		return destinatarios;
	}
	
}
