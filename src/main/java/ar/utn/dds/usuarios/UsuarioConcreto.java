package ar.utn.dds.usuarios;

import java.util.ArrayList;
import java.util.List;
import ar.utn.dds.POI.POI;
import ar.utn.dds.observers.Observador;
import ar.utn.dds.utils.Consulta;

public class UsuarioConcreto implements Usuario {
	
	private List<Observador> accionesObservers = new ArrayList<Observador>();
	private String nombreUsuario;
	

	@Override
	public List<POI> buscarPuntos(String pablabraBuscada) {
		
		Consulta consulta = new Consulta(this,pablabraBuscada);
		List<POI> poisResultantes = new ArrayList<POI>();
		
		poisResultantes = consulta.evaluarse();
		
		this.notificarObservadores(consulta);
		
		return poisResultantes;
	}

	@Override
	public void agregarObservador(Observador observerador) {
		accionesObservers.add(observerador);
	}

	@Override
	public void quitarObservador(Observador observerador) {
		accionesObservers.remove(observerador);
	}

	@Override
	public void notificarObservadores(Consulta consulta) {
		accionesObservers.forEach(observer -> observer.actualizar(consulta));
		// TODO Auto-generated method stub
		
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}



}
