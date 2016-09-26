package ar.utn.dds.repositorio;
import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.POI.Review;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.POI.SucursalBanco;

import org.uqbar.commons.model.*;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.utn.dds.POI.POI;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.creacionales.BancoBuilder;
import ar.utn.dds.creacionales.CgpBuilder;
import ar.utn.dds.creacionales.ColectivoBuilder;
import ar.utn.dds.creacionales.ListaJornadasBuilder;
import ar.utn.dds.creacionales.LocalComercialBuilder;
import ar.utn.dds.exceptions.RepositoryException;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.OrigenDeDatos;
import ar.utn.dds.utils.RangoHorario;

import org.uqbar.geodds.Point;

public class Repositorio extends CollectionBasedRepo<POI> implements OrigenDeDatos{

	public List<DayOfWeek> lunesAViernes;
	public RangoHorario rangoDe10a20;
	public List<Jornada> jornadaNormalLunesAViernes;
	public List<String> palabrasClave;
	public Rubro libreriaRubro;
	public List<Rubro> rubroLibreria;
	public List<Review> reviews;
	public List<Point> puntosComuna6;
	public Servicio pagoDeFacturas;
	public List<Servicio> servicioPagoDeFacturas;
	
	
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
		
		/* Jornada */
		
		lunesAViernes = new ArrayList<DayOfWeek>();
		lunesAViernes.add(DayOfWeek.MONDAY);
		lunesAViernes.add(DayOfWeek.TUESDAY);
		lunesAViernes.add(DayOfWeek.WEDNESDAY);
		lunesAViernes.add(DayOfWeek.THURSDAY);
		lunesAViernes.add(DayOfWeek.FRIDAY);
		
		ListaJornadasBuilder builderJornadaNormal = new ListaJornadasBuilder();
		rangoDe10a20 = new RangoHorario(LocalTime.of(10,0,0), LocalTime.of(20,0,0));
		jornadaNormalLunesAViernes = builderJornadaNormal.buildJornadas(lunesAViernes, rangoDe10a20);
		
		/* Palabras Clave */
		
		palabrasClave = new ArrayList<String>();
		palabrasClave.add("lapiz");
		palabrasClave.add("cartuchera");
		palabrasClave.add("mapa");
		
		/* Rubros */
		
		libreriaRubro = new Rubro("libreria", 0.4);
		rubroLibreria = new ArrayList<Rubro>();
		rubroLibreria.add(libreriaRubro);
		
		/* Reviews */
		
		reviews = new ArrayList<Review>();
		
		/* Comunas */
		
		puntosComuna6 = new ArrayList<Point>();
		puntosComuna6.add(new Point(10.0,5.2));
		puntosComuna6.add(new Point(5.0, 10.3));
		
		/* Servicios */
		
		pagoDeFacturas = new Servicio("Pago de facturas",jornadaNormalLunesAViernes);
		servicioPagoDeFacturas = new ArrayList<Servicio>();
		servicioPagoDeFacturas.add(pagoDeFacturas);		
		
		
		this.crearPoi("Libreria", "Av. Hipolito Yrigoyen 4276", "La boca", 4276, new Point(21.0 , 2.1), palabrasClave, jornadaNormalLunesAViernes, rubroLibreria, reviews);
		this.crearPoi("Linea 45", "Av. Santa Fe 3245", "Boedo", 3245, "San juan 12", new Point(21.0 , 2.1), palabrasClave, reviews);
		this.crearPoi("CGP 481", "Catamarca 256", "Once", 256, new Point(2.0, 5.6), puntosComuna6,	servicioPagoDeFacturas, palabrasClave, jornadaNormalLunesAViernes, reviews);
		this.crearPoi("Banco Santander", "Medrano 564", 564, new Point(5.0, 6.0), "Palermo", servicioPagoDeFacturas, palabrasClave, jornadaNormalLunesAViernes, reviews);
	}

	/* Local Comercial */
	
	public POI crearPoi(String nombre, String direccion, String barrio, int numero, Point ubicacion, 
			List<String> palabrasClave, List<Jornada> jornada, List<Rubro> rubros, List<Review> reviews){
		
		POI poi = new LocalComercial();
		LocalComercialBuilder builder = new LocalComercialBuilder();
		builder.crearListaRubros(rubros)
						.setNombre(nombre)
						.setBarrio(barrio)
						.setDireccion(direccion)
						.setNumero(numero)
						.setUbicacion(ubicacion)
						.setPalabrasClave(palabrasClave)
						.setJornada(jornada);
						poi = builder.build();
						poi.setReviews(reviews);

		this.create(poi);
		return poi;
	}
	
	/* Parada de Colectivo */ 
	
	public POI crearPoi(String nombre, String direccion, String barrio, int numero, String linea, Point ubicacion, 
			List<String> palabrasClave, List<Review> reviews){
		
		POI poi = new ParadaDeColectivo();
		ColectivoBuilder builder15 = new ColectivoBuilder();
		builder15.crearLinea(linea)
					.setNombre(nombre)
					.setBarrio(barrio)
					.setDireccion(direccion)
					.setNumero(numero)
					.setUbicacion(ubicacion)
					.setPalabrasClave(palabrasClave);
					poi = builder15.build();
					poi.setReviews(reviews);
			this.create(poi);
			return poi;	
					
	}		
	
	/* CGP */
	
	public POI crearPoi(String nombre, String direccion, String barrio, int numero, Point ubicacion, List<Point> comuna,
						List<Servicio> servicios, List<String> palabrasClave, List<Jornada> jornadas, List<Review> reviews){
		
		POI poi = new CentroGestionParticipacion();
		CgpBuilder builder = new CgpBuilder();
				  builder.crearComuna(comuna)
						.crearListaServicios(servicios)
						.setNombre(nombre)
						.setBarrio(barrio)
						.setDireccion(direccion)
						.setNumero(numero)
						.setUbicacion(ubicacion)
						.setPalabrasClave(palabrasClave)
						.setJornada(jornadas);
						poi = builder.build();
						poi.setReviews(reviews);
			this.create(poi);
			return poi;
	}
	
	/* Sucursal Banco */
	
	public POI crearPoi(String nombre, String direccion, int numero, Point ubicacion, String barrio, List<Servicio> servicios,
			List<String> palabrasClave, List<Jornada> jornadas, List<Review> reviews){
		
		POI poi = new SucursalBanco();
		BancoBuilder builder = new BancoBuilder();
		builder.crearListaServicios(servicios)
					.setNombre(nombre)
					.setBarrio(barrio)
					.setDireccion(direccion)
					.setNumero(numero)
					.setUbicacion(ubicacion)
					.setPalabrasClave(palabrasClave)
					.setJornada(jornadas);
					poi = builder.build();
					poi.setReviews(reviews);
			this.create(poi);
			return poi;
		
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
