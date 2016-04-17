package juegoDeDatos;

import java.util.ArrayList;

import org.junit.Before;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.comunas.Comuna;
import ar.utn.dds.servicios.Servicio;

public class JuegoDeDatos {
	
	protected static JuegoDeDatos instance;

	protected Point puntoTerminal;
	protected Point puntoTerminal2;
	
	protected CentroGestionParticipacion cgpPalermo;
	protected CentroGestionParticipacion cgpVillaCrespo;

	//
	protected ParadaDeColectivo parada114;//si yo busco 11, me devuelve las paradas del 11 y las del 114, 115, ...?
	protected ParadaDeColectivo parada11;
	protected ParadaDeColectivo parada7Rojo;// si yo busco 7, me deberia devolver 7 ramal medina, 7 ramal samore, etc
	protected ParadaDeColectivo parada7Amarillo;
	protected ParadaDeColectivo parada7Azul;
	
	protected SucursalBanco sucursalRetiro;
	protected SucursalBanco sucursalMartinez;
	
	protected LocalComercial nike;//solo un rubro: indumentaria
	protected LocalComercial falabella; //varios rubros: indumentaria, muebleria, perfumeria, etc
	protected Rubro indumentaria;
	protected Rubro muebleria;
	protected Rubro perfumeria;
	
	protected Servicio asesoramientoLegal;
	protected Servicio pagoDeFacturas;
	protected Servicio asesoramientoTecnico;
	
	
	//juego de datos pensado para el PUNTO1  calculo de cercania 
	
		//Bondis
	protected ParadaDeColectivo parada15;
	protected Point ubicacionParada15;
	protected ParadaDeColectivo parada88;
	protected Point ubicacionParada88;
	
		//CGPS
	
	protected CentroGestionParticipacion cgpCaballito;
	protected CentroGestionParticipacion cgpAlmagro;
	protected Comuna comuna1;
	protected Comuna comuna2;
	protected Point punto1comuna;
	protected Point punto2comuna;
	protected Point punto3comuna;
	
		//Locales comerciales
	
	protected LocalComercial cafeMartinez;
	protected Point ubicacionLocalCafeMartinez;
	
	protected LocalComercial addidas;
	protected Point ubicacionLocalAddidas;
	
	protected LocalComercial panquequesCarlitos;
	protected Point ubicacionLocalPanquequesCarlitos;
	
	protected JuegoDeDatos() {
		
	}
	
	public static JuegoDeDatos getInstance(){
		if (instance == null){
			instance = new JuegoDeDatos();
		}
		return instance;
	}
	
	
	@Before
	public void SetUp(){
		//Punto 3 Busqueda de puntos
	//BONDIS
	parada114.setLinea("114");
	parada11.setLinea("11");
	parada7Rojo.setLinea("7 Barrio Samore");
	parada7Amarillo.setLinea("7 Avellaneda");
	parada7Azul.setLinea("7 Medina");
	//RUBROS
	nike = new LocalComercial();//esta cerca de la terminal
	falabella = new LocalComercial();//esta cerca de la terminal
	indumentaria.setNombre("indumentaria");
	muebleria.setNombre("muebleria");
	perfumeria.setNombre("perfumeria");
	
	ArrayList<Rubro> listaRubrosDeNike = new ArrayList<Rubro>();
	listaRubrosDeNike.add(indumentaria);
	nike.setListaRubros(listaRubrosDeNike);
	
	ArrayList<Rubro> listaRubrosDeFallabella = new ArrayList<Rubro>();
	listaRubrosDeFallabella.add(indumentaria);
	listaRubrosDeFallabella.add(muebleria);
	listaRubrosDeFallabella.add(perfumeria);
	falabella.setListaRubros(listaRubrosDeFallabella);
	
	ArrayList <String> listaPalabrasClaveNike = new ArrayList <String>();
	listaPalabrasClaveNike.add("futbol");
	listaPalabrasClaveNike.add("basket");
	listaPalabrasClaveNike.add("running");
	listaPalabrasClaveNike.add("tenis");
	nike.setListaPalabrasClave(listaPalabrasClaveNike);
	
	ArrayList <String> listaPalabrasClaveFalabella = new ArrayList <String>();
	listaPalabrasClaveFalabella.add("new port");
	listaPalabrasClaveFalabella.add("levis");
	listaPalabrasClaveFalabella.add("running");
	falabella.setListaPalabrasClave(listaPalabrasClaveFalabella);
	
	
	pagoDeFacturas.setNombre("Pago de facturas");
	asesoramientoLegal.setNombre("Asesoramiento Legal");
	asesoramientoTecnico.setNombre("Asesoramiento Tecnico");
	
	ArrayList <Servicio> listaServiciosVillaCrespo = new ArrayList <Servicio>();
	listaServiciosVillaCrespo.add(asesoramientoLegal);
	listaServiciosVillaCrespo.add(asesoramientoTecnico);
	cgpVillaCrespo.setListaServicios(listaServiciosVillaCrespo);
	
	ArrayList <Servicio> listaServiciosBelgrano = new ArrayList <Servicio>();
	listaServiciosBelgrano.add(asesoramientoLegal);
	listaServiciosBelgrano.add(pagoDeFacturas);
	cgpPalermo.setListaServicios(listaServiciosBelgrano);
	
	//Punto 1
	puntoTerminal = new Point(10,20);
	puntoTerminal2 = new Point(1000,2000);
	
		//COLECTIVOS
	
	parada15 = new ParadaDeColectivo();
	ubicacionParada15 = new Point(10.0008,20);// tiene que dar cercana a la terminal
	parada15.setUbicacionActual(ubicacionParada15);
	parada88 = new ParadaDeColectivo();
	ubicacionParada88 = new Point(88.0008,88);// tiene que dar lejana a la terminal
	parada15.setUbicacionActual(ubicacionParada88);
	
		//CGPS
	
	comuna1 = new Comuna();
	comuna2 = new Comuna();

	punto1comuna = new Point(11,20);
	punto2comuna = new Point(10.005,20.001);
	punto3comuna = new Point(10.009,20.005);

	comuna1.setAreaDeComuna(punto1comuna);// se forma un triangulo con los 3 puntos y la terminal esta adentro la comuna1
	comuna1.setAreaDeComuna(punto2comuna);
	comuna1.setAreaDeComuna(punto3comuna);
	
	comuna2.setAreaDeComuna(punto2comuna);// los 3 puntos son el mismo. Entonces la comuna es del tama�o de un punto diferente al de la terminal
	comuna2.setAreaDeComuna(punto2comuna);//la terminal esta fuera de la comuna2
	comuna2.setAreaDeComuna(punto2comuna);
	
	cgpCaballito = new CentroGestionParticipacion();
	cgpCaballito .setComuna(comuna1);//cgp Cercano a la terminal
	
	cgpAlmagro = new CentroGestionParticipacion();
	cgpAlmagro .setComuna(comuna2);//cgp lejano a la termianl
	
		//locales comerciales
	cafeMartinez = new LocalComercial();//esta cerca de la terminal
	ubicacionLocalCafeMartinez = new Point(10.003,20);
	cafeMartinez.setUbicacionActual(ubicacionLocalCafeMartinez);
	cafeMartinez.setRadioDeCercania(0.4);
	
	addidas = new LocalComercial();//esta lejos de la terminal
	ubicacionLocalAddidas = new Point(14,22);
	addidas.setUbicacionActual(ubicacionLocalAddidas);
	addidas.setRadioDeCercania(0.4);
	
	panquequesCarlitos = new LocalComercial();
	ubicacionLocalPanquequesCarlitos = new Point(10,20);
	panquequesCarlitos.setUbicacionActual(ubicacionLocalPanquequesCarlitos);
	panquequesCarlitos.setRadioDeCercania(0.0);// esta en el mismo punto que la terminal y no tiene radio de cercania
		
	
	}

}
