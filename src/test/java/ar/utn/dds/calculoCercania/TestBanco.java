package ar.utn.dds.calculoCercania;

import static org.junit.Assert.*;

import org.junit.*;
import org.uqbar.geodds.Point;
import ar.utn.dds.POI.*;

public class TestBanco {

	private SucursalBanco sucursalBanco;
	private Point ubicacionSucursal;
	private Point puntoTerminal;
	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(10,20);

		//Sucursal Banco
		sucursalBanco = new SucursalBanco();
		ubicacionSucursal = new Point(10.004,20);
		sucursalBanco.setUbicacionActual(ubicacionSucursal);
	}
	
	@Test
	public void estoyCercaDeUnaParadaDeColectivo(){
		assertTrue(sucursalBanco.estaCercaDe(puntoTerminal));
	}

}

