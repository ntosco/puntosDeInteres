package ar.utn.dds.utils;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

import ar.utn.dds.POI.CentroGestionParticipacion;
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
	
	// dato Hardcodeado en vez de 6 va: this.primerNumero(domicilio)
	private int getDireccionNumero(String domicilio) {
		int numero = 0;
		numero = Integer.parseInt(domicilio.substring(6, (domicilio.length())));		
		return numero;
	}
	
	// Dato Hardcodeado para probar en vez de 5 va : this.primerNumero(domicilio) - 1
	private String getDireccionNombre(String domicilio) {
		return domicilio.substring(0, 5);
		
	}
	
	public int primerNumero(String domicilio) {
		int i = 0;
		String numeros = "01234567890";
		while(!numeros.contains(domicilio.substring(i, i))){
			i = i +1;
		 }
		return i;
		
	} 

	private Comuna convertirAComuna(int numero) {
		return getComunaAsociadaAlNumero(numero);
		
	}

	
		// Datos Hardcodeados para probar
	private Comuna getComunaAsociadaAlNumero(int numero) {
		Map<Integer,Comuna> comunas = new HashMap<Integer, Comuna>();
		Comuna comuna16 = new Comuna();
		Point puntoAcomuna = new Point(10, 20);
		Point puntoBcomuna = new Point(20, 20);
		Point puntoCcomuna = new Point(15, 10);
		comuna16.setAreaDeComuna(puntoAcomuna);
		comuna16.setAreaDeComuna(puntoBcomuna);
		comuna16.setAreaDeComuna(puntoCcomuna);
		comunas.put(1, comuna16);
		comunas.put(2, comuna2);
		comunas.put(3, comuna3);
		comunas.put(4, comuna4);
		comunas.put(5, comuna5);
		comunas.put(6, comuna6);
		return comunas.get(numero);
	}
	
	

}
