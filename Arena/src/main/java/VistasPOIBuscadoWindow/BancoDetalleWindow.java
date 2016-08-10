package VistasPOIBuscadoWindow;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import AppModel.ZonaServicioAppModel;

@SuppressWarnings("serial")
@Observable
public class BancoDetalleWindow extends POIDetalleWindow{

	public BancoDetalleWindow(WindowOwner owner, ZonaServicioAppModel model) {
		super(owner, model);
	}
	
	@Override
	public void createFormPanel(Panel mainPanel){
		this.createMainFormContents(mainPanel);
		new Label(mainPanel).setText("Zona");
	//	new Label(mainPanel).bindValueToProperty(getModelObject().getZona());
		// Crear tabla
	}

}
