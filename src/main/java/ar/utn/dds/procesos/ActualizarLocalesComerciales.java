package ar.utn.dds.procesos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.utn.dds.POI.LocalComercial;
import ar.utn.dds.POI.POI;
import ar.utn.dds.procesos.estrategiaFallo.EstrategiaPorFallo;
import ar.utn.dds.repositorio.Repositorio;
import ar.utn.dds.utils.Estado;

public class ActualizarLocalesComerciales  extends Proceso{


	
	private Repositorio repo = Repositorio.getInstance();
	private EstrategiaPorFallo fallo;
	private String archivo;
	private Boolean flag;
	
	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	@Override
	public void ejecutarse(EstrategiaPorFallo estrategiaPorFallo) {
		// TODO Auto-generated method stub
		fallo = estrategiaPorFallo;
		
		actualizarLocales(archivo);
	}
	
	public void actualizarLocales(String archivo){
		List<LocalComercial> listaLocalesActualizados = leerArchivoTXT(archivo);	
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
					
					Estado estado = new Estado();
					estado.setValor(2);
					estado.setDescripcion("El proceso funciono correctamente");
					this.setEstado(estado);
										
					flag = false;
					
				}else{
					//No es el local que busco
				}
			}
			if(encontroPoi == false){
				fallo.ejecutarse(this);	
				
				Estado estado = new Estado();
				estado.setValor(1);
				estado.setDescripcion("El proceso fallo");
				this.setEstado(estado);
				
				flag = true;
			}
			}else{
				if(encontroPoi == false){
					fallo.ejecutarse(this);	
					
					Estado estado = new Estado();
					estado.setValor(1);
					estado.setDescripcion("El proceso fallo");
					this.setEstado(estado);
					
					flag = true;
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
	

	public List<LocalComercial> leerArchivoTXT(String archivo) {
		List<LocalComercial> listaLocales = new ArrayList<LocalComercial>();	
		  String cadena;
		  String current;
		try {
			current = new java.io.File( "." ).getCanonicalPath();
		      FileReader f = new FileReader(current.replaceAll("\\\\","/") + "/src/main/java/ar/utn/dds/procesos/" + archivo);
		      BufferedReader b = new BufferedReader(f);
		      while((cadena = b.readLine())!=null) {
		    	  String[] parts = cadena.split(";");
		    	  String nombre = parts[0];
		    	  String palabrasClave = parts[1]; 
		    	  String[] arrayPalabrasClave = palabrasClave.split(" ");
		    	  
		  		  LocalComercial localArchivoDeTexto = new LocalComercial();
				  List<String> listaPalabrasClave = new ArrayList<String>();
		    	  listaPalabrasClave = Arrays.asList(arrayPalabrasClave);
		    	  localArchivoDeTexto.setNombre(nombre);
		    	  localArchivoDeTexto.setListaPalabrasClave(listaPalabrasClave);
		    	  listaLocales.add(localArchivoDeTexto);
		      }
		      b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fallo.ejecutarse(this);
			
			Estado estado = new Estado();
			estado.setValor(1);
			estado.setDescripcion("El proceso fallo");
			this.setEstado(estado);
			
			flag = true;
		}		  

		return listaLocales;
	}
	




}
