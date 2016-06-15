package ar.utn.dds.creacionales;



import java.util.List;

import org.uqbar.geodds.Point;
import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.servicios.Servicio;

public class CgpBuilder extends PoiBuilder {

	private List<Servicio> servicios;
	private Comuna comuna;

	public CgpBuilder crearListaServicios(List<Servicio> lista) {
		servicios = lista;
		return this;
	}

	public CgpBuilder crearComuna(List<Point> puntos) {
		comuna = new Comuna();
		puntos.forEach(punto -> comuna.setAreaDeComuna(punto));
		return this;
	}
	
	public CentroGestionParticipacion build(){
		
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
		cgp.validate();
		return cgp;
	}
}
