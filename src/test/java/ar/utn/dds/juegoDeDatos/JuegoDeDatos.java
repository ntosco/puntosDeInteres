package ar.utn.dds.juegoDeDatos;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.ServicioExterno.CentroDTO;
import ar.utn.dds.ServicioExterno.RangoServicioDTO;
import ar.utn.dds.ServicioExterno.ServicioDTO;
import ar.utn.dds.buscador.buscadorDeBancos;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;


abstract public class JuegoDeDatos {

	//Buscador de bancos
	
	protected buscadorDeBancos buscadorBancos;
	
	protected Point puntoTerminal;
	protected Point puntoTerminal2;

	// Servicios

	protected Servicio servicioCP;
	protected Servicio rentas;
	protected Servicio asesoramientoLegal;
	protected Servicio pagoDeFacturas;
	protected Servicio asesoramientoTecnico;
	protected Servicio extracciones;
	protected Servicio depositos;
	protected Servicio consultarSaldo;

	protected List<Servicio> servicioAsesoramientoLegalyPagoFacturas;
	protected List<Servicio> servicioAsesoramientoLegalYTecnico;
	protected List<Servicio> servicioCPyRentas;
	protected List<Servicio> servicioTecnico;
	protected List<Servicio> servicioCajeroAutomatico;

	// Rubros

	protected Rubro indumentaria;
	protected Rubro muebleria;
	protected Rubro perfumeria;
	protected Rubro cafeteria;
	protected Rubro comidas;
	protected Rubro farmacia;
	protected Rubro libreriaRubro;

	protected List<Rubro> rubrosIndumentariaMuebleriaPerfumeria;
	protected List<Rubro> rubroIndumentaria;
	protected List<Rubro> cafeteriaYComidas;
	protected List<Rubro> farmaciaYperfumeria;
	protected List<Rubro> rubroLibreria;
	protected List<Rubro> rubroComidas;
	// Colectivos

	protected ParadaDeColectivo parada110Paternal;
	protected ParadaDeColectivo parada110LaBoca;
	protected ParadaDeColectivo parada15;
	protected ParadaDeColectivo parada114;
	protected ParadaDeColectivo parada11;
	protected ParadaDeColectivo parada7Rojo;
	protected ParadaDeColectivo parada7Amarillo;
	protected ParadaDeColectivo parada60;// 12 y 60 comparten cartel de la
											// parada
	protected ParadaDeColectivo parada12;// van a ser 2 pois con la mimma
											// ubicacion

	protected Point ubicacionParada110Paternal;
	protected Point ubicacionParada110LaBoca;
	protected Point ubicacionParada15;
	protected Point ubicacionParada114;
	protected Point ubicacionParada11;
	protected Point ubicacionParada7Rojo;
	protected Point ubicacionParada7Amarillo;
	protected Point ubicacionParada60;

	protected List<String> paradaDel10Paternal;
	protected List<String> paradaDel10PLaBoca;
	protected List<String> paradaDel7Amarillo;
	protected List<String> paradaDel7Rojo;
	protected List<String> paradaDel11;
	protected List<String> paradaDel114;
	protected List<String> paradaDel15;
	protected List<String> palabrasClave60;
	protected List<String> palabrasClave12;
	protected List<String> palabrasClave110Paternal;
	protected List<String> palabrasClave110LaBoca;

	// CGPS

	protected CentroGestionParticipacion cgpPaternal;
	protected CentroGestionParticipacion cgpLaBoca;
	protected CentroGestionParticipacion cgpNunez;
	protected CentroGestionParticipacion cgpBoedo;
	protected CentroGestionParticipacion cgpCaballito;
	protected CentroGestionParticipacion cgpAlmagro;
	protected CentroGestionParticipacion cgpPalermo;

	protected Point ubicacionCGPPaternal;
	protected Point ubicacionCGPLaBoca;
	protected Point ubicacionCGPNunez;
	protected Point ubicacionCGPBoedo;
	protected Point ubicacionCGPCaballito;
	protected Point ubicacionCGPAlmagro;
	protected Point ubicacionCGPPalermo;

	protected List<String> palabrasClaveCGPAlmagro;
	protected List<String> palabrasClaveCGPLaBoca;
	protected List<String> palabrasClaveCGPPaternal;
	protected List<String> palabrasClaveCGPNunez;
	protected List<String> palabrasClaveCGPBoedo;

	// comunas

	protected Comuna comuna1;
	protected Comuna comuna2;
	protected Comuna comuna3;
	protected Comuna comuna4;
	protected Comuna comuna5;
	protected Comuna comuna6;
	protected Point punto1comuna;
	protected Point punto2comuna;
	protected Point punto3comuna;
	protected Point punto4comuna;
	protected Point punto5comuna;
	protected Point punto6comuna;
	protected Point punto7comuna;
	protected Point punto8comuna;
	protected Point punto9comuna;
	protected Point punto10comuna;
	protected Point punto11comuna;
	protected Point punto12comuna;

	// Locales comerciales

	protected LocalComercial heinsenburger;
	protected Point ubicacionHeinsenbuger;
	protected List<String> listaPalabrasClaveHeinsenburger;

	protected LocalComercial morita;
	protected Point ubicacionMorita;
	protected List<String> listaPalabrasClaveMorita;

	protected LocalComercial zapateria;
	protected Point ubicacionZapateria;
	protected List<String> listaPalabrasClaveZapateria;

	protected LocalComercial libreria;
	protected Point ubicacionLibreria;
	protected List<String> listaPalabrasClaveLibreria;

	protected LocalComercial farmacity;
	protected Point ubicacionFarmacity;
	protected List<String> listaPalabrasClaveFarmacity;

	protected LocalComercial nike;// solo un rubro: indumentaria
	protected Point ubicacionLocalNike;
	protected List<String> listaPalabrasClaveNike;

	protected LocalComercial fallabella;
	protected Point ubicacionLocalFallabella;
	protected List<String> listaPalabrasClaveFallabella;

	protected LocalComercial cafeMartinez;
	protected Point ubicacionLocalCafeMartinez;
	protected List<String> palabrasClaveCafeMartinez;

	protected LocalComercial addidas;
	protected Point ubicacionLocalAddidas;

	protected LocalComercial panquequesCarlitos;
	protected Point ubicacionLocalPanquequesCarlitos;

	// Bancos
	protected SucursalBanco sucursalPalermo;

	protected SucursalBanco sucursalRetiro;
	protected SucursalBanco sucursalMartinez;
	protected SucursalBanco bancoFrances;
	protected SucursalBanco bancoGalicia;
	protected SucursalBanco bancoRio;

	protected Point ubicacionSucursalRetiro;
	protected Point ubicacionSucursalMartinez;
	protected Point ubicacionSucursalFrances;
	protected Point ubicacionSucursalGalicia;
	protected Point ubicacionSucursalRio;

	protected List<String> palabrasClaveBancoRetiro;
	protected List<String> palabrasClaveBancoFrances;
	protected List<String> palabrasClaveBancoGalicia;
	protected List<String> palabrasClaveBancoRio;

	protected JSONObject bancoFrances2;
	protected JSONObject bancoGalicia2;
	
	
	
	// Jornadas

	protected List<Jornada> jornadaBancaria;
	protected List<Jornada> jornadaNormalLunesAViernes;
	protected List<Jornada> noche;// los 7 dias
	protected List<Jornada> manana;// los 7 dias
	protected List<Jornada> tarde;// los 7 dias
	protected List<Jornada> jornada24x7;// 24 hs los 7 dias

	// LOCAL DATE TIME

	protected LocalDateTime lunes1210hs;
	protected LocalDateTime lunes23hs;
	protected LocalDateTime martes04hs;
	protected LocalDateTime sabado1210hs;
	protected LocalDateTime lunes12hs;
	protected LocalDateTime jueves11hs;
	protected LocalDateTime sabado23hs;
	
	// Rangos Horarios
	
	protected RangoHorario rangolaboral_10a20;
	protected RangoHorario rango24hs;
	protected RangoHorario rangomanana;
	protected RangoHorario rangoTarde;
	protected RangoHorario rangoNoche;
	protected RangoHorario rangoBancario;
	
	// Entrega 2
	
	// Dto
	
	protected List<CentroDTO> centrosDTO;
	protected List<ServicioDTO> serviciosDTO;
	protected List<RangoServicioDTO> rangosDTO;
	
	protected RangoServicioDTO rangoDe9a18;
	protected ServicioDTO servicioRentasDTO;
	protected CentroDTO centroRecoleta;
	
	//

	public void setUpGeneral() {
		setUpLocalDateTime();
		setUpJornadas();
		setUpServicios();
		setUpUbicaciones();
		setUpComunas();
		setUpRubro();
		setUpPuntos();
		setUpPalabrasClave();
	}	
	
	public void setUpBanco() {
		// galicia y frances comparten ubicacion
		bancoFrances = new SucursalBanco();
		bancoFrances.setNombre("Banco Frances");
		
		bancoFrances.setListaServicios(servicioCajeroAutomatico);
		bancoFrances.setUbicacionActual(ubicacionSucursalFrances);
		palabrasClaveBancoFrances = new ArrayList<String>();
		palabrasClaveBancoFrances.add("deposito");
		palabrasClaveBancoFrances.add("extraccion");
		palabrasClaveBancoFrances.add("consulta");
		palabrasClaveBancoFrances.add("saldo");
		bancoFrances.setListaPalabrasClave(palabrasClaveBancoFrances);

		bancoGalicia = new SucursalBanco();
		
		bancoGalicia.setNombre("Banco Galicia");
		
		bancoGalicia.setListaServicios(servicioCajeroAutomatico);
		bancoGalicia.setUbicacionActual(ubicacionSucursalFrances);
		palabrasClaveBancoGalicia = new ArrayList<String>();
		palabrasClaveBancoGalicia.add("deposito");
		palabrasClaveBancoGalicia.add("extraccion");
		palabrasClaveBancoGalicia.add("consulta");
		palabrasClaveBancoGalicia.add("saldo");
		bancoGalicia.setListaPalabrasClave(palabrasClaveBancoGalicia);

		bancoRio = new SucursalBanco();
		bancoRio.setListaServicios(servicioCajeroAutomatico);
		bancoRio.setUbicacionActual(ubicacionSucursalRio);
		palabrasClaveBancoRio = new ArrayList<String>();
		palabrasClaveBancoRio.add("deposito");
		palabrasClaveBancoRio.add("extraccion");
		palabrasClaveBancoRio.add("consulta");
		palabrasClaveBancoRio.add("saldo");
		bancoRio.setListaPalabrasClave(palabrasClaveBancoRio);

		sucursalRetiro = new SucursalBanco();
		sucursalRetiro.setListaServicios(servicioCPyRentas);
		sucursalRetiro.setUbicacionActual(ubicacionSucursalRetiro);
		sucursalRetiro
				.setListaServicios(servicioAsesoramientoLegalyPagoFacturas);
		palabrasClaveBancoRetiro = new ArrayList<String>();
		palabrasClaveBancoRetiro.add("Rentas");
		palabrasClaveBancoRetiro.add("Pago de facturas");
		sucursalRetiro.setListaPalabrasClave(palabrasClaveBancoRetiro);

		sucursalMartinez = new SucursalBanco();
		sucursalMartinez
				.setListaServicios(servicioAsesoramientoLegalyPagoFacturas);
		sucursalMartinez.setUbicacionActual(ubicacionSucursalMartinez);
		sucursalMartinez
				.setListaServicios(servicioAsesoramientoLegalyPagoFacturas);
		
		
		//Entrega 2
		
		JSONObject bancoFrances2 = new JSONObject();
		
		bancoFrances2.put("banco", "Banco Frances");
		bancoFrances2.put("x", "45");
		bancoFrances2.put("y", "20");
		bancoFrances2.put("sucursal", "Avellaneda");
		bancoFrances2.put("gerente", "Pablo Perez");
		bancoFrances2.put("servicios", "[cobrocheques,dep�sitos,extracciones]");
		
		JSONObject bancoGalicia2 = new JSONObject();
		
		bancoGalicia2.put("banco", "Banco Galicia");
		bancoGalicia2.put("x", "45");
		bancoGalicia2.put("y", "20");
		bancoGalicia2.put("sucursal", "Avellaneda");
		bancoGalicia2.put("gerente", "Pablo Perez");
		bancoGalicia2.put("servicios", "[cobrocheques,dep�sitos,extracciones]");
		
	}

	public void setUpJornadas() {

		rangolaboral_10a20 = new RangoHorario(LocalTime.of(10, 0,
				0), LocalTime.of(20, 0, 0));
		RangoHorario rangolaboral_9a13 = new RangoHorario(
				LocalTime.of(9, 0, 0), LocalTime.of(13, 0, 0));
		Jornada jornadaLaboral_Lunes_10a20 = new Jornada(DayOfWeek.MONDAY,
				rangolaboral_10a20);
		Jornada jornadaLaboral_Martes_10a20 = new Jornada(DayOfWeek.TUESDAY,
				rangolaboral_10a20);
		Jornada jornadaLaboral_Miercoles_10a20 = new Jornada(
				DayOfWeek.WEDNESDAY, rangolaboral_10a20);
		Jornada jornadaLaboral_Jueves_13a15 = new Jornada(DayOfWeek.THURSDAY,
				rangolaboral_10a20);
		Jornada jornadaLaboral_Viernes_10a20 = new Jornada(DayOfWeek.FRIDAY,
				rangolaboral_10a20);
		Jornada jornadaLaboral_Sabado_10a13 = new Jornada(DayOfWeek.SATURDAY,
				rangolaboral_9a13);
		jornadaNormalLunesAViernes = new ArrayList<Jornada>();
		jornadaNormalLunesAViernes.add(jornadaLaboral_Lunes_10a20);
		jornadaNormalLunesAViernes.add(jornadaLaboral_Martes_10a20);
		jornadaNormalLunesAViernes.add(jornadaLaboral_Miercoles_10a20);
		jornadaNormalLunesAViernes.add(jornadaLaboral_Jueves_13a15);
		jornadaNormalLunesAViernes.add(jornadaLaboral_Viernes_10a20);
		jornadaNormalLunesAViernes.add(jornadaLaboral_Sabado_10a13);

		rango24hs = new RangoHorario(LocalTime.of(0, 0, 0),
				LocalTime.of(23, 59, 59));
		Jornada jornadaLunes24hs = new Jornada(DayOfWeek.MONDAY, rango24hs);
		Jornada jornadaMartes24hs = new Jornada(DayOfWeek.TUESDAY, rango24hs);
		Jornada jornadaMiercoles24hs = new Jornada(DayOfWeek.WEDNESDAY,
				rango24hs);
		Jornada jornadaJueves24hs = new Jornada(DayOfWeek.THURSDAY, rango24hs);
		Jornada jornadaViernes24hs = new Jornada(DayOfWeek.FRIDAY, rango24hs);
		Jornada jornadaSabado24hs = new Jornada(DayOfWeek.SATURDAY, rango24hs);
		Jornada jornadaDomingo24hs = new Jornada(DayOfWeek.SUNDAY, rango24hs);
		jornada24x7 = new ArrayList<Jornada>();
		jornada24x7.add(jornadaLunes24hs);
		jornada24x7.add(jornadaMartes24hs);
		jornada24x7.add(jornadaMiercoles24hs);
		jornada24x7.add(jornadaJueves24hs);
		jornada24x7.add(jornadaViernes24hs);
		jornada24x7.add(jornadaSabado24hs);
		jornada24x7.add(jornadaDomingo24hs);

		rangomanana = new RangoHorario(LocalTime.of(8, 0, 0),
				LocalTime.of(12, 0, 0));
		Jornada jornadaLunesM = new Jornada(DayOfWeek.MONDAY, rangomanana);
		Jornada jornadaMartesM = new Jornada(DayOfWeek.TUESDAY, rangomanana);
		Jornada jornadaMiercolesM = new Jornada(DayOfWeek.WEDNESDAY,
				rangomanana);
		Jornada jornadaJuevesM = new Jornada(DayOfWeek.THURSDAY, rangomanana);
		Jornada jornadaViernesM = new Jornada(DayOfWeek.FRIDAY, rangomanana);
		Jornada jornadaSabadoM = new Jornada(DayOfWeek.SATURDAY, rangomanana);
		Jornada jornadaDomingoM = new Jornada(DayOfWeek.SUNDAY, rangomanana);
		manana = new ArrayList<Jornada>();
		manana.add(jornadaLunesM);
		manana.add(jornadaMartesM);
		manana.add(jornadaMiercolesM);
		manana.add(jornadaJuevesM);
		manana.add(jornadaViernesM);
		manana.add(jornadaSabadoM);
		manana.add(jornadaDomingoM);

		rangoTarde = new RangoHorario(LocalTime.of(12, 0, 0),
				LocalTime.of(18, 0, 0));
		Jornada jornadaLunesT = new Jornada(DayOfWeek.MONDAY, rangoTarde);
		Jornada jornadaMartesT = new Jornada(DayOfWeek.TUESDAY, rangoTarde);
		Jornada jornadaMiercolesT = new Jornada(DayOfWeek.WEDNESDAY, rangoTarde);
		Jornada jornadaJuevesT = new Jornada(DayOfWeek.THURSDAY, rangoTarde);
		Jornada jornadaViernesT = new Jornada(DayOfWeek.FRIDAY, rangoTarde);
		Jornada jornadaSabadoT = new Jornada(DayOfWeek.SATURDAY, rangoTarde);
		Jornada jornadaDomingoT = new Jornada(DayOfWeek.SUNDAY, rangoTarde);
		tarde = new ArrayList<Jornada>();
		manana.add(jornadaLunesT);
		manana.add(jornadaMartesT);
		manana.add(jornadaMiercolesT);
		manana.add(jornadaJuevesT);
		manana.add(jornadaViernesT);
		manana.add(jornadaSabadoT);
		manana.add(jornadaDomingoT);

		rangoNoche = new RangoHorario(LocalTime.of(18, 0, 0),
				LocalTime.of(23, 0, 0));
		Jornada jornadaLunesN = new Jornada(DayOfWeek.MONDAY, rangoNoche);
		Jornada jornadaMartesN = new Jornada(DayOfWeek.TUESDAY, rangoNoche);
		Jornada jornadaMiercolesN = new Jornada(DayOfWeek.WEDNESDAY, rangoNoche);
		Jornada jornadaJuevesN = new Jornada(DayOfWeek.THURSDAY, rangoNoche);
		Jornada jornadaViernesN = new Jornada(DayOfWeek.FRIDAY, rangoNoche);
		Jornada jornadaSabadoN = new Jornada(DayOfWeek.SATURDAY, rangoNoche);
		Jornada jornadaDomingoN = new Jornada(DayOfWeek.SUNDAY, rangoNoche);
		noche = new ArrayList<Jornada>();
		manana.add(jornadaLunesN);
		manana.add(jornadaMartesN);
		manana.add(jornadaMiercolesN);
		manana.add(jornadaJuevesN);
		manana.add(jornadaViernesN);
		manana.add(jornadaSabadoN);
		manana.add(jornadaDomingoN);

		rangoBancario = new RangoHorario(LocalTime.of(10, 0, 0),
				LocalTime.of(15, 0, 0));
		Jornada jornadaBancariaDe10a15LUNES = new Jornada(DayOfWeek.MONDAY,
				rangoBancario);
		Jornada jornadaBancariaDe10a15MARTES = new Jornada(DayOfWeek.TUESDAY,
				rangoBancario);
		Jornada jornadaBancariaDe10a15MIERCOLES = new Jornada(
				DayOfWeek.WEDNESDAY, rangoBancario);
		Jornada jornadaBancariaDe10a15JUEVES = new Jornada(DayOfWeek.THURSDAY,
				rangoBancario);
		Jornada jornadaBancariaDe10a15VIERNES = new Jornada(DayOfWeek.FRIDAY,
				rangoBancario);
		jornadaBancaria = new ArrayList<Jornada>();
		jornadaBancaria.add(jornadaBancariaDe10a15LUNES);
		jornadaBancaria.add(jornadaBancariaDe10a15MARTES);
		jornadaBancaria.add(jornadaBancariaDe10a15MIERCOLES);
		jornadaBancaria.add(jornadaBancariaDe10a15JUEVES);
		jornadaBancaria.add(jornadaBancariaDe10a15VIERNES);

	}

	public void setUpServicios() {

		pagoDeFacturas = new Servicio("Pago de facturas",
				jornadaNormalLunesAViernes);
		asesoramientoLegal = new Servicio("Asesoramiento Legal",
				jornadaNormalLunesAViernes);
		asesoramientoTecnico = new Servicio("Asesoramieno Tecnico",
				jornadaNormalLunesAViernes);
		rentas = new Servicio("rentas", jornadaBancaria);
		servicioCP = new Servicio("CP", jornadaBancaria);
		depositos = new Servicio("depositos", jornadaBancaria);
		extracciones = new Servicio("extracciones", jornada24x7);
		consultarSaldo = new Servicio("consultar saldo", jornada24x7);

		servicioCajeroAutomatico = new ArrayList<Servicio>();
		servicioCajeroAutomatico.add(depositos);
		servicioCajeroAutomatico.add(extracciones);
		servicioCajeroAutomatico.add(consultarSaldo);

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
		ubicacionSucursalFrances = new Point(45, 20);
		ubicacionSucursalGalicia = new Point(45, 20);
		ubicacionSucursalRio = new Point(40, 25);

		ubicacionCGPPaternal = new Point(30, 20);
		ubicacionCGPLaBoca = new Point(40, 30);
		ubicacionCGPNunez = new Point(50, 30);
		ubicacionCGPBoedo = new Point(50, 20);
		ubicacionCGPAlmagro = new Point(10, 15.005);
		ubicacionCGPCaballito = new Point(8, 10);
		ubicacionCGPPalermo = new Point(15, 15);

	}

	public void setUpLocalComercial() {

		farmacity = new LocalComercial();
		farmacity.setUbicacionActual(ubicacionFarmacity);
		farmacity.setListaRubros(farmaciaYperfumeria);
		farmacity.setListaPalabrasClave(listaPalabrasClaveFarmacity);
		farmacity.setJornadaDisponible(jornada24x7);

		morita = new LocalComercial();
		morita.setUbicacionActual(ubicacionMorita);
		morita.setListaRubros(rubroComidas);
		morita.setListaPalabrasClave(listaPalabrasClaveMorita);
		morita.setJornadaDisponible(noche);

		zapateria = new LocalComercial();
		zapateria.setUbicacionActual(ubicacionZapateria);
		zapateria.setListaRubros(rubroIndumentaria);
		zapateria.setListaPalabrasClave(listaPalabrasClaveZapateria);
		zapateria.setJornadaDisponible(tarde);

		heinsenburger = new LocalComercial();
		heinsenburger.setUbicacionActual(ubicacionHeinsenbuger);
		heinsenburger.setListaRubros(rubroComidas);
		heinsenburger.setListaPalabrasClave(listaPalabrasClaveHeinsenburger);
		heinsenburger.setJornadaDisponible(noche);

		libreria = new LocalComercial();
		libreria.setUbicacionActual(ubicacionLibreria);
		libreria.setListaRubros(rubroLibreria);
		libreria.setListaPalabrasClave(listaPalabrasClaveLibreria);
		libreria.setJornadaDisponible(manana);

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

		cgpPaternal = new CentroGestionParticipacion();
		cgpPaternal.setUbicacionActual(ubicacionCGPPaternal);
		cgpPaternal.setComuna(comuna3);
		cgpPaternal.setListaServicios(servicioCPyRentas);
		cgpPaternal.setListaPalabrasClave(palabrasClaveCGPPaternal);
		cgpPaternal.setJornadaDisponible(jornada24x7);

		cgpLaBoca = new CentroGestionParticipacion();
		cgpLaBoca.setUbicacionActual(ubicacionCGPLaBoca);
		cgpLaBoca.setComuna(comuna4);
		cgpLaBoca.setListaServicios(servicioAsesoramientoLegalyPagoFacturas);
		cgpLaBoca.setListaPalabrasClave(palabrasClaveCGPLaBoca);
		cgpLaBoca.setJornadaDisponible(jornada24x7);

		cgpNunez = new CentroGestionParticipacion();
		cgpNunez.setUbicacionActual(ubicacionCGPNunez);
		cgpNunez.setComuna(comuna5);
		cgpNunez.setListaServicios(servicioAsesoramientoLegalyPagoFacturas);
		cgpNunez.setListaPalabrasClave(palabrasClaveCGPNunez);
		cgpNunez.setJornadaDisponible(noche);

		cgpBoedo = new CentroGestionParticipacion();
		cgpBoedo.setUbicacionActual(ubicacionCGPBoedo);
		cgpBoedo.setComuna(comuna6);
		cgpBoedo.setListaServicios(servicioAsesoramientoLegalyPagoFacturas);
		cgpBoedo.setListaPalabrasClave(palabrasClaveCGPBoedo);
		cgpBoedo.setJornadaDisponible(noche);

		cgpCaballito = new CentroGestionParticipacion();
		cgpCaballito.setUbicacionActual(ubicacionCGPCaballito);
		cgpCaballito.setComuna(comuna2);
		cgpCaballito.setListaServicios(servicioCPyRentas);

		cgpAlmagro = new CentroGestionParticipacion();
		cgpAlmagro.setUbicacionActual(ubicacionCGPAlmagro);
		cgpAlmagro.setComuna(comuna1);
		cgpAlmagro.setListaServicios(servicioCPyRentas);
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
		comuna3 = new Comuna();
		comuna4 = new Comuna();
		comuna5 = new Comuna();
		comuna6 = new Comuna();

		punto1comuna = new Point(11, 20);
		punto2comuna = new Point(10.005, 20.001);
		punto3comuna = new Point(10.009, 20.005);
		punto4comuna = new Point(10, 20);
		punto5comuna = new Point(20, 20);
		punto6comuna = new Point(15, 10);

		punto7comuna = new Point(30, 30);
		punto8comuna = new Point(40, 30);
		punto9comuna = new Point(50, 30);
		punto10comuna = new Point(30, 20);
		punto11comuna = new Point(40, 20);
		punto12comuna = new Point(50, 20);

		comuna3.setAreaDeComuna(punto7comuna);
		comuna3.setAreaDeComuna(punto8comuna);
		comuna3.setAreaDeComuna(punto10comuna);

		comuna4.setAreaDeComuna(punto10comuna);
		comuna4.setAreaDeComuna(punto8comuna);
		comuna4.setAreaDeComuna(punto11comuna);

		comuna5.setAreaDeComuna(punto8comuna);
		comuna5.setAreaDeComuna(punto9comuna);
		comuna5.setAreaDeComuna(punto10comuna);

		comuna6.setAreaDeComuna(punto11comuna);
		comuna6.setAreaDeComuna(punto9comuna);
		comuna6.setAreaDeComuna(punto12comuna);

		comuna1.setAreaDeComuna(punto1comuna);
		comuna1.setAreaDeComuna(punto2comuna);
		comuna1.setAreaDeComuna(punto3comuna);

		comuna2.setAreaDeComuna(punto4comuna);
		comuna2.setAreaDeComuna(punto5comuna);
		comuna2.setAreaDeComuna(punto6comuna);

		comuna2.setAreaDeComuna(punto4comuna);
		comuna2.setAreaDeComuna(punto5comuna);
		comuna2.setAreaDeComuna(punto6comuna);

		comuna2.setAreaDeComuna(punto4comuna);
		comuna2.setAreaDeComuna(punto5comuna);
		comuna2.setAreaDeComuna(punto6comuna);

		comuna2.setAreaDeComuna(punto4comuna);
		comuna2.setAreaDeComuna(punto5comuna);
		comuna2.setAreaDeComuna(punto6comuna);

		comuna2.setAreaDeComuna(punto4comuna);
		comuna2.setAreaDeComuna(punto5comuna);
		comuna2.setAreaDeComuna(punto6comuna);

		comuna2.setAreaDeComuna(punto4comuna);
		comuna2.setAreaDeComuna(punto5comuna);
		comuna2.setAreaDeComuna(punto6comuna);
	}

	public void setUpRubro() {

		indumentaria = new Rubro("indumentaria", 0.2);
		muebleria = new Rubro("muebleria", 0.3);
		perfumeria = new Rubro("perfumeria", 0.1);
		cafeteria = new Rubro("cafeteria", 0.2);
		comidas = new Rubro("comidas", 0.1);

		farmacia = new Rubro("farmacia", 0.5);
		libreriaRubro = new Rubro("libreria", 0.4);

		rubroLibreria = new ArrayList<Rubro>();
		rubroLibreria.add(libreriaRubro);

		rubroComidas = new ArrayList<Rubro>();
		rubroComidas.add(comidas);

		farmaciaYperfumeria = new ArrayList<Rubro>();
		farmaciaYperfumeria.add(perfumeria);
		farmaciaYperfumeria.add(farmacia);

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
		listaPalabrasClaveHeinsenburger = new ArrayList<String>();
		listaPalabrasClaveHeinsenburger.add("Braking bad");
		listaPalabrasClaveHeinsenburger.add("gastronomia");
		listaPalabrasClaveHeinsenburger.add("comida");
		listaPalabrasClaveHeinsenburger.add("hamburguesa");

		listaPalabrasClaveFarmacity = new ArrayList<String>();
		listaPalabrasClaveFarmacity.add("perfume");
		listaPalabrasClaveFarmacity.add("remedio");
		listaPalabrasClaveFarmacity.add("medicamento");

		listaPalabrasClaveMorita = new ArrayList<String>();
		listaPalabrasClaveMorita.add("empanada");
		listaPalabrasClaveMorita.add("delivery");

		listaPalabrasClaveLibreria = new ArrayList<String>();
		listaPalabrasClaveLibreria.add("lapiz");
		listaPalabrasClaveLibreria.add("cartuchera");
		listaPalabrasClaveLibreria.add("mapa");

		listaPalabrasClaveZapateria = new ArrayList<String>();
		listaPalabrasClaveZapateria.add("nike");
		listaPalabrasClaveZapateria.add("addidas");
		listaPalabrasClaveZapateria.add("puma");
		listaPalabrasClaveZapateria.add("zapato");

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

		palabrasClaveCGPAlmagro = new ArrayList<String>();
		palabrasClaveCGPAlmagro.add("almagro");
		palabrasClaveCGPAlmagro.add("CP");
		palabrasClaveCGPAlmagro.add("Rentas");

		palabrasClaveCGPPaternal = new ArrayList<String>();
		palabrasClaveCGPPaternal.add("paternal");
		palabrasClaveCGPPaternal.add("argentinos");
		palabrasClaveCGPPaternal.add("boyaca");

		palabrasClaveCGPLaBoca = new ArrayList<String>();
		palabrasClaveCGPLaBoca.add("boca");
		palabrasClaveCGPLaBoca.add("la boca");
		palabrasClaveCGPLaBoca.add("caminito");

		palabrasClaveCGPNunez = new ArrayList<String>();
		palabrasClaveCGPNunez.add("nunez");
		palabrasClaveCGPNunez.add("belgrano");

		palabrasClaveCGPBoedo = new ArrayList<String>();
		palabrasClaveCGPBoedo.add("boedo");
		palabrasClaveCGPBoedo.add("avenida la plata");
		palabrasClaveCGPBoedo.add("av boedo");

	}

	public void setUpColectivos() {

		parada15 = new ParadaDeColectivo();
		ubicacionParada15 = new Point(10.0008, 20);
		parada15.setUbicacionActual(ubicacionParada15);
		parada15.setLinea("15");
		List<String> paradaDel15 = new ArrayList<String>();
		paradaDel15.add("15");
		parada15.setListaPalabrasClave(paradaDel15);
		parada15.setNombre("parada15");

		parada114 = new ParadaDeColectivo();
		parada114.setNombre("parada114");
		ubicacionParada114 = new Point(11, 13);
		parada114.setLinea("114");
		parada114.setUbicacionActual(ubicacionParada114);
		List<String> paradaDel114 = new ArrayList<String>();
		paradaDel114.add("114");
		parada114.setListaPalabrasClave(paradaDel114);

		parada11 = new ParadaDeColectivo();
		ubicacionParada11 = new Point(12, 18);
		parada11.setLinea("11");
		parada11.setUbicacionActual(ubicacionParada11);
		List<String> paradaDel11 = new ArrayList<String>();
		paradaDel11.add("11");
		parada11.setListaPalabrasClave(paradaDel11);

		parada7Rojo = new ParadaDeColectivo();
		ubicacionParada7Rojo = new Point(11, 14);
		parada7Rojo.setLinea("7 Barrio Samore");
		parada7Rojo.setUbicacionActual(ubicacionParada7Rojo);
		List<String> paradaDel7Rojo = new ArrayList<String>();
		paradaDel7Rojo.add("7");
		paradaDel7Rojo.add("rojo");
		parada7Rojo.setListaPalabrasClave(paradaDel7Rojo);

		parada7Amarillo = new ParadaDeColectivo();
		ubicacionParada7Amarillo = new Point(11, 13);
		parada7Amarillo.setLinea("7 Avellaneda");
		parada7Amarillo.setUbicacionActual(ubicacionParada7Amarillo);
		List<String> paradaDel7Amarillo = new ArrayList<String>();
		paradaDel7Amarillo.add("7");
		paradaDel7Amarillo.add("amarillo");
		parada7Amarillo.setListaPalabrasClave(paradaDel7Amarillo);

		parada60 = new ParadaDeColectivo();
		parada60.setLinea("60");
		ubicacionParada60 = new Point(30, 25);
		parada60.setUbicacionActual(ubicacionParada60);
		palabrasClave60 = new ArrayList<String>();
		palabrasClave60.add("60");
		palabrasClave60.add("palermo");
		palabrasClave60.add("las canitas");
		parada60.setListaPalabrasClave(palabrasClave60);

		parada12 = new ParadaDeColectivo();
		parada12.setLinea("12");
		parada12.setUbicacionActual(ubicacionParada60);
		palabrasClave12 = new ArrayList<String>();
		palabrasClave12.add("12");
		palabrasClave12.add("palermo");
		palabrasClave12.add("las canitas");
		parada12.setListaPalabrasClave(palabrasClave12);

		parada110Paternal = new ParadaDeColectivo();
		parada110Paternal.setLinea("110");
		ubicacionParada110Paternal = new Point(32, 25);
		parada110Paternal.setUbicacionActual(ubicacionParada110Paternal);
		palabrasClave110Paternal = new ArrayList<String>();
		palabrasClave110Paternal.add("110");
		palabrasClave110Paternal.add("paternal");
		parada110Paternal.setListaPalabrasClave(palabrasClave110Paternal);

		parada110LaBoca = new ParadaDeColectivo();
		parada110LaBoca.setLinea("110");
		ubicacionParada110LaBoca = new Point(39, 22);
		parada110LaBoca.setUbicacionActual(ubicacionParada110Paternal);
		palabrasClave110LaBoca = new ArrayList<String>();
		palabrasClave110LaBoca.add("110");
		palabrasClave110LaBoca.add("la boca");
		parada110LaBoca.setListaPalabrasClave(palabrasClave110LaBoca);

	}

	public void setUpPuntos() {

		puntoTerminal = new Point(10, 15);
		puntoTerminal2 = new Point(1000, 2000);
	}

	public void setUpLocalDateTime() {

		lunes1210hs = LocalDateTime.of(2016, 4, 11, 12, 10, 00);
		lunes23hs = LocalDateTime.of(2016, 4, 11, 23, 10, 00);
		martes04hs = LocalDateTime.of(2016, 4, 21, 04, 00, 00);
		lunes12hs = LocalDateTime.of(2016, 4, 11, 12, 00, 00);
		jueves11hs = LocalDateTime.of(2016, 4, 7, 11, 00, 00);
		sabado1210hs = LocalDateTime.of(2016, 4, 2, 12, 10, 00);
		sabado23hs = LocalDateTime.of(2016, 4, 2, 23, 00, 00);
	}
	
	// Entrega 2
	
	public void setUpDTO(){
		
		rangoDe9a18 = new RangoServicioDTO(1,9,0,18,0);
		rangosDTO = new ArrayList<RangoServicioDTO>();
		rangosDTO.add(rangoDe9a18);
		servicioRentasDTO = new ServicioDTO("rentas", rangosDTO);
		serviciosDTO = new ArrayList<ServicioDTO>();
		serviciosDTO.add(servicioRentasDTO);
		centroRecoleta = new CentroDTO(1, "Recoleta", "Juan Perez", "Jujuy 998", "45647898", serviciosDTO);
		centrosDTO = new ArrayList<CentroDTO>();
		centrosDTO.add(centroRecoleta);
	}
}
