package org.iesalandalus.programacion.tutorias.mvc.vista.texto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

	private Consola() {

	}

	// MOSTRAR MENÚ

	public static void mostrarMenu() {
		System.out.println("*************************************************************");
		System.out.println("*          GESTIÓN  DE TUTORÍAS DEL IES AL-ÁNDALUS          *");
		System.out.println("*************************************************************");
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
		System.out.println("*************************************************************");
	}

	// MOSTRAR CABECERA

	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String formatoStr = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(formatoStr, 0).replace("0", "-"));
	}

	// ELEGIR OPCIÓN

	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("¿Qué opción desea elegir?: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}

	// LEER ALUMNO

	public static Alumno leerAlumno() {
		System.out.print("Introduce el nombre del alumno: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce el correo del alumno: ");
		String correo = Entrada.cadena();
		Alumno alumno = new Alumno(nombre, correo);

		return alumno;

	}

	public static Alumno leerAlumnoFicticio() {
		System.out.print("Introduce el correo del alumno: ");
		return Alumno.getAlumnoFicticio(Entrada.cadena());
	}

	// LEER PROFESOR

	public static Profesor leerProfesor() {
		System.out.print("Introduce el nombre del profesor: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce el dni del profesor: ");
		String dni = Entrada.cadena();
		System.out.print("Introduce el correo del profesor: ");
		String correo = Entrada.cadena();
		Profesor profesor = new Profesor(nombre, dni, correo);

		return profesor;
	}

	public static Profesor leerProfesorFicticio() {
		System.out.print("Introduce el dni del profesor: ");
		String dni = Entrada.cadena();
		return Profesor.getProfesorFicticio(dni);
	}

	// LEER TUTORÍA

	public static Tutoria leerTutoria() {
		System.out.print("Introduce el nombre de la tutoría: ");
		String nombre = Entrada.cadena();
		Profesor profesor = leerProfesorFicticio();

		return new Tutoria(profesor, nombre);
	}

	// LEER SESIÓN

	public static Sesion leerSesion() {
		LocalDate fecha = null;
		do {
			System.out.println("Introduzca una fecha en el formato dd/mm/aaaa: ");
			try {
				fecha = LocalDate.parse(Entrada.cadena(), FORMATO_FECHA);
			} catch (DateTimeParseException e) {
				System.out.println("La fecha introducida no está en el formato correcto o no es válida.");
			}
		} while (fecha == null);

		LocalTime horaInicio = null;
		do {
			System.out.println("Introduzca la hora de inicio: ");
			try {
				horaInicio = LocalTime.parse(Entrada.cadena(), FORMATO_HORA);
			} catch (DateTimeParseException e) {
				System.out.println("La hora introducida no está en el formato correcto o no es válida.");
			}
		} while (horaInicio == null);

		LocalTime horaFin = null;
		do {
			System.out.println("Introduzca la hora de fin: ");
			try {
				horaFin = LocalTime.parse(Entrada.cadena(), FORMATO_HORA);
			} catch (DateTimeParseException e) {
				System.out.println("La hora introducida no está en el formato correcto o no es válida.");
			}
		} while (horaFin == null);

		System.out.println("Introduzca los minutos de duración: ");
		int minutosDuracion = Entrada.entero();

		Tutoria tutoria = leerTutoria();

		return new Sesion(tutoria, fecha, horaInicio, horaFin, minutosDuracion);
	}

	public static Sesion leerSesionFicticia() {
		LocalDate fecha = null;
		do {
			System.out.println("Introduzca la fecha de la tutoría en el formato dd/mm/aaaa: ");
			try {
				fecha = LocalDate.parse(Entrada.cadena(), FORMATO_FECHA);
			} catch (DateTimeParseException e) {
				System.out.println("La fecha introducida no está en el formato correcto o no es válida.");
			}
		} while (fecha == null);
		return Sesion.getSesionFicticia(leerTutoria(), fecha);
	}

	// LEER CITA

	public static Cita leerCita() {
		LocalTime hora = null;
		do {
			System.out.println("Introduzca una hora en el formato HH:mm : ");
			try {
				hora = LocalTime.parse(Entrada.cadena(), FORMATO_HORA);
			} catch (DateTimeParseException e) {
				System.out.println("La hora introducida no está en el formato correcto o no es válida.");
			}
		} while (hora == null);

		Alumno alumno = leerAlumno();
		Sesion sesion = leerSesion();

		return new Cita(alumno, sesion, hora);
	}
}
