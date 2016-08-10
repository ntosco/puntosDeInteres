package VistasPOIBuscadoWindow;

import java.awt.Color;

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
		new Label(mainPanel).setText("Nombre").setBackground(Color.WHITE).setWidth(300);
		new Label(mainPanel).bindValueToProperty("nombre");
		new Label(mainPanel).setText("Direccion").setBackground(Color.WHITE);
		new Label(mainPanel).bindValueToProperty("direccionNombre");
		this.addFormPanel(mainPanel);
//		new Label(mainPanel).bindValueToProperty(this.model.getDireccionNombre()
//				.concat(" ")
	//			.concat(Integer.toString(this.model.getDireccionNumero())));

	}
	
	void addFormPanel(Panel mainPanel) {
		this.addFormPanel(mainPanel);
	}
	
	
}
