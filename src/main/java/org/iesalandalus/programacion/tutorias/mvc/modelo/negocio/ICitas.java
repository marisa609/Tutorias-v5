package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;

public interface ICitas {

	List<Cita> get();

	List<Cita> get(Sesion sesion);

	List<Cita> get(Alumno alumno);

	int getTamano();

	void insertar(Cita cita) throws OperationNotSupportedException;

	Cita buscar(Cita cita);

	void borrar(Cita cita) throws OperationNotSupportedException;

	void comenzar();

	void terminar();

}