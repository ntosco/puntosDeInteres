package ar.utn.dds.repositorio;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import ar.utn.dds.creacionales.BancoBuilder;
import ar.utn.dds.creacionales.ListaJornadasBuilder;
import ar.utn.dds.servicios.Servicio;
import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;
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
		
		List<Servicio> servicioCajeroAutomatico = new ArrayList<Servicio>();
		servicioCajeroAutomatico.add(depositos);

		Point ubicacionSucursalFrances = new Point(45, 20);
		
		List<String> palabrasClaveBancoFrances = new ArrayList<String>();
		palabrasClaveBancoFrances.add("deposito");
		palabrasClaveBancoFrances.add("extraccion");
		palabrasClaveBancoFrances.add("consulta");
		palabrasClaveBancoFrances.add("saldo");
		
		BancoBuilder builderFrances = new BancoBuilder();
		builderFrances.crearListaServicios(servicioCajeroAutomatico)
					.setNombre("Frances San Cristobal")
					.setBarrio("San Cristobal")
					.setDireccion("Estados Unidos")
					.setNumero(2206)
					.setUbicacion(ubicacionSucursalFrances)
					.setPalabrasClave(palabrasClaveBancoFrances)
					.setJornada(jornadaBancaria);
		SucursalBanco bancoFrances = builderFrances.build();
		
		this.create(bancoFrances);
	}
	
	

}
