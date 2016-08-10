package VistasPOIBuscadoWindow;


import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import ar.utn.dds.POI.LocalComercial;

@SuppressWarnings("serial")
@Observable
public class LocalComercialDetallelWindow extends POIDetalleWindow {

	public LocalComercial model;
	
	public LocalComercialDetallelWindow(WindowOwner owner, LocalComercial model) {
		super(owner, model);
		this.setTitle("Detalle de Local Comercial");
		this.model = model;
	}
	
	@Override
	public void addFormPanel(Panel mainPanel){
		new Label(mainPanel).setText("Rubro");
		new Label(mainPanel).bindValueToProperty(this.listaDeRubros());
	}
	
	public String listaDeRubros(){
		String rubros = model.getListaRubros().get(0).getNombre();
		model.getListaRubros().forEach(r -> rubros.concat(" ").concat(r.getNombre()));
		return rubros;
	}

}
