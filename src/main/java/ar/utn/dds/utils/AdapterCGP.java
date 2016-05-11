package ar.utn.dds.utils;

import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.POI.CentroGestionParticipacion; //SACAR

import ar.utn.dds.POI.POI;
import ar.utn.dds.ServicioExterno.CentroDTO;
import ar.utn.dds.buscador.BuscadorDeCGP;

public class AdapterCGP extends OrigenDeDatos {

	private BuscadorDeCGP servicioCGP;
	
	@Override
	public List<POI> buscarPOI(String nombre) {
		
			List<CentroDTO> listaDeCentroDTO = servicioCGP.buscarPOI(nombre);
			if (listaDeCentroDTO.isEmpty()){
				return new ArrayList<POI>();
			}else{
				
				List<POI> listaCGP = new ArrayList<POI>(); 	//Pongo que devuelva lista de POI's para que me compile
															//Se podria castear.
				
				//Sacar la clase 'Conversor' y crear los metodos necesarios para que convierta CGP e incluirlos aqui.
				
				listaDeCentroDTO.forEach(dto -> listaCGP.add(Conversor.getInstance().convertirDTOACGP(dto)));			
				return listaCGP;
			}
		}
		
	// ********************************************************
	// ** Getters and Setters
	// ********************************************************
	
	public BuscadorDeCGP getServicioCGP() {
		return servicioCGP;
	}

	public void setServicioCGP(BuscadorDeCGP servicioCGP) {
		this.servicioCGP = servicioCGP;
	}
}
