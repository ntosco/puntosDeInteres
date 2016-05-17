package ar.utn.dds.utils;

public class Mail {
	  private final String titulo;
	  private final String remitente; //notificarTiempoDeConsulta
	  private final String destinatario; //admin

	  public Mail(String titulo, String remitente, String destinatario) {
	    this.titulo = titulo;

	    this.remitente = remitente;
	    this.destinatario = destinatario;
	  }

	public String getRemitente() {
		return remitente;
	}

	public String getDestinatario() {
		return destinatario;
	}
	
}
