package ar.utn.dds.test.decorators;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.decorators.AlmacenarResultados;
import ar.utn.dds.decorators.GenerarReporteBusquedasParciales;
import ar.utn.dds.decorators.GenerarReporteBusquedasxFechas;
import ar.utn.dds.decorators.GenerarReporteBusquedasxUsuario;
import ar.utn.dds.decorators.NotificarTiempoDeConsulta;
import ar.utn.dds.managers.Buscador;
import ar.utn.dds.test.TestBusquedaGeneral;

public class TestDecorator extends TestBusquedaGeneral {
	
	AlmacenarResultados managerDeAlmacenamiento;
	GenerarReporteBusquedasParciales managerReporteBusquedasParciales;
	GenerarReporteBusquedasxUsuario managerReporteBusquedaxUsuario;
	GenerarReporteBusquedasxFechas managerReporteBusquedaxFecha;
	NotificarTiempoDeConsulta managerNotificarTiempoDeConsulta;
	Buscador managerMixto;
	
	@Before
	public void SetUp(){
		super.SetUp();
		
		managerDeAlmacenamiento = new AlmacenarResultados(this.buscadorPuntos);
		managerReporteBusquedasParciales = new GenerarReporteBusquedasParciales(this.buscadorPuntos);
		managerReporteBusquedaxUsuario = new GenerarReporteBusquedasxUsuario(this.buscadorPuntos);
		managerReporteBusquedaxFecha = new GenerarReporteBusquedasxFechas(this.buscadorPuntos);
		managerNotificarTiempoDeConsulta = new NotificarTiempoDeConsulta(10,this.buscadorPuntos,"AdminAlberto");
		managerMixto = new AlmacenarResultados(new GenerarReporteBusquedasParciales(new GenerarReporteBusquedasxUsuario(new NotificarTiempoDeConsulta(10,this.buscadorPuntos,"AdminAlberto"))));
	}

	
	@Test
	public void consultaAlmacenadaConResultados(){
		managerDeAlmacenamiento.busquedaGeneral("15");
		assertTrue(managerDeAlmacenamiento.getConsultasAlmacenadas().size() >= 1);
	}
	
	@Test
	public void consultaReporteDeBusquedasParciales(){
		managerReporteBusquedasParciales.busquedaGeneral("15");
		managerReporteBusquedasParciales.busquedaGeneral("15");
		Integer cantidadDeBusqueda = managerReporteBusquedasParciales.getCantParciales().get(1);
		Integer cantidadEsperada = 4;
		//FIXME si pongo directo el 4 en el assert no me lo toma
		assertEquals(cantidadEsperada,cantidadDeBusqueda);
	}
	



	
}
