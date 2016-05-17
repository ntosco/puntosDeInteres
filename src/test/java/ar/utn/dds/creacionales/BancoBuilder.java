package ar.utn.dds.creacionales;

import java.util.List;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.exceptions.BusinessException;
import ar.utn.dds.servicios.Servicio;

public class BancoBuilder extends PoiBuilder {
	
	private List<Servicio> servicios;
	
	public void crearListaServicios(List<Servicio> lista) {
		servicios = lista;
	}
	
	public SucursalBanco build(){
		if(nombre == null){
			throw new BusinessException("Falta ingresar nombre");
		}
		if(ubicacionActual == null){
			throw new BusinessException("Falta cagar ubicacion");
		}
		
		SucursalBanco banco = new SucursalBanco();
		banco.setNombre(nombre);
		banco.setBarrio(barrio);
		banco.setDireccionNombre(direccion);
		banco.setDireccionNumero(numero);
		banco.setUbicacionActual(ubicacionActual);
		banco.setListaPalabrasClave(palabrasClave);
		banco.setJornadaDisponible(jornada);
		banco.setListaServicios(servicios);
		return banco;
	}

}
