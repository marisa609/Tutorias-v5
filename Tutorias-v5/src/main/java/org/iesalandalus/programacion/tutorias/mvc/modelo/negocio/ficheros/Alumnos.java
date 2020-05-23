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
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IAlumnos;

public class Alumnos implements IAlumnos {

	// Declaración

	private List<Alumno> coleccionAlumnos;

	private static final String NOMBRE_FICHERO_ALUMNOS = "datos/alumnos.dat";

	// Constructor

	public Alumnos() {
		coleccionAlumnos = new ArrayList<>();
	}

	// Comenzar

	@Override
	public void comenzar() {
		leer();
	}

	private void leer() {
		File directorio = new File("datos");
		directorio.mkdir();
		File ficheroAlumnos = new File(NOMBRE_FICHERO_ALUMNOS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAlumnos))) {
			Alumno leido = null;
			do {
				leido = new Alumno((Alumno) entrada.readObject());
				Alumno copia = new Alumno(leido.getNombre(), leido.getCorreo());
				insertar(copia);

			} while (leido != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fihero de alumnos.");
		} catch (EOFException e) {
			System.out.println("Fichero alumnos leído satisfactoriamente.");
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
		File ficheroAlumnos = new File(NOMBRE_FICHERO_ALUMNOS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAlumnos))) {
			for (Alumno alumno : coleccionAlumnos)
				salida.writeObject(alumno);
			System.out.println("Fichero alumnos escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de alumnos.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		}
	}

	// Get

	@Override
	public List<Alumno> get() {
		List<Alumno> alumnosOrdenados = copiaProfundaAlumnos();
		alumnosOrdenados.sort(Comparator.comparing(Alumno::getCorreo));
		return alumnosOrdenados;
	}

	// Método copia profunda

	private List<Alumno> copiaProfundaAlumnos() {
		List<Alumno> copiaAlumnos = new ArrayList<>();
		for (Alumno alumno : coleccionAlumnos) {
			copiaAlumnos.add(new Alumno(alumno));
		}
		return copiaAlumnos;
	}

	// getTamano

	@Override
	public int getTamano() {
		return coleccionAlumnos.size();
	}

	// Insertar

	@Override
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}
		int indice = coleccionAlumnos.indexOf(alumno);
		if (indice == -1) {
			coleccionAlumnos.add(new Alumno(alumno));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese expediente.");
		}

	}

	// Buscar

	@Override
	public Alumno buscar(Alumno alumno) {
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}
		int indice = coleccionAlumnos.indexOf(alumno);
		if (indice == -1) {
			return null;
		} else {
			return new Alumno(coleccionAlumnos.get(indice));
		}
	}

	// Borrar

	@Override
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}
		int indice = coleccionAlumnos.indexOf(alumno);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese expediente.");
		} else {
			coleccionAlumnos.remove(indice);
		}
	}
}
