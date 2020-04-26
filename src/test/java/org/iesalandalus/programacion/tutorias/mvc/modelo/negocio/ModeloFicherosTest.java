package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;


import org.iesalandalus.programacion.tutorias.mvc.modelo.IModelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.Modelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Alumnos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Citas;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Profesores;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Sesiones;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Tutorias;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ModeloFicherosTest {
	
	private static final String ERROR_TUTORIA_NULA = "ERROR: No se puede insertar una tutoría nula.";
	private static final String ERROR_TUTORIA_PROFESOR_NO_EXISTENTE = "ERROR: No existe el profesor de esta tutoría.";
	private static final String ERROR_SESION_NULA = "ERROR: No se puede insertar una sesión nula.";
	private static final String ERROR_SESION_TUTORIA_NO_EXISTENTE = "ERROR: No existe la tutoría de esta sesión.";
	private static final String ERROR_CITA_NULA = "ERROR: No se puede insertar una cita nula.";
	private static final String ERROR_CITA_ALUMNO_NO_EXISTENTE = "ERROR: No existe el alumno de esta cita.";
	private static final String ERROR_CITA_SESION_NO_EXISTENTE = "ERROR: No existe la sesión de esta cita.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	private static final String EXCEPCION_ESPERADA = "Debería haber saltado la excepción.";
	
	private static Alumno alumnoExistente;
	private static Alumno alumnoNoExistente;
	private static Profesor profesorExistente;
	private static Profesor profesorNoExistente;
	private static Tutoria tutoriaExistente;
	private static Tutoria tutoriaNoExistente;
	private static Sesion sesionExistente;
	private static Sesion sesionNoExistente;
	private static Cita cita;
	
	@InjectMocks private static IModelo modelo = new Modelo(FactorialFuenteDatos.FICHEROS.crear());
	
	@Mock private Alumnos alumnosSimulados;
	@Mock private Profesores profesoresSimulados;
	@Mock private Tutorias tutoriasSimuladas;
	@Mock private Sesiones sesionesSimuladas;
	@Mock private Citas citasSimuladas;
	
	@BeforeClass
	public static void asignarValoresAtributos() {
		alumnoExistente = Alumno.getAlumnoFicticio("bob@gmail.com");
		alumnoNoExistente = Alumno.getAlumnoFicticio("patricio@gmail.com");
		profesorExistente = Profesor.getProfesorFicticio("11223344B");
		profesorNoExistente = Profesor.getProfesorFicticio("22334455Y");
		tutoriaExistente = new Tutoria(profesorExistente, "Dudas PROG05");
		tutoriaNoExistente = new Tutoria(profesorExistente, "Dudas PROG06");
		sesionExistente = Sesion.getSesionFicticia(tutoriaExistente, LocalDate.now().plusDays(1));
		sesionNoExistente = Sesion.getSesionFicticia(tutoriaNoExistente, LocalDate.now().plusDays(1));
		cita = new Cita(alumnoExistente, sesionExistente, LocalTime.of(16, 0));
	}
	
	@Test
	public void comenzarLlamaAlumnosComenzarProfesoresComenzarTutoriasComenzarSesionesComenzarCitasComenzar() {
		modelo.comenzar();
		InOrder orden = Mockito.inOrder(alumnosSimulados, profesoresSimulados, tutoriasSimuladas, sesionesSimuladas, citasSimuladas);
		orden.verify(alumnosSimulados).comenzar();
		orden.verify(profesoresSimulados).comenzar();
		orden.verify(tutoriasSimuladas).comenzar();
		orden.verify(sesionesSimuladas).comenzar();
		orden.verify(citasSimuladas).comenzar();
	}
	
	@Test
	public void terminarLlamaAlumnosTerminarProfesoresTerminarTutoriasTerminarSesionesTerminarCitasTerminar() {
		modelo.terminar();
		InOrder orden = Mockito.inOrder(alumnosSimulados, profesoresSimulados, tutoriasSimuladas, sesionesSimuladas, citasSimuladas);
		orden.verify(alumnosSimulados).terminar();
		orden.verify(profesoresSimulados).terminar();
		orden.verify(tutoriasSimuladas).terminar();
		orden.verify(sesionesSimuladas).terminar();
		orden.verify(citasSimuladas).terminar();
	}

	@Test
	public void insertarAlumnoLlamaAlumnosInsertar() {
		try {
			modelo.insertar(alumnoExistente);
			verify(alumnosSimulados).insertar(alumnoExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarAlumnoLlamaAlumnosBuscar() {
		modelo.buscar(alumnoExistente);
		verify(alumnosSimulados).buscar(alumnoExistente);
	}
	
	@Test
	public void borrarAlumnoLlamaCitasGetAlumnoCitasBorrarAlumnosBorrar() {
		try {
			List<Cita> citasAlumno = simularComportamientoBorrarAlumnoConCitas();
			modelo.borrar(alumnoExistente);
			InOrder orden = Mockito.inOrder(alumnosSimulados, citasSimuladas);
			orden.verify(citasSimuladas).get(alumnoExistente);
			for (Cita cita : citasAlumno) {
				orden.verify(citasSimuladas).borrar(cita);
			}
			orden.verify(alumnosSimulados).borrar(alumnoExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private List<Cita> simularComportamientoBorrarAlumnoConCitas() {
		List<Cita> citasAlumno = new ArrayList<>();
		citasAlumno.add(new Cita(alumnoExistente, sesionExistente, LocalTime.of(16, 0)));
		citasAlumno.add(new Cita(alumnoExistente, sesionExistente, LocalTime.of(16, 15)));
		citasAlumno.add(new Cita(alumnoExistente, sesionExistente, LocalTime.of(16, 30)));
		when(citasSimuladas.get(alumnoExistente)).thenReturn(citasAlumno);
		return citasAlumno;
	}
	
	@Test
	public void getAlumnosLlamaAlumnosGet() {
		modelo.getAlumnos();
		verify(alumnosSimulados).get();
	}
	
	@Test
	public void insertarProfesorLlamaProfesoresInsertar() {
		try {
			modelo.insertar(profesorExistente);
			verify(profesoresSimulados).insertar(profesorExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarProfesorLlamaProfesoresBuscar() {
		modelo.buscar(profesorExistente);
		verify(profesoresSimulados).buscar(profesorExistente);
	}
	
	@Test
	public void borrarProfesorLlamaTutoriasGetProfesorTutoriasBorrarProfesoresBorrar() {
		try {
			List<Tutoria> tutoriasProfesor = simularComportamientoBorrarProfesorConTutorias();
			modelo.borrar(profesorExistente);
			InOrder orden = Mockito.inOrder(tutoriasSimuladas, profesoresSimulados);
			orden.verify(tutoriasSimuladas).get(profesorExistente);
			for (Tutoria tutoria : tutoriasProfesor) {
				orden.verify(tutoriasSimuladas).borrar(tutoria);
			}
			orden.verify(profesoresSimulados).borrar(profesorExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private List<Tutoria> simularComportamientoBorrarProfesorConTutorias() {
		List<Tutoria> tutoriasProfesor = new ArrayList<>();
		tutoriasProfesor.add(new Tutoria(profesorExistente, "Tutoria 1"));
		tutoriasProfesor.add(new Tutoria(profesorExistente, "Tutoria 2"));
		tutoriasProfesor.add(new Tutoria(profesorExistente, "Tutoria 3"));
		when(tutoriasSimuladas.get(profesorExistente)).thenReturn(tutoriasProfesor);
		return tutoriasProfesor;
	}
	
	@Test
	public void getProfesoresLlamaProfesoresGet() {
		modelo.getProfesores();
		verify(profesoresSimulados).get();
	}
	
	@Test
	public void insertarTutoriaValidaLlamaProfesoresBuscarTutoriasInsertar() {
		simularComportamientoInsertarTutoriaValida();
		InOrder orden = Mockito.inOrder(profesoresSimulados, tutoriasSimuladas);
		try {
			modelo.insertar(tutoriaExistente);
			orden.verify(profesoresSimulados).buscar(profesorExistente);
			orden.verify(tutoriasSimuladas).insertar(tutoriaExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private void simularComportamientoInsertarTutoriaValida() {
		when(profesoresSimulados.buscar(Profesor.getProfesorFicticio("11223344B"))).thenReturn(profesorExistente);
	}
	
	@Test
	public void insertarTutoriaProfesorNoExistenteLanzaExcepcion() {
		Tutoria tutoriaProfesorNoExistenteTutoria = new Tutoria(profesorNoExistente, "Dudas");
		try {
			simularComportamientoInsertarTutoriaProfesorNoExistente();
			modelo.insertar(tutoriaProfesorNoExistenteTutoria);
			fail(EXCEPCION_ESPERADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TUTORIA_PROFESOR_NO_EXISTENTE));
		}
	}
	
	private void simularComportamientoInsertarTutoriaProfesorNoExistente() {
		when(profesoresSimulados.buscar(profesorNoExistente)).thenReturn(null);
	}
	
	@Test
	public void insertarTutoriaNulaLanzaExcepcion() {
		Tutoria tutoriaNula = null;
		try {
			modelo.insertar(tutoriaNula);
			fail(EXCEPCION_ESPERADA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TUTORIA_NULA));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarTutoriaLlamaTutoriasBuscar() {
		modelo.buscar(tutoriaExistente);
		verify(tutoriasSimuladas).buscar(tutoriaExistente);
	}
	
	@Test
	public void borrarTutoriaLlamaSesionesGetTutoriaSesionesBorrarTutoriasBorrar() {
		try {
			List<Sesion> sesionesTutoria = simularComportamientoBorrarTutoriaConSesiones();
			modelo.borrar(tutoriaExistente);
			InOrder orden = Mockito.inOrder(sesionesSimuladas, tutoriasSimuladas);
			orden.verify(sesionesSimuladas).get(tutoriaExistente);
			for (Sesion sesion : sesionesTutoria) {
				orden.verify(sesionesSimuladas).borrar(sesion);
			}
			orden.verify(tutoriasSimuladas).borrar(tutoriaExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private List<Sesion> simularComportamientoBorrarTutoriaConSesiones() {
		List<Sesion> sesionesTutoria = new ArrayList<>();
		sesionesTutoria.add(Sesion.getSesionFicticia(tutoriaExistente, LocalDate.now().plusDays(7)));
		sesionesTutoria.add(Sesion.getSesionFicticia(tutoriaExistente, LocalDate.now().plusDays(8)));
		sesionesTutoria.add(Sesion.getSesionFicticia(tutoriaExistente, LocalDate.now().plusDays(9)));
		when(sesionesSimuladas.get(tutoriaExistente)).thenReturn(sesionesTutoria);
		return sesionesTutoria;
	}
	
	@Test
	public void getTutoriasLlamaTutoriasGet() {
		modelo.getTutorias();
		verify(tutoriasSimuladas).get();
	}
	
	@Test
	public void getTutoriasProfesorLlamaTutoriasGetConParametroProfesor() {
		modelo.getTutorias(profesorExistente);
		verify(tutoriasSimuladas).get(profesorExistente);
	}
	
	@Test
	public void insertarSesionValidaLlamaTutoriasBuscarSesionesInsertar() {
		simularComportamientoInsertarSesionValida();
		InOrder orden = Mockito.inOrder(tutoriasSimuladas, sesionesSimuladas);
		try {
			modelo.insertar(sesionExistente);
			orden.verify(tutoriasSimuladas).buscar(tutoriaExistente);
			orden.verify(sesionesSimuladas).insertar(sesionExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private void simularComportamientoInsertarSesionValida() {
		when(tutoriasSimuladas.buscar(tutoriaExistente)).thenReturn(tutoriaExistente);
	}
	
	@Test
	public void insertarSesionTutoriaNoExistenteLanzaExcepcion() {
		Sesion sesionTutoriaNoExistente = Sesion.getSesionFicticia(tutoriaNoExistente, LocalDate.now().plusDays(1));
		try {
			simularComportamientoInsertarSesionTutoriaNoExistente();
			modelo.insertar(sesionTutoriaNoExistente);
			fail(EXCEPCION_ESPERADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_SESION_TUTORIA_NO_EXISTENTE));
		}
	}
	
	private void simularComportamientoInsertarSesionTutoriaNoExistente() {
		when(tutoriasSimuladas.buscar(tutoriaNoExistente)).thenReturn(null);
	}	
	
	@Test
	public void insertarSesionNulaLanzaExcepcion() {
		Sesion sesionNula = null;
		try {
			modelo.insertar(sesionNula);
			fail(EXCEPCION_ESPERADA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_SESION_NULA));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}

	@Test
	public void buscarSesionLlamaSesionesBuscar() {
		modelo.buscar(sesionExistente);
		verify(sesionesSimuladas).buscar(sesionExistente);
	}
	
	@Test
	public void borrarSesionLlamaCitasGetSesionCitasBorrarSesionesBorrar() {
		try {
			List<Cita> citasSesion = simularComportamientoBorrarSesionConCitas();
			modelo.borrar(sesionExistente);
			InOrder orden = Mockito.inOrder(sesionesSimuladas, citasSimuladas);
			orden.verify(citasSimuladas).get(sesionExistente);
			for (Cita cita : citasSesion) {
				orden.verify(citasSimuladas).borrar(cita);
			}
			orden.verify(sesionesSimuladas).borrar(sesionExistente);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private List<Cita> simularComportamientoBorrarSesionConCitas() {
		List<Cita> citasSesion = new ArrayList<>();
		citasSesion.add(new Cita(Alumno.getAlumnoFicticio("patricio@gmail.com"), sesionExistente, LocalTime.of(16, 0)));
		citasSesion.add(new Cita(Alumno.getAlumnoFicticio("bob@gmail.com"), sesionExistente, LocalTime.of(16, 15)));
		citasSesion.add(new Cita(Alumno.getAlumnoFicticio("calamardo@gmail.com"), sesionExistente, LocalTime.of(16, 30)));
		when(citasSimuladas.get(sesionExistente)).thenReturn(citasSesion);
		return citasSesion;
	}
	
	@Test
	public void getSesionesLlamaSesionesGet() {
		modelo.getSesiones();
		verify(sesionesSimuladas).get();
	}
	
	@Test
	public void getSesionesTutoriaLlamaSesionesGetConParametroTutoria() {
		modelo.getSesiones(tutoriaExistente);
		verify(sesionesSimuladas).get(tutoriaExistente);
	}
	
	@Test
	public void insertarCitaValidaLlamaAlumnosBuscarSesionesBuscarCitasInsertar() {
		simularComportamientoInsertarCitaValida();
		InOrder orden = Mockito.inOrder(alumnosSimulados, sesionesSimuladas, citasSimuladas);
		try {
			modelo.insertar(cita);
			orden.verify(alumnosSimulados).buscar(alumnoExistente);
			orden.verify(sesionesSimuladas).buscar(sesionExistente);
			orden.verify(citasSimuladas).insertar(cita);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	private void simularComportamientoInsertarCitaValida() {
		when(alumnosSimulados.buscar(alumnoExistente)).thenReturn(alumnoExistente);
		when(sesionesSimuladas.buscar(sesionExistente)).thenReturn(sesionExistente);
	}
	
	@Test
	public void insertarCitaAlumnoNoExistenteLanzaExcepcion() {
		Cita citaAlumnoNoExistente = new Cita(alumnoNoExistente, sesionExistente, LocalTime.of(16, 0));
		try {
			simularComportamientoInsertarCitaAlumnoNoExistente();
			modelo.insertar(citaAlumnoNoExistente);
			fail(EXCEPCION_ESPERADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CITA_ALUMNO_NO_EXISTENTE));
		}
	}	
	
	private void simularComportamientoInsertarCitaAlumnoNoExistente() {
		when(alumnosSimulados.buscar(alumnoNoExistente)).thenReturn(null);
	}
	
	@Test
	public void insertarCitaSesionNoExistenteLanzaExcepcion() {
		Cita citaSesionNoExistente = new Cita(alumnoExistente, sesionNoExistente, LocalTime.of(16, 0));
		try {
			simularComportamientoInsertarCitaSesionNoExistente();
			modelo.insertar(citaSesionNoExistente);
			fail(EXCEPCION_ESPERADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CITA_SESION_NO_EXISTENTE));
		}
	}	
	
	private void simularComportamientoInsertarCitaSesionNoExistente() {
		when(alumnosSimulados.buscar(alumnoExistente)).thenReturn(alumnoExistente);
		when(sesionesSimuladas.buscar(sesionNoExistente)).thenReturn(null);
	}
	
	
	@Test
	public void insertarCitaNulaLanzaExcepcion() {
		Cita citaNula = null;
		try {
			modelo.insertar(citaNula);
			fail(EXCEPCION_ESPERADA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CITA_NULA));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void buscarCitaLlamaCitasBuscar() {
		modelo.buscar(cita);
		verify(citasSimuladas).buscar(cita);
	}
	
	@Test
	public void borrarCitaLlamaCitasBorrar() {
		try {
			modelo.borrar(cita);
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getCitasLlamaCitasGet() {
		modelo.getCitas();
		verify(citasSimuladas).get();
	}
	
	@Test
	public void getCitasAlumnoLlamaCitasGetConParametroAlumno() {
		modelo.getCitas(alumnoExistente);
		verify(citasSimuladas).get(alumnoExistente);
	}
	
	@Test
	public void getCitasSesionLlamaCitasGetConParametroSesion() {
		modelo.getCitas(sesionExistente);
		verify(citasSimuladas).get(sesionExistente);
	}
	
}
