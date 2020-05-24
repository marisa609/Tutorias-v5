package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.bson.Document;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ICitas;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb.utilidades.MongoDB;

import com.mongodb.client.MongoCollection;

public class Citas implements ICitas {

	private static final String COLECCION = "citas";
	
	private MongoCollection<Document> coleccionCitas;

	@Override
	public void comenzar() {
		coleccionCitas = MongoDB.getBD().getCollection(COLECCION);
	}

	@Override
	public void terminar() {
		MongoDB.cerrarConexion();
	}

	@Override
	public List<Cita> get() {
		List<Cita> citas = new ArrayList<>();
		for (Document documentoCita : coleccionCitas.find().sort(MongoDB.getCriterioOrdenacionCita())) {
			citas.add(MongoDB.getCita(documentoCita));
		}
		return citas;
	}

	@Override
	public List<Cita> get(Sesion sesion) {
		List<Cita> citas = new ArrayList<>();
		for (Document documentoCita : coleccionCitas.find()
				.filter(and(eq(MongoDB.SESION_TUTORIA_PROFESOR_DNI, sesion.getTutoria().getProfesor().getDni()), 
						eq(MongoDB.SESION_TUTORIA_NOMBRE, sesion.getTutoria().getNombre()), 
						eq(MongoDB.SESION_FECHA, MongoDB.FORMATO_DIA.format(sesion.getFecha()))))
				.sort(MongoDB.getCriterioOrdenacionCita())) {
			citas.add(MongoDB.getCita(documentoCita));
		}
		return citas;
	}

	@Override
	public List<Cita> get(Alumno alumno) {
		List<Cita> citas = new ArrayList<>();
		for (Document documentoCita : coleccionCitas.find()
				.filter(eq(MongoDB.ALUMNO_CORREO, alumno.getCorreo()))
				.sort(MongoDB.getCriterioOrdenacionCita())) {
			citas.add(MongoDB.getCita(documentoCita));
		}
		return citas;
	}

	@Override
	public int getTamano() {
		return (int)coleccionCitas.countDocuments();
	}

	@Override
	public void insertar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede insertar una cita nula.");
		}
		if (buscar(cita) != null) {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita igual.");
		} else {
			coleccionCitas.insertOne(MongoDB.getDocumento(cita));
		}
	}

	@Override
	public Cita buscar(Cita cita) {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una cita nula.");
		}
		Document documentoSesion = coleccionCitas.find()
				.filter(and(eq(MongoDB.HORA, MongoDB.FORMATO_HORA.format(cita.getHora())),
						eq(MongoDB.SESION_FECHA, MongoDB.FORMATO_DIA.format(cita.getSesion().getFecha())), 
						eq(MongoDB.SESION_TUTORIA_NOMBRE, cita.getSesion().getTutoria().getNombre()),
						eq(MongoDB.SESION_TUTORIA_PROFESOR_DNI, cita.getSesion().getTutoria().getProfesor().getDni()))).first();
		return MongoDB.getCita(documentoSesion);
	}

	@Override
	public void borrar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}
		if (buscar(cita) != null) {
			coleccionCitas.deleteOne(and(eq(MongoDB.HORA, MongoDB.FORMATO_HORA.format(cita.getHora())),
					eq(MongoDB.SESION_FECHA, MongoDB.FORMATO_DIA.format(cita.getSesion().getFecha())), 
					eq(MongoDB.SESION_TUTORIA_NOMBRE, cita.getSesion().getTutoria().getNombre()),
					eq(MongoDB.SESION_TUTORIA_PROFESOR_DNI, cita.getSesion().getTutoria().getProfesor().getDni())));
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita igual.");
		} 
	}

}
