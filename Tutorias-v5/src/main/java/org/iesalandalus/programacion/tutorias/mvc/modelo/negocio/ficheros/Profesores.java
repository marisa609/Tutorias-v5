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
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IProfesores;

public class Profesores implements IProfesores {
	
	private static final String NOMBRE_FICHERO_PROFESORES = "datos/profesores.dat";

	// Declaración

	private List<Profesor> coleccionProfesores;

	// Constructor

	public Profesores() {
		coleccionProfesores = new ArrayList<>();
	}
	
	// Comenzar
	
	@Override
	public void comenzar() {
		leer();
	}
	
	private void leer() {
		File ficheroProfesores = new File(NOMBRE_FICHERO_PROFESORES);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroProfesores))) {
			Profesor profesor = null;
			do {
				profesor = (Profesor) entrada.readObject();
				insertar(profesor);
			} while (profesor != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fihero de profesores.");
		} catch (EOFException e) {
			System.out.println("Fichero profesores leído satisfactoriamente.");
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
		File ficheroAulas = new File(NOMBRE_FICHERO_PROFESORES);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAulas))){
			for (Profesor profesor : coleccionProfesores)
				salida.writeObject(profesor);
			System.out.println("Fichero profesores escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de profesores.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		}
	}

	// Get

	@Override
	public List<Profesor> get() {
		List<Profesor> profesoresOrdenados = copiaProfundaProfesores();
		profesoresOrdenados.sort(Comparator.comparing(Profesor::getDni));
		return profesoresOrdenados;
	}

	// Copia profunda

	private List<Profesor> copiaProfundaProfesores() {
		List<Profesor> copiaProfesores = new ArrayList<>();
		for (Profesor profesor : coleccionProfesores) {
			copiaProfesores.add(new Profesor(profesor));
		}
		return copiaProfesores;
	}

	// GetTamano

	@Override
	public int getTamano() {
		return coleccionProfesores.size();
	}

	// Insertar

	@Override
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		int indice = coleccionProfesores.indexOf(profesor);
		if (indice == -1) {
			coleccionProfesores.add(new Profesor(profesor));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese DNI.");
		}

	}

	// Buscar

	@Override
	public Profesor buscar(Profesor profesor) {
		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un profesor nulo.");
		}
		int indice = coleccionProfesores.indexOf(profesor);
		if (indice == -1) {
			return null;
		} else {
			return new Profesor(coleccionProfesores.get(indice));
		}
	}

	// Borrar

	@Override
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un profesor nulo.");
		}
		int indice = coleccionProfesores.indexOf(profesor);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese DNI.");
		} else {
			coleccionProfesores.remove(indice);
		}
	}
}
