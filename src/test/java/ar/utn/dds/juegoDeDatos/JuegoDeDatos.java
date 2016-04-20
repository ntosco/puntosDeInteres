package ar.utn.dds.juegoDeDatos;

import static org.junit.Assert.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
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

	private Point puntoTerminal;
	private Point puntoTerminal2;

	// Servicios

	private Servicio servicioCP;
	private Servicio rentas;
	private Servicio asesoramientoLegal;
	private Servicio pagoDeFacturas;
	private Servicio asesoramientoTecnico;

	List<Servicio> servicioAsesoramientoLegalyPagoFacturas;
	List<Servicio> servicioAsesoramientoLegalYTecnico;
	List<Servicio> servicioCPyRentas;
	List<Servicio> servicioTecnico;

	// Rubros

	private Rubro indumentaria;
	private Rubro muebleria;
	private Rubro perfumeria;

	List<Rubro> rubrosIndumentariaMuebleriaPerfumeria;
	List<Rubro> rubroIndumentaria;

	// Colectivos

	private ParadaDeColectivo parada15;
	private ParadaDeColectivo parada114;
	private ParadaDeColectivo parada11;
	private ParadaDeColectivo parada7Rojo;
	private ParadaDeColectivo parada7Amarillo;

	private Point ubicacionParada15;
	private Point ubicacionParada114;
	private Point ubicacionParada11;
	private Point ubicacionParada7Rojo;
	private Point ubicacionParada7Amarillo;

	List<String> paradaDel7Amarillo;
	List<String> paradaDel7Rojo;
	List<String> paradaDel11;
	List<String> paradaDel114;
	List<String> paradaDel15;

	// CGPS

	private CentroGestionParticipacion cgpCaballito;
	private CentroGestionParticipacion cgpAlmagro;
	private CentroGestionParticipacion cgpPalermo;

	private Point ubicacionCGPCaballito;
	private Point ubicacionCGPAlmagro;
	private Point ubicacionCGPPalermo;

	// comunas

	private Comuna comuna1;
	private Comuna comuna2;
	private Point punto1comuna;
	private Point punto2comuna;
	private Point punto3comuna;
	private Point punto4comuna;
	private Point punto5comuna;
	private Point punto6comuna;

	// Locales comerciales

	private LocalComercial nike;// solo un rubro: indumentaria
	private Point ubicacionLocalNike;
	private List<String> listaPalabrasClaveNike;

	private LocalComercial fallabella;
	private Point ubicacionLocalFallabella;
	private List<String> listaPalabrasClaveFallabella;

	private LocalComercial cafeMartinez;
	private Point ubicacionLocalCafeMartinez;
	private List<String> palabrasClaveCafeMartinez;
	
	private LocalComercial addidas;
	private Point ubicacionLocalAddidas;

	private LocalComercial panquequesCarlitos;
	private Point ubicacionLocalPanquequesCarlitos;

	// Bancos

	private SucursalBanco sucursalRetiro;
	private SucursalBanco sucursalMartinez;

	private Point ubicacionSucursalRetiro;
	private Point ubicacionSucursalMartinez;

	// Jornadas

	List<Jornada> jornadaInversa;
	List<Jornada> jornadaNormal;

	public void setUp() {

	}

	public void setUpBanco() {

		sucursalRetiro = new SucursalBanco();
		sucursalRetiro.setListaServicios(servicioCPyRentas);
		sucursalRetiro.setUbicacionActual(ubicacionSucursalRetiro);

		sucursalMartinez = new SucursalBanco();
		sucursalMartinez
				.setListaServicios(servicioAsesoramientoLegalyPagoFacturas);
		sucursalMartinez.setUbicacionActual(ubicacionSucursalMartinez);
	}

	public void sepUpJornadas() {

		RangoHorario rangolaboral_10a20 = new RangoHorario(LocalTime.of(10, 0,
				0), LocalTime.of(20, 0, 0));
		RangoHorario rangolaboral_13a15 = new RangoHorario(LocalTime.of(13, 0,
				0), LocalTime.of(15, 0, 0));
		Jornada jornadaLaboral_Lunes_10a20 = new Jornada(DayOfWeek.MONDAY,
				rangolaboral_10a20);
		Jornada jornadaLaboral_Jueves_13a15 = new Jornada(DayOfWeek.TUESDAY,
				rangolaboral_13a15);
		jornadaNormal = new ArrayList<>();
		jornadaNormal.add(jornadaLaboral_Lunes_10a20);
		jornadaNormal.add(jornadaLaboral_Jueves_13a15);

		RangoHorario rangolaboral_17a20 = new RangoHorario(LocalTime.of(17, 0,
				0), LocalTime.of(20, 0, 0));
		Jornada jornadaLaboral_Sabado_13a15 = new Jornada(DayOfWeek.SATURDAY,
				rangolaboral_13a15);
		Jornada jornadaLaboral_Jueves_17a20 = new Jornada(DayOfWeek.TUESDAY,
				rangolaboral_17a20);
		jornadaInversa = new ArrayList<>();
		jornadaInversa.add(jornadaLaboral_Sabado_13a15);
		jornadaInversa.add(jornadaLaboral_Jueves_17a20);

	}

	public void setUpServicios() {

		pagoDeFacturas = new Servicio("Pago de facturas", jornadaNormal);
		asesoramientoLegal = new Servicio("Asesoramiento Legal", jornadaNormal);
		asesoramientoTecnico = new Servicio("Asesoramieno Tecnico",
				jornadaNormal);
		rentas = new Servicio("rentas", jornadaInversa);
		servicioCP = new Servicio("servicio CP", jornadaInversa);

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

		ubicacionLocalCafeMartinez = new Point(10.003, 20);
		ubicacionLocalAddidas = new Point(14, 22);
		ubicacionLocalPanquequesCarlitos = new Point(10, 20);
		ubicacionLocalNike = new Point(10, 20);
		ubicacionLocalFallabella = new Point(10, 15);

		ubicacionSucursalRetiro = new Point(13, 14);
		ubicacionSucursalMartinez = new Point(14, 18);

		ubicacionCGPAlmagro = new Point(11, 16);
		ubicacionCGPCaballito = new Point(8, 10);
		ubicacionCGPPalermo = new Point(15, 15);

	}

	public void setUpLocalComercial() {
		setUpUbicaciones();

		nike = new LocalComercial();
		fallabella = new LocalComercial();
		cafeMartinez = new LocalComercial();
		addidas = new LocalComercial();
		panquequesCarlitos = new LocalComercial();

		cafeMartinez.setUbicacionActual(ubicacionLocalCafeMartinez);
		addidas.setUbicacionActual(ubicacionLocalAddidas);
		panquequesCarlitos.setUbicacionActual(ubicacionLocalPanquequesCarlitos);
		nike.setUbicacionActual(ubicacionLocalNike);
		fallabella.setUbicacionActual(ubicacionLocalFallabella);

	}

	public void setUpCGP() {

		setUpComunas();
		setUpServicios();

		cgpCaballito = new CentroGestionParticipacion();
		cgpCaballito.setUbicacionActual(ubicacionCGPCaballito);
		cgpCaballito.setComuna(comuna1);
		cgpCaballito.setListaServicios(servicioCPyRentas);

		cgpAlmagro = new CentroGestionParticipacion();
		cgpAlmagro.setUbicacionActual(ubicacionCGPAlmagro);
		cgpAlmagro.setComuna(comuna2);
		cgpAlmagro.setListaServicios(servicioCPyRentas);

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

		comuna1.setAreaDeComuna(punto1comuna);
		comuna1.setAreaDeComuna(punto2comuna);
		comuna1.setAreaDeComuna(punto3comuna);
		
		comuna2.setAreaDeComuna(punto2comuna);
		comuna2.setAreaDeComuna(punto2comuna);
		comuna2.setAreaDeComuna(punto2comuna);
	}

	public void setUpRubro() {

		indumentaria = new Rubro("indumentaria", 0.2);
		muebleria = new Rubro("muebleria", 0.3);
		perfumeria = new Rubro("perfumeria", 0.1);

		rubroIndumentaria = new ArrayList<Rubro>();
		rubroIndumentaria.add(indumentaria);
		nike.setListaRubros(rubroIndumentaria);

		rubrosIndumentariaMuebleriaPerfumeria = new ArrayList<Rubro>();
		rubrosIndumentariaMuebleriaPerfumeria.add(indumentaria);
		rubrosIndumentariaMuebleriaPerfumeria.add(muebleria);
		rubrosIndumentariaMuebleriaPerfumeria.add(perfumeria);
		fallabella.setListaRubros(rubrosIndumentariaMuebleriaPerfumeria);

	}

	public void setUpPalabrasClave() {

		listaPalabrasClaveNike = new ArrayList<String>();
		listaPalabrasClaveNike.add("futbol");
		listaPalabrasClaveNike.add("basket");
		listaPalabrasClaveNike.add("running");
		listaPalabrasClaveNike.add("tenis");
		nike.setListaPalabrasClave(listaPalabrasClaveNike);

		listaPalabrasClaveFallabella = new ArrayList<String>();
		listaPalabrasClaveFallabella.add("new port");
		listaPalabrasClaveFallabella.add("levis");
		listaPalabrasClaveFallabella.add("running");
		fallabella.setListaPalabrasClave(listaPalabrasClaveFallabella);
		
		palabrasClaveCafeMartinez = new ArrayList<String>();
		palabrasClaveCafeMartinez.add("cafe");
		palabrasClaveCafeMartinez.add("martinez");
		palabrasClaveCafeMartinez.add("desayuno");
		palabrasClaveCafeMartinez.add("merienda");
		cafeMartinez.setListaPalabrasClave(palabrasClaveCafeMartinez);
		
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

		puntoTerminal = new Point(10, 20);
		puntoTerminal2 = new Point(1000, 2000);
	}

	public void setUpLocales() {

	}

	@Test
	public void testColectivoParaprobarQueAnda() {
		setUpColectivos();
		assertEquals(parada11.getLinea(), "11");
	}
}
