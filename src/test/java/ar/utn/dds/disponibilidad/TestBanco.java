package ar.utn.dds.disponibilidad;

import static org.junit.Assert.*;

import org.junit.*;
import org.uqbar.geodds.Point;
import ar.utn.dds.POI.*;


public class TestBanco {

	private SucursalBanco sucursalRetiro;
	private SucursalBanco sucursalMartinez;
	private Point ubicacionSucursal;
	private Point ubicacionSucursalLejana;
	private Point puntoTerminal;
	private Point puntoTerminal2;
	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(10,20);
		puntoTerminal2 = new Point(1000,2000);

		//Sucursal Banco
		sucursalRetiro = new SucursalBanco();
		ubicacionSucursal = new Point(10.004,20);
		sucursalRetiro.setUbicacionActual(ubicacionSucursal);
		sucursalMartinez = new SucursalBanco();
		ubicacionSucursalLejana = new Point(800, 200);
		sucursalMartinez.setUbicacionActual(ubicacionSucursalLejana);
	}
	
	@Test
	public void estoyCercaDeUnaSucursalCercana(){
		assertTrue(sucursalRetiro.estaCercaDe(puntoTerminal));
	}
	
	@Test
	public void noEstoyCercaDeUnaSucursalLejana(){
		assertFalse(sucursalMartinez.estaCercaDe(puntoTerminal));
	}

} 
