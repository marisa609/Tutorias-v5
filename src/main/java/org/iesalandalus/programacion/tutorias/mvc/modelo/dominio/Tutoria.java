package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public class Tutoria implements Serializable {

	// Declaración

	private String nombre;
	private Profesor profesor;

	// Constructor con parámetros

	public Tutoria(Profesor profesor, String nombre) {
		setProfesor(profesor);
		setNombre(nombre);
	}

	// Constructor copia

	public Tutoria(Tutoria tutoria) {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: No es posible copiar una tutoría nula.");
		}

		setNombre(tutoria.getNombre());
		setProfesor(tutoria.getProfesor());
	}

	// Get y Set

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}
		this.nombre = nombre.trim();
	}

	public Profesor getProfesor() {
		return new Profesor(profesor);
	}

	private void setProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		}
		if (profesor.equals("")) {
			throw new IllegalArgumentException("El nombre de un profesor no puede estar vacío.");
		}
		this.profesor = new Profesor(profesor);
	}

	// Método equals y hasCode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((profesor == null) ? 0 : profesor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tutoria other = (Tutoria) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.toUpperCase().equals(other.nombre.toUpperCase()))
			return false;
		if (profesor == null) {
			if (other.profesor != null)
				return false;
		} else if (!profesor.equals(other.profesor))
			return false;
		return true;
	}

	// TosString

	@Override
	public String toString() {
		return "profesor=" + profesor + ", nombre=" + nombre + "";
	}

}
