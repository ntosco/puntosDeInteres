package ar.utn.dds.POI;

public class SucursalBanco extends POI{
	
	//Agregar atributos

	public boolean cumpleCondicionBusqueda(String textoLibre){
		return contieneKeyword(textoLibre);		
		
	}
	
}
