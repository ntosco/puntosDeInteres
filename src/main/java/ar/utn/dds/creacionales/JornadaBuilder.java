package ar.utn.dds.creacionales;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

public class JornadaBuilder {

	private List<Jornada> jornadas;
	 
	public JornadaBuilder(){
		jornadas = new ArrayList<Jornada>();
	}

	public List<Jornada> buildJornada(List<DayOfWeek> lunesAViernes, int horaDesde, int minDesde, int horaHasta, int minHasta) {
		RangoHorario rangolaboral = new RangoHorario(LocalTime.of(horaDesde, minDesde,0), LocalTime.of(horaHasta, minHasta, 0));
		lunesAViernes.forEach(dia -> agregarALaLista(dia, rangolaboral));
		return jornadas;
	}
	
	public void agregarALaLista(DayOfWeek dia, RangoHorario rango){
		jornadas.add(new Jornada(dia, rango));
	}
	
	
}
