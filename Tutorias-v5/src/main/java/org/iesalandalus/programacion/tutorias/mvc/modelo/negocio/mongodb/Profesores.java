package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.bson.Document;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IProfesores;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb.utilidades.MongoDB;

import com.mongodb.client.MongoCollection;

public class Profesores implements IProfesores {
	
	private static final String COLECCION = "profesores";
	
	private MongoCollection<Document> colecccionProfesores;	
	
	@Override
	public void comenzar() {
		colecccionProfesores = MongoDB.getBD().getCollection(COLECCION);
	}

	@Override
	public void terminar() {
		MongoDB.cerrarConexion();
	}
	
	@Override
	public List<Profesor> get() {
		List<Profesor> profesores = new ArrayList<>();
		for (Document documentoProfesor : colecccionProfesores.find().sort(MongoDB.getCriterioOrdenacionProfesor())) {
			profesores.add(MongoDB.getProfesor(documentoProfesor));
		}
		return profesores;
	}
	
	@Override
	public int getTamano() {
		return (int)colecccionProfesores.countDocuments();
	}
	
	@Override
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
		}
		if (buscar(profesor) != null) {
			throw new OperationNotSupportedException("El profesor ya existe.");
		} else {
			colecccionProfesores.insertOne(MongoDB.getDocumento(profesor));
		} 
	}

	@Override
	public Profesor buscar(Profesor profesor) {
		Document documentoProfesor = colecccionProfesores.find().filter(eq(MongoDB.CORREO, profesor.getCorreo())).first();
		return MongoDB.getProfesor(documentoProfesor);	}
	
	@Override
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
		}
		if (buscar(profesor) != null) {
			colecccionProfesores.deleteOne(eq(MongoDB.CORREO, profesor.getCorreo()));
		} else {
			throw new OperationNotSupportedException("El profesor a borrar no existe.");
		} 
	}

}
