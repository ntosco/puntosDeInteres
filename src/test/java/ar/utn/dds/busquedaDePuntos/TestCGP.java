package ar.utn.dds.busquedaDePuntos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.servicios.Servicio;

public class TestCGP {
	
	private CentroGestionParticipacion cgpCaballito;
	private CentroGestionParticipacion cgpAlmagro;
	
	private Servicio asesoramientoLegal;
	private Servicio pagoDeFacturas;
	private Servicio Rentas;
	
	
	@Before
	public void setUp(){
		
		pagoDeFacturas.setNombre("Pago de facturas");
		asesoramientoLegal.setNombre("Asesoramiento Legal");
		Rentas.setNombre("Rentas");
		
		ArrayList <Servicio> listaServiciosCaballito = new ArrayList <Servicio>();
		listaServiciosCaballito.add(asesoramientoLegal);
		listaServiciosCaballito.add(Rentas);
		cgpCaballito.setListaServicios(listaServiciosCaballito);
		
		ArrayList <Servicio> listaServiciosAlmagro = new ArrayList <Servicio>();
		listaServiciosAlmagro.add(asesoramientoLegal);
		listaServiciosAlmagro.add(pagoDeFacturas);
		cgpAlmagro.setListaServicios(listaServiciosAlmagro);
	}
	
	@Test
	public void testContieneServicioDelTextoLibre(){
		assertTrue(cgpCaballito.cumpleCondicionBusqueda("Rentas"));
	}

}
