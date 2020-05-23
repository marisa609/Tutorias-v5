package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;

public class Sesion implements Serializable {

	// Declaración de atributos

	private LocalDate fecha;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private int minutosDuracion;
	private Tutoria tutoria;

	public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private static final LocalTime HORA_COMIENZO_CLASES = LocalTime.of(16, 0);
	private static final LocalTime HORA_FIN_CLASES = LocalTime.of(22, 15);

	// Constructor con parámetros

	public Sesion(Tutoria tutoria, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, int minutosDuracion) {

		setTutoria(tutoria);
		setFecha(fecha);
		setHoraInicio(horaInicio);
		setHoraFin(horaFin);
		setMinutosDuracion(minutosDuracion);
		comprobarValidezSesion();
	}

	// Constructor copia

	public Sesion(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: No es posible copiar una sesión nula.");
		}
		setTutoria(sesion.getTutoria());
		setFecha(sesion.getFecha());
		setHoraInicio(sesion.getHoraInicio());
		setHoraFin(sesion.getHoraFin());
		setMinutosDuracion(sesion.getMinutosDuracion());
	}

	// Get y Set

	public Tutoria getTutoria() {
		return new Tutoria(tutoria); 
	}

	private void setTutoria(Tutoria tutoria) {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
		}
		this.tutoria = new Tutoria(tutoria);
	}

	public LocalDate getFecha() {
		return fecha;
	}

	private void setFecha(LocalDate fecha) {
		if (fecha == null)
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
	
		this.fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	private void setHoraInicio(LocalTime horaInicio) {
		if (horaInicio == null) {
			throw new NullPointerException("ERROR: La hora de inicio no puede ser nula.");
		}
		if (horaInicio.isBefore(HORA_COMIENZO_CLASES) || horaInicio.equals(HORA_FIN_CLASES)
				|| horaInicio.isAfter(HORA_FIN_CLASES)) {
			throw new IllegalArgumentException("ERROR: La hora de inicio no es válida.");
		}

		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	private void setHoraFin(LocalTime horaFin) {
		if (horaFin == null) {
			throw new NullPointerException("ERROR: La hora de fin no puede ser nula.");
		}
		if (horaFin.isAfter(HORA_FIN_CLASES) || horaFin.isBefore(HORA_COMIENZO_CLASES)) {
			throw new IllegalArgumentException("ERROR: La hora de fin no es válida.");
		}

		this.horaFin = horaFin;
	}

	public int getMinutosDuracion() {
		return minutosDuracion;
	}

	private void setMinutosDuracion(int minutosDuracion) {
		if (minutosDuracion <= 0) {
			throw new IllegalArgumentException("ERROR: Los minutos de duración no son válidos.");
		}

		this.minutosDuracion = minutosDuracion;
	}

	private void comprobarValidezSesion() {
		if (horaInicio.equals(horaFin) || horaInicio.isAfter(horaFin)) {

			throw new IllegalArgumentException("ERROR: Las hora para establecer la sesión no son válidas.");
		}

		int minutosSesion = (((horaFin.toSecondOfDay() - horaInicio.toSecondOfDay()) / 60) % minutosDuracion);

		if (minutosSesion % minutosDuracion != 0) {

			throw new IllegalArgumentException(
					"ERROR: Los minutos de duración no es divisor de los minutos establecidos para toda la sesión.");
		}

	}

	// Método getProfesorFicticio TUTORIA

	public static Sesion getSesionFicticia(Tutoria tutoria, LocalDate fecha) {
		return new Sesion(tutoria, fecha, LocalTime.of(16, 0), LocalTime.of(22, 0), 15);
	}
	
	// Método hasCode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((tutoria == null) ? 0 : tutoria.hashCode());
		return result;
	}

	// Método equals

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sesion other = (Sesion) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (tutoria == null) {
			if (other.tutoria != null)
				return false;
		} else if (!tutoria.equals(other.tutoria))
			return false;
		return true;
	}

	// Método toString

	public String toString() {
		return "tutoria=" + getTutoria().toString() + ", fecha=" + getFecha().format(FORMATO_FECHA) + ", horaInicio="
				+ getHoraInicio().format(FORMATO_HORA) + ", horaFin=" + getHoraFin().format(FORMATO_HORA)
				+ ", minutosDuracion=" + getMinutosDuracion() + "";
	}

}
