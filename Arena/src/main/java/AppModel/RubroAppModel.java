package AppModel;

import org.uqbar.commons.utils.Observable;

import ar.utn.dds.POI.Rubro;

@Observable
public class RubroAppModel extends VistaPOIAppModel {
	
	public Rubro rubro;

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}
	

}
