package ar.utn.dds.repositorio;
import org.uqbar.commons.model.*;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

import java.util.ArrayList;
import java.util.List;
import ar.utn.dds.POI.POI;
import ar.utn.dds.exceptions.RepositoryException;
import ar.utn.dds.utils.OrigenDeDatos;

public class Repositorio extends CollectionBasedRepo<POI> implements OrigenDeDatos{
	
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

	private List<POI> repoPois = new ArrayList<POI>();

	public List<POI> getPuntosDeInteres(){
		return repoPois;
	}

	// ********************************************************
	// ** SetUp Repositorio de Puntos de interes
	// ********************************************************

	void init(){



/*
		new PuntoDeInteres("1", "Cafe Martinez", "(-34.703658, -58.393030)", "Gdor. F. Llavallol 80", "Local Comercial", { rubro: "Cafetería"} ),
		new PuntoDeInteres("2","Planet Taco | Mexican & American Food", "(-34.702516, -58.393320)", "Gdor. Carlos Tejedor 102", "Local Comercial", { rubro: "Restaurant"} ),
		new PuntoDeInteres("3","Banco Itaú", "(-34.703325, -58.391981)", "Av. Hipólito Yrigoyen 4189", "Sucursal de banco", { serviciosAsociados: ["Asistencia Financiera" , "Tramite de alta", "Pago de Tarjeta"] , zona: "Lanus" } ),
		new PuntoDeInteres("4","Banco ICBC", "(-34.703915, -58.391966)", "Av. Hipólito Yrigoyen 4227", "Sucursal de banco", { serviciosAsociados: ["Evaluacion para un credito" , "Chequeras", "Asesoramiento"] , zona: "Boedo" } ),
		new PuntoDeInteres("5","Linea 45 - Remedios de Escalada", "(-34.704478, -58.391965)", "Avenida Hipólito Yrigoyen 4299", "Parada de colectivo", { numeroDeLinea: "45" } ),
		new PuntoDeInteres("6","Linea 33 - Retiro", "(-34.704410, -58.391708)", "Av. Hipólito Yrigoyen 4276", "Parada de colectivo", { numeroDeLinea: "33" } ),
		new PuntoDeInteres("7","CGP 9", "(-34.645662, -58.485099)", "Av Directorio 4360", "CGP", { zona: "Comuna 9", serviciosAsociados: [{servicio: "Tarjeta VOS", horario: "10 - 15 hs"} , {servicio: "EcoBici", horario: "10 -14 hs"}]  } ),
		new PuntoDeInteres("8","CGP 2", "(-34.596665, -58.398946)", "Pres. José E. Uriburu 1022", "CGP", { zona: "Comuna 2", serviciosAsociados: [{servicio: "Tarjeta VOS", horario: "10 - 15 hs"} , {servicio: "Venta de pasajes - Bus Turístico", horario: "10 -14 hs"}]})
*/
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
