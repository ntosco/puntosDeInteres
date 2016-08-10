package VistasPOIBuscadoWindow;

import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import ar.utn.dds.POI.POI;

@SuppressWarnings("serial")
@Observable
public class POIDetalleWindow extends TransactionalDialog<POI> {
	
	public POI model;
	
	public POIDetalleWindow(WindowOwner owner, POI poi) {
		super(owner, poi);
		this.getDelegate().setErrorViewer(this);
		this.model = poi;
	}

	public void createFormPanel(Panel mainPanel) {
		new Label(mainPanel).setText("Nombre");
		new Label(mainPanel).bindValueToProperty("nombre");
		new Label(mainPanel).setText("Direccion");
		new Label(mainPanel).bindValueToProperty("direccionNombre");
//		new Label(mainPanel).bindValueToProperty(this.model.getDireccionNombre()
//				.concat(" ")
	//			.concat(Integer.toString(this.model.getDireccionNumero())));

	}
	
	public void addFormPanel(Panel mainPanel){
	}
	
	
}
