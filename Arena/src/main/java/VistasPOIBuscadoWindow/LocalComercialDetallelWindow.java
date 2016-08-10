package VistasPOIBuscadoWindow;


import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.Rubro;

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
		createResultsGrid(mainPanel);
		
	}
	
	
	
	private void createResultsGrid(Panel mainPanel) {
		Table<Rubro> table = new Table<Rubro>(mainPanel, Rubro.class);

		table.setNumberVisibleRows(10);

		table.bindItemsToProperty("listaRubros");

		this.describeResultsGrid(table);
	}

	
	public void describeResultsGrid(Table<Rubro> table) {
		new Column<Rubro>(table) 
					.setTitle("Rubros que contiene").setFixedSize(300).bindContentsToProperty("nombre");
	}

}
