package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno implements Serializable {

	// DeclaraciÃ³n

	private String nombre, correo, expediente;

	private static final String ER_NOMBRE = "([a-zA-ZÁÉÍÓÚáéíóú]+)(\\s+([a-zA-ZÁÉÍÓÚáéíóú]+))+";
	private static final String ER_CORREO = ".+@[a-zA-Z]+\\.[a-zA-Z]+";
	private static final String PREFIJO_EXPEDIENTE = "SP_";
	private static int ultimoIdentificador = 0;

	// MÃ©todo incrementaUltimoIdentificador

	private static void incrementaUltimoIdentificador() {
		ultimoIdentificador++;
	}

	// MÃ©todo formateaNombre

	private String formateaNombre(String nombreAlumno) {
		if (nombreAlumno == null || nombreAlumno.trim().length() == 0) {
			throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo o vacÃ­o.");
		}

		nombreAlumno = nombreAlumno.replace("  ", " ");

		String[] words = nombreAlumno.split("\\s+");

		if (words.length == 0) {
			throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo o vacÃ­o.");
		}

		StringBuilder stb = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
			stb.append(word).append(" ");
		}

		return stb.toString().trim();
	}

	// Constructor con parÃ¡metros

	public Alumno(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
		setExpediente();
	}

	// Constructor copia

	public Alumno(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
		}

		setNombre(alumno.getNombre());
		setCorreo(alumno.getCorreo());
		expediente = alumno.getExpediente();
	}

	// Constructor sobrecargado

	public Alumno(String nombre, String correo, int identificador) {
		setNombre(nombre);
		setCorreo(correo);
		setExpediente(identificador);
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
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato vÃ¡lido.");
		}
		if (!nombre.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato vÃ¡lido.");
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

		Pattern p = Pattern.compile(ER_CORREO);
		Matcher m = p.matcher(correo);
		if (m.matches()) {
			this.correo = correo;
		} else {
			throw new IllegalArgumentException("ERROR: El formato del correo no es vÃ¡lido.");
		}

	}

	public String getExpediente() {
		return expediente;
	}

	private void setExpediente() {

		incrementaUltimoIdentificador();
		expediente = PREFIJO_EXPEDIENTE + getIniciales() + "_" + ultimoIdentificador;

	}

	private void setExpediente(int identificador) {
		if (identificador <= 0) {
			throw new NullPointerException("ERROR: El identificador tiene que ser mayor que 0.");
		}
		this.expediente = PREFIJO_EXPEDIENTE + getIniciales() + "_" + identificador;

	}

	public static void setUltimoIdentificador(int maxIdentificador) {
		Alumno.ultimoIdentificador = maxIdentificador;
	}

	// MÃ©todo getIniciales

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

	public static Alumno getAlumnoFicticio(String correo) {
		if (correo == null)
			throw new NullPointerException("ERROR: El correo no puede ser nulo.");

		if (correo.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El formato del correo no es vÃ¡lido.");
		}

		return new Alumno("Alumnx Inventadx", correo);
	}

	// equals y hashCode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
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
		Alumno other = (Alumno) obj;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		return true;
	}

	// TosString

	@Override
	public String toString() {
		return "nombre=" + nombre + " (" + getIniciales() + ")," + " correo=" + correo + ", expediente=" + expediente;
	}

}
