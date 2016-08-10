package VistasPOIBuscadoWindow;


import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import AppModel.RubroAppModel;

@SuppressWarnings("serial")
@Observable
public class LocalComercialDetallelWindow extends POIDetalleWindow {

	public LocalComercialDetallelWindow(WindowOwner owner, RubroAppModel model) {
		super(owner, model);
		
	}
	
	@Override
	public void createContents(Panel mainPanel){
		this.createMainFormContents(mainPanel);
		new Label(mainPanel).setText("Rubro");
	//	new Label(mainPanel).bindValueToProperty(getModelObject().getRubro().getNombre());
	}

}
