package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.bson.Document;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ITutorias;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb.utilidades.MongoDB;

import com.mongodb.client.MongoCollection;

public class Tutorias implements ITutorias {

	private static final String COLECCION = "tutorias";
	
	private MongoCollection<Document> coleccionTutorias;

	@Override
	public void comenzar() {
		coleccionTutorias = MongoDB.getBD().getCollection(COLECCION);
	}

	@Override
	public void terminar() {
		MongoDB.cerrarConexion();
	}

	@Override
	public List<Tutoria> get() {
		List<Tutoria> tutorias = new ArrayList<>();
		for (Document documentoTutoria : coleccionTutorias.find().sort(MongoDB.getCriterioOrdenacionTutoria())) {
			tutorias.add(MongoDB.getTutoria(documentoTutoria));
		}
		return tutorias;
	}

	@Override
	public List<Tutoria> get(Profesor profesor) {
		List<Tutoria> tutorias = new ArrayList<>();
		for (Document documentoTutoria : coleccionTutorias.find()
				.filter(eq(MongoDB.PROFESOR_DNI, profesor.getDni())).sort(MongoDB.getCriterioOrdenacionTutoria())) {
			tutorias.add(MongoDB.getTutoria(documentoTutoria));
		}
		return tutorias;
	}

	@Override
	public int getTamano() {
		return (int)coleccionTutorias.countDocuments();
	}

	@Override
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: No se puede insertar una tutoría nula.");
		}
		if (buscar(tutoria) != null) {
			throw new OperationNotSupportedException("ERROR: Ya existe una tutoría igual.");
		} else {
			coleccionTutorias.insertOne(MongoDB.getDocumento(tutoria));
		} 
	}

	@Override
	public Tutoria buscar(Tutoria tutoria) {
		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una tutoría nula.");
		}
		Document documentoTutoria = coleccionTutorias.find()
				.filter(and(eq(MongoDB.NOMBRE, tutoria.getNombre()), eq(MongoDB.PROFESOR_DNI, tutoria.getProfesor().getDni()))).first();
		return MongoDB.getTutoria(documentoTutoria);	
	}

	@Override
	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una tutoría nula.");
		}
		if (buscar(tutoria) != null) {
			coleccionTutorias.deleteOne(and(eq(MongoDB.NOMBRE, tutoria.getNombre()), eq(MongoDB.PROFESOR_DNI, tutoria.getProfesor().getDni())));
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna tutoría con ese nombre y profesor.");
		} 
	}

}
