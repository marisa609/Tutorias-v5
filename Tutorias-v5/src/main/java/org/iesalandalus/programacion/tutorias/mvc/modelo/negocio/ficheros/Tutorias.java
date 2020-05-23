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

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ITutorias;

public class Tutorias implements ITutorias {

	// Declaración

	private List<Tutoria> coleccionTutorias;
	
	private static final String NOMBRE_FICHERO_TUTORIAS = "datos/tutorias.dat";

	// Constructor

	public Tutorias() {
		coleccionTutorias = new ArrayList<>();
	}

	@Override
	public void comenzar() {
		leer();
	}
	
	private void leer() {
		File ficheroTutorias = new File(NOMBRE_FICHERO_TUTORIAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroTutorias))) {
			Tutoria tutoria = null;
			do {
				tutoria = (Tutoria) entrada.readObject();
				insertar(tutoria);
			} while (tutoria != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fihero de tutorías.");
		} catch (EOFException e) {
			System.out.println("Fichero tutorías leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void terminar() {
		escribir();
	}
	
	private void escribir() {
		File ficheroTutorias = new File(NOMBRE_FICHERO_TUTORIAS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroTutorias))){
			for (Tutoria tutoria : coleccionTutorias)
				salida.writeObject(tutoria);
			System.out.println("Fichero tutorías escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de tutorías.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		}
	}

	// Las tutorías se ordenarán por profesor y por el nombre de la tutoría.

	@Override
	public List<Tutoria> get() {
		List<Tutoria> tutoriasOrdenadas = copiaProfundaTutorias();
		Comparator<Profesor> comparadorProfesor = Comparator.comparing(Profesor::getDni);
		tutoriasOrdenadas.sort(Comparator.comparing(Tutoria::getProfesor, comparadorProfesor).thenComparing(Tutoria::getNombre));
		return tutoriasOrdenadas;
	}

	// Copia profunda

	private List<Tutoria> copiaProfundaTutorias() {
		List<Tutoria> copiaTutorias = new ArrayList<>();
		for (Tutoria tutoria : coleccionTutorias) {
			copiaTutorias.add(new Tutoria(tutoria));
		}
		return copiaTutorias;
	}

	// Get
	// Cuando se listen las tutorías de un profesor se mostrarán ordenadas por
	// nombre de la tutoría.

	@Override
	public List<Tutoria> get(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		}
		List<Tutoria> tutoriasProfesor = new ArrayList<>();
		for (Tutoria tutoria : coleccionTutorias) {
			if (tutoria.getProfesor().equals(profesor)) {
				tutoriasProfesor.add(new Tutoria(tutoria));
			}
		}

		Comparator<Tutoria> comparadorTutoria = Comparator.comparing(Tutoria::getNombre);
		tutoriasProfesor.sort(Comparator.comparing(Tutoria::getNombre));
		return tutoriasProfesor;
	}

	// Get Tamaño

	@Override
	public int getTamano() {
		return coleccionTutorias.size();
	}

	// Insertar

	@Override
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: No se puede insertar una tutoría nula.");
		}
		int indice = coleccionTutorias.indexOf(tutoria);
		if (indice == -1) {
			coleccionTutorias.add(new Tutoria(tutoria));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una tutoría con ese identificador.");
		}

	}

	// Buscar Tutoria

	@Override
	public Tutoria buscar(Tutoria tutoria) {
		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una tutoría nula.");
		}
		int indice = coleccionTutorias.indexOf(tutoria);
		if (indice == -1) {
			return null;
		} else {
			return new Tutoria(coleccionTutorias.get(indice));

		}

	}

	// Borrar

	@Override
	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una tutoría nula.");
		}
		int indice = coleccionTutorias.indexOf(tutoria);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna tutoría con ese identificador.");
		} else {
			coleccionTutorias.remove(indice);
		}
	}

}
