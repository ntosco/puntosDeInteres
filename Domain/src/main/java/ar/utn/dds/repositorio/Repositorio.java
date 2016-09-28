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
	public List<String> palabrasClaveLibreria;
	public List<String> palabrasClaveCGP;
	public List<String> palabrasClaveColectivo;
	public List<String> palabrasClaveBanco;
	public Rubro libreriaRubro;
	public List<Rubro> rubroLibreria;
	public List<Review> reviews;
	public List<Point> puntosComuna6;
	public Servicio pagoDeFacturas;
	public List<Servicio> servicioPagoDeFacturas;
	public Review unReview;
	private Review reviewCGP1;
	private Review reviewCGP2;
	private Review reviewBanco1;
	private Review reviewBanco2;
	private Review reviewLibreria1;
	private Review reviewColectivo1;
	private Review reviewColectivo2;
	private List<Review> reviewsBondi;
	private ArrayList<Review> reviewsCGP;
	private ArrayList<Review> reviewsLibreria;
	private ArrayList<Review> reviewsBanco;
	private Servicio ventaDeArticulos;
	private ArrayList<Servicio> serviciosLibreria;
	private Servicio cajeroAutomatico;
	private ArrayList<Servicio> serviciosBanco;
	private ArrayList<String> palabrasClaveFallabella;
	private Rubro ventaRopa;
	private ArrayList<Rubro> rubroFallabella;
	private ArrayList<Review> reviewsBanco2;
	private ArrayList<Review> reviewsCGP2;
	private ArrayList<Review> reviewsBondi2;
	private ArrayList<Review> reviewsFallabella;
	
	
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
		
		palabrasClaveLibreria = new ArrayList<String>();
		palabrasClaveLibreria.add("lapiz");
		palabrasClaveLibreria.add("cartuchera");
		palabrasClaveLibreria.add("mapa");
		palabrasClaveLibreria.add("fotocopias");
		palabrasClaveLibreria.add("libreria");
		
		palabrasClaveFallabella = new ArrayList<String>();
		palabrasClaveFallabella.add("compras");
		palabrasClaveFallabella.add("muebles");
		palabrasClaveFallabella.add("ropa");
		palabrasClaveFallabella.add("nike");
		
		palabrasClaveCGP = new ArrayList<String>();
		palabrasClaveCGP.add("CGP");
		palabrasClaveCGP.add("cgp");
		palabrasClaveCGP.add("facturas");
		
		palabrasClaveColectivo = new ArrayList<String>();
		palabrasClaveColectivo.add("parada");
		palabrasClaveColectivo.add("bondi");
		palabrasClaveColectivo.add("linea");
		palabrasClaveColectivo.add("45");
		palabrasClaveColectivo.add("86");
		
		palabrasClaveBanco = new ArrayList<String>();
		palabrasClaveBanco.add("banco");
		palabrasClaveBanco.add("santander");
		palabrasClaveBanco.add("frances");
		palabrasClaveBanco.add("cajero");
		
		/* Rubros */
		
		libreriaRubro = new Rubro("Libreria", 0.4);
		ventaRopa = new Rubro("Venta Ropa", 0.5);
		rubroFallabella = new ArrayList<Rubro>();
		rubroLibreria = new ArrayList<Rubro>();
		rubroLibreria.add(libreriaRubro);
		rubroFallabella.add(ventaRopa);
		rubroFallabella.add(libreriaRubro);

		
		/* Review */
		
		reviewCGP1 = new Review("Todo bien", "Juan", 5);
		reviewCGP2 = new Review("Mala atencion", "Pedro", 1);
		reviewBanco1 = new Review("Muy rapido", "Ana", 5);
		reviewBanco2 = new Review("Nunca hay sistema", "Juan", 1);
		reviewLibreria1 = new Review("Cierra los sabados", "Facu", 2);
		reviewColectivo1 = new Review("No paro el hdp", "Pedro", 1);
		reviewColectivo2 = new Review("tarda mucho", "Jose", 2);
		
		/* Reviews */
		
		reviewsBondi = new ArrayList<Review>();
		reviewsBondi.add(reviewColectivo1);
		reviewsBondi.add(reviewColectivo2);
		
		reviewsBondi2 = new ArrayList<Review>();
		reviewsBondi2.add(reviewBanco1);
		reviewsBondi2.add(reviewCGP1);
		
		reviewsCGP = new ArrayList<Review>();
		reviewsCGP.add(reviewCGP1);
		reviewsCGP.add(reviewCGP2);
		
		reviewsCGP2 = new ArrayList<Review>();
		reviewsCGP2.add(reviewCGP1);
		reviewsCGP2.add(reviewLibreria1);
		
		reviewsLibreria = new ArrayList<Review>();
		reviewsLibreria.add(reviewLibreria1);
		
		reviewsFallabella = new ArrayList<Review>();
		reviewsFallabella.add(new Review("Muy lindo", "Anita", 5));
		
		reviewsBanco = new ArrayList<Review>();
		reviewsBanco.add(reviewBanco1);
		reviewsBanco.add(reviewBanco2);
		
		reviewsBanco2 = new ArrayList<Review>();
		reviewsBanco2.add(reviewBanco1);
		reviewsBanco2.add(reviewBanco2);
		
		/* Comunas */
		
		puntosComuna6 = new ArrayList<Point>();
		puntosComuna6.add(new Point(10.0,5.2));
		puntosComuna6.add(new Point(5.0, 10.3));
		
		/* Servicios */
		
		pagoDeFacturas = new Servicio("Pago de facturas",jornadaNormalLunesAViernes);
		cajeroAutomatico = new Servicio("Cajero Automatico", jornadaNormalLunesAViernes);
		servicioPagoDeFacturas = new ArrayList<Servicio>();
		pagoDeFacturas.setNombreDeJornada("Lunes a Viernes 10 a 15");
		cajeroAutomatico.setNombreDeJornada("Lunes a Viernes 10 a 20");
		servicioPagoDeFacturas.add(pagoDeFacturas);	
		
		serviciosBanco = new ArrayList<Servicio>();
		serviciosBanco.add(pagoDeFacturas);
		serviciosBanco.add(cajeroAutomatico);
		
		
		this.crearPoi("localComercial", 1,"Libreria El Tano", "Av. Hipolito Yrigoyen", "La boca", 4276, new Point(21.0 , 2.1), palabrasClaveLibreria, jornadaNormalLunesAViernes, rubroLibreria, reviewsLibreria);
		this.crearPoi("localComercial", 8,"Fallabella", "Av. San Juan", "Recoleta", 4276, new Point(21.0 , 2.1), palabrasClaveFallabella, jornadaNormalLunesAViernes, rubroFallabella, reviewsFallabella);
		this.crearPoi("lineaColectivo", 2,"Linea 45", "Av. Santa Fe", "Boedo", 3245, "45", new Point(21.0 , 2.1), palabrasClaveColectivo, reviewsBondi2);
		this.crearPoi("lineaColectivo", 5,"Linea 86", "Av. Corrientes", "Onces", 3245, "86", new Point(21.0 , 2.1), palabrasClaveColectivo, reviewsBondi);
		this.crearPoi("CGP", 3,"CGP 3", "Catamarca", "Once", 256, new Point(2.0, 5.6), puntosComuna6,	servicioPagoDeFacturas, palabrasClaveCGP, jornadaNormalLunesAViernes, reviewsCGP);
		this.crearPoi("CGP", 6,"CGP 6", "Av. Callao", "Centro", 1364, new Point(2.0, 5.6), puntosComuna6,	serviciosBanco, palabrasClaveCGP, jornadaNormalLunesAViernes, reviewsCGP2);
		this.crearPoi("sucursalBanco", 4,"Banco Santander", "Medrano", 564, new Point(5.0, 6.0), "Palermo", serviciosBanco, palabrasClaveBanco, jornadaNormalLunesAViernes, reviewsBanco);
		this.crearPoi("sucursalBanco", 7,"Banco Frances", "Boyaca", 765, new Point(5.0, 6.0), "San Telmo", serviciosBanco, palabrasClaveBanco, jornadaNormalLunesAViernes, reviewsBanco2);
	}

	/* Local Comercial */
	
	public POI crearPoi(String tipo, int id, String nombre, String direccion, String barrio, int numero, Point ubicacion, 
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
						poi.setId(id);
						poi.setTipo(tipo);
		this.create(poi);
		return poi;
	}
	
	/* Parada de Colectivo */ 
	
	public POI crearPoi(String tipo, int id, String nombre, String direccion, String barrio, int numero, String linea, Point ubicacion, 
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
					poi.setId(id);
					poi.setTipo(tipo);
			this.create(poi);
			return poi;	
					
	}		
	
	/* CGP */
	
	public POI crearPoi(String tipo, int id, String nombre, String direccion, String barrio, int numero, Point ubicacion, List<Point> comuna,
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
						poi.setId(id);
						poi.setTipo(tipo);
			this.create(poi);
			return poi;
	}
	
	/* Sucursal Banco */
	
	public POI crearPoi(String tipo, int id, String nombre, String direccion, int numero, Point ubicacion, String barrio, List<Servicio> servicios,
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
					poi.setId(id);
					poi.setTipo(tipo);
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
