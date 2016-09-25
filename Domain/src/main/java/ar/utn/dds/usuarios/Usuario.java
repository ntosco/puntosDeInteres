package ar.utn.dds.usuarios;

import java.util.List;
import ar.utn.dds.POI.POI;
import ar.utn.dds.observers.Observador;
import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.utils.Consulta;

public interface Usuario {
	public List<POI> buscarPuntos (String pablabraBuscar);
		
	public void opinar(POI punto, String comentario, int valoracion);
	
	public void agregarObservador(Observador observerador);
	
	public void quitarObservador(Observador observerador);
	
	public void notificarObservadores(Consulta consulta);
	
	public void ejecutarProceso(Proceso proceso);
	
	public String getNombreUsuario();

	public Boolean tieneRolAdministrador();
	
	public String getEmail();
	
	public void actualizarAcciones(List<Observador> acciones);

	public List<Observador> getAccionesObservers();

	public void notificarFalla();

	public void setAccionesObservers(List<Observador> acciones2);
}
