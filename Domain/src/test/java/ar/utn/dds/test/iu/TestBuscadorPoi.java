package ar.utn.dds.test.iu;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.utils.BusquedaDePuntos;

public class TestBuscadorPoi extends JuegoDeDatos{
	
	@Before
	
	public void SetUp(){
		this.setUpRepositorio();
		BusquedaDePuntos.getInstance().setServicio(this.repositorioDefault);
	}
	
	

}
