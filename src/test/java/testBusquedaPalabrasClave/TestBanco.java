package testBusquedaPalabrasClave;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.servicios.Servicio;

public class TestBanco {

	private SucursalBanco sucursalRetiro;
	private SucursalBanco sucursalMartinez;
	
	private Servicio asesoramientoLegal;
	private Servicio pagoDeFacturas;
	private Servicio Rentas;

	@Before
	public void setUp() {
		
		pagoDeFacturas.setNombre("Pago de facturas");
		asesoramientoLegal.setNombre("Asesoramiento Legal");
		Rentas.setNombre("Rentas");
		
		ArrayList <Servicio> listaServiciosRetiro = new ArrayList <Servicio>();
		listaServiciosRetiro.add(asesoramientoLegal);
		listaServiciosRetiro.add(Rentas);
		sucursalRetiro.setListaServicios(listaServiciosRetiro);
		
		ArrayList <Servicio> listaServiciosMartinez = new ArrayList <Servicio>();
		listaServiciosMartinez.add(asesoramientoLegal);
		listaServiciosMartinez.add(pagoDeFacturas);
		sucursalMartinez.setListaServicios(listaServiciosMartinez);

	}

	
	@Test
	public void testElBancoPrestaAlgunoDeLosServiciosBuscados(){
		assertTrue(sucursalRetiro.contieneKeyword("Pago"));
	}
}
