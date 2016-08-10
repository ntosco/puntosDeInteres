package ar.utn.dds.juegoDeDatos;

import static org.mockito.Mockito.mock;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;

import org.json.simple.JSONObject;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.creacionales.BancoBuilder;
import ar.utn.dds.creacionales.CgpBuilder;
import ar.utn.dds.creacionales.ColectivoBuilder;
import ar.utn.dds.creacionales.JornadaBuilder;
import ar.utn.dds.creacionales.ListaJornadasBuilder;
import ar.utn.dds.creacionales.LocalComercialBuilder;
import ar.utn.dds.creacionales.ProcesoMultipleBuilder;
import ar.utn.dds.extern.banco.buscadorDeBancos;
import ar.utn.dds.extern.cgp.CentroDTO;
import ar.utn.dds.extern.cgp.RangoServicioDTO;
import ar.utn.dds.extern.cgp.ServicioDTO;





import ar.utn.dds.procesos.ActualizarLocalesComerciales;
import ar.utn.dds.procesos.BajaDePOIS;
import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.procesos.ProcesoMultiple;

import ar.utn.dds.procesos.estrategiaFallo.EnvioMensajePorFalla;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.procesos.estrategiaFallo.NoRealizarAccionPorFalla;
import ar.utn.dds.procesos.estrategiaFallo.ReplicaPorFallo;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.roles.RolAdministrador;
import ar.utn.dds.roles.RolConsulta;

import ar.utn.dds.servicios.MailSender;

import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.usuarios.UsuarioConcreto;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;


abstract public class JuegoDeDatos {
	
	//Usuarios
	protected UsuarioConcreto usuarioAdmin = new UsuarioConcreto();
	protected UsuarioConcreto usuarioConsulta = new UsuarioConcreto();
	protected List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	//Roles
	protected RolAdministrador rolAdmin = new RolAdministrador();
	protected RolConsulta rolConsulta = new RolConsulta();
	
	//MailSender
	protected MailSender mockMailSender ;
	
	
	//Estrategias por falla
	
	protected EnvioMensajePorFalla estrategiaEnvioMensaje ;
	protected ReplicaPorFallo estrategiaReplica3veces = new ReplicaPorFallo(3);
	protected NoRealizarAccionPorFalla estrategiaNoHaceNada = new NoRealizarAccionPorFalla();

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
	protected List<String> palabrasClaveCGPCaballito;
	protected List<String> palabrasClaveCGPPalermo;

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

	protected LocalComercial adidas;
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
	protected List<String> palabrasClaveBancoMartinez;

	protected JSONObject bancoFrances2;
	protected JSONObject bancoGalicia2;
		
	// Jornadas

	protected List<Jornada> jornadaBancaria;
	protected List<Jornada> jornadaNormalLunesAViernes;
	protected List<Jornada> noche;// los 7 dias
	protected List<Jornada> manana;// los 7 dias
	protected List<Jornada> tarde;// los 7 dias
	protected List<Jornada> jornada24x7;// 24 hs los 7 dias
	private ArrayList<Jornada> jornadaNocturna;


	// LOCAL DATE TIME

	protected LocalDateTime lunes1210hs;
	protected LocalDateTime lunes23hs;
	protected LocalDateTime martes04hs;
	protected LocalDateTime sabado1210hs;
	protected LocalDateTime lunes12hs;
	protected LocalDateTime jueves11hs;
	protected LocalDateTime sabado23hs;
	
	// Entrega 2
	
	// Dto
	
	protected List<CentroDTO> centrosDTO;
	protected List<ServicioDTO> serviciosDTO;
	protected List<RangoServicioDTO> rangosDTO;
	
	protected RangoServicioDTO rangoDe9a18;
	protected ServicioDTO servicioRentasDTO;
	protected CentroDTO centroRecoleta;

	protected List<DayOfWeek> lunesAViernes;
	private List<DayOfWeek> todosLosDias;

	protected List<Point> puntosComunaPaternal;
	protected List<Point> puntosComunaLaBoca;
	protected List<Point> puntosComunaNunez;
	protected List<Point> puntosComunaBoedo;
	protected List<Point> puntosComunaCaballito;
	protected List<Point> puntosComunaAlmagro;
	protected List<Point> puntosComunaPalermo;

	protected RangoHorario rangoDe10a20;
	protected RangoHorario rangoNoche;
	protected RangoHorario rangoBancario;
	protected RangoHorario rango24x7;
	protected RangoHorario rangoManiana;
	protected RangoHorario rangoTarde;
	private RangoHorario rangoNocturno;
	
	protected ActualizarLocalesComerciales procesoActualizarLocalesComerciales;
	protected ActualizarLocalesComerciales procesoActualizarLocalesComerciales2;
	
	protected ActualizarLocalesComerciales procesoActualizarLocalesComercialesSinTXT;
	protected BajaDePOIS procesoBajaDePois;
	protected List<Proceso> procesosAEjecutarOK;
	protected List<Proceso> procesosAEjecutarNoTieneTXT;
	protected ProcesoMultiple procesoMultiplePruebaOK;
	protected ProcesoMultiple procesoMultiplePruebaERROR;
	
	//Procesos genericos mockeados
	protected Proceso procesoErroneoMock = mock(Proceso.class);
	protected Proceso procesoOKMock = mock(Proceso.class);

	//Estrategias mockeadas
	protected EstrategiaPorFallo estrategiaPorFalloMock = mock(EstrategiaPorFallo.class);
	
	// Entrega 4
	
	protected BajaDePOIS procesoBajas;
	protected ActualizarLocalesComerciales procesoActualizacion;
	protected ActualizarLocalesComerciales procesoActualizacion2;
	protected ActualizarLocalesComerciales procesoActualizacion3;

	
	//Estrategias
	

	protected EstrategiaPorFallo estrategiaFalloMock;
	
	//Repositorio
	protected Repositorio repositorioDefault = Repositorio.getInstance();
	
	
	
	public void setUpGeneral() {
		setUpLocalDateTime();
		setUpJornadas();
		setUpServicios();
		setUpUbicaciones();
		setUpPuntos();
		setUpComunas();
		setUpRubro();
		setUpPalabrasClave();
	}	
	
	public void setUpBanco() {

		BancoBuilder builderFrances = new BancoBuilder();
		builderFrances.crearListaServicios(servicioCajeroAutomatico)
					.setNombre("Frances San Cristobal")
					.setBarrio("San Cristobal")
					.setDireccion("Estados Unidos")
					.setNumero(2206)
					.setUbicacion(ubicacionSucursalFrances)
					.setPalabrasClave(palabrasClaveBancoFrances)
					.setJornada(jornadaBancaria);
					bancoFrances = builderFrances.build();

		BancoBuilder builderGalicia = new BancoBuilder();
		builderGalicia.crearListaServicios(servicioCajeroAutomatico)
					.setNombre("Banco Galicia")
					.setBarrio("Palermo")
					.setDireccion("Arenales")
					.setNumero(200)
					.setUbicacion(ubicacionSucursalFrances)
					.setPalabrasClave(palabrasClaveBancoGalicia)
					.setJornada(jornadaBancaria);
					bancoGalicia = builderGalicia.build();

		BancoBuilder builderRio = new BancoBuilder();
		builderRio.crearListaServicios(servicioCajeroAutomatico)
					.setNombre("Banco Galicia")
					.setBarrio("Palermo")
					.setDireccion("Arenales")
					.setNumero(200)
					.setUbicacion(ubicacionSucursalFrances)
					.setPalabrasClave(palabrasClaveBancoGalicia)
					.setJornada(jornadaBancaria);
					bancoRio = builderRio.build();
		
		BancoBuilder builderRetiro = new BancoBuilder();
		builderRetiro.crearListaServicios(servicioCPyRentas)
					.setNombre("Banco Retiro")
					.setBarrio("Retiro")
					.setDireccion("Paseo Colon")
					.setNumero(2207)
					.setUbicacion(ubicacionSucursalRetiro)
					.setPalabrasClave(palabrasClaveBancoRetiro)
					.setJornada(jornadaBancaria);
					sucursalRetiro = builderRetiro.build();

		BancoBuilder builderMartinez = new BancoBuilder();
		builderMartinez.crearListaServicios(servicioAsesoramientoLegalyPagoFacturas)
					.setNombre("Banco Martinez")
					.setBarrio("Martinez")
					.setDireccion("San Martin")
					.setNumero(591)
					.setUbicacion(ubicacionSucursalMartinez)
					.setPalabrasClave(palabrasClaveBancoMartinez)
					.setJornada(jornadaBancaria);
					sucursalMartinez = builderMartinez.build();
		
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
		bancoGalicia2.put("servicios", "[cobrocheques,depositos,extracciones]");
		
	}

	public void setUpJornadas() {
		
		lunesAViernes = new ArrayList<DayOfWeek>();
		lunesAViernes.add(DayOfWeek.MONDAY);
		lunesAViernes.add(DayOfWeek.TUESDAY);
		lunesAViernes.add(DayOfWeek.WEDNESDAY);
		lunesAViernes.add(DayOfWeek.THURSDAY);
		lunesAViernes.add(DayOfWeek.FRIDAY);
		
		todosLosDias = new ArrayList<DayOfWeek>();
		todosLosDias.addAll(lunesAViernes);
		todosLosDias.add(DayOfWeek.SATURDAY);
		todosLosDias.add(DayOfWeek.SUNDAY);
		
		
		ListaJornadasBuilder builderJornadaNormal = new ListaJornadasBuilder();
		rangoDe10a20 = new RangoHorario(LocalTime.of(10,0,0), LocalTime.of(20,0,0));
		jornadaNormalLunesAViernes = builderJornadaNormal.buildJornadas(lunesAViernes, rangoDe10a20);
		
		ListaJornadasBuilder builderJornada24x7 = new ListaJornadasBuilder();
		rango24x7 = new RangoHorario(LocalTime.of(0,0,0), LocalTime.of(23,59,59));
		jornada24x7 = builderJornada24x7.buildJornadas(todosLosDias, rango24x7);
		
		ListaJornadasBuilder builderJornadaManiana = new ListaJornadasBuilder();
		rangoManiana = new RangoHorario(LocalTime.of(8,0,0), LocalTime.of(12,0,0));
 		manana = builderJornadaManiana.buildJornadas(todosLosDias, rangoManiana);
		
		ListaJornadasBuilder builderJornadaTarde = new ListaJornadasBuilder();
		rangoTarde = new RangoHorario(LocalTime.of(12,0,0), LocalTime.of(18,0,0));
		tarde = builderJornadaTarde.buildJornadas(todosLosDias, rangoTarde);

		ListaJornadasBuilder builderJornadaNoche = new ListaJornadasBuilder();
		rangoNoche = new RangoHorario(LocalTime.of(18,0,0), LocalTime.of(23,0,0));
		noche = builderJornadaNoche.buildJornadas(todosLosDias, rangoNoche);
		
		ListaJornadasBuilder builderJornadaBancaria = new ListaJornadasBuilder();
		rangoBancario = new RangoHorario(LocalTime.of(10,0,0), LocalTime.of(15,0,0));
		jornadaBancaria = builderJornadaBancaria.buildJornadas(lunesAViernes, rangoBancario);
		
		JornadaBuilder builderJornadaNocturna = new JornadaBuilder();
		rangoNocturno = new RangoHorario(LocalTime.of(23,0,0), LocalTime.of(6,0,0));
		jornadaNocturna = new ArrayList<Jornada>();
		jornadaNocturna.add(builderJornadaNocturna.build(DayOfWeek.MONDAY, rangoNocturno));
		jornadaNocturna.add(builderJornadaNocturna.build(DayOfWeek.WEDNESDAY, rangoNocturno));
		jornadaNocturna.add(builderJornadaNocturna.build(DayOfWeek.FRIDAY, rangoNocturno));
			
	}


	
	public void setUpServicios() {

		pagoDeFacturas = new Servicio("Pago de facturas",jornadaNormalLunesAViernes);
		asesoramientoLegal = new Servicio("Asesoramiento Legal",jornadaNormalLunesAViernes);
		asesoramientoTecnico = new Servicio("Asesoramieno Tecnico",	jornadaNormalLunesAViernes);
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
		
		ubicacionParada15 = new Point(10.0008, 20);
		ubicacionParada60 = new Point(30, 25);
		ubicacionParada11 = new Point(12, 18);
		ubicacionParada114 = new Point(11, 13);
		ubicacionParada110LaBoca = new Point(39, 22);
		ubicacionParada7Amarillo = new Point(11, 13);
		ubicacionParada7Rojo = new Point(11, 14);
		
		ubicacionFarmacity = new Point(11,14);
		ubicacionMorita = new Point(20,16);
		ubicacionZapateria = new Point(11,15);
		ubicacionHeinsenbuger = new Point(15,12);
		ubicacionLibreria = new Point(23,65);
		ubicacionLocalNike = new Point(8,9);
		ubicacionLocalFallabella = new Point(9,8);
		ubicacionLocalCafeMartinez = new Point(4,16);
		ubicacionLocalAddidas = new Point(12,23);
		ubicacionLocalPanquequesCarlitos = new Point(10,10);
		
	}

	public void setUpLocalComercial() {

		LocalComercialBuilder builderFarmacity = new LocalComercialBuilder();
		builderFarmacity.crearListaRubros(rubroComidas)
						.setNombre("Farmacity")
						.setBarrio("Boedo")
						.setDireccion("Boedo")
						.setNumero(156)
						.setUbicacion(ubicacionFarmacity)
						.setPalabrasClave(listaPalabrasClaveFarmacity)
						.setJornada(jornada24x7);
						farmacity = builderFarmacity.build();
		
		LocalComercialBuilder builderMorita = new LocalComercialBuilder();
		builderMorita.crearListaRubros(rubroComidas)
						.setNombre("Morita")
						.setBarrio("Belgrano")
						.setDireccion("Paraguay")
						.setNumero(556)
						.setUbicacion(ubicacionMorita)
						.setPalabrasClave(listaPalabrasClaveMorita)
						.setJornada(noche);
						morita = builderMorita.build();
		
		LocalComercialBuilder builderZapateria = new LocalComercialBuilder();
		builderZapateria.crearListaRubros(rubroIndumentaria)
						.setNombre("Zapateria Jorge")
						.setBarrio("Flores")
						.setDireccion("Varela")
						.setNumero(1423)
						.setUbicacion(ubicacionZapateria)
						.setPalabrasClave(listaPalabrasClaveZapateria)
						.setJornada(tarde);
						zapateria = builderZapateria.build();
	
		LocalComercialBuilder builderHeinsenburguer = new LocalComercialBuilder();
		builderHeinsenburguer.crearListaRubros(rubroComidas)
						.setNombre("Heinsenburguer")
						.setBarrio("Colegiales")
						.setDireccion("Chile")
						.setNumero(147)
						.setUbicacion(ubicacionHeinsenbuger)
						.setPalabrasClave(listaPalabrasClaveHeinsenburger)
						.setJornada(noche);
						heinsenburger = builderHeinsenburguer.build();

		LocalComercialBuilder builderLibreria = new LocalComercialBuilder();
		builderLibreria.crearListaRubros(rubroLibreria)
						.setNombre("Libreria")
						.setBarrio("Parque Patricios")
						.setDireccion("Colon")
						.setNumero(2331)
						.setUbicacion(ubicacionLibreria)
						.setPalabrasClave(listaPalabrasClaveLibreria)
						.setJornada(manana);
						libreria = builderLibreria.build();

		LocalComercialBuilder builderNike = new LocalComercialBuilder();
		builderNike.crearListaRubros(rubroIndumentaria)
						.setNombre("Nike")
						.setBarrio("Abasto")
						.setDireccion("Corrientes")
						.setNumero(4231)
						.setUbicacion(ubicacionLocalNike)
						.setPalabrasClave(listaPalabrasClaveNike)
						.setJornada(manana);
						nike = builderNike.build();
		
		LocalComercialBuilder builderFallabella = new LocalComercialBuilder();
		builderFallabella.crearListaRubros(rubrosIndumentariaMuebleriaPerfumeria)
						.setNombre("Fallabella")
						.setBarrio("Microcentro")
						.setDireccion("Florida")
						.setNumero(120)
						.setUbicacion(ubicacionLocalFallabella)
						.setPalabrasClave(listaPalabrasClaveFallabella)
						.setJornada(jornadaNormalLunesAViernes);
						fallabella = builderFallabella.build();
						
		LocalComercialBuilder builderCafeMartinez = new LocalComercialBuilder();
		builderCafeMartinez.crearListaRubros(cafeteriaYComidas)
						.setNombre("Fallabella")
						.setBarrio("Microcentro")
						.setDireccion("Florida")
						.setNumero(120)
						.setUbicacion(ubicacionLocalFallabella)
						.setPalabrasClave(listaPalabrasClaveFallabella)
						.setJornada(jornadaNormalLunesAViernes);
						cafeMartinez = builderCafeMartinez.build();
		
		LocalComercialBuilder builderAdidas = new LocalComercialBuilder();
		builderAdidas.crearListaRubros(rubroIndumentaria)
						.setNombre("Adidas")
						.setBarrio("Palermo")
						.setDireccion("Av. Santa Fe")
						.setNumero(856)
						.setUbicacion(ubicacionLocalAddidas)
						.setPalabrasClave(listaPalabrasClaveNike)
						.setJornada(jornadaNormalLunesAViernes);
						adidas = builderAdidas.build();

		LocalComercialBuilder builderCarlitos = new LocalComercialBuilder();
		builderCarlitos.crearListaRubros(cafeteriaYComidas)
						.setNombre("Carlitos Panqueques")
						.setBarrio("Recoleta")
						.setDireccion("Rosas")
						.setNumero(1086)
						.setUbicacion(ubicacionLocalPanquequesCarlitos)
						.setPalabrasClave(palabrasClaveCafeMartinez)
						.setJornada(jornadaNormalLunesAViernes);
						panquequesCarlitos = builderCarlitos.build();
						
	}

	public void setUpCGP() {
		
		CgpBuilder builderPaternal = new CgpBuilder();
		builderPaternal.crearComuna(puntosComunaPaternal)
						.crearListaServicios(servicioCPyRentas)
						.setNombre("Paternal")
						.setBarrio("Paternal")
						.setDireccion("Boedo")
						.setNumero(156)
						.setUbicacion(punto10comuna)
						.setPalabrasClave(palabrasClaveCGPPaternal)
						.setJornada(jornadaBancaria);
						cgpPaternal = builderPaternal.build();
		
		CgpBuilder builderLaBoca = new CgpBuilder();
		builderLaBoca.crearComuna(puntosComunaLaBoca)
						.crearListaServicios(servicioAsesoramientoLegalyPagoFacturas)
						.setNombre("La boca")
						.setBarrio("La boca")
						.setDireccion("Brasil")
						.setNumero(4456)
						.setUbicacion(ubicacionCGPLaBoca)
						.setPalabrasClave(palabrasClaveCGPLaBoca)
						.setJornada(jornada24x7);
						cgpLaBoca = builderLaBoca.build();
		
		
		CgpBuilder builderNunez = new CgpBuilder();
		builderNunez.crearComuna(puntosComunaNunez)
						.crearListaServicios(servicioAsesoramientoLegalyPagoFacturas)
						.setNombre("Nunez")
						.setBarrio("Nunez, Belgrano")
						.setDireccion("Av. Libertador")
						.setNumero(879)
						.setUbicacion(ubicacionCGPNunez)
						.setPalabrasClave(palabrasClaveCGPNunez)
						.setJornada(noche);
						cgpNunez = builderNunez.build();
		
		CgpBuilder builderBoedo = new CgpBuilder();
		builderBoedo.crearComuna(puntosComunaBoedo)
						.crearListaServicios(servicioAsesoramientoLegalyPagoFacturas)
						.setNombre("Boedo")
						.setBarrio("Boedo")
						.setDireccion("Av. Boedo")
						.setNumero(1565)
						.setUbicacion(ubicacionCGPBoedo)
						.setPalabrasClave(palabrasClaveCGPBoedo)
						.setJornada(noche);
						cgpBoedo = builderBoedo.build();

		CgpBuilder builderCaballito = new CgpBuilder();
		builderCaballito.crearComuna(puntosComunaCaballito)
						.crearListaServicios(servicioCPyRentas)
						.setNombre("Caballito")
						.setBarrio("Caballito")
						.setDireccion("Rivadavia")
						.setNumero(123)
						.setUbicacion(ubicacionCGPCaballito)
						.setPalabrasClave(palabrasClaveCGPCaballito)
						.setJornada(jornadaNormalLunesAViernes);
						cgpCaballito = builderCaballito.build();
		
		CgpBuilder buiderAlmagro = new CgpBuilder();
		buiderAlmagro.crearComuna(puntosComunaAlmagro)
						.crearListaServicios(servicioCPyRentas)
						.setNombre("Almagro")
						.setBarrio("Almagro, Paternal")
						.setDireccion("Tucuman")
						.setNumero(4567)
						.setUbicacion(ubicacionCGPAlmagro)
						.setPalabrasClave(palabrasClaveCGPAlmagro)
						.setJornada(jornadaNormalLunesAViernes);
						cgpAlmagro = buiderAlmagro.build();
		
		CgpBuilder buiderPalermo = new CgpBuilder();
		buiderPalermo.crearComuna(puntosComunaPalermo)
						.crearListaServicios(servicioAsesoramientoLegalyPagoFacturas)
						.setNombre("Palermo")
						.setBarrio("Palermo Soho, Palermo Hollywood")
						.setDireccion("Av. Santa Fe")
						.setNumero(7841)
						.setUbicacion(ubicacionCGPPalermo)
						.setPalabrasClave(palabrasClaveCGPPalermo)
						.setJornada(jornadaNormalLunesAViernes);
						cgpPalermo = buiderPalermo.build();

	}

	public void setUpComunas() {

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
		
		puntosComunaPaternal = new ArrayList<Point>();
		puntosComunaPaternal.add(punto1comuna);
		puntosComunaPaternal.add(punto2comuna);
		puntosComunaPaternal.add(punto4comuna);
		puntosComunaPaternal.add(punto10comuna);
		
		puntosComunaLaBoca = new ArrayList<Point>();
		puntosComunaLaBoca.add(punto8comuna);
		puntosComunaLaBoca.add(punto5comuna);
		puntosComunaLaBoca.add(punto6comuna);
		
		puntosComunaNunez = new ArrayList<Point>();
		puntosComunaNunez.add(punto8comuna);
		puntosComunaNunez.add(punto6comuna);
		puntosComunaNunez.add(punto7comuna);
		puntosComunaNunez.add(punto9comuna);
		puntosComunaNunez.add(punto1comuna);
		
		puntosComunaBoedo = new ArrayList<Point>();
		puntosComunaBoedo.add(punto10comuna);
		puntosComunaBoedo.add(punto11comuna);
		puntosComunaBoedo.add(punto12comuna);
		
		puntosComunaCaballito = new ArrayList<Point>();
		puntosComunaCaballito.add(punto10comuna);
		puntosComunaCaballito.add(punto11comuna);
		puntosComunaCaballito.add(punto1comuna);
		
		puntosComunaAlmagro = new ArrayList<Point>();
		puntosComunaAlmagro.add(punto1comuna);
		puntosComunaAlmagro.add(punto4comuna);
		puntosComunaAlmagro.add(punto10comuna);
		puntosComunaAlmagro.add(punto7comuna);
		
		puntosComunaPalermo = new ArrayList<Point>();
		puntosComunaPalermo.add(punto1comuna);
		puntosComunaPalermo.add(punto5comuna);
		puntosComunaPalermo.add(punto8comuna);
		puntosComunaPalermo.add(punto7comuna);
		puntosComunaPalermo.add(punto9comuna);
		puntosComunaPalermo.add(punto10comuna);
		
		
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

		palabrasClaveCGPCaballito = new ArrayList<String>();
		palabrasClaveCGPCaballito.add("Caballito");
		palabrasClaveCGPCaballito.add("Rivadavia");
		palabrasClaveCGPCaballito.add("Rentas");
		
		palabrasClaveCGPPalermo = new ArrayList<String>();
		palabrasClaveCGPPalermo.add("Palermo");
		palabrasClaveCGPPalermo.add("Santa Fe");
		palabrasClaveCGPPalermo.add("Soho");
		
		palabrasClaveBancoFrances = new ArrayList<String>();
		palabrasClaveBancoFrances.add("deposito");
		palabrasClaveBancoFrances.add("extraccion");
		palabrasClaveBancoFrances.add("consulta");
		palabrasClaveBancoFrances.add("saldo");
		
		palabrasClaveBancoGalicia = new ArrayList<String>();
		palabrasClaveBancoGalicia.add("deposito");
		palabrasClaveBancoGalicia.add("extraccion");
		palabrasClaveBancoGalicia.add("consulta");
		palabrasClaveBancoGalicia.add("saldo");
		
		palabrasClaveBancoRio = new ArrayList<String>();
		palabrasClaveBancoRio.add("deposito");
		palabrasClaveBancoRio.add("extraccion");
		palabrasClaveBancoRio.add("consulta");
		palabrasClaveBancoRio.add("saldo");

		palabrasClaveBancoRetiro = new ArrayList<String>();
		palabrasClaveBancoRetiro.add("Rentas");
		palabrasClaveBancoRetiro.add("Pago de facturas");
		
		palabrasClaveBancoMartinez = palabrasClaveBancoRetiro;
		
		paradaDel15 = new ArrayList<String>();
		paradaDel15.add("15");
		
		palabrasClave110LaBoca = new ArrayList<String>();
		palabrasClave110LaBoca.add("110");
		palabrasClave110LaBoca.add("la boca");

		ubicacionParada110Paternal = new Point(32, 25);
		palabrasClave110Paternal = new ArrayList<String>();
		palabrasClave110Paternal.add("110");
		palabrasClave110Paternal.add("paternal");
		
		paradaDel7Amarillo = new ArrayList<String>();
		paradaDel7Amarillo.add("7");
		paradaDel7Amarillo.add("amarillo");

		
		parada7Rojo = new ParadaDeColectivo();
		paradaDel7Rojo = new ArrayList<String>();
		paradaDel7Rojo.add("7");
		paradaDel7Rojo.add("rojo");

		
		paradaDel114 = new ArrayList<String>();
		paradaDel114.add("114");

		paradaDel11 = new ArrayList<String>();
		paradaDel11.add("11");
		
		palabrasClave60 = new ArrayList<String>();
		palabrasClave60.add("60");
		palabrasClave60.add("palermo");
		palabrasClave60.add("las canitas");
	}
	
	public void setUpColectivos() {
		
		ColectivoBuilder builder15 = new ColectivoBuilder();
		builder15.crearLinea("15")
						.setNombre("Parada San Telmo")
						.setBarrio("San Telmo")
						.setDireccion("Belgrano")
						.setNumero(156)
						.setUbicacion(ubicacionParada15)
						.setPalabrasClave(palabrasClaveCGPPaternal);
						parada15 = builder15.build();
		
		ColectivoBuilder builder114 = new ColectivoBuilder();
		builder114.crearLinea("15")
						.setNombre("Parada San Telmo")
						.setBarrio("San Telmo")
						.setDireccion("Belgrano")
						.setNumero(156)
						.setUbicacion(ubicacionParada114)
						.setPalabrasClave(paradaDel114);
						parada114 = builder114.build();
		
		ColectivoBuilder builder11 = new ColectivoBuilder();
		builder11.crearLinea("11")
						.setNombre("Parada 11")
						.setBarrio("Retiro")
						.setDireccion("belgrano")
						.setNumero(1256)
						.setUbicacion(ubicacionParada11)
						.setPalabrasClave(paradaDel11);
						parada11 = builder11.build();
		
		ColectivoBuilder builder7Rojo = new ColectivoBuilder();
		builder7Rojo.crearLinea("7")
						.setNombre("Parada 7 Rojo")
						.setBarrio("Paternal")
						.setDireccion("Pomelo")
						.setNumero(897)
						.setUbicacion(ubicacionParada7Rojo)
						.setPalabrasClave(paradaDel7Rojo);
						parada7Rojo = builder7Rojo.build();
		
		ColectivoBuilder builder7Amarillo = new ColectivoBuilder();
		builder7Amarillo.crearLinea("7")
						.setNombre("Parada 7 Amarillo")
						.setBarrio("Paternal")
						.setDireccion("Pomelo")
						.setNumero(897)
						.setUbicacion(ubicacionParada7Amarillo)
						.setPalabrasClave(paradaDel7Amarillo);
						parada7Amarillo = builder7Amarillo.build();
		
		ColectivoBuilder builder60 = new ColectivoBuilder();
		builder60.crearLinea("60")
						.setNombre("Parada 60")
						.setBarrio("Palermo")
						.setDireccion("Santa Fe")
						.setNumero(1514)
						.setUbicacion(ubicacionParada60)
						.setPalabrasClave(palabrasClave60);
						parada60 = builder60.build();
		
		ColectivoBuilder builder110Paternal = new ColectivoBuilder();
		builder110Paternal.crearLinea("110")
						.setNombre("Parada 110")
						.setBarrio("Paternal")
						.setDireccion("Sosa")
						.setNumero(2354)
						.setUbicacion(ubicacionParada110Paternal)
						.setPalabrasClave(palabrasClave110Paternal);
						parada110Paternal = builder110Paternal.build();

		ColectivoBuilder builder110LaBoca = new ColectivoBuilder();
		builder110LaBoca.crearLinea("110")
						.setNombre("Parada 110")
						.setBarrio("La Boca")
						.setDireccion("Pasteur")
						.setNumero(23)
						.setUbicacion(ubicacionParada110Paternal)
						.setPalabrasClave(palabrasClave110LaBoca);
						parada110LaBoca = builder110LaBoca.build();
						
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
	

/*
	public void setUpProcesos(){
	 
		procesoBajas = new BajaDePOIS();
		procesoBajas.setIdProceso(1);
		procesoBajas.setNombre("proceso de bajas de POI");
		procesoBajas.setServicioREST(new StubServicioREST());
		
				
		
		procesoActualizacion = new ActualizarLocalesComerciales();
		procesoActualizacion.setArchivo("Locales.txt"); 
		procesoActualizacion.setNombre("ProcesoActualizarVariosLocales");
		
		procesoActualizacion2 = new ActualizarLocalesComerciales();
		procesoActualizacion2.setNombre("PActualizacionUnSoloLocal");
		procesoActualizacion2.setFallo(estrategiaFalloMock);
		
	}
	
		public void setUpEstrategias(){
		
		estrategiaMail =  new EnvioMensajePorFalla();
		estrategiaFalloMock = mock(EstrategiaPorFallo.class);
		
	}
*/
	



	// ********************************************************
	// ** Procesos y Estrategias de Fallos
	// ********************************************************

	public void setUpProcesos(){
		procesoActualizarLocalesComerciales = new ActualizarLocalesComerciales();
		procesoActualizarLocalesComerciales.setArchivo("Locales.txt"); 
		procesoActualizarLocalesComerciales.setNombre("ProcesoActualizarVariosLocales");
		
		procesoActualizarLocalesComerciales2 = new ActualizarLocalesComerciales();
		procesoActualizarLocalesComerciales2.setArchivo("Locales.txt"); 
		procesoActualizarLocalesComerciales2.setNombre("ProcesoActualizarVariosLocales");
		procesoActualizarLocalesComerciales2.setEstrategiaPorFallo(estrategiaFalloMock);

		
		procesoActualizarLocalesComercialesSinTXT = new ActualizarLocalesComerciales();
		procesoActualizarLocalesComercialesSinTXT.setNombre("ProcesoActualizarVariosLocalesSinTXT");
		
		procesoBajaDePois = new BajaDePOIS();
		procesoBajaDePois.setServicioREST(new StubServicioREST());
		
		procesosAEjecutarOK = new ArrayList<Proceso>();
		procesosAEjecutarOK.add(procesoActualizarLocalesComerciales);
		procesosAEjecutarOK.add(procesoBajaDePois);
	
		procesosAEjecutarNoTieneTXT = new ArrayList<Proceso>();
		procesosAEjecutarNoTieneTXT.add(procesoActualizarLocalesComercialesSinTXT);
		procesosAEjecutarNoTieneTXT.add(procesoBajaDePois);
	
	
		ProcesoMultipleBuilder builderProcesoMultiple = new ProcesoMultipleBuilder();
		builderProcesoMultiple.crearListaProcesos(procesosAEjecutarOK)
							  .setNombre("ProcesoMultiplePruebaOK");
							  procesoMultiplePruebaOK = builderProcesoMultiple.build();
							  
		builderProcesoMultiple.crearListaProcesos(procesosAEjecutarNoTieneTXT)
								  .setNombre("ProcesoMultiplePruebaERROR");
								  procesoMultiplePruebaERROR = builderProcesoMultiple.build();
							  
	}

	public void setUpUsuario(){
		usuarioAdmin.setRol(rolAdmin);
		usuarioConsulta.setRol(rolConsulta);
		usuarioAdmin.setEmail("usuarioadmin@gmail");
		usuarioConsulta.setEmail("usuarioaConsulta@gmail");
		listaUsuarios.add(usuarioAdmin);
		listaUsuarios.add(usuarioConsulta);
	}
	
	public void setUpEstrategiasXFallo(){
		setUpUsuario();
		estrategiaEnvioMensaje = new EnvioMensajePorFalla(listaUsuarios);
		estrategiaFalloMock = mock(EstrategiaPorFallo.class);
	}
	
	public void setUpRepositorio() {
		this.setUpLocalComercial();
		this.setUpBanco();
		this.setUpCGP();
		this.setUpColectivos();
		this.repositorioDefault.create(morita);
		this.repositorioDefault.create(libreria);
		
	}	

}
