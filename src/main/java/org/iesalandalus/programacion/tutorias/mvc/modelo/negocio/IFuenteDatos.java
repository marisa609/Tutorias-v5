package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

public interface IFuenteDatos {

	IAlumnos crearAlumnos();

	IProfesores crearProfesores();

	ITutorias crearTutorias();

	ISesiones crearSesiones();

	ICitas crearCitas();

}