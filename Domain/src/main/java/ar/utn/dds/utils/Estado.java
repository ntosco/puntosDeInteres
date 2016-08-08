package ar.utn.dds.utils;

public class Estado {
	
	public int valor;
	public String descripcion;
	
	public Estado(){
		this.descripcion = descripcion;
	}
	
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Boolean esUnEstadoOk(){
		return(valor == 2 );
	}

	public Boolean esUnEstadoError(){
		return(valor == 1 );
	}
	
	public void setEstadoComoErroneo(){
		this.valor = 2;
	}
	public void setEstadoComoOK(){
		this.valor = 1;
	}
}
