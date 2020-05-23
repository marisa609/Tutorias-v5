package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.iesalandalus.programacion.utilidades.Comprobaciones;

public class Profesor implements Serializable {

	// Declaración de atributos

	private static final String ER_NOMBRE = "[A-Za-záéíóúÁÉÍÓÚ]+(\\s+[A-Za-záéíóúÁÉÍÓÚ]+)+";
	private static final String ER_CORREO = ".+@[a-zA-Z]+\\.[a-zA-Z]+";
	private final static String ER_DNI = "([0-9]{8})([a-zA-Z])";

	private String nombre, dni, correo;

	private String formateaNombre(String nombreProfesor) {
		if (nombreProfesor == null || nombreProfesor.trim().length() == 0) {
			throw new NullPointerException("ERROR: El nombre de un profesor no puede ser nulo o vacío.");
		}

		nombreProfesor = nombreProfesor.replace("  ", " ");

		String[] words = nombreProfesor.split("\\s+");

		if (words.length == 0) {
			throw new NullPointerException("ERROR: El nombre de un profesor no puede ser nulo o vacío.");
		}

		StringBuilder stb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
			stb.append(word).append(" ");
		}

		return stb.toString().trim();
	}

	// Constructor con parámetros

	public Profesor(String nombre, String dni, String correo) {
		setNombre(nombre);
		setDni(dni);
		setCorreo(correo);
	}

	// Constructor copia

	public Profesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No es posible copiar un profesor nulo.");
		}

		setNombre(profesor.getNombre());
		setDni(profesor.getDni());
		setCorreo(profesor.getCorreo());
	}

	// Métodos Get y Set

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
		if (!nombre.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}

		this.nombre = formateaNombre(nombre);
	}

	public String getCorreo() {
		return correo;
	}

	private void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo no puede ser nulo.");
		}
		if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
		}
		this.correo = correo;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		if (dni.contentEquals("")) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (!comprobarLetraDni(dni)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (comprobarLetraDni(dni)) {
			this.dni = dni;
		}
	}

	private boolean comprobarLetraDni(String dni) {
		return Comprobaciones.dniValido(dni);
	}

	// Método getProfesorFicticio

	public static Profesor getProfesorFicticio(String dni) {
		if (dni == null)
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");

		if (dni.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		return new Profesor("Profesorx Ficticix", dni, "profesorxFicticix@gmail.com");
	}

	// Método hashCode

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	// Método equals

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Profesor)) {
			return false;
		}
		Profesor other = (Profesor) obj;
		return Objects.equals(dni, other.dni);
	}

	// Método toString

	@Override
	public String toString() {
		return "nombre=" + nombre + " (" + getIniciales() + "), DNI=" + dni + ", correo=" + correo + "";
	}

	// Método getIniciales

	private String getIniciales() {

		StringBuilder stb = new StringBuilder();

		Pattern p = Pattern.compile("([A-Z]*)");
		Matcher m = p.matcher(nombre);

		while (m.find()) {
			String inicial = m.group(1);
			stb.append(inicial);
		}

		return stb.toString();

	}

}
