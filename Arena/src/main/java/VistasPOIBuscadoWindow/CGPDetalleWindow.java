package VistasPOIBuscadoWindow;

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import AppModel.ZonaServicioAppModel;

@SuppressWarnings("serial")
@Observable
public class CGPDetalleWindow extends BancoDetalleWindow {

	public CGPDetalleWindow(WindowOwner owner, ZonaServicioAppModel model) {
		super(owner, model);
	}
	
	@Override
	public void createFormPanel(Panel mainPanel){
		this.createMainFormContents(mainPanel);
		
		//this.createTable(Servicios)
		//Hacer la tabla Con los servicios y horarios
		
	}

}
