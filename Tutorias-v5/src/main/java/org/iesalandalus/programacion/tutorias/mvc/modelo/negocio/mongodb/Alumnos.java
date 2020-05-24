package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.bson.Document;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb.utilidades.MongoDB;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IAlumnos;

import com.mongodb.client.MongoCollection;


public class Alumnos implements IAlumnos {

	private static final String COLECCION = "alumnos";

	private MongoCollection<Document> colecccionAlumnos;

	private void calcularUltimoIdentificador() {
		int maxIdentificador = 0;
		for (Alumno alumno :get()) {
			int identificador = Integer.parseInt(alumno.getExpediente().split("_")[2]);
			maxIdentificador = identificador > maxIdentificador ? identificador : maxIdentificador;
			
		}
		Alumno.setUltimoIdentificador(maxIdentificador);
	}
	
	@Override
	public void comenzar() {
		colecccionAlumnos = MongoDB.getBD().getCollection(COLECCION);
		calcularUltimoIdentificador();
	}

	@Override
	public void terminar() {
		MongoDB.cerrarConexion();
	}

	// Devuelve con el .find() los documentos de los alumnos
	// Con .sort ordenamos

	@Override
	public List<Alumno> get() {
		List<Alumno> alumnos = new ArrayList<>();
		for (Document documentoAlumno : colecccionAlumnos.find().sort(MongoDB.getCriterioOrdenacionAlumno())) {
			alumnos.add(MongoDB.getAlumno(documentoAlumno)); // Convertimos documentos en objetos y los añadimos
		}
		return alumnos;
	}

	// Obtenemos el tamaño y lo convertimos a entero (recibimos un long)

	@Override
	public int getTamano() {
		return (int) colecccionAlumnos.countDocuments();
	}

	// Insertamos con el método inserOne(), pero antes debemos convertir el objeto
	// alumno en un documento

	@Override
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) {
			throw new IllegalArgumentException("No se puede insertar un alumno nulo.");
		}
		if (buscar(alumno) != null) {
			throw new OperationNotSupportedException("El alumno ya existe.");
		} else {
			colecccionAlumnos.insertOne(MongoDB.getDocumento(alumno));
		}
	}
	
	// Con el .filter le indicamos el filtro, eq es un equals

	@Override
	public Alumno buscar(Alumno alumno) {
		Document documentoAlumno = colecccionAlumnos.find().filter(eq(MongoDB.CORREO, alumno.getCorreo())).first();
		return MongoDB.getAlumno(documentoAlumno);
	}
	
	@Override
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) {
			throw new IllegalArgumentException("No se puede borrar un alumno nulo.");
		}
		if (buscar(alumno) != null) {
			colecccionAlumnos.deleteOne(eq(MongoDB.CORREO, alumno.getCorreo()));
		} else {
			throw new OperationNotSupportedException("El alumno a borrar no existe.");
		} 
	}
}
