package ar.utn.dds.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.repositorio.RepoPoisNeo4j;

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

}
