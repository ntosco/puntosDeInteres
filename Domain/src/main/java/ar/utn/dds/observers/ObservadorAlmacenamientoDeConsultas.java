package ar.utn.dds.observers;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.uqbar.commons.utils.Observable;

import ar.utn.dds.reportes.ReporteAlmacenamientoConsultas;
import ar.utn.dds.utils.Consulta;

@Entity
@Observable
@DiscriminatorValue("ObservadorAlmacenamientoDeConsultas")
public class ObservadorAlmacenamientoDeConsultas extends Observador{

	
	public void actualizar(Consulta consulta) {
		ReporteAlmacenamientoConsultas.getInstance().procesarConsulta(consulta);		
	}
	
}