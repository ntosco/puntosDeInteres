package ar.utn.dds.repositorio;
import org.uqbar.commons.model.*;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

import java.util.List;
import java.util.Random;

import ar.utn.dds.POI.POI;
import ar.utn.dds.exceptions.BusinessException;


public class Repositorio extends CollectionBasedRepo<POI>{
	
	private Random generadorRandom;
	static Repositorio instance;
	

	private Repositorio() {
		super();
		this.generadorRandom = new Random();
	}
	
	public static Repositorio getInstance(){
		if (instance == null) {
			instance = new Repositorio();
		}	
		return instance;
		
	}
	
	// ********************************************************
	// ** ABMC Repositorio
	// ********************************************************
	
//	public void create(POI poi){
//		if(poi.esValido()){
//			if(!this.validateExistence(poi)){
//				poi.setId(this.generateID());
//				super.effectiveCreate(poi);
//			}
//			//TODO Analizar uso de update
//		}
//	}
	
	public void delete(POI poiAEliminar){
		this.validateExistence(poiAEliminar);
		super.effectiveDelete(poiAEliminar);
	}
	
	
	/*
	* void update(PuntoInteres): modifica el punto de interés dentro de la colección. En
	* caso de que el punto de interés tenga errores de validación no debe actualizar la
	* referencia al objeto. De no existir el objeto buscado, se debe lanzar una excepción.
	*/
	
	public void update(POI poi){
		if(poi.esValido()){
			super.update(poi);			
		}
		//TODO: no está finalizado, no hace lo que pide el enunciado
		
	}

	public POI searchById(int id){
		return (POI) this.getObjects().stream().filter((poi) -> poi.getId() == id);
		}
	
	public List<POI> search(String nombre){
		return (List<POI>) CollectionUtils.select(this.getObjects(),(poi)-> poi.buscarPOI(nombre));
	}
	
	// ********************************************************
	// ** Validaciones Repositorio
	// ********************************************************

//TODO	Debe lanzar una exception
	private void validateExistence(POI nuevoPoi){
		if(this.allInstances().stream().anyMatch((poi)-> poi.esIgualA(nuevoPoi)))throw new BusinessException("El POI ya existe");
	}
	
	@Override
	protected void validateCreate(POI nuevoPoi){
		nuevoPoi.validateCreate();
		this.validateExistence(nuevoPoi);
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

//	Redefinir para la utilizacion en searchByExample
	@Override
	protected Predicate getCriterio(POI example) {
		// TODO Auto-generated method stub : 
		return null;
	}

}
