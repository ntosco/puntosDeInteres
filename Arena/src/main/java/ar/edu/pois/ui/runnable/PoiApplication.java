package ar.edu.pois.ui.runnable;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import ar.edu.pois.ui.BusquedaPoisWindow;

public class PoiApplication extends Application{
	
	public static void main(String[] args) {
		new PoiApplication().start();
	}

	@Override
	protected Window<?> createMainWindow() {
		return new BusquedaPoisWindow(this);
	}

}
