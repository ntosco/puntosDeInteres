package ar.utn.dds.creacionales;

import java.util.List;

import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.servicios.Servicio;

public class BancoBuilder extends PoiBuilder {
	
	private List<Servicio> servicios;
	
	public BancoBuilder crearListaServicios(List<Servicio> lista) {
		servicios = lista;
		return this;
	}
	
	public SucursalBanco build(){
		
		SucursalBanco banco = new SucursalBanco();
		banco.setNombre(nombre);
		banco.setBarrio(barrio);
		banco.setDireccionNombre(direccion);
		banco.setDireccionNumero(numero);
		banco.setUbicacionActual(ubicacionActual);
		banco.setListaPalabrasClave(palabrasClave);
		banco.setJornadaDisponible(jornada);
		banco.setListaServicios(servicios);
		banco.validate();
		return banco;
	}

}
