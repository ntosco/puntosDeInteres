package ar.utn.dds.creacionales;

import ar.utn.dds.POI.ParadaDeColectivo;

public class ColectivoBuilder extends PoiBuilder {
	
	private String linea;
	private double distancia = 0.1;
	
	public ParadaDeColectivo build(){
				
		ParadaDeColectivo colectivo = new ParadaDeColectivo();
		colectivo.setNombre(nombre);
		colectivo.setBarrio(barrio);
		colectivo.setDireccionNombre(direccion);
		colectivo.setDireccionNumero(numero);
		colectivo.setUbicacionActual(ubicacionActual);
		colectivo.setListaPalabrasClave(palabrasClave);
		colectivo.setJornadaDisponible(jornada);
		colectivo.setLinea(linea);
		colectivo.validate();
		return colectivo;
	}
	
	public ColectivoBuilder crearLinea(String linea){
		this.linea = linea;
		return this;
	}

}
