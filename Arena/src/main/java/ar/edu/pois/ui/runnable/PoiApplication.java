package ar.edu.pois.ui.runnable;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.ApplicationContext;

import ar.edu.pois.ui.BusquedaPoisWindow;
import ar.utn.dds.POI.POI;
import ar.utn.dds.juegoDeDatos.JuegoDeDatos;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.repositorio.RepositorioPOIDefault;
import ar.utn.dds.utils.BusquedaDePuntos;

public class PoiApplication extends Application {

	public static void main(String[] args) {
		BusquedaDePuntos.getInstance().setServicio(new RepositorioPOIDefault());
		new PoiApplication().start();
	}

	@Override
	protected Window<?> createMainWindow() {
		return new BusquedaPoisWindow(this);
		
	}

}
