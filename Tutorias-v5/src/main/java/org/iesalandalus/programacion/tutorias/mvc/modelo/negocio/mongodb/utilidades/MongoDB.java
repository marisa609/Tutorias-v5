package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb.utilidades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	
	public static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	
	private static final String SERVIDOR = "35.246.226.125";
	private static final int PUERTO = 27017;
	private static final String BD = "tutorias";
	private static final String USUARIO = "tutorias";
	private static final String CONTRASENA = "tutorias-2020";
	
	public static final String PROFESOR = "profesor";
	public static final String NOMBRE = "nombre";
	public static final String DNI = "dni";
	public static final String CORREO = "correo";
	public static final String PROFESOR_DNI = PROFESOR + "." + DNI;
	
	public static final String ALUMNO = "alumno";
	public static final String EXPEDIENTE = "expediente";
	
	public static final String TUTORIA = "tutoria";
	public static final String TUTORIA_NOMBRE = TUTORIA + "." + NOMBRE;
	public static final String TUTORIA_PROFESOR_DNI = TUTORIA + "." + PROFESOR_DNI;
	
	public static final String SESION = "sesion";
	public static final String FECHA = "fecha";
	public static final String HORA_INICIO = "horaInicio";
	public static final String HORA_FIN = "horaFin";
	public static final String DURACION = "duracion";
	
	public static final String HORA = "hora";
	public static final String SESION_TUTORIA_PROFESOR_DNI = SESION + "." + TUTORIA_PROFESOR_DNI;
	public static final String SESION_TUTORIA_NOMBRE = SESION + "." + TUTORIA_NOMBRE;
	public static final String SESION_FECHA = SESION + "." + FECHA;
	public static final String ALUMNO_CORREO = ALUMNO + "." + CORREO;
	
	private static MongoClient conexion = null;
	
	private MongoDB() {
		// Evitamos que se cree el constructor por defecto
	}
	
	public static MongoDatabase getBD() {
		if (conexion == null) {
			establecerConexion();
		}
		return conexion.getDatabase(BD);
	}
	
	private static MongoClient establecerConexion() {
	    Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
	    mongoLogger.setLevel(Level.SEVERE);
		if (conexion == null) {
			MongoCredential credenciales = MongoCredential.createScramSha1Credential(USUARIO, BD, CONTRASENA.toCharArray());
			conexion = MongoClients.create(
			        MongoClientSettings.builder()
	                .applyToClusterSettings(builder -> 
	                        builder.hosts(Arrays.asList(new ServerAddress(SERVIDOR, PUERTO))))
	                .credential(credenciales)
	                .build());
			System.out.println("Conexión a MongoDB realizada correctamente.");
		}
		return conexion;
	}
	
	public static void cerrarConexion() {
		if (conexion != null) {
			conexion.close();
			conexion = null;
			System.out.println("Conexión a MongoDB cerrada.");
		}
	}
	
	public static Document getDocumento(Profesor profesor) {
		if (profesor == null) {
			return null;
		}
		String nombre = profesor.getNombre();
		String dni = profesor.getDni();
		String correo = profesor.getCorreo();
		return new Document().append(NOMBRE, nombre).append(DNI, dni).append(CORREO, correo);
	}
	
	public static Document getCriterioOrdenacionProfesor() {
		return new Document().append(DNI, 1);
	}

	public static Profesor getProfesor(Document documentoProfesor) {
		if (documentoProfesor == null) {
			return null;
		}
		return new Profesor(documentoProfesor.getString(NOMBRE), documentoProfesor.getString(DNI), documentoProfesor.getString(CORREO));
	}
	
	public static Document getDocumento(Alumno alumno) {
		if (alumno == null) {
			return null;
		}
		String nombre = alumno.getNombre();
		String correo = alumno.getCorreo();
		String expediente = alumno.getExpediente();
		return new Document().append(NOMBRE, nombre).append(CORREO, correo).append(EXPEDIENTE, expediente);
	}
	
	public static Document getCriterioOrdenacionAlumno() {
		return new Document().append(CORREO, 1);
	}

	public static Alumno getAlumno(Document documentoAlumno) {
		if (documentoAlumno == null) {
			return null;
		}
		return new Alumno(documentoAlumno.getString(NOMBRE), documentoAlumno.getString(CORREO), Integer.valueOf(documentoAlumno.getString(EXPEDIENTE).split("_")[2]));
	}
	
	public static Document getDocumento(Tutoria tutoria) {
		if (tutoria == null) {
			return null;
		}
		Profesor profesor = tutoria.getProfesor();
		String nombre = tutoria.getNombre();
		return new Document().append(PROFESOR, getDocumento(profesor)).append(NOMBRE, nombre);
	}
	
	public static Document getCriterioOrdenacionTutoria() {
		return new Document().append(PROFESOR_DNI, 1).append(NOMBRE, 1);
	}

	public static Tutoria getTutoria(Document documentoTutoria) {
		if (documentoTutoria == null) {
			return null;
		}
		return new Tutoria(getProfesor((Document) documentoTutoria.get(PROFESOR)), documentoTutoria.getString(NOMBRE));
	}
	
	public static Document getDocumento(Sesion sesion) {
		if (sesion == null) {
			return null;
		}
		Tutoria tutoria = sesion.getTutoria();
		LocalDate fecha = sesion.getFecha();
		LocalTime horaInicio = sesion.getHoraInicio();
		LocalTime horaFin = sesion.getHoraFin();
		int duracion = sesion.getMinutosDuracion();
		return new Document().append(TUTORIA, getDocumento(tutoria)).append(FECHA, FORMATO_DIA.format(fecha))
				.append(HORA_INICIO, FORMATO_HORA.format(horaInicio))
				.append(HORA_FIN, FORMATO_HORA.format(horaFin))
				.append(DURACION, String.valueOf(duracion));
	}
	
	public static Document getCriterioOrdenacionSesion() {
		return new Document().append(TUTORIA_PROFESOR_DNI, 1).append(TUTORIA_NOMBRE,  1).append(FECHA, 1);
	}

	public static Sesion getSesion(Document documentoSesion) {
		if (documentoSesion == null) {
			return null;
		}
		return new Sesion(getTutoria((Document) documentoSesion.get(TUTORIA)),
				LocalDate.parse(documentoSesion.getString(FECHA), FORMATO_DIA),
				LocalTime.parse(documentoSesion.getString(HORA_INICIO), FORMATO_HORA),
				LocalTime.parse(documentoSesion.getString(HORA_FIN), FORMATO_HORA),
				Integer.valueOf(documentoSesion.getString(DURACION)));
	}
	
	public static Document getDocumento(Cita cita) {
		if (cita == null) {
			return null;
		}
		Sesion sesion = cita.getSesion();
		Alumno alumno = cita.getAlumno();
		return new Document().append(SESION, getDocumento(sesion))
				.append(ALUMNO, getDocumento(alumno))
				.append(HORA, FORMATO_HORA.format(cita.getHora()));
	}
	
	public static Document getCriterioOrdenacionCita() {
		return new Document().append(SESION_TUTORIA_PROFESOR_DNI, 1)
				.append(SESION_FECHA, 1)
				.append(HORA, 1);
	}

	public static Cita getCita(Document documentoCita) {
		if (documentoCita == null) {
			return null;
		}
		return new Cita(getAlumno((Document) documentoCita.get(ALUMNO)),
				getSesion((Document) documentoCita.get(SESION)),
				LocalTime.parse(documentoCita.getString(HORA), FORMATO_HORA));
	}
}
