package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ICitas;

public class Citas implements ICitas {

	// Declaración de atributos

	private List<Cita> coleccionCitas;
	
	private static final String NOMBRE_FICHERO_CITAS = "datos/citas.dat";

	// Constructor

	public Citas() {
		coleccionCitas = new ArrayList<>();
	}
	
	//Comenzar
	
	@Override
	public void comenzar() {
		leer();
	}
	
	private void leer() {
		File ficheroCitas = new File(NOMBRE_FICHERO_CITAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroCitas))) {
			Cita cita = null;
			do {
				cita = (Cita) entrada.readObject();
				insertar(cita);
			} while (cita != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fihero de citas.");
		} catch (EOFException e) {
			System.out.println("Fichero citas leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Terminar

	@Override
	public void terminar() {
		escribir();
	}
	
	private void escribir() {
		File ficheroCitas = new File(NOMBRE_FICHERO_CITAS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroCitas))){
			for (Cita cita : coleccionCitas)
				salida.writeObject(cita);
			System.out.println("Fichero citas escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de citas.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		}
	}

	// Copia profunda

	private List<Cita> copiaProfundaCita() {
		List<Cita> copiaCitas = new ArrayList<>();
		for (Cita cita : coleccionCitas) {
			copiaCitas.add(new Cita(cita));
		}
		return copiaCitas;
	}

	// Get

	@Override
	public List<Cita> get() {
		List<Cita> citasOrdenadas = copiaProfundaCita();
		Comparator<Profesor> comparadorProfesor = Comparator.comparing(Profesor::getDni);
		Comparator<Tutoria> comparadorTutoria = Comparator.comparing(Tutoria::getProfesor, comparadorProfesor)
				.thenComparing(Tutoria::getNombre);
		Comparator<Sesion> comparadorSesion = Comparator.comparing(Sesion::getTutoria, comparadorTutoria)
				.thenComparing(Sesion::getFecha);
		citasOrdenadas.sort(Comparator.comparing(Cita::getSesion, comparadorSesion).thenComparing(Cita::getHora));
		return citasOrdenadas;
	}

	// Cuando se listen las citas de una sesión se mostrarán ordenadas por hora de
	// la sesión

	@Override
	public List<Cita> get(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: La sesión no puede ser nula.");
		}
		List<Cita> citasSesion = new ArrayList<>();
		for (Cita cita : coleccionCitas) {
			if (cita.getSesion().equals(sesion)) {
				citasSesion.add(new Cita(cita));
			}
		}
		citasSesion.sort(Comparator.comparing(Cita::getHora));
		return citasSesion;
	}

	// Cuando se listen las citas de un alumno se mostrarán ordenadas por sesión y
	// por hora de la sesión.

	@Override
	public List<Cita> get(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		List<Cita> citasAlumno = new ArrayList<>();
		for (Cita cita : coleccionCitas) {
			if (cita.getAlumno().equals(alumno)) {
				citasAlumno.add(new Cita(cita));
			}
		}

		Comparator<Profesor> comparadorProfesor = Comparator.comparing(Profesor::getDni);
		Comparator<Tutoria> comparadorTutoria = Comparator.comparing(Tutoria::getProfesor, comparadorProfesor)
				.thenComparing(Tutoria::getNombre);
		Comparator<Sesion> comparadorSesion = Comparator.comparing(Sesion::getTutoria, comparadorTutoria)
				.thenComparing(Sesion::getFecha);
		citasAlumno.sort(Comparator.comparing(Cita::getSesion, comparadorSesion).thenComparing(Cita::getHora));

		return citasAlumno;
	}

	// Get

	@Override
	public int getTamano() {
		return coleccionCitas.size();
	}

	// Insertar

	@Override
	public void insertar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		int indice = coleccionCitas.indexOf(cita);
		if (indice == -1) {
			coleccionCitas.add(new Cita(cita));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita con esa hora.");
		}

	}

	// Buscar

	@Override
	public Cita buscar(Cita cita) {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una cita nula.");
		}
		int indice = coleccionCitas.indexOf(cita);
		if (indice == -1) {
			return null;
		} else {
			return new Cita(coleccionCitas.get(indice));
		}
	}

	// Borrar

	@Override
	public void borrar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}
		int indice = coleccionCitas.indexOf(cita);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita con esa hora.");
		} else {
			coleccionCitas.remove(indice);
		}
	}
}
