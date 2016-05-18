package ar.utn.dds.creacionales;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.exceptions.BusinessException;
import ar.utn.dds.exceptions.InvalidModelException;

public class ColectivoBuilder extends PoiBuilder {
	
	private String linea;
	private double distancia = 0.1;
	
	public ParadaDeColectivo build(){
		if(nombre == null){
			throw new InvalidModelException("Falta ingresar nombre");
		}
		if(ubicacionActual == null){
			throw new InvalidModelException("Falta cagar ubicacion");
		}
		
		ParadaDeColectivo colectivo = new ParadaDeColectivo();
		colectivo.setNombre(nombre);
		colectivo.setBarrio(barrio);
		colectivo.setDireccionNombre(direccion);
		colectivo.setDireccionNumero(numero);
		colectivo.setUbicacionActual(ubicacionActual);
		colectivo.setListaPalabrasClave(palabrasClave);
		colectivo.setJornadaDisponible(jornada);
		colectivo.setLinea(linea);
		return colectivo;
	}
	
	public void crearLinea(String linea){
		this.linea = linea;
	}

}
