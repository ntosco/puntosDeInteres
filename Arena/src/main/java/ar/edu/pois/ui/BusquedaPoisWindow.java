package ar.edu.pois.ui;

import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.widgets.List;

import ar.utn.dds.POI.POI;
import ar.utn.dds.arena.appModel.BusquedaPois;

public class BusquedaPoisWindow extends SimpleWindow<BusquedaPois> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusquedaPoisWindow(WindowOwner parent) {
		super(parent, new BusquedaPois());
	}

	@Override
	protected void addActions(Panel actionsPanel) {

		new Button(actionsPanel).setCaption("Buscar").onClick(() -> this.getModelObject().buscarPOI());

	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Buscador");
		this.setTaskDescription("Criterio de buqueda");

		super.createMainTemplate(mainPanel);
		this.createResultsGrid(mainPanel);
		this.createGridActions(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		// TODO Arreglar layout
		searchFormPanel.setLayout(new ColumnLayout(3));
		new Label(searchFormPanel).setText("Nombre");
		new TextBox(searchFormPanel).bindValueToProperty("palabraClave");
		new Button(searchFormPanel) 
		.setCaption("Agregar palabra").onClick(() -> this.getModelObject().agregarPalabrasClave());

		new List<String>(searchFormPanel).bindItemsToProperty("listaPalabrasClaves");
		

	}

	@SuppressWarnings("unchecked")
	private void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button view = new Button(actionsPanel);
		view.setCaption("Ver");
		view.onClick(() -> this.getModelObject().visualizarPOI());


		NotNullObservable elementSelected = new NotNullObservable("poiSeleccionado");
		view.bindEnabled(elementSelected);
	}

	private void createResultsGrid(Panel mainPanel) {
		Table<POI> table = new Table<POI>(mainPanel, POI.class);

		table.setNumberVisibleRows(10);

		table.bindItemsToProperty("pois");
		table.bindValueToProperty("poiSeleccionado");

		this.describeResultsGrid(table);
	}

	private void describeResultsGrid(Table<POI> table) {
		new Column<POI>(table) 
				.setTitle("Nombre").setFixedSize(100).bindContentsToProperty("nombre");

		new Column<POI>(table) 
				.setTitle("Dirección").setFixedSize(100).bindContentsToProperty("direccionNombre");

	}
	
	
	

}
