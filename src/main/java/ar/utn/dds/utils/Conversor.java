package ar.utn.dds.utils;

import java.util.List;

import ar.utn.dds.POI.CentroGestionParticipacion;
import ar.utn.dds.ServicioExterno.CentroDTO;
import ar.utn.dds.ServicioExterno.RangoServicioDTO;
import ar.utn.dds.comunas.Comuna;

public class Conversor {
	
	public static void convertirDTOACGP(CentroDTO dto){
		CentroGestionParticipacion nuevoCGP = new CentroGestionParticipacion();
		nuevoCGP.setComuna(convertirAComuna(dto.getComuna()));
		nuevoCGP.setBarrio(dto.getZonasIncluidas());
		nuevoCGP.setDireccionNombre(getDireccionNombre(dto.getDomicilio()));
		nuevoCGP.setDireccionNumero(getDireccionNumero(dto.getDomicilio()));
	}
	
	private static int getDireccionNumero(String domicilio) {
		int numero = 0;
		numero = Integer.parseInt(domicilio);
		return domicilio;
	}

	// Hacer que solo devuelva el nombre de la calle
	private static String getDireccionNombre(String domicilio) {
		return domicilio;
	}


	//Arreglar esto !!!!!!!!!!!!!!!! Hacer que devuelva la comuna asociada a ese numero
	private static Comuna convertirAComuna(int comuna) {
		return new Comuna();
		
	}
	
	

}
