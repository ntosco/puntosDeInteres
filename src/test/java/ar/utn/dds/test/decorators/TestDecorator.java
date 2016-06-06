package ar.utn.dds.test.decorators;

import static org.junit.Assert.*;

import java.time.LocalDate;

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
		int cantidadConPalabraClave15 = managerReporteBusquedasParciales.getCantParciales().get(0);
		managerReporteBusquedasParciales.busquedaGeneral("15");
		int cantidadDeBusqueda = managerReporteBusquedasParciales.getCantParciales().get(1);
		assertEquals(cantidadConPalabraClave15,cantidadDeBusqueda);
	}
	
	@Test
	public void consultaReporteDeBusquedasPorFechaAsignacinDeFecha(){
		managerReporteBusquedaxFecha.busquedaGeneral("15");
		managerReporteBusquedaxFecha.busquedaGeneral("15");
		assertTrue(managerReporteBusquedaxFecha.getListaBusquedasxFecha().containsKey(LocalDate.now().toString()));
	}
	
	@Test
	public void consultaReporteDeBusquedasPorFechaAsignacinDeValorAFechaa(){ //FIXME: Cambiar nombre test
		managerReporteBusquedaxFecha.busquedaGeneral("15");
		managerReporteBusquedaxFecha.busquedaGeneral("15");
		Integer cantidadDeBusquedasEsperadas = 2;
		Integer cantidadDeBusquedasDelDiaActual = managerReporteBusquedaxFecha.getListaBusquedasxFecha().get(LocalDate.now().toString());
		assertEquals(cantidadDeBusquedasDelDiaActual,cantidadDeBusquedasEsperadas);
	}



	
}
