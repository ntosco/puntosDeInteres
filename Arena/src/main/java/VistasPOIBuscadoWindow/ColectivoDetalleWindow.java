package VistasPOIBuscadoWindow;


import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.commons.utils.Observable;

import AppModel.VistaPOIAppModel;


@SuppressWarnings("serial")
@Observable
public class ColectivoDetalleWindow extends POIDetalleWindow {

	public ColectivoDetalleWindow(VistaPOIAppModel model) {
		super(new VistaPOIAppModel());
	}
	
	@Override
	public void createContents(Panel mainPanel){
		new Label(mainPanel).setText("Numero de Linea");
		new Label(mainPanel).setText(getModelObject().getNombre());
		new Label(mainPanel).setText("Direccion");
		new Label(mainPanel).setText(getModelObject().getDireccion());
	}
	
	public static void main(final String[] args) {
		   new ColectivoDetalleWindow(new VistaPOIAppModel()).startApplication();
		  }

}
