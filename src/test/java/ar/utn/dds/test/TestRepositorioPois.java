package ar.utn.dds.test;

import static org.junit.Assert.*;


import org.junit.*;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.exceptions.BusinessException;

public class TestRepositorioPois extends JuegoDeDatos {

	Repositorio repositorio = Repositorio.getInstance();
	
	@Before
	public void SetUp(){
		setUpGeneral();
		setUpBanco();
		setUpCGP();
		setUpLocalComercial();
		setUpColectivos();
		
//		repositorio.setObjects(Arrays.asList(this.addidas,this.bancoFrances,this.cgpAlmagro,this.parada114,this.cafeMartinez));
	}
	
	@Test
	public void createPoiValido(){
		int size = repositorio.allInstances().size();
		repositorio.create(this.parada15);
		assertEquals(repositorio.allInstances().size(),++size);
	}

	@Test(expected = BusinessException.class)
	public void createPoiInvalido(){
		int size = repositorio.allInstances().size();
		repositorio.create(this.cafeMartinez);
		assertEquals(repositorio.allInstances().size(),++size);
	}
	
	
	@Test(expected = BusinessException.class)
	public void createPoiExistenteEnListaTest(){
		int size = repositorio.allInstances().size();
		repositorio.create(this.parada15);
		repositorio.create(this.parada15);
		assertEquals(++size,repositorio.allInstances().size());

	}
//	
//	@Test
//	public void deletePoiValido(){
//		repositorio.create(this.parada15);
//		int size = repositorio.allInstances().size();
//		repositorio.delete(this.parada15);
//		assertEquals(++size,repositorio.allInstances().size());
//	}
//	
//	@Test
//	public void deletePoiInvalido(){
//		int size = repositorio.allInstances().size();
//		repositorio.create(this.parada15);
//		//Da false ya que no debe dejar añadir otra vez a parada15 -> Se arreglará cuando se implementen exception
//		repositorio.create(this.parada15);
//		assertEquals(++size,repositorio.allInstances().size());
//
//	}
//	@Test
//	public void deletePoiInexistente(){
//		int size = repositorio.allInstances().size();
//		repositorio.create(this.parada15);
//		//Da false ya que no debe dejar añadir otra vez a parada15 -> Se arreglará cuando se implementen exception
//		repositorio.create(this.parada15);
//		assertEquals(++size,repositorio.allInstances().size());
//
//	}
	
	

}
