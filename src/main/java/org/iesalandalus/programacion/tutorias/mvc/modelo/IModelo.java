package org.iesalandalus.programacion.tutorias.mvc.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public interface IModelo {

	void insertar(Alumno alumno) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException;

	void insertar(Profesor profesor)
			throws OperationNotSupportedException, IllegalArgumentException, NullPointerException;

	void insertar(Tutoria tutoria)
			throws OperationNotSupportedException, IllegalArgumentException, NullPointerException;

	void insertar(Sesion sesion) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException;

	void insertar(Cita cita) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException;

	Alumno buscar(Alumno alumno) throws IllegalArgumentException;

	Profesor buscar(Profesor profesor) throws IllegalArgumentException;

	Tutoria buscar(Tutoria tutoria) throws IllegalArgumentException;

	Sesion buscar(Sesion sesion) throws IllegalArgumentException;

	Cita buscar(Cita cita) throws IllegalArgumentException;

	void borrar(Alumno alumno) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException;

	void borrar(Profesor profesor)
			throws OperationNotSupportedException, IllegalArgumentException, NullPointerException;

	void borrar(Tutoria tutoria) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException;

	void borrar(Sesion sesion) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException;

	void borrar(Cita cita) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException;

	List<Alumno> getAlumnos();

	List<Profesor> getProfesores();

	List<Tutoria> getTutorias();

	List<Tutoria> getTutorias(Profesor profesor);

	List<Sesion> getSesiones();

	List<Sesion> getSesiones(Tutoria tutoria);

	List<Cita> getCitas();

	List<Cita> getCitas(Sesion sesion);

	List<Cita> getCitas(Alumno alumno);

	void comenzar();

	void terminar();

}