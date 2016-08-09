package VistasPOIBuscadoWindow;

import AppModel.VistaPOIAppModel;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.commons.utils.Observable;

@SuppressWarnings("serial")
@Observable
public class POIDetalleWindow extends MainWindow<VistaPOIAppModel> {

	public POIDetalleWindow(VistaPOIAppModel model) {
		super(new VistaPOIAppModel());
	}

	public void createMainContents(Panel mainPanel) {
		new Label(mainPanel).setText("Nombre");
		new Label(mainPanel).setText(getModelObject().getNombre());
		new Label(mainPanel).setText("Direccion");
		new Label(mainPanel).setText(getModelObject().getDireccion());

	}

	@Override
	public void createContents(Panel mainPanel) {
		}

	
}
