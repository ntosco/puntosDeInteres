package ar.utn.dds.creacionales;

import java.util.List;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.exceptions.InvalidModelException;

public class LocalComercialBuilder extends PoiBuilder {

	public double cercania = 0;
	public List<Rubro> rubros;
	
	public LocalComercial build(){
		if(nombre == null){
			throw new InvalidModelException("Falta ingresar nombre");
		}
		if(ubicacionActual == null){
			throw new InvalidModelException("Falta cagar ubicacion");
		}
		
		LocalComercial local = new LocalComercial();
		local.setNombre(nombre);
		local.setBarrio(barrio);
		local.setDireccionNombre(direccion);
		local.setDireccionNumero(numero);
		local.setUbicacionActual(ubicacionActual);
		local.setListaPalabrasClave(palabrasClave);
		local.setJornadaDisponible(jornada);
		local.setListaRubros(rubros);
		return local;
	}
	
	public void crearListaRubros(List<Rubro> lista) {
		rubros = lista;
	}
}
