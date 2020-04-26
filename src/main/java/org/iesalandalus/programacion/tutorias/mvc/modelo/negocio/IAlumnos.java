package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;

public interface IAlumnos {

	List<Alumno> get();

	int getTamano();

	void insertar(Alumno alumno) throws OperationNotSupportedException;

	Alumno buscar(Alumno alumno);

	void borrar(Alumno alumno) throws OperationNotSupportedException;

	void comenzar();

	void terminar();

}