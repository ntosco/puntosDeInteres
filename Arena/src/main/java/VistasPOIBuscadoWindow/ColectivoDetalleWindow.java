package VistasPOIBuscadoWindow;


import java.awt.Color;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import ar.utn.dds.POI.ParadaDeColectivo;


@SuppressWarnings("serial")
@Observable
public class ColectivoDetalleWindow extends POIDetalleWindow {

	public ParadaDeColectivo model;
	
	public ColectivoDetalleWindow(WindowOwner owner, ParadaDeColectivo model) {
		super(owner, model);
		this.setTitle("Detalle de parada de colectivo");
		this.model = model;
	}
	
	@Override
	public void createFormPanel(Panel mainPanel){
		new Label(mainPanel).setText("Numero de Linea").setBackground(Color.WHITE).setWidth(300);
		new Label(mainPanel).bindValueToProperty("linea");
		new Label(mainPanel).setText("Direccion").setBackground(Color.WHITE);
		new Label(mainPanel).bindValueToProperty("direccionNombre");
	}
	
}
