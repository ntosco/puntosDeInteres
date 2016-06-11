package ar.utn.dds.observers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import ar.utn.dds.reportes.ReportePorFecha;
import ar.utn.dds.utils.BusquedaDePuntos;
import ar.utn.dds.utils.Consulta;

public class ObservadorPorFecha implements Observador{

	//private List<Integer> BusquedasPorFecha = new ArrayList<Integer>();
	//	private Hashtable<String,Integer> BusquedasPorFecha = new Hashtable<String,Integer>();

	// Mantengo al Observer como Singleton.
	
	public static ObservadorPorFecha instance;
	private ReportePorFecha reporte = new ReportePorFecha();
	
	
	public static ObservadorPorFecha getInstance(){
		if (instance == null) {
			instance = new ObservadorPorFecha();
		}	
		return instance;
	}
	
	
	@Override
	public void actualizar(Consulta consulta) {
		
		reporte.validarConsulta(consulta);
		
		// El observer lo unico que realiza es avisar a la abstracciòn reporte asignada.
	}



}
