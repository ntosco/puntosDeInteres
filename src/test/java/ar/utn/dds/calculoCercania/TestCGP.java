package ar.utn.dds.calculoCercania;

import static org.junit.Assert.*;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;
import ar.utn.dds.comunas.Comuna;

public class TestCGP {

	private Point puntoTerminal;
	
	private CentroGestionParticipacion cgpAlmagro;
	private Comuna comuna2;

	private Point punto1comuna;
	private Point punto2comuna;
	private Point punto3comuna;
	
	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(9,20);
		
		//Comuna
		comuna2 = new Comuna();
	
		punto1comuna = new Point(10,20);
		punto2comuna = new Point(10.005,20.001);
		punto3comuna = new Point(10.009,20.005);

		comuna2.setAreaDeComuna(punto1comuna);
		comuna2.setAreaDeComuna(punto2comuna);
		comuna2.setAreaDeComuna(punto3comuna);
		
		// Centro de Gestion y Participación
		cgpAlmagro = new CentroGestionParticipacion();
		cgpAlmagro.setComuna(comuna2);
		
	}
	
	@Test
	public void estoyCercaDeUnCGP(){
		assertFalse(cgpAlmagro.estaCercaDe(puntoTerminal));
	}
	
}
