package ar.utn.dds.utils;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.ServicioExterno.CentroDTO;
import ar.utn.dds.ServicioExterno.RangoServicioDTO;
import ar.utn.dds.ServicioExterno.ServicioDTO;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.servicios.Servicio;

public class Conversor extends JuegoDeDatos {
	
	public static Conversor instance;
	
	public static Conversor getInstance(){
		if(instance == null){
			instance = new Conversor();
		} return instance;
	}	
	
	public SucursalBanco jsonAbanco(JSONObject obj) {
		SucursalBanco banco = new SucursalBanco();
		
		banco.setBarrio(obj.get("sucursal").toString()); 
		banco.setDireccionNombre(obj.get("banco").toString());
		
		Point ubcicacionActual = new Point(Integer.parseInt(obj.get("x").toString()), Integer.parseInt(obj.get("y").toString()));
		banco.setUbicacionActual(ubcicacionActual);
		
		String serviciosJson = obj.get("servicios").toString();
		String[] arrayServicios = serviciosJson.split(",");
		List<Servicio> listaServicios = new ArrayList<Servicio>();
		for(int i=0; i<arrayServicios.length; i++){
			Servicio servicioBanco = new Servicio(arrayServicios[i], null);
			listaServicios.add(servicioBanco);
        }
		banco.setListaServicios(listaServicios);
		return banco;
	}
	
	public CentroGestionParticipacion convertirDTOACGP(CentroDTO dto){
		CentroGestionParticipacion nuevoCGP = new CentroGestionParticipacion();
		nuevoCGP.setComuna(getComunaAsociadaAlNumero(dto.getComuna()));
		nuevoCGP.setBarrio(dto.getZonasIncluidas());
		nuevoCGP.setDireccionNombre(getDireccionNombre(dto.getDomicilio()));
		nuevoCGP.setDireccionNumero(getDireccionNumero(dto.getDomicilio()));
		nuevoCGP.setListaServicios(getListaServiciosCGP(dto.getServicios()));
		return nuevoCGP;
	}
	
	private List<Servicio> getListaServiciosCGP(List<ServicioDTO> servicios) {
		List<Servicio> lista = new ArrayList<Servicio>();
		servicios.forEach(servicio -> lista.add(crearSerivicioEnBaseAlString(servicio)));
		return lista;
	}

	private Servicio crearSerivicioEnBaseAlString(ServicioDTO servicioDTO) {
		Servicio servicio = new Servicio(servicioDTO.getNombreServicio(), crearJornadasParaElServicioDTO(servicioDTO));
		return servicio;
	}

	private List<Jornada> crearJornadasParaElServicioDTO(ServicioDTO servicioDTO) {
		List<Jornada> jornadas = new ArrayList<Jornada>();
		servicioDTO.getRangosHorarios().forEach(rango -> jornadas.add(crearJornadaParaElRangoDTO(rango)));
		return jornadas;
	}

	private Jornada crearJornadaParaElRangoDTO(RangoServicioDTO rango) {
		RangoHorario rangoHorario = new RangoHorario(
				LocalTime.of(rango.getHorarioDesde(), rango.getMinutosDesde(), 0),
				LocalTime.of(rango.getHorarioHasta(), rango.getMinutosHasta(), 0));
		Jornada jornada = new Jornada(getDiaDeLaSemana(rango.getNumeroDia()), rangoHorario);
		return jornada;
	}

	private DayOfWeek getDiaDeLaSemana(int numeroDia) {
		Map<Integer, DayOfWeek> diasDeLaSemana = new HashMap<Integer, DayOfWeek>();
		diasDeLaSemana.put(1, DayOfWeek.MONDAY);
		diasDeLaSemana.put(2, DayOfWeek.TUESDAY);
		diasDeLaSemana.put(3, DayOfWeek.WEDNESDAY);
		diasDeLaSemana.put(4, DayOfWeek.THURSDAY);
		diasDeLaSemana.put(5, DayOfWeek.FRIDAY);
		diasDeLaSemana.put(6, DayOfWeek.SATURDAY);
		diasDeLaSemana.put(7, DayOfWeek.SUNDAY);
		return diasDeLaSemana.get(numeroDia);
		
	}
	
	// dato Hardcodeado en vez de 6 va: this.primerNumero(domicilio) + 1
	private int getDireccionNumero(String domicilio) {
		int numero = 0;
		numero = Integer.parseInt(domicilio.substring(this.primerNumero(domicilio)+1, (domicilio.length())));		
		return numero;
	}
	
	// Dato Hardcodeado para probar en vez de 5 va : this.primerNumero(domicilio)
	private String getDireccionNombre(String domicilio) {
		return domicilio.substring(0, this.primerNumero(domicilio));
		
	}
	
	public int primerNumero(String domicilio) {
		int i = 0;
		String numeros = "01234567890";
		while(!numeros.contains(domicilio.substring(i, i+1))){
			i = i +1;
		 }
		return i;
		
	} 
	
	private Comuna getComunaAsociadaAlNumero(int numero) {
		Map<Integer,Comuna> comunas = new HashMap<Integer, Comuna>();
		Comuna comuna16 = new Comuna();
		Point puntoAcomuna = new Point(10, 20);
		Point puntoBcomuna = new Point(20, 20);
		Point puntoCcomuna = new Point(15, 10);
		comuna16.setAreaDeComuna(puntoAcomuna);
		comuna16.setAreaDeComuna(puntoBcomuna);
		comuna16.setAreaDeComuna(puntoCcomuna);
		comunas.put(1, new Comuna());
		comunas.put(2, new Comuna());
		comunas.put(3, new Comuna());
		comunas.put(4, new Comuna());
		comunas.put(5, new Comuna());
		comunas.put(6, new Comuna());
		return comunas.get(numero);
	}
	
	

}
