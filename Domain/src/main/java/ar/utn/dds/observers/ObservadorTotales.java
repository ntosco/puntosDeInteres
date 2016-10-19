package ar.utn.dds.observers;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.uqbar.commons.utils.Observable;

import ar.utn.dds.reportes.ReporteTotales;
import ar.utn.dds.utils.Consulta;

@Entity
@Observable
@DiscriminatorValue("ObservadorTotales")
public class ObservadorTotales extends Observador{
	
	public static ObservadorTotales instance;
	
	public static ObservadorTotales getInstance(){
		if (instance == null) {
			instance = new ObservadorTotales();
		}
		return instance;
	}
	
	
	public void actualizar(Consulta consulta) {
		
		ReporteTotales.getInstance().procesarConsulta(consulta);
		
	}
}
