package org.iesalandalus.programacion.tutorias.mvc.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ICitas;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IProfesores;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ISesiones;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ITutorias;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Alumnos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Citas;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Profesores;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Sesiones;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Tutorias;

public class Modelo implements IModelo {

	private IAlumnos alumnos;
	private IProfesores profesores;
	private ITutorias tutorias;
	private ISesiones sesiones;
	private ICitas citas;
	
	// Modifica la clase Modelo para que en su constructor acepte como parámetro una fuente de datos 
	//(su interfaz IFuenteDatos) y cree los diferentes objetos llamando a los métodos de dicha interfaz.

	public Modelo(IFuenteDatos fuenteDeDatos) {

		alumnos = fuenteDeDatos.crearAlumnos();
		profesores = fuenteDeDatos.crearProfesores();
		tutorias = fuenteDeDatos.crearTutorias();
		sesiones = fuenteDeDatos.crearSesiones();
		citas = fuenteDeDatos.crearCitas();
	}
	
	// COMENZAR
	
	@Override
	public void comenzar() {
		alumnos.comenzar();
		profesores.comenzar();
		tutorias.comenzar();
		sesiones.comenzar();
		citas.comenzar();
	}
	
	// TERMINAR
	
	@Override
	public void terminar() {
		alumnos.terminar();
		profesores.terminar();
		tutorias.terminar();
		sesiones.terminar();
		citas.terminar();
	}

	// INSERTAR

	@Override
	public void insertar(Alumno alumno) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		alumnos.insertar(alumno);
	}

	@Override
	public void insertar(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		profesores.insertar(profesor);
	}
	
	// Al insertar una tutoría deberemos comprobar que el profesor de la tutoría existe e insertar la tutoría para el profesor encontrado.

	@Override
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: No se puede insertar una tutoría nula.");
		}

		Profesor profesor = profesores.buscar(tutoria.getProfesor());

		if (profesor == null) {
			throw new  OperationNotSupportedException("ERROR: No existe el profesor de esta tutoría.");
		}

		tutorias.insertar(new Tutoria(profesor, tutoria.getNombre()));
	}
	
	// Al insertar una sesión deberemos comprobar que la tutoría de la sesión existe e insertar la sesión para la tutoría encontrada.

	@Override
	public void insertar(Sesion sesion) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		if (sesion == null) {
			throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
		}

		Tutoria tutoria = tutorias.buscar(sesion.getTutoria());

		if (tutoria == null) {
			throw new OperationNotSupportedException("ERROR: No existe la tutoría de esta sesión.");
		}

		LocalDate manana = LocalDate.now().plusDays(1);
		if (sesion.getFecha().isBefore(manana)) {
			throw new IllegalArgumentException("ERROR: Las sesiones de deben planificar para fechas futuras."); // Error
																												// en el
																												// test:
																												// de
																												// deben
		}
		sesiones.insertar(new Sesion(tutoria, sesion.getFecha(), sesion.getHoraInicio(), sesion.getHoraFin(),
				sesion.getMinutosDuracion()));
	}
	
	// Al insertar una cita deberemos comprobar que la sesión de la cita existe y que el alumno de la cita también existe e insertar la cita para la sesión y el alumno encontrados.

	@Override
	public void insertar(Cita cita) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}

		Alumno alumno = alumnos.buscar(cita.getAlumno());

		if (alumno == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alumno de esta cita.");
		}

		Sesion sesion = sesiones.buscar(cita.getSesion());

		if (sesion == null) {
			throw new OperationNotSupportedException("ERROR: No existe la sesión de esta cita.");
		}

		citas.insertar(new Cita(alumno, sesion, cita.getHora()));
	}

	// BUSCAR

	@Override
	public Alumno buscar(Alumno alumno) throws IllegalArgumentException {
		return alumnos.buscar(alumno);
	}

	@Override
	public Profesor buscar(Profesor profesor) throws IllegalArgumentException {
		return profesores.buscar(profesor);
	}

	@Override
	public Tutoria buscar(Tutoria tutoria) throws IllegalArgumentException {
		return tutorias.buscar(tutoria);
	}

	@Override
	public Sesion buscar(Sesion sesion) throws IllegalArgumentException {
		return sesiones.buscar(sesion);
	}

	@Override
	public Cita buscar(Cita cita) throws IllegalArgumentException {
		return citas.buscar(cita);
	}

	// BORRAR

	// Al borrar un alumno deberemos borrar todas las citas asociadas al mismo.

	@Override
	public void borrar(Alumno alumno) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		List<Cita> citasAlumno = citas.get(alumno);
		for (Cita cita : citasAlumno) {
			citas.borrar(cita);
		}
		alumnos.borrar(alumno);
	}

	// Al borrar un profesor deberemos borrar todas las tutorías (en cascada)
	// asociadas al mismo.

	@Override
	public void borrar(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		List<Tutoria> tutoriasProfesor = tutorias.get(profesor);
		for (Tutoria tutoria : tutoriasProfesor) {
			tutorias.borrar(tutoria);
		}
		profesores.borrar(profesor);
	}

	// Al borrar una tutoría deberemos borrar todas las sesiones (en cascada)
	// asociadas a la misma.

	@Override
	public void borrar(Tutoria tutoria) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		List<Sesion> sesionesTutoria = sesiones.get(tutoria);
		for (Sesion sesion : sesionesTutoria) {
			sesiones.borrar(sesion);
		}
		tutorias.borrar(tutoria);
	}

	// Al borrar una sesión deberemos borrar todas las citas asociadas a la misma.

	@Override
	public void borrar(Sesion sesion) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		List<Cita> citaSesion = citas.get(sesion);
		for (Cita cita : citaSesion) {
			citas.borrar(cita);
		}
		sesiones.borrar(sesion);
	}

	@Override
	public void borrar(Cita cita) throws OperationNotSupportedException, IllegalArgumentException, NullPointerException {
		citas.borrar(cita);
	}

	// GET

	@Override
	public List<Alumno> getAlumnos() {
		return alumnos.get();
	}

	@Override
	public List<Profesor> getProfesores() {
		return profesores.get();
	}

	@Override
	public List<Tutoria> getTutorias() {
		return tutorias.get();
	}

	@Override
	public List<Tutoria> getTutorias(Profesor profesor) {
		return tutorias.get(profesor);
	}

	@Override
	public List<Sesion> getSesiones() {
		return sesiones.get();
	}

	@Override
	public List<Sesion> getSesiones(Tutoria tutoria) {
		return sesiones.get(tutoria);
	}

	@Override
	public List<Cita> getCitas() {
		return citas.get();
	}

	@Override
	public List<Cita> getCitas(Sesion sesion) {
		return citas.get(sesion);
	}

	@Override
	public List<Cita> getCitas(Alumno alumno) {
		return citas.get(alumno);
	}

}
