package ar.utn.dds.observers;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.uqbar.commons.utils.Observable;

import ar.utn.dds.utils.Consulta;

@Entity
@Observable
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoObservador", discriminatorType=DiscriminatorType.STRING)
public abstract class Observador {
	@Id
	@GeneratedValue Long id;
	
	public abstract void actualizar(Consulta consulta);
}
