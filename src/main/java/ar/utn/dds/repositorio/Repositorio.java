package ar.utn.dds.repositorio;
import org.uqbar.commons.model.*;
import org.uqbar.geodds.Point;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

import java.util.List;
import java.util.Random;

import ar.utn.dds.POI.POI;

public class Repositorio extends CollectionBasedRepo<POI>{
	
	Random generadorRandom;
	
	public Repositorio() {
		super();
		this.generadorRandom = new Random();
	}
		
	
	// ********************************************************
	// ** ABMC Repositorio
	// ********************************************************
	
	public void create(POI poi){
		if(poi.esValido()){
			if(!this.validateExistence(poi)){
				poi.setId(this.generateID());
				super.effectiveCreate(poi);
			}
			//TODO Analizar uso de update
		}
	}
	
	public void delete(POI poi){
		//FIXME: Implementar delete		
	}
	
	public void update(POI poi){
		//FIXME: Implementar delete
	}

	public POI searchById(int id){
		//FIXME: Implementar searchById
		return null; //TODO: diughh, cambiar :D
	}
	
	public List<POI> search(String nombre){
		return (List<POI>) CollectionUtils.select(this.getObjects(),(poi)-> poi.buscarPOI(nombre));
	}
	
	// ********************************************************
	// ** Validaciones Repositorio
	// ********************************************************

	
	private boolean validateExistence(POI nuevoPoi) {
		return this.getObjects().stream().anyMatch((poi)-> poi.esIgualA(nuevoPoi));
	}

	// ********************************************************
	// ** Metodos complementarios del Repositorio
	// ********************************************************
	
	private int generateID(){
		int newID = this.generadorRandom.nextInt();
		if(this.searchById(newID) == null){
			return this.generadorRandom.nextInt();
		}
		return this.generateID();
	}
	
	@Override
	public Class<POI> getEntityType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public POI createExample() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Predicate getCriterio(POI example) {
		// TODO Auto-generated method stub
		return null;
	}

}
