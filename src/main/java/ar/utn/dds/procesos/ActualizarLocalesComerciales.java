package ar.utn.dds.procesos;
import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.POI;
import ar.utn.dds.POI.Rubro;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.servicios.Servicio;

public class ActualizarLocalesComerciales  implements Proceso{
	private Repositorio repo = Repositorio.getInstance();
	private EstrategiaPorFallo fallo;
	private String nombre;
	@Override
	public void ejecutarse(EstrategiaPorFallo estrategiaPorFallo) {
		// TODO Auto-generated method stub
		fallo = estrategiaPorFallo;
		actualizarLocales();
	}
	
	public void actualizarLocales(){
		List<LocalComercial> listaLocalesActualizados = leerArchivo();
		
		for (int i = 0; i < listaLocalesActualizados.size(); i++) { 
			actualizarLocal(listaLocalesActualizados.get(i));
		}
		
	}
	
	public void actualizarLocal(LocalComercial local){
		boolean encontroPoi = false;
		for (int i = 0; i < local.getListaPalabrasClave().size(); i++) { 
			List<POI> poisEncontrados = repo.search(local.getListaPalabrasClave().get(i));
			if(0 < poisEncontrados.size()){
			for (int a = 0; a < poisEncontrados.size(); a++) {
				if(poisEncontrados.get(a).getNombre().equals(local.getNombre())){
					//Es el local que estaba buscando
					encontroPoi = true;
					//Actualizo sus palabras claves
					poisEncontrados.get(a).setListaPalabrasClave(local.getListaPalabrasClave());
					repo.update(poisEncontrados.get(a));
				}else{
					//No es el local que busco
				}
			}
			if(encontroPoi == false){
				fallo.ejecutarse(this);					
			}
			}else{
				if(encontroPoi == false){
					fallo.ejecutarse(this);					
				}
			}
			
		}
	}

	public EstrategiaPorFallo getFallo() {
		return fallo;
	}

	public void setFallo(EstrategiaPorFallo fallo) {
		this.fallo = fallo;
	}

	public List<LocalComercial> leerArchivo(){
		List<LocalComercial> listaLocales = new ArrayList<LocalComercial>();
	
		LocalComercial localArchivoDeTexto = new LocalComercial();
		localArchivoDeTexto.setNombre("Libreria");
		List<String> listaPalabrasClave = new ArrayList<String>();

		listaPalabrasClave.add("colegio");
		listaPalabrasClave.add("escolar");
		listaPalabrasClave.add("uniformes");
		listaPalabrasClave.add("modas");
		listaPalabrasClave.add("lapiz");
		localArchivoDeTexto.setListaPalabrasClave(listaPalabrasClave);
		
		listaLocales.add(localArchivoDeTexto);
		return listaLocales;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}


}
