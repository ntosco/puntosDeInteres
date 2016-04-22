package ar.utn.dds.juegoDeDatos;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

abstract public class JuegoDeDatos {

	public Point puntoTerminal;
	public Point puntoTerminal2;

	// Servicios

	public Servicio servicioCP;
	public Servicio rentas;
	public Servicio asesoramientoLegal;
	public Servicio pagoDeFacturas;
	public Servicio asesoramientoTecnico;

	public List<Servicio> servicioAsesoramientoLegalyPagoFacturas;
	public List<Servicio> servicioAsesoramientoLegalYTecnico;
	public List<Servicio> servicioCPyRentas;
	public List<Servicio> servicioTecnico;

	// Rubros

	public Rubro indumentaria;
	public Rubro muebleria;
	public Rubro perfumeria;
	public Rubro cafeteria;
	public Rubro comidas;
	
	public List<Rubro> rubrosIndumentariaMuebleriaPerfumeria;
	public List<Rubro> rubroIndumentaria;
	public List<Rubro> cafeteriaYComidas;

	// Colectivos

	public ParadaDeColectivo parada15;
	public ParadaDeColectivo parada114;
	public ParadaDeColectivo parada11;
	public ParadaDeColectivo parada7Rojo;
	public ParadaDeColectivo parada7Amarillo;

	public Point ubicacionParada15;
	public Point ubicacionParada114;
	public Point ubicacionParada11;
	public Point ubicacionParada7Rojo;
	public Point ubicacionParada7Amarillo;

	public List<String> paradaDel7Amarillo;
	public List<String> paradaDel7Rojo;
	public List<String> paradaDel11;
	public List<String> paradaDel114;
	public List<String> paradaDel15;

	// CGPS

	public CentroGestionParticipacion cgpCaballito;
	public CentroGestionParticipacion cgpAlmagro;
	public CentroGestionParticipacion cgpPalermo;

	public Point ubicacionCGPCaballito;
	public Point ubicacionCGPAlmagro;
	public Point ubicacionCGPPalermo;
	
	public List<String> palabrasClaveCGPAlmagro;

	// comunas

	public Comuna comuna1;
	public Comuna comuna2;
	public Point punto1comuna;
	public Point punto2comuna;
	public Point punto3comuna;
	public Point punto4comuna;
	public Point punto5comuna;
	public Point punto6comuna;

	// Locales comerciales

	public LocalComercial nike;// solo un rubro: indumentaria
	public Point ubicacionLocalNike;
	public List<String> listaPalabrasClaveNike;

	public LocalComercial fallabella;
	public Point ubicacionLocalFallabella;
	public List<String> listaPalabrasClaveFallabella;

	public LocalComercial cafeMartinez;
	public Point ubicacionLocalCafeMartinez;
	public List<String> palabrasClaveCafeMartinez;
	
	public LocalComercial addidas;
	public Point ubicacionLocalAddidas;

	public LocalComercial panquequesCarlitos;
	public Point ubicacionLocalPanquequesCarlitos;

	// Bancos

	public SucursalBanco sucursalRetiro;
	public SucursalBanco sucursalMartinez;

	public Point ubicacionSucursalRetiro;
	public Point ubicacionSucursalMartinez;
	
	public List<String> palabrasClaveBancoRetiro;

	// Jornadas

	public List<Jornada> jornadaBancaria;
	public List<Jornada> jornadaNormalLunesAViernes;
	
	// LOCAL DATE TIME
	
	public LocalDateTime lunes1210hs;
	public LocalDateTime lunes23hs;
	public LocalDateTime martes04hs;
	public LocalDateTime sabado1210hs ;
	public LocalDateTime lunes12hs ;
	public LocalDateTime jueves11hs ;
	public LocalDateTime sabado23hs;
	

	public void setUpGeneral() {
		setUpLocalDateTime();
		setUpJornadas();
		setUpServicios();
		setUpUbicaciones();
		setUpComunas();
		setUpRubro();
		setUpPuntos();
	}

	public void setUpBanco() {

		sucursalRetiro = new SucursalBanco();
		sucursalRetiro.setListaServicios(servicioCPyRentas);
		sucursalRetiro.setUbicacionActual(ubicacionSucursalRetiro);
		sucursalRetiro.setListaServicios(servicioAsesoramientoLegalyPagoFacturas);
		palabrasClaveBancoRetiro = new ArrayList<String>();
		palabrasClaveBancoRetiro.add("Rentas");
		palabrasClaveBancoRetiro.add("Pago de facturas");
		sucursalRetiro.setListaPalabrasClave(palabrasClaveBancoRetiro);

		sucursalMartinez = new SucursalBanco();
		sucursalMartinez.setListaServicios(servicioAsesoramientoLegalyPagoFacturas);
		sucursalMartinez.setUbicacionActual(ubicacionSucursalMartinez);
		sucursalMartinez.setListaServicios(servicioAsesoramientoLegalyPagoFacturas);
	}

	public void setUpJornadas() {

		RangoHorario rangolaboral_10a20 = new RangoHorario(LocalTime.of(10, 0,0), LocalTime.of(20, 0, 0));
		RangoHorario rangolaboral_9a13 = new RangoHorario(LocalTime.of(9, 0,0), LocalTime.of(13, 0, 0));
		Jornada jornadaLaboral_Lunes_10a20 = new Jornada(DayOfWeek.MONDAY,rangolaboral_10a20);
		Jornada jornadaLaboral_Martes_10a20 = new Jornada(DayOfWeek.TUESDAY,rangolaboral_10a20);
		Jornada jornadaLaboral_Miercoles_10a20 = new Jornada(DayOfWeek.WEDNESDAY,rangolaboral_10a20);
		Jornada jornadaLaboral_Jueves_13a15 = new Jornada(DayOfWeek.THURSDAY,rangolaboral_10a20);
		Jornada jornadaLaboral_Viernes_10a20 = new Jornada(DayOfWeek.FRIDAY,rangolaboral_10a20);
		Jornada jornadaLaboral_Sabado_10a13 = new Jornada(DayOfWeek.SATURDAY,rangolaboral_9a13);
		jornadaNormalLunesAViernes = new ArrayList<>();
		jornadaNormalLunesAViernes.add(jornadaLaboral_Lunes_10a20);
		jornadaNormalLunesAViernes.add(jornadaLaboral_Martes_10a20);
		jornadaNormalLunesAViernes.add(jornadaLaboral_Miercoles_10a20);
		jornadaNormalLunesAViernes.add(jornadaLaboral_Jueves_13a15);
		jornadaNormalLunesAViernes.add(jornadaLaboral_Viernes_10a20);
		jornadaNormalLunesAViernes.add(jornadaLaboral_Sabado_10a13);
		
		RangoHorario rangoBancario = new RangoHorario(LocalTime.of(10, 0, 0),LocalTime.of(15, 0, 0));
		Jornada jornadaBancariaDe10a15LUNES = new Jornada(DayOfWeek.MONDAY, rangoBancario);
		Jornada jornadaBancariaDe10a15MARTES = new Jornada(DayOfWeek.TUESDAY, rangoBancario);
		Jornada jornadaBancariaDe10a15MIERCOLES = new Jornada(DayOfWeek.WEDNESDAY, rangoBancario);
		Jornada jornadaBancariaDe10a15JUEVES = new Jornada(DayOfWeek.THURSDAY, rangoBancario);
		Jornada jornadaBancariaDe10a15VIERNES = new Jornada(DayOfWeek.FRIDAY, rangoBancario);
		ArrayList<Jornada> jornadaBancaria = new ArrayList<>();
		jornadaBancaria.add(jornadaBancariaDe10a15LUNES);
		jornadaBancaria.add(jornadaBancariaDe10a15MARTES);
		jornadaBancaria.add(jornadaBancariaDe10a15MIERCOLES);
		jornadaBancaria.add(jornadaBancariaDe10a15JUEVES);
		jornadaBancaria.add(jornadaBancariaDe10a15VIERNES);
		

	}

	public void setUpServicios() {

		pagoDeFacturas = new Servicio("Pago de facturas", jornadaNormalLunesAViernes);
		asesoramientoLegal = new Servicio("Asesoramiento Legal", jornadaNormalLunesAViernes);
		asesoramientoTecnico = new Servicio("Asesoramieno Tecnico",	jornadaNormalLunesAViernes);
		rentas = new Servicio("rentas", jornadaBancaria);
		servicioCP = new Servicio("servicio CP", jornadaBancaria);

		servicioAsesoramientoLegalYTecnico = new ArrayList<Servicio>();
		servicioAsesoramientoLegalYTecnico.add(asesoramientoLegal);
		servicioAsesoramientoLegalYTecnico.add(asesoramientoTecnico);

		servicioAsesoramientoLegalyPagoFacturas = new ArrayList<Servicio>();
		servicioAsesoramientoLegalyPagoFacturas.add(asesoramientoLegal);
		servicioAsesoramientoLegalyPagoFacturas.add(pagoDeFacturas);

		servicioCPyRentas = new ArrayList<Servicio>();
		servicioCPyRentas.add(servicioCP);
		servicioCPyRentas.add(rentas);

	}

	public void setUpUbicaciones() {

		ubicacionLocalCafeMartinez = new Point(10.002, 15);
		ubicacionLocalAddidas = new Point(14, 22);
		ubicacionLocalPanquequesCarlitos = new Point(10, 20);
		ubicacionLocalNike = new Point(10, 20);

		ubicacionSucursalRetiro = new Point(10, 15);
		ubicacionSucursalMartinez = new Point(14, 18);

		ubicacionCGPAlmagro = new Point(10, 15.005);
		ubicacionCGPCaballito = new Point(8, 10);
		ubicacionCGPPalermo = new Point(15, 15);

	}

	public void setUpLocalComercial() {

		nike = new LocalComercial();
		nike.setUbicacionActual(ubicacionLocalNike);
		nike.setListaRubros(rubroIndumentaria);
		nike.setListaPalabrasClave(listaPalabrasClaveNike);
		
		fallabella = new LocalComercial();
		ubicacionLocalFallabella = new Point(10, 15);
		fallabella.setUbicacionActual(ubicacionLocalFallabella);
		fallabella.setJornadaDisponible(jornadaNormalLunesAViernes);
		fallabella.setListaRubros(rubrosIndumentariaMuebleriaPerfumeria);
		fallabella.setListaPalabrasClave(listaPalabrasClaveFallabella);

		
		cafeMartinez = new LocalComercial();
		cafeMartinez.setUbicacionActual(ubicacionLocalCafeMartinez);
		cafeMartinez.setJornadaDisponible(jornadaNormalLunesAViernes);
		cafeMartinez.setListaPalabrasClave(palabrasClaveCafeMartinez);
		cafeMartinez.setListaRubros(cafeteriaYComidas);

		
		addidas = new LocalComercial();
		addidas.setUbicacionActual(ubicacionLocalAddidas);
		
		panquequesCarlitos = new LocalComercial();
		panquequesCarlitos.setUbicacionActual(ubicacionLocalPanquequesCarlitos);


	}

	public void setUpCGP() {

		cgpCaballito = new CentroGestionParticipacion();
		cgpCaballito.setUbicacionActual(ubicacionCGPCaballito);
		cgpCaballito.setComuna(comuna2);
		cgpCaballito.setListaServicios(servicioCPyRentas);

		cgpAlmagro = new CentroGestionParticipacion();
		cgpAlmagro.setUbicacionActual(ubicacionCGPAlmagro);
		cgpAlmagro.setComuna(comuna1);
		cgpAlmagro.setListaServicios(servicioCPyRentas);
		palabrasClaveCGPAlmagro = new ArrayList<String>();
		palabrasClaveCGPAlmagro.add("almagro");
		palabrasClaveCGPAlmagro.add("CP");
		palabrasClaveCGPAlmagro.add("Rentas");
		cgpAlmagro.setListaPalabrasClave(palabrasClaveCGPAlmagro);
		cgpAlmagro.setJornadaDisponible(jornadaNormalLunesAViernes);

		cgpPalermo = new CentroGestionParticipacion();
		cgpPalermo.setUbicacionActual(ubicacionCGPPalermo);
		cgpPalermo.setComuna(comuna1);
		cgpPalermo.setListaServicios(servicioAsesoramientoLegalyPagoFacturas);

	}

	public void setUpComunas() {

		comuna1 = new Comuna();
		comuna2 = new Comuna();

		punto1comuna = new Point(11, 20);
		punto2comuna = new Point(10.005, 20.001);
		punto3comuna = new Point(10.009, 20.005);
		
		punto4comuna = new Point(10,20);
		punto5comuna = new Point(20,20);
		punto6comuna = new Point(15,10);

		comuna1.setAreaDeComuna(punto1comuna);
		comuna1.setAreaDeComuna(punto2comuna);
		comuna1.setAreaDeComuna(punto3comuna);
		
		comuna2.setAreaDeComuna(punto4comuna);
		comuna2.setAreaDeComuna(punto5comuna);
		comuna2.setAreaDeComuna(punto6comuna);
	}

	public void setUpRubro() {

		indumentaria = new Rubro("indumentaria", 0.2);
		muebleria = new Rubro("muebleria", 0.3);
		perfumeria = new Rubro("perfumeria", 0.1);
		cafeteria = new Rubro("cafeteria",0.2);
		comidas = new Rubro("comidas", 0.1);
		
		cafeteriaYComidas = new ArrayList<Rubro>();
		cafeteriaYComidas.add(cafeteria);
		cafeteriaYComidas.add(comidas);
		
		rubroIndumentaria = new ArrayList<Rubro>();
		rubroIndumentaria.add(indumentaria);

		rubrosIndumentariaMuebleriaPerfumeria = new ArrayList<Rubro>();
		rubrosIndumentariaMuebleriaPerfumeria.add(indumentaria);
		rubrosIndumentariaMuebleriaPerfumeria.add(muebleria);
		rubrosIndumentariaMuebleriaPerfumeria.add(perfumeria);

	}

	public void setUpPalabrasClave() {

		listaPalabrasClaveNike = new ArrayList<String>();
		listaPalabrasClaveNike.add("futbol");
		listaPalabrasClaveNike.add("basket");
		listaPalabrasClaveNike.add("running");
		listaPalabrasClaveNike.add("tenis");

		listaPalabrasClaveFallabella = new ArrayList<String>();
		listaPalabrasClaveFallabella.add("new port");
		listaPalabrasClaveFallabella.add("levis");
		listaPalabrasClaveFallabella.add("running");
		
		palabrasClaveCafeMartinez = new ArrayList<String>();
		palabrasClaveCafeMartinez.add("cafe");
		palabrasClaveCafeMartinez.add("martinez");
		palabrasClaveCafeMartinez.add("desayuno");
		palabrasClaveCafeMartinez.add("merienda");
		
		
		
		
		
	}

	public void setUpColectivos() {

		parada15 = new ParadaDeColectivo();
		ubicacionParada15 = new Point(10.0008, 20);
		parada15.setUbicacionActual(ubicacionParada15);
		parada15.setLinea("15");
		List<String> paradaDel15 = new ArrayList<String>();
		paradaDel15.add("15");

		parada114 = new ParadaDeColectivo();
		ubicacionParada114 = new Point(11, 13);
		parada114.setLinea("114");
		parada114.setUbicacionActual(ubicacionParada114);
		List<String> paradaDel114 = new ArrayList<String>();
		paradaDel114.add("114");

		parada11 = new ParadaDeColectivo();
		ubicacionParada11 = new Point(12, 18);
		parada11.setLinea("11");
		parada11.setUbicacionActual(ubicacionParada11);
		List<String> paradaDel11 = new ArrayList<String>();
		paradaDel11.add("11");

		parada7Rojo = new ParadaDeColectivo();
		ubicacionParada7Rojo = new Point(11, 14);
		parada7Rojo.setLinea("7 Barrio Samore");
		parada7Rojo.setUbicacionActual(ubicacionParada7Rojo);
		List<String> paradaDel7Rojo = new ArrayList<String>();
		paradaDel7Rojo.add("7");
		paradaDel7Rojo.add("rojo");

		parada7Amarillo = new ParadaDeColectivo();
		ubicacionParada7Amarillo = new Point(11, 13);
		parada7Amarillo.setLinea("7 Avellaneda");
		parada7Amarillo.setUbicacionActual(ubicacionParada7Amarillo);
		List<String> paradaDel7Amarillo = new ArrayList<String>();
		paradaDel7Amarillo.add("7");
		paradaDel7Amarillo.add("amarillo");

	}

	public void setUpPuntos() {

		puntoTerminal = new Point(10, 15);
		puntoTerminal2 = new Point(1000, 2000);
	}
	 public void setUpLocalDateTime(){
		 
		lunes1210hs =LocalDateTime.of(2016,4,11,12,10,00);
		lunes23hs= LocalDateTime.of(2016,4,11,23,10,00);
		martes04hs = LocalDateTime.of(2016,4,21,04,00,00);
		lunes12hs =LocalDateTime.of(2016,4,11,12,00,00);
		jueves11hs = LocalDateTime.of(2016,4,7,11,00,00);
		sabado1210hs = LocalDateTime.of(2016,4,2,12,10,00);
		sabado23hs = LocalDateTime.of(2016,4,2,23,00,00);
	 }
}
