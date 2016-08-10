package VistasPOIBuscadoWindow;

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.SucursalBanco;
import ar.utn.dds.servicios.Servicio;

@SuppressWarnings("serial")
@Observable
public class BancoDetalleWindow extends POIDetalleWindow{

	public BancoDetalleWindow(WindowOwner owner, POI model) {
		super(owner, model);
	}
	
	@Override
	public void addFormPanel(Panel mainPanel){
		createResultsGrid(mainPanel);
		
	}
	
	private void createResultsGrid(Panel mainPanel) {
		Table<Servicio> table = new Table<Servicio>(mainPanel, Servicio.class);

		table.setNumberVisibleRows(10);

		table.bindItemsToProperty("listaServicios");

		this.describeResultsGrid(table);
	}

	
	public void describeResultsGrid(Table<Servicio> table) {
		new Column<Servicio>(table) 
					.setTitle("Nombre").setFixedSize(100).bindContentsToProperty("nombre");
	}

}
