package ar.utn.dds.usuarios;

import java.util.List;

import org.json.simple.JSONArray;

import ar.utn.dds.POI.POI;
import ar.utn.dds.observers.Observador;
import ar.utn.dds.utils.Consulta;

public interface Usuario {
	public List<POI> buscarPuntos (String pablabraBuscar);
	
	public void agregarObservador(Observador observerador);
	
	public void quitarObservador(Observador observerador);
	
	public void notificarObservadores(Consulta consulta);
}
