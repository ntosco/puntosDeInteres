package VistasPOIBuscadoWindow;


import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import AppModel.VistaPOIAppModel;


@SuppressWarnings("serial")
@Observable
public class ColectivoDetalleWindow extends POIDetalleWindow {

	public ColectivoDetalleWindow(WindowOwner owner, VistaPOIAppModel model) {
		super(owner, model);
	}
	
	@Override
	public void createContents(Panel mainPanel){
		new Label(mainPanel).setText("Numero de Linea");
	//	new Label(mainPanel).bindValueToProperty(getModelObject().getPoiSeleccionado().getLinea());
		new Label(mainPanel).setText("Direccion");
		new Label(mainPanel).setText(getModelObject().getDireccion());
	}
	
}
