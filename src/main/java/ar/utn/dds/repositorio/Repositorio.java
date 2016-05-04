package ar.utn.dds.repositorio;
import org.uqbar.commons.model.*;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

import java.util.List;
//import java.util.Random;

import ar.utn.dds.POI.POI;
import ar.utn.dds.exceptions.BusinessException;


public class Repositorio extends CollectionBasedRepo<POI>{
	
	static Repositorio instance;

	public static Repositorio getInstance(){
		if (instance == null) {
			instance = new Repositorio();
		}	
		return instance;
	}

	public void clean(){
		instance = null;
	}
	
	// ********************************************************
	// ** ABMC Repositorio
	// ********************************************************
	
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

	public List<POI> search(String nombre){
		return (List<POI>) CollectionUtils.select(this.getObjects(),(poi)-> poi.buscarPOI(nombre));
	}
	
	// ********************************************************
	// ** Validaciones Repositorio
	// ********************************************************

	private void validateExistence(POI nuevoPoi){
		if(this.allInstances().stream().anyMatch((poi)-> poi.esIgualA(nuevoPoi)))
			throw new BusinessException("El POI ya existe");
	}
	
	@Override
	protected void validateCreate(POI nuevoPoi){
		nuevoPoi.validateCreate();
		this.validateExistence(nuevoPoi);
	}

	// ********************************************************
	// ** Metodos complementarios del Repositorio
	// ********************************************************
	
	
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
