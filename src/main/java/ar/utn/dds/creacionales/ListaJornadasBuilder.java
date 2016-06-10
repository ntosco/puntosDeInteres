package ar.utn.dds.creacionales;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

public class ListaJornadasBuilder {

	private List<Jornada> jornadas;
	 
	public ListaJornadasBuilder(){
		jornadas = new ArrayList<Jornada>();
	}

	public List<Jornada> buildJornadas(List<DayOfWeek> lunesAViernes, RangoHorario rango) {
		lunesAViernes.forEach(dia -> agregarALaLista(dia, rango));
		return jornadas;
	}
	
	public void agregarALaLista(DayOfWeek dia, RangoHorario rango){
		jornadas.add(new Jornada(dia, rango));
	}
		
	
}
