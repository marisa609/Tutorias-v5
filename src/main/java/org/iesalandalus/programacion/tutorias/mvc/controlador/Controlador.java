package org.iesalandalus.programacion.tutorias.mvc.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.IModelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;

public class Controlador implements IControlador {

	private IVista vista;
	private IModelo modelo;

	public Controlador(IModelo modelo, IVista vista) {
		if (modelo == null) {
			throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo.");
		}
		if (vista == null) {
			throw new IllegalArgumentException("ERROR: La vista no puede ser nula.");
		}
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}

	@Override
	public void comenzar() {
		modelo.comenzar();
		vista.comenzar();
	}

	@Override
	public void terminar() {
		modelo.terminar();
		System.out.println("El programa ha terminado.");
	}

	// INSERTAR

	@Override
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		modelo.insertar(alumno);
	}

	@Override
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertar(profesor);
	}

	@Override
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
		modelo.insertar(tutoria);
	}

	@Override
	public void insertar(Sesion sesion) throws OperationNotSupportedException {
		modelo.insertar(sesion);
	}

	@Override
	public void insertar(Cita cita) throws OperationNotSupportedException {
		modelo.insertar(cita);
	}

	// BUSCAR

	@Override
	public Alumno buscar(Alumno alumno) {
		return modelo.buscar(alumno);
	}

	@Override
	public Profesor buscar(Profesor profesor) {
		return modelo.buscar(profesor);
	}

	@Override
	public Tutoria buscar(Tutoria tutoria) {
		return modelo.buscar(tutoria);
	}

	@Override
	public Sesion buscar(Sesion sesion) {
		return modelo.buscar(sesion);
	}

	@Override
	public Cita buscar(Cita cita) {
		return modelo.buscar(cita);
	}

	// BORRAR

	@Override
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		modelo.borrar(alumno);
	}

	@Override
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrar(profesor);
	}

	@Override
	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
		modelo.borrar(tutoria);
	}

	@Override
	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		modelo.borrar(sesion);
	}

	@Override
	public void borrar(Cita cita) throws OperationNotSupportedException {
		modelo.borrar(cita);
	}

	// GET

	@Override
	public List<Alumno> getAlumnos() {
		return modelo.getAlumnos();
	}

	@Override
	public List<Profesor> getProfesores() {
		return modelo.getProfesores();
	}

	@Override
	public List<Tutoria> getTutorias() {
		return modelo.getTutorias();
	}

	@Override
	public List<Tutoria> getTutorias(Profesor profesor) {
		return modelo.getTutorias(profesor);
	}

	@Override
	public List<Sesion> getSesiones() {
		return modelo.getSesiones();
	}

	@Override
	public List<Sesion> getSesiones(Tutoria tutoria) {
		return modelo.getSesiones(tutoria);
	}

	@Override
	public List<Cita> getCitas() {
		return modelo.getCitas();
	}

	@Override
	public List<Cita> getCitas(Sesion sesion) {
		return modelo.getCitas(sesion);
	}

	@Override
	public List<Cita> getCitas(Alumno alumno) {
		return modelo.getCitas(alumno);
	}

}
