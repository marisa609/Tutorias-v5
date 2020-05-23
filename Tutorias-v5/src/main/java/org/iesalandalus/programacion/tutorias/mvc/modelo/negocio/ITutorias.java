package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public interface ITutorias {

	List<Tutoria> get();

	List<Tutoria> get(Profesor profesor);

	int getTamano();

	void insertar(Tutoria tutoria) throws OperationNotSupportedException;

	Tutoria buscar(Tutoria tutoria);

	void borrar(Tutoria tutoria) throws OperationNotSupportedException;

	void terminar();

	void comenzar();

}