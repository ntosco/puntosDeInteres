package ar.utn.dds.utils;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.uqbar.geodds.Point;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.POI;
//SACAR
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.extern.cgp.BuscadorDeCGP;
import ar.utn.dds.extern.cgp.CentroDTO;
import ar.utn.dds.extern.cgp.RangoServicioDTO;
import ar.utn.dds.extern.cgp.ServicioDTO;
import ar.utn.dds.servicios.Servicio;

public class AdapterCGP implements OrigenDeDatos {

	private BuscadorDeCGP servicioCGP;
	
	@Override
	public List<POI> buscarPOI(String nombre) {
		
			List<CentroDTO> listaDeCentroDTO = servicioCGP.buscarPOI(nombre);
			if (listaDeCentroDTO.isEmpty()){
				return new ArrayList<POI>();
			}else{
				
				List<POI> listaCGP = new ArrayList<POI>(); 	//Pongo que devuelva lista de POI's para que me compile
															//Se podria castear.				
				listaDeCentroDTO.forEach(dto -> listaCGP.add(convertirDTOACGP(dto)));			
				return listaCGP;
			}
		}
		
	public POI convertirDTOACGP(CentroDTO dto) {
		CentroGestionParticipacion nuevoCGP = new CentroGestionParticipacion();
		nuevoCGP.setNombre("comuna" + String.valueOf(dto.getComuna()));
		nuevoCGP.setUbicacionActual(ubicacionDeCGP(dto.getComuna()));
		nuevoCGP.setComuna(getComunaAsociadaAlNumero(dto.getComuna()));
		nuevoCGP.setBarrio(dto.getZonasIncluidas());
		nuevoCGP.setDireccionNombre(getDireccionNombre(dto.getDomicilio()));
		nuevoCGP.setDireccionNumero(getDireccionNumero(dto.getDomicilio()));
		nuevoCGP.setListaServicios(getListaServiciosCGP(dto.getServicios()));
		nuevoCGP.setListaPalabrasClave(palabrasClaveParaCGP(dto));
		return nuevoCGP;
	}
	
	public List<String> palabrasClaveParaCGP(CentroDTO dto) {
		StringTokenizer st = new StringTokenizer(dto.getZonasIncluidas(),",",true);
		List<String> listaPalabras = new ArrayList<String>();
		while(st.hasMoreTokens()){
			listaPalabras.add(st.nextToken());}
		dto.getServicios().forEach(servicio -> listaPalabras.add(servicio.getNombreServicio()));
		
		return listaPalabras;
		
		
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
				Jornada jornada = new Jornada();
				jornada.setDiaSemanal(getDiaDeLaSemana(rango.getNumeroDia()));
				jornada.setHorarioDeAtencion(rangoHorario);
		return jornada;
	}
	
	private Point ubicacionDeCGP(int numeroComuna) {
		Map<Integer, Point> puntos = new HashMap<Integer, Point>();
		puntos.put(1, new Point(10,20));
		puntos.put(2, new Point(1,2));
		puntos.put(3, new Point(15,13));
		puntos.put(4, new Point(21,14));
		puntos.put(5, new Point(10,7));
		puntos.put(6, new Point(10,27));
		return puntos.get(numeroComuna);
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
	
	private int getDireccionNumero(String domicilio) {
		int numero = 0;
		numero = Integer.parseInt(domicilio.substring(this.primerNumero(domicilio)+1, (domicilio.length())));		
		return numero;
	}
	
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
		Comuna comuna1 = new Comuna();
		Comuna comuna2 = new Comuna();
		Comuna comuna3 = new Comuna();
		Comuna comuna4 = new Comuna();
		Comuna comuna5 = new Comuna();
		Comuna comuna6 = new Comuna();
		comunas.put(1, comuna1);
		comunas.put(2, comuna2);
		comunas.put(3, comuna3);
		comunas.put(4, comuna4);
		comunas.put(5, comuna5);
		comunas.put(6, comuna6);
		return comunas.get(numero);
	}

	// ********************************************************
	// ** Getters and Setters
	// ********************************************************
	
	public BuscadorDeCGP getServicioCGP() {
		return servicioCGP;
	}

	public void setServicioCGP(BuscadorDeCGP servicioCGP) {
		this.servicioCGP = servicioCGP;
	}
}
