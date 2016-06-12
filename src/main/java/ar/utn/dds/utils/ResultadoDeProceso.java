package ar.utn.dds.utils;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.utn.dds.procesos.Proceso;
import ar.utn.dds.usuarios.Usuario;

public class ResultadoDeProceso {
	
	private LocalDate fecha;
	private LocalTime horarioInicial;
	private LocalTime horarioFinal;
	private Proceso procesoEjecutado;
	private Usuario usuarioEjecutor;
	private Estado estado;

}
