package ar.utn.dds.repositorio;
import org.uqbar.commons.model.*;
import org.uqbar.geodds.Point;
import org.apache.commons.collections15.Predicate;

import java.util.Random;

import ar.utn.dds.POI.POI;

public class Repositorio extends CollectionBasedRepo<POI>{
	
	Random generadorRandom;
	
	public Repositorio() {
		super();
		this.generadorRandom = new Random();
	}
	
	public void create(POI poi){
		if(poi.esValido()){
			if(!this.validateExistence(poi)){
				poi.setId(this.generateID());
				super.effectiveCreate(poi);
			}
			//TODO Analizar uso de update
		}
	}
	
	private boolean validateExistence(POI nuevoPoi) {
		return this.getObjects().stream().anyMatch((poi)-> poi.esIgualA(nuevoPoi));
	}


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
