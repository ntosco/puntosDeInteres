package ar.utn.dds.creacionales;

import java.time.DayOfWeek;

import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

public class JornadaBuilder {

	public Jornada build(DayOfWeek dia, RangoHorario rango){
		return new Jornada(dia, rango);
		
	}
}
