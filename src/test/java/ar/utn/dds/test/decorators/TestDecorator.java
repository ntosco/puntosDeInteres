package ar.utn.dds.test.decorators;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.POI.POI;
import ar.utn.dds.decorators.AlmacenarResultados;
import ar.utn.dds.decorators.GenerarReporteBusquedasParciales;
import ar.utn.dds.decorators.GenerarReporteBusquedasxFechas;
import ar.utn.dds.decorators.GenerarReporteBusquedasxUsuario;
import ar.utn.dds.decorators.NotificarTiempoDeConsulta;
import ar.utn.dds.managers.ManagerDeConsultas;
import ar.utn.dds.managers.ManagerDeConsultasImpl;
import ar.utn.dds.test.TestBusquedaGeneral;
import ar.utn.dds.utils.Consulta;

public class TestDecorator extends TestBusquedaGeneral {
	
	ManagerDeConsultas managerDeConsulta;
	AlmacenarResultados managerDeAlmacenamiento;
	GenerarReporteBusquedasParciales managerReporteBusquedasParciales;
	GenerarReporteBusquedasxUsuario managerReporteBusquedaxUsuario;
	GenerarReporteBusquedasxFechas managerReporteBusquedaxFecha;
	NotificarTiempoDeConsulta managerNotificarTiempoDeConsulta;
	ManagerDeConsultas managerMixto;
	
	@Before
	public void SetUp(){
		super.SetUp();
		
		managerDeConsulta = new ManagerDeConsultasImpl();
		managerDeAlmacenamiento = new AlmacenarResultados(managerDeConsulta);
		managerReporteBusquedasParciales = new GenerarReporteBusquedasParciales(managerDeConsulta);
		managerReporteBusquedaxUsuario = new GenerarReporteBusquedasxUsuario(managerDeConsulta);
		managerReporteBusquedaxFecha = new GenerarReporteBusquedasxFechas(managerDeConsulta);
		managerNotificarTiempoDeConsulta = new NotificarTiempoDeConsulta(10,managerDeConsulta);
		managerMixto = new AlmacenarResultados(new GenerarReporteBusquedasParciales(new GenerarReporteBusquedasxUsuario(new NotificarTiempoDeConsulta(10,managerDeConsulta))));
		
		
		
	}

	
	@Test
	public void consultaAlmacenadaConResultados(){
		Consulta consulta = new Consulta("15","usuarioA");
		managerDeAlmacenamiento.ejecutarse(consulta);
		assertTrue(managerDeAlmacenamiento.getConsultasAlmacenadas().size() >= 1);
	}
	
	@Test
	public void consultaReporteDeBusquedasParciales(){
		Consulta consulta = new Consulta("15","usuarioA");
		managerReporteBusquedasParciales.ejecutarse(consulta);
		managerReporteBusquedasParciales.ejecutarse(consulta);
		Integer cantidadDeBusqueda = managerReporteBusquedasParciales.getCantParciales().get(1);
		Integer cantidadEsperada = 4;
		//FIXME si pongo directo el 4 en el assert no me lo toma
		assertEquals(cantidadEsperada,cantidadDeBusqueda);
	}
	
	@Test
	public void consultaReporteDeBusquedasxUsuario(){
		Consulta consultaUsuarioA = new Consulta("15","usuarioA");
		Consulta consultaUsuarioB = new Consulta("15","usuarioB");
		managerReporteBusquedaxUsuario.ejecutarse(consultaUsuarioA);
		managerReporteBusquedaxUsuario.ejecutarse(consultaUsuarioB);
		Integer cantidadDeBusquedaUsuarioB = managerReporteBusquedaxUsuario.getListaBusquedasxUsuario().get("usuarioB");
		Integer cantidadEsperada = 4;
		//FIXME si pongo directo el 4 en el assert no me lo toma
		assertEquals(cantidadEsperada,cantidadDeBusquedaUsuarioB);
	}


	
}
