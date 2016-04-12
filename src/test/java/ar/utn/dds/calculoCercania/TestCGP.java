package ar.utn.dds.calculoCercania;

import static org.junit.Assert.*;

import org.junit.*;
import org.uqbar.geodds.Point;

import ar.utn.dds.POI.*;
import ar.utn.dds.comunas.Comuna;

public class TestCGP {

	private Point puntoTerminal;
	
	private CentroGestionParticipacion cgpAlmagro;
	private CentroGestionParticipacion cgpCaballito;
	private Comuna comuna2;
	private Comuna comuna1;

	private Point punto1comuna;
	private Point punto2comuna;
	private Point punto3comuna;
	
	private Point punto4comuna;
	private Point punto5comuna;
	private Point punto6comuna;

	
	
	@Before
	public void SetUp(){

		// Terminal de consulta
		puntoTerminal = new Point(15,25);
		
		//Comuna
		comuna1 = new Comuna();
		comuna2 = new Comuna();
	
		punto1comuna = new Point(10,20);
		punto2comuna = new Point(20,20);
		punto3comuna = new Point(15,30);
		
		punto4comuna = new Point(10,20);
		punto5comuna = new Point(20,20);
		punto6comuna = new Point(15,10);

		comuna1.setAreaDeComuna(punto1comuna);
		comuna1.setAreaDeComuna(punto2comuna);
		comuna1.setAreaDeComuna(punto3comuna);
		
		comuna2.setAreaDeComuna(punto4comuna);
		comuna2.setAreaDeComuna(punto5comuna);
		comuna2.setAreaDeComuna(punto6comuna);
		
		// Centro de Gestion y Participación
		cgpAlmagro = new CentroGestionParticipacion();
		cgpAlmagro.setComuna(comuna1);
		
		cgpCaballito = new CentroGestionParticipacion();
		cgpCaballito.setComuna(comuna2);
		
	}
	
	@Test
	public void estoyCercaDeUnCGPCercano(){
		assertTrue(cgpAlmagro.estaCercaDe(puntoTerminal));
	}
	
	@Test 
	public void estoyLejosDeUnCGPLejano(){
		assertFalse(cgpCaballito.estaCercaDe(puntoTerminal));
		
	}
	
}
