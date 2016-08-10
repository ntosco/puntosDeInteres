package VistasPOIBuscadoWindow;


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
		new Label(mainPanel).setText("Numero de Linea");
//		new Label(mainPanel).bindValueToProperty(model.getLinea());
		new Label(mainPanel).bindValueToProperty("linea");
		new Label(mainPanel).setText("Direccion");
		new Label(mainPanel).bindValueToProperty("direccionNombre");
/*		new Label(mainPanel).bindValueToProperty(model.getDireccionNombre()
				.concat(" ")
				.concat(Integer.toString(model.getDireccionNumero()))); */
	}
	
}
