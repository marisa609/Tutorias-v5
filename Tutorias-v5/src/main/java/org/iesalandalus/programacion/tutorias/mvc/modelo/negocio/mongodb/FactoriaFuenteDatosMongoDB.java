package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb;



import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ICitas;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IProfesores;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ISesiones;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ITutorias;

public class FactoriaFuenteDatosMongoDB implements IFuenteDatos {
	
	@Override
	public IAlumnos crearAlumnos() {
		return new Alumnos();
	}
	
	@Override
	public IProfesores crearProfesores() {
		return new Profesores();
	}
	
	
	@Override
	public ITutorias crearTutorias() {
		return new Tutorias();
	}
	
	@Override
	public ISesiones crearSesiones() {
		return new Sesiones();
	}
	
	@Override
	public ICitas crearCitas() {
		return new Citas();
	}

}
