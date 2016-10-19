package ar.utn.dds.creacionales;

import java.time.DayOfWeek;

import ar.utn.dds.utils.Jornada;
import ar.utn.dds.utils.RangoHorario;

public class JornadaBuilder {

	public Jornada build(DayOfWeek dia, RangoHorario rango){
		Jornada jornada = new Jornada();
		jornada.setDiaSemanal(dia);
		jornada.setHorarioDeAtencion(rango);
		return jornada;
		
	}
}
