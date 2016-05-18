package ar.utn.dds.usuarios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.uqbar.geodds.Polygon;

import ar.utn.dds.POI.POI;
import ar.utn.dds.observers.Observador;
import ar.utn.dds.utils.BusquedaDePuntos;
import ar.utn.dds.utils.Consulta;

public class UsuarioConsulta implements Usuario {
	private List<Observador> observadores = new ArrayList<Observador>();
	private List<Consulta> listaConsultas = new ArrayList<Consulta>();
	public List<Consulta> getListaConsultas() {
		return listaConsultas;
	}

	public void setListaConsultas(List<Consulta> listaConsultas) {
		this.listaConsultas = listaConsultas;
	}

	private Consulta ultimaConsulta;
	private String nombreUsuario;
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public List<POI> buscarPuntos(String pablabraBuscar) {
		
		List<POI> listaResultado = new ArrayList<POI>();
		
		long startTime = System.nanoTime();
		listaResultado = BusquedaDePuntos.getInstance().busquedaGeneral(pablabraBuscar);
		long endTime = System.nanoTime();
		//long duracion = (endTime - startTime) / 1000000000;// Calculo de tiempo de busqueda
		long duracion = (endTime - startTime) / 1;// Calculo de tiempo de busqueda

		
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		String fechaBusqueda = formato.format(fechaActual);
		
		setUltimaConsulta(new Consulta(this.nombreUsuario, listaResultado.size(),fechaBusqueda, pablabraBuscar , (int)duracion));
		listaConsultas.add(getUltimaConsulta());
		
		
		this.notificarObservadores(getUltimaConsulta());
		return listaResultado;
	}

	@Override
	public void agregarObservador(Observador observerador) {
		observadores.add(observerador);
	}

	@Override
	public void quitarObservador(Observador observerador) {
		observadores.remove(observerador);
	}

	@Override
	public void notificarObservadores(Consulta consulta) {
		observadores.forEach(observer -> observer.actualizar(consulta));
		// TODO Auto-generated method stub
		
	}

	public Consulta getUltimaConsulta() {
		return ultimaConsulta;
	}

	public void setUltimaConsulta(Consulta ultimaConsulta) {
		this.ultimaConsulta = ultimaConsulta;
	}


}
