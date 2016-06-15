package ar.utn.dds.creacionales;

import java.util.List;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.Rubro;

public class LocalComercialBuilder extends PoiBuilder {

	public double cercania = 0;
	public List<Rubro> rubros;
	
	public LocalComercial build(){
		
		LocalComercial local = new LocalComercial();
		local.setNombre(nombre);
		local.setBarrio(barrio);
		local.setDireccionNombre(direccion);
		local.setDireccionNumero(numero);
		local.setUbicacionActual(ubicacionActual);
		local.setListaPalabrasClave(palabrasClave);
		local.setJornadaDisponible(jornada);
		local.setListaRubros(rubros);
		local.validate();
		return local;
	}
	
	public LocalComercialBuilder crearListaRubros(List<Rubro> lista) {
		rubros = lista;
		return this;
	}
}
