package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.bson.Document;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ISesiones;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb.utilidades.MongoDB;

import com.mongodb.client.MongoCollection;

public class Sesiones implements ISesiones {

	private static final String COLECCION = "sesiones";
	
	private MongoCollection<Document> coleccionSesiones;

	@Override
	public void comenzar() {
		coleccionSesiones = MongoDB.getBD().getCollection(COLECCION);
	}

	@Override
	public void terminar() {
		MongoDB.cerrarConexion();
	}

	@Override
	public List<Sesion> get() {
		List<Sesion> sesiones = new ArrayList<>();
		for (Document documentoSesiones : coleccionSesiones.find().sort(MongoDB.getCriterioOrdenacionSesion())) {
			sesiones.add(MongoDB.getSesion(documentoSesiones));
		}
		return sesiones;
	}

	@Override
	public List<Sesion> get(Tutoria tutoria) {
		List<Sesion> sesiones = new ArrayList<>();
		for (Document documentoSesiones : coleccionSesiones.find()
				.filter(and(eq(MongoDB.TUTORIA_PROFESOR_DNI, tutoria.getProfesor().getDni()), eq(MongoDB.TUTORIA_NOMBRE, tutoria.getNombre())))
				.sort(MongoDB.getCriterioOrdenacionSesion())) {
			sesiones.add(MongoDB.getSesion(documentoSesiones));
		}
		return sesiones;
	}

	@Override
	public int getTamano() {
		return (int)coleccionSesiones.countDocuments();
	}

	@Override
	public void insertar(Sesion sesion) throws OperationNotSupportedException {
		if (sesion == null) {
			throw new IllegalArgumentException("ERROR: No se puede insertar una sesión nula.");
		}
		if (buscar(sesion) != null) {
			throw new OperationNotSupportedException("ERROR: Ya existe una sesión igual.");
		} else {
			coleccionSesiones.insertOne(MongoDB.getDocumento(sesion));
		}
	}

	@Override
	public Sesion buscar(Sesion sesion) {
		if (sesion == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una sesión nula.");
		}
		Document documentoSesion = coleccionSesiones.find()
				.filter(and(eq(MongoDB.FECHA, MongoDB.FORMATO_DIA.format(sesion.getFecha())), 
						eq(MongoDB.TUTORIA_NOMBRE, sesion.getTutoria().getNombre()),
						eq(MongoDB.TUTORIA_PROFESOR_DNI, sesion.getTutoria().getProfesor().getDni()))).first();
		return MongoDB.getSesion(documentoSesion);
	}

	@Override
	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		if (sesion == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una sesión nula.");
		}
		if (buscar(sesion) != null) {
			coleccionSesiones.deleteOne(and(eq(MongoDB.FECHA, MongoDB.FORMATO_DIA.format(sesion.getFecha())), 
					eq(MongoDB.TUTORIA_NOMBRE, sesion.getTutoria().getNombre()),
					eq(MongoDB.TUTORIA_PROFESOR_DNI, sesion.getTutoria().getProfesor().getDni())));
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna sesión igual.");
		} 
	}

}
