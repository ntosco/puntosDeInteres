package ar.utn.dds.creacionales;

import java.util.List;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.exceptions.BusinessException;
import ar.utn.dds.servicios.Servicio;

public class CgpBuilder extends PoiBuilder {

	private List<Servicio> servicios;
	private Comuna comuna;

	public void crearListaServicios(List<Servicio> lista) {
		servicios = lista;
	}

	public void crearComuna() {
		comuna = new Comuna();
	}
	
	public CentroGestionParticipacion build(){
		if(nombre == null){
			throw new BusinessException("Falta ingresar nombre");
		}
		if(ubicacionActual == null){
			throw new BusinessException("Falta cagar ubicacion");
		}
		
		CentroGestionParticipacion cgp = new CentroGestionParticipacion();
		cgp.setNombre(nombre);
		cgp.setBarrio(barrio);
		cgp.setDireccionNombre(direccion);
		cgp.setDireccionNumero(numero);
		cgp.setUbicacionActual(ubicacionActual);
		cgp.setListaPalabrasClave(palabrasClave);
		cgp.setJornadaDisponible(jornada);
		cgp.setComuna(comuna);
		cgp.setListaServicios(servicios);
		return cgp;
		
	}
}
