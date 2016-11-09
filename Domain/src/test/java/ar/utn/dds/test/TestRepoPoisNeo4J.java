package ar.utn.dds.test;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Node;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.repositorio.POIToNodeConverter;
import ar.utn.dds.repositorio.RepoPoisNeo4j;
import ar.utn.dds.repositorio.UsuarioToNodeConverter;
import ar.utn.dds.usuarios.UsuarioConcreto;

public class TestRepoPoisNeo4J {
	RepoPoisNeo4j repoPois;
	
	@Before
	public void setUp() throws Exception {
		repoPois = RepoPoisNeo4j.getInstace();
	}

	@Test
	public void testGetPois() {
		assertEquals(repoPois.getPois("parada 144").size(),  1);
	}
	
	@Test
	public void testAgregarReviewPorId() {
		repoPois.agregarReview("fer", "parada 144", "4", "no tarda mucho en venir");
		assertEquals(repoPois.getReviewById(15).getComentario(), "no tarda mucho en venir");
		assertEquals(repoPois.getReviewById(15).getValoracion(), 4);
	}
	
	@Test
	public void testObtenerReviewsDeUnPoi() {
		assertEquals(repoPois.getReviews("banco rio").size(),2);
	}
	
	@Test
	public void testAgregarUnReviewYConsultarlo() {	
		repoPois.agregarReview("fer", "parada 144", "4", "no tarda mucho en venir");
		
		assertEquals(repoPois.getReviews("parada 144").get(0).getNombreUsuario(),"fer");	
		assertEquals(repoPois.getReviews("parada 144").get(0).getComentario(),"no tarda mucho en venir");
		assertEquals(repoPois.getReviews("parada 144").get(0).getValoracion(),4);
	}
	
	
	@Test
	public void testGetNodoPoiById() {
		Node nodoPoi = repoPois.getNodoPoiById(5);
		assertEquals(nodoPoi.getId(), 5);
		assertEquals(nodoPoi.getAllProperties().get("nombre"), "parada 144");
		assertEquals(nodoPoi.getAllProperties().get("direccionNombre"), "Mozart");
		assertEquals(nodoPoi.getAllProperties().get("direccionNumero"), "2300");
		assertEquals(nodoPoi.getAllProperties().get("linea"), "114");
	}

	
	@Test
	public void convertirNodoToPoi() {		
		Node nodoParada = repoPois.getNodoPoiById(5);
		POI parada = POIToNodeConverter.convertToPOI(nodoParada, true);
		
		Node nodoCgp = repoPois.getNodoPoiById(7);
		POI cgp = POIToNodeConverter.convertToPOI(nodoCgp, true);
		
		Node nodoBanco = repoPois.getNodoPoiById(8);
		POI banco = POIToNodeConverter.convertToPOI(nodoBanco, true);
		
		Node nodoLocal = repoPois.getNodoPoiById(6);
		POI local = POIToNodeConverter.convertToPOI(nodoLocal, true);
		
		assertEquals(parada.getTipo(), "paradaColectivo");
		assertEquals(parada.getDireccionNombre(), "Mozart");
		assertEquals(parada.getDireccionNumero(), 2300);
		//assertEquals(parada.getLinea(), "114");
		assertEquals(cgp.getTipo(), "cgp");
		assertEquals(banco.getTipo(), "sucursalBanco");
		assertEquals(local.getTipo(), "localComercial");
	}
	
	@Test
	public void convertirCGP(){
		Node nodoCgp = repoPois.getNodoPoiById(7);
		CentroGestionParticipacion cgp = (CentroGestionParticipacion)POIToNodeConverter.convertToPOI(nodoCgp, true);
		assertEquals(cgp.getTipo(), "cgp");
		assertEquals(cgp.getDireccionNumero(), 2300);
		assertEquals(cgp.getListaServicios().size(), 3);
		assertEquals(cgp.getListaServicios().get(0).getNombre(), "a comercial");
	}
	
	@Test
	public void convertirBanco(){
		Node nodoBanco = repoPois.getNodoPoiById(8);
		SucursalBanco banco = new SucursalBanco();
		banco = (SucursalBanco)POIToNodeConverter.convertToPOI(nodoBanco, true);
		assertEquals(banco.getTipo(), "sucursalBanco");
		assertEquals(banco.getNombre(), "banco rio");
		assertEquals(banco.getDireccionNumero(), 2300);
		assertEquals(banco.getJornadaDisponible().size(), 3);
		assertEquals(banco.getJornadaDisponible().get(0).getDiaSemanal(), DayOfWeek.SUNDAY);
	}
	
	@Test
	public void convertirLocalComercial(){
		
		Node nodeLocal = repoPois.getNodoPoiById(6);
				
		LocalComercial local =(LocalComercial)POIToNodeConverter.convertToPOI(nodeLocal, true);
		
		assertEquals(local.getTipo(), "localComercial");
		
		assertEquals(local.getListaRubros().size(), 2);
		assertEquals(local.getListaRubros().get(0).getNombre(), "calzado");
		assertEquals(local.getListaRubros().get(1).getNombre(), "indumentaria");
		
	}
	
	
	@Test
	public void getPoisPorPalabraClave() {
		List<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("hola");
		palabrasClave.add("chau");
		assertEquals(repoPois.getPoisPorPalabraClave(palabrasClave).size(), 1);
		assertEquals(repoPois.getPoisPorPalabraClave(palabrasClave).get(0).getNombre(), "parada 7");
	}

}
