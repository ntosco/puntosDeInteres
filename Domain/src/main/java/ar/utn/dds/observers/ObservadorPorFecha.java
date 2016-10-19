package ar.utn.dds.observers;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.uqbar.commons.utils.Observable;

import ar.utn.dds.reportes.ReportePorFecha;
import ar.utn.dds.utils.Consulta;

@Entity
@Observable
@DiscriminatorValue("ObservadorPorFecha")
public class ObservadorPorFecha extends Observador{
	// Mantengo al Observer como Singleton.
	
	public static ObservadorPorFecha instance;

	public static ObservadorPorFecha getInstance(){
		if (instance == null) {
			instance = new ObservadorPorFecha();
		}	
		return instance;
	}
	
	public void actualizar(Consulta consulta) {
		ReportePorFecha.getInstance().procesarConsulta(consulta);		
	}

}
