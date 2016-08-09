package ar.edu.pois.ui;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MainWindow;
import ar.utn.dds.arena.appModel.BusquedaPois;


public class BusquedaPoisWindow extends MainWindow<BusquedaPois>{

	public BusquedaPoisWindow() {
		super(new BusquedaPois());
	}

	@Override
	public void createContents(Panel mainPanel) {
		this.setTitle("Búsqueda");

		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setText("Criterio de búsqueda");

		new Label(mainPanel).setText("Nombre");
		new TextBox(mainPanel).bindValueToProperty("palabraClave");
		
//		Panel panelColumna = new Panel(mainPanel);
//		panelColumna.setLayout(new ColumnLayout(2));
		
		
		
	}

	public static void main(String[] args) {
		new BusquedaPoisWindow().startApplication();
	}
	
}
