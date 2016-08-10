package VistasPOIBuscadoWindow;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import AppModel.VistaPOIAppModel;

@SuppressWarnings("serial")
@Observable
public class POIDetalleWindow extends Dialog<VistaPOIAppModel> {

	public POIDetalleWindow(WindowOwner owner, VistaPOIAppModel model) {
		super(owner, model);
	}

	public void createMainFormContents(Panel mainPanel) {
		new Label(mainPanel).setText("Nombre");
		new Label(mainPanel).setText(getModelObject().getNombre());
		new Label(mainPanel).setText("Direccion");
		new Label(mainPanel).setText(getModelObject().getDireccion());

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.createMainFormContents(mainPanel);
	}

	
}
