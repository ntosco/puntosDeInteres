package ar.utn.dds.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.Review;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.juegoDeDatos.StubBuscadorBanco;
import ar.utn.dds.utils.AdapterBancos;

public class TestReviews extends JuegoDeDatos{

	@Before

	public void SetUp(){
		setUpGeneral();

		
	}
	
	@Test
	public void agregarUnaReview() {
		LocalComercial local = new LocalComercial();
		local.setReviews(new ArrayList <Review>());
		Review opinion = new Review("muy buen poi","samo",3);
		local.agregarReview(opinion);
		assertTrue(local.getReviews().size() == 1 );
	}
	
	@Test
	public void calcularValoracionPromedio() {
		LocalComercial local = new LocalComercial();
		local.setReviews(new ArrayList <Review>());
		Review opinion = new Review("muy buen poi","samo",3);
		Review opinion2 = new Review("muy feo poi","martin",5);
		local.agregarReview(opinion);
		local.agregarReview(opinion2);
		assertTrue(local.calcularValoracionPromedio() == 4 );
	}
	
	@Test
	public void calcularValoracionPromedioDouble() {
		LocalComercial local = new LocalComercial();
		local.setReviews(new ArrayList <Review>());
		Review opinion = new Review("muy buen poi","samo",4);
		Review opinion2 = new Review("muy feo poi","martin",5);
		local.agregarReview(opinion);
		local.agregarReview(opinion2);
		assertTrue(local.calcularValoracionPromedio() == 4.5 );
	}
	
	@Test
	public void calcularValoracionPromedioSinReviews() {
		LocalComercial local = new LocalComercial();
		local.setReviews(new ArrayList <Review>());
	
		assertTrue(local.calcularValoracionPromedio() == 0 );
	}

	@Test
	public void usuarioOpinaSobreUnPoi() {
		LocalComercial local = new LocalComercial();
		local.setReviews(new ArrayList <Review>());
		usuarioConsulta.setNombreUsuario("userConsulta");
		usuarioConsulta.opinar(local, "me guuuuuusta el locaaal", 5);

		assertTrue(local.getReviews().size() == 1);
	}
	
	@Test
	public void usuarioOpinaDosVeces() {
		LocalComercial local = new LocalComercial();
		local.setReviews(new ArrayList <Review>());
		usuarioConsulta.setNombreUsuario("userConsulta");
		usuarioConsulta.opinar(local, "me guuuuuusta el locaaal", 5);
		usuarioConsulta.opinar(local, "mas o menos", 3);

		assertTrue(local.getReviews().size() == 1);
	}
	
	@Test
	public void elUsuarioYaOpino() {
		LocalComercial local = new LocalComercial();
		local.setReviews(new ArrayList <Review>());
		usuarioConsulta.setNombreUsuario("userConsulta");
		usuarioConsulta.opinar(local, "me guuuuuusta el locaaal", 5);
		assertTrue(local.elUsuarioYaOpino("userConsulta"));
	}
	
	@Test
	public void elUsuarioTodaviaNoOpino() {
		LocalComercial local = new LocalComercial();
		local.setReviews(new ArrayList <Review>());;
		assertTrue(local.elUsuarioYaOpino("userConsulta") == false);
	}
}
