package VistasPOIBuscadoWindow;

import java.awt.Color;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.utils.Observable;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.servicios.Servicio;

@SuppressWarnings("serial")
@Observable
public class CGPDetalleWindow extends POIDetalleWindow {

	public CentroGestionParticipacion model;
	
	public CGPDetalleWindow(WindowOwner owner, CentroGestionParticipacion model) {
		super(owner, model);
		this.setTitle("Detalle de CGP");
		this.model = model;
	}
	
	@Override
	public void addFormPanel(Panel mainPanel){
		new Label(mainPanel).setText("Zona donde se ubica").setBackground(Color.WHITE);
		new Label(mainPanel).bindValueToProperty("barrio");
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
					.setTitle("Nombre").setFixedSize(150).bindContentsToProperty("nombre");

		new Column<Servicio>(table) 
					.setTitle("Horario de atencion")
					.setFixedSize(150).bindContentsToProperty("nombreDeJornada");		
	}

}
