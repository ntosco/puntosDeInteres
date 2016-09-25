package ar.utn.dds.repositorio;
import ar.utn.dds.POI.ParadaDeColectivo;
import org.uqbar.commons.model.*;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.utn.dds.POI.POI;
import ar.utn.dds.exceptions.RepositoryException;
import ar.utn.dds.utils.OrigenDeDatos;
import org.uqbar.geodds.Point;

public class Repositorio extends CollectionBasedRepo<POI> implements OrigenDeDatos{

	/* Singleton */
	private static Repositorio repoPois;

	public static Repositorio getInstance(){
		if (Repositorio.repoPois == null) {
			Repositorio.repoPois = new Repositorio();
		}	
		return Repositorio.repoPois;
	}

	public void clean(){Repositorio.repoPois = null;}

	// ********************************************************
	// ** SetUp Repositorio de Puntos de interes
	// ********************************************************

	private Repositorio(){
		this.crearPoi("Linea 33 - Retiro", "Av. Hipólito Yrigoyen 4276");
		this.crearPoi("Linea 45 - Remedios de Escalado", "Avenida Hipólito Yrigoyen 4299");
	}

	public POI crearPoi(String unaDescripcion, String unaDireccion){

		ParadaDeColectivo unPoi = new ParadaDeColectivo();

		unPoi.setDireccionNombre(unaDireccion);
		unPoi.setLinea(unaDescripcion);
		unPoi.setNombre("bla");
		unPoi.setUbicacionActual(new Point(2,1));

		this.create(unPoi);
		return unPoi;
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
		poi.validateUpdate();
		this.validateExistenceByID(poi);
		super.update(poi);
	}

	public List<POI> search(String nombre){
		return (List<POI>) CollectionUtils.select(this.allInstances(),(poi)-> poi.buscarPOI(nombre));
	}
	
	// ********************************************************
	// ** Validaciones Repositorio
	// ********************************************************

	private Boolean validateExistence(POI nuevoPoi){
		return (!this.searchByExample(nuevoPoi).isEmpty());
	}
	
	private void validateExistenceByID(POI nuevoPoi){
			this.searchById(nuevoPoi.getId());
	}	
	
	@Override
	protected void validateCreate(POI nuevoPoi) {
		nuevoPoi.validateCreate();
		if (this.validateExistence(nuevoPoi)) 
			throw new RepositoryException("El POI ya existe");
	}

	@Override
	protected void validateDelete(POI nuevoPoi) {
		if (!this.validateExistence(nuevoPoi))
			throw new RepositoryException("El POI no existe en el repositorio");
	}
	
	// ********************************************************
	// ** Metodos complementarios del Repositorio
	// ********************************************************
	
	
	@Override
	public Class<POI> getEntityType() {
		return POI.class;
	}

	@Override
	public POI createExample() {
		// TODO Auto-generated method stub
		return null;
	}


//	Redefinir para la utilizacion en searchByExample
	@SuppressWarnings("unchecked")
	@Override
	protected Predicate<POI> getCriterio(POI example) {
		return new Predicate<POI>(){
			public boolean evaluate(POI poi) {
				return poi.equals((POI)example);
					}
			};
	}

	@Override
	public List<POI> buscarPOI(String nombre) {
		return this.search(nombre);
	}

}
