package ar.utn.dds.observers;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.uqbar.commons.utils.Observable;

import ar.utn.dds.reportes.ReporteParciales;
import ar.utn.dds.utils.Consulta;
@Entity
@Observable
@DiscriminatorValue("ObservadorParciales")
public class ObservadorParciales extends Observador {

	
	public void actualizar(Consulta consulta) {
		ReporteParciales.getInstance().procesarConsulta(consulta);				
	}

}
