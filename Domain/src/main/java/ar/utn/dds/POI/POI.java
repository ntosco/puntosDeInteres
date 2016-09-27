package ar.utn.dds.POI;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;
import org.uqbar.geodds.*;

import ar.utn.dds.estrategias.EstrategiaDisponibilidad;
import ar.utn.dds.utils.Jornada;

import org.apache.commons.lang.StringUtils;

import ar.utn.dds.exceptions.*;

import com.google.gson.annotations.*;

@Observable
//TODO Ver si es necesario realizar un objeto intermediario que sea observable
public abstract class POI extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Expose private String tipo;
	@Expose private int id;
	@Expose private String nombre;
	@Expose private String direccionNombre;
	@Expose private String barrio;
	@Expose private int direccionNumero;
	@Expose private Point ubicacionActual;

	private final double DISTANCIA_MINIMA_DE_CERCANIA = 0.5;
	
	@Expose private List<Jornada> JornadaDisponible = new ArrayList<Jornada>();
	@Expose private List<EstrategiaDisponibilidad> EstrategiasDisponibilidad = new ArrayList<EstrategiaDisponibilidad>();
	@Expose private List<String> listaPalabrasClave = new ArrayList <String>();
	@Expose private List<Review> reviews = new ArrayList <Review>();
	
	// Baja logica
	
	private String fechaBaja;
	
	
	// ********************************************************
	// ** Validacion
	// ********************************************************
	


	public void validate(){
		if (StringUtils.isEmpty(this.nombre))
			throw new InvalidModelException("El POI no posee un nombre");
		if (this.ubicacionActual == null)
			throw new InvalidModelException("El POI no posee ubicacion");
	}
		
	@Override
	public void validateCreate(){
		this.validate();
	}

	public void validateUpdate(){
		this.validate();
		if(this.isNew()){
			throw new InvalidModelException("El POI no posee un ID asociado al repositorio");
		}
	}
	
	@Override
	public boolean equals(Object obj){
		try{
			if (obj == null){
				return false;
			}
			
			POI otroPOI = (POI) obj;
			
			if (this.getId() == null || otroPOI.getId() == null){
				return this.esIgualA((POI) obj);
			}
			
			return this.getId() == otroPOI.getId();
		}catch (ClassCastException e){
			return false;
		}
	}
	
	
	public Boolean esIgualA(POI otroPoi){
		 return (nombre.equals(otroPoi.getNombre()) && 
				 ubicacionActual.equals(otroPoi.getUbicacionActual())
				);
	}
	
//	public Boolean esIgualA(POI otroPoi){
//		 return (this.nombre.equals(otroPoi.getNombre()) && 
//				 this.ubicacionActual.equals(otroPoi.getUbicacionActual())
//				);
//	}
	
	
	// ********************************************************
	// ** Geolocalizacion
	// ********************************************************
	
	public Boolean estaCercaDe(Point ubicacionTerminal){
		double d = ubicacionActual.distance(ubicacionTerminal);	
		return d < DISTANCIA_MINIMA_DE_CERCANIA;
	}
	
	// ********************************************************
	// ** Busqueda
	// ********************************************************
	
	public boolean contieneKeyword(String palabraClave){
		return listaPalabrasClave.contains(palabraClave);
	}

	public boolean buscarPOI(String textoAbuscar){
		return(contieneKeyword(textoAbuscar)||cumpleCondicionBusqueda(textoAbuscar));
	}
	
	abstract public boolean cumpleCondicionBusqueda(String textoAbuscar);
	
	
	// ********************************************************
	// ** Disponibilidad
	// ********************************************************
	
	public Boolean estaDisponible(String nombreServicio, LocalDateTime horarioConsultado) {
		return this.getEstrategiasDisponibilidad().stream()
				.allMatch((estrategiaDisponibilidad) -> estrategiaDisponibilidad.estaDisponible(this, null,
						nombreServicio, horarioConsultado));
	}

	
	
	// ********************************************************
	// ** Getters and Setters
	// ********************************************************
	
	
	public List<Jornada> getJornadaDisponible() {
		return JornadaDisponible;
	}

	public void setJornadaDisponible(List<Jornada> jornadaDisponible) {
		JornadaDisponible = jornadaDisponible;
	}

	public List<EstrategiaDisponibilidad> getEstrategiasDisponibilidad() {
		return EstrategiasDisponibilidad;
	}

	public void setEstrategiasDisponibilidad(List<EstrategiaDisponibilidad> estrategiasDisponibilidad) {
		EstrategiasDisponibilidad = estrategiasDisponibilidad;
	}

	public String getDireccionNombre() {
		return (direccionNombre.concat(" ").concat(Integer.toString(direccionNumero)));
	}
	
	public String getBarrio(){
		return barrio;
	}
	
	public void setBarrio(String barrio){
		this.barrio = barrio;
	}	

	public void setDireccionNombre(String direccionNombre) {
		this.direccionNombre = direccionNombre;
	}

	public int getDireccionNumero() {
		return direccionNumero;
	}

	public void setDireccionNumero(int direccionNumero) {
		this.direccionNumero = direccionNumero;
	}

	public Point getUbicacionActual() {
		return ubicacionActual;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public List<String> getListaPalabrasClave() {
		return listaPalabrasClave;
	}

	public void setListaPalabrasClave(List<String> listaPalabrasClave) {
		this.listaPalabrasClave = listaPalabrasClave;
	}

	public void setUbicacionActual(Point unPunto){
		ubicacionActual = unPunto;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void agregarReview(Review unaRev){
		List<Review> reviewsActuales = this.getReviews();
		reviewsActuales.add(unaRev);
		this.setReviews(reviewsActuales);
	}
	
	public boolean elUsuarioYaOpino(String nombreUsuario){
		List<String> listaNombres = new ArrayList <String>();
		this.getReviews().forEach(opinion -> listaNombres.add(opinion.getNombreUsuario()));
        if(listaNombres.contains(nombreUsuario)){
        	return true;
        }else{
        	return false;
        }
	}

	public double calcularValoracionPromedio(){
        if(this.getReviews().size() > 0){
			double totalDeValoraciones = 0;        
			for (int i = 0; i < this.getReviews().size(); i++) {
	        	totalDeValoraciones = totalDeValoraciones + this.getReviews().get(i).getValoracion();
	        }
	        return (totalDeValoraciones/this.getReviews().size());
        }else{
        	return 0;
        }
	}
	
	public  Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
