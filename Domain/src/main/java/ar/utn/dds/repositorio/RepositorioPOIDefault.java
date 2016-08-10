package ar.utn.dds.repositorio;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import ar.utn.dds.creacionales.BancoBuilder;
import ar.utn.dds.creacionales.CgpBuilder;
import ar.utn.dds.creacionales.ColectivoBuilder;
import ar.utn.dds.creacionales.ListaJornadasBuilder;
import ar.utn.dds.creacionales.LocalComercialBuilder;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;
import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.ParadaDeColectivo;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.POI.SucursalBanco;

public class RepositorioPOIDefault extends Repositorio {

	public RepositorioPOIDefault() {
		super();
		
		List<DayOfWeek> lunesAViernes = new ArrayList<DayOfWeek>();
		lunesAViernes.add(DayOfWeek.MONDAY);
		lunesAViernes.add(DayOfWeek.TUESDAY);
		lunesAViernes.add(DayOfWeek.WEDNESDAY);
		lunesAViernes.add(DayOfWeek.THURSDAY);
		lunesAViernes.add(DayOfWeek.FRIDAY);
		
		ListaJornadasBuilder builderJornadaBancaria = new ListaJornadasBuilder();
		RangoHorario rangoBancario = new RangoHorario(LocalTime.of(10,0,0), LocalTime.of(15,0,0));
		List<Jornada> jornadaBancaria = builderJornadaBancaria.buildJornadas(lunesAViernes, rangoBancario);
		
		Servicio depositos = new Servicio("depositos", jornadaBancaria);
		depositos.setNombreDeJornada("Lun a Vie 10 a 15");
		Servicio consultaSaldo = new Servicio("consulta de saldo", jornadaBancaria);
		consultaSaldo.setNombreDeJornada("Lun a Vier 10 a 15");
		
		List<Servicio> servicioCajeroAutomatico = new ArrayList<Servicio>();
		servicioCajeroAutomatico.add(depositos);
		servicioCajeroAutomatico.add(consultaSaldo);
		

		Point ubicacion = new Point(45, 20);
		
		List<String> palabrasClaveBancoFrances = new ArrayList<String>();
		palabrasClaveBancoFrances.add("deposito");
		palabrasClaveBancoFrances.add("extraccion");
		palabrasClaveBancoFrances.add("consulta");
		palabrasClaveBancoFrances.add("saldo");
		
		List<String> palabrasClaveCGPPaternal = new ArrayList<String>();
		palabrasClaveCGPPaternal.add("paternal");
		palabrasClaveCGPPaternal.add("argentinos");
		palabrasClaveCGPPaternal.add("boyaca");
		
		Point punto1comuna = new Point(11, 20);
		Point punto2comuna = new Point(10.005, 20.001);
		Point punto3comuna = new Point(10.009, 20.005);
		Point punto4comuna = new Point(10, 20);
		
		List<Point> puntosComunaPaternal = new ArrayList<Point>();
		puntosComunaPaternal.add(punto1comuna);
		puntosComunaPaternal.add(punto2comuna);
		puntosComunaPaternal.add(punto4comuna);
		puntosComunaPaternal.add(punto3comuna);
		
		Rubro comidas = new Rubro("comidas", 0.1);
		List<Rubro>	rubroComidas = new ArrayList<Rubro>();
		rubroComidas.add(comidas);
		
		BancoBuilder builderFrances = new BancoBuilder();
		builderFrances.crearListaServicios(servicioCajeroAutomatico)
					.setNombre("Frances San Cristobal")
					.setBarrio("San Cristobal")
					.setDireccion("Estados Unidos")
					.setNumero(2206)
					.setUbicacion(ubicacion)
					.setPalabrasClave(palabrasClaveBancoFrances)
					.setJornada(jornadaBancaria);
		
		CgpBuilder builderPaternal = new CgpBuilder();
		builderPaternal.crearComuna(puntosComunaPaternal)
						.crearListaServicios(servicioCajeroAutomatico)
						.setNombre("Paternal")
						.setBarrio("Paternal")
						.setDireccion("Boedo")
						.setNumero(156)
						.setUbicacion(ubicacion)
						.setPalabrasClave(palabrasClaveCGPPaternal)
						.setJornada(jornadaBancaria);
		
		LocalComercialBuilder builderHeinsenburguer = new LocalComercialBuilder();
		builderHeinsenburguer.crearListaRubros(rubroComidas)
						.setNombre("Heinsenburguer")
						.setBarrio("Colegiales")
						.setDireccion("Chile")
						.setNumero(147)
						.setUbicacion(ubicacion)
						.setPalabrasClave(palabrasClaveCGPPaternal)
						.setJornada(jornadaBancaria);
		
		
		ColectivoBuilder builder114 = new ColectivoBuilder();
		builder114.crearLinea("15")
						.setNombre("Parada San Telmo")
						.setBarrio("San Telmo")
						.setDireccion("Belgrano")
						.setNumero(156)
						.setUbicacion(ubicacion)
						.setPalabrasClave(palabrasClaveCGPPaternal);
						
		SucursalBanco bancoFrances = builderFrances.build();
		CentroGestionParticipacion cgpPaternal =  builderPaternal.build();
		LocalComercial heinsenburger =  builderHeinsenburguer.build();
		ParadaDeColectivo parada114 = builder114.build();
		this.create(bancoFrances);
		this.create(cgpPaternal);
		this.create(heinsenburger);
		this.create(parada114);
	}
	
	

}
