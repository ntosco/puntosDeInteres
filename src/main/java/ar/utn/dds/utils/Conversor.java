package ar.utn.dds.utils;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.ServicioExterno.CentroDTO;
import ar.utn.dds.ServicioExterno.RangoServicioDTO;
import ar.utn.dds.ServicioExterno.ServicioDTO;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.servicios.Servicio;

public class Conversor {
	
	public static CentroGestionParticipacion convertirDTOACGP(CentroDTO dto){
		CentroGestionParticipacion nuevoCGP = new CentroGestionParticipacion();
		nuevoCGP.setComuna(convertirAComuna(dto.getComuna()));
		nuevoCGP.setBarrio(dto.getZonasIncluidas());
		nuevoCGP.setDireccionNombre(getDireccionNombre(dto.getDomicilio()));
		nuevoCGP.setDireccionNumero(getDireccionNumero(dto.getDomicilio()));
		nuevoCGP.setListaServicios(getListaServiciosCGP(dto.getServicios()));
		return nuevoCGP;
	}
	
	private static List<Servicio> getListaServiciosCGP(List<ServicioDTO> servicios) {
		List<Servicio> lista = null; 
		servicios.forEach(servicio -> lista.add(crearSerivicioEnBaseAlString(servicio)));
		return lista;
	}

	private static Servicio crearSerivicioEnBaseAlString(ServicioDTO servicioDTO) {
		Servicio servicio = new Servicio(servicioDTO.getNombreServicio(), crearJornadasParaElServicioDTO(servicioDTO));
		return servicio;
	}

	private static List<Jornada> crearJornadasParaElServicioDTO(ServicioDTO servicioDTO) {
		List<Jornada> jornadas = null;
		servicioDTO.getRangosHorarios().forEach(rango -> jornadas.add(crearJornadaParaElRangoDTO(rango)));
		return jornadas;
	}

	private static Jornada crearJornadaParaElRangoDTO(RangoServicioDTO rango) {
		RangoHorario rangoHorario = new RangoHorario(
				LocalTime.of(rango.getHorarioDesde(), rango.getMinutosDesde(), 0),
				LocalTime.of(rango.getHorarioHasta(), rango.getMinutosHasta(), 0));
		Jornada jornada = new Jornada(getDiaDeLaSemana(rango.getNumeroDia()), rangoHorario);
		return jornada;
	}

	private static DayOfWeek getDiaDeLaSemana(int numeroDia) {
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

	private static int getDireccionNumero(String domicilio) {
		int numero = 0;
		numero = Integer.parseInt(domicilio);
		return numero;
	}

	//FIXME:  Hacer que solo devuelva el nombre de la calle
	private static String getDireccionNombre(String domicilio) {
		return domicilio;
	}


	//FIXME: Hacer que devuelva la comuna asociada a ese numero
	private static Comuna convertirAComuna(int comuna) {
		return new Comuna();
		
	}
	
	

}
