package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

public class CitaTest {
	
	private static final String ERROR_HORA_NULA = "ERROR: La hora no puede ser nula.";
	private static final String ERROR_HORA_FUERA_RANGO = "ERROR: La hora debe estar comprendida entre la hora de inicio y fin de la sesión.";
	private static final String ERROR_HORA_NO_MULTIPLO = "ERROR: La hora debe comenzar en un múltiplo de los minutos de duración.";
	private static final String ERROR_ALUMNO_NULO = "ERROR: El alumno no puede ser nulo.";
	private static final String ERROR_SESION_NULA = "ERROR: La sesión no puede ser nula.";
	private static final String ERROR_COPIAR_CITA_NULA = "ERROR: No es posible copiar una cita nula.";
	private static final String HORA_INCORRECTA = "Debería haber saltado una excepción indicando que la hora es incorrecta";
	private static final String ALUMNO_INCORRECTO = "Debería haber saltado una excepción indicando que el alumno es incorrecto";
	private static final String SESION_INCORRECTA = "Debería haber saltado una excepción indicando que la sesión es incorrecta";
	private static final String CITA_NULA = "Debería haber saltado una excepción indicando que no se puede copiar una cita nula.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String CITA_NO_ESPERADA = "La cita copiada debería ser la misma que la pasada como parámetro.";
	private static final String HORA_NO_ESPERADA = "La hora devuelta no es la misma que la pasada al constructor.";
	private static final String ALUMNO_NO_ESPERADO = "El alumno devuelto no es el mismo que el pasado al constructor.";
	private static final String SESION_NO_ESPERADA = "La sesión devuelta no es la misma que la pasada al constructor.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto sesión.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";

	private static final Sesion SESION = new Sesion(
			new Tutoria(Profesor.getProfesorFicticio("11223344B"), "Revisión tarea presencial1"), 
				LocalDate.now().plusDays(1), LocalTime.of(16, 0), LocalTime.of(17, 0), 10);
	private static final LocalTime HORA = LocalTime.of(16, 0);
	private static final Alumno ALUMNO = new Alumno("Bob Esponja", "bob@gmail.com");

	@Test
	public void constructorAlumnoValidoHoraValidaCreaCitaCorrectamente() {
		Cita cita = new Cita(ALUMNO, SESION, HORA);
		assertThat(ALUMNO_NO_ESPERADO, cita.getAlumno(), is(ALUMNO));
		assertThat(REFERENCIA_NO_ESPERADA, cita.getAlumno(), not(sameInstance(ALUMNO)));
		assertThat(SESION_NO_ESPERADA, cita.getSesion(), is(SESION));
		assertThat(REFERENCIA_NO_ESPERADA, cita.getSesion(), not(sameInstance(SESION)));
		assertThat(HORA_NO_ESPERADA, cita.getHora(), is(HORA));
	}
	
	@Test
	public void constructorAlumnoNoValidoSesionValidaHoraValidaLanzaExcepcion() {
		Cita cita = null;
		try {
			cita = new Cita(null, SESION, HORA);
			fail(ALUMNO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_ALUMNO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, cita, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorAlumnoValidoSesionNoValidaHoraValidaLanzaExcepcion() {
		Cita cita = null;
		try {
			cita = new Cita(ALUMNO, null, HORA);
			fail(SESION_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_SESION_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, cita, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorAlumnoValidoSesionValidaHoraNoValidaLanzaExcepcion() {
		Cita cita = null;
		try {
			cita = new Cita(ALUMNO, SESION, null);
			fail(HORA_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, cita, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			cita = new Cita(ALUMNO, SESION, LocalTime.of(15, 59));
			fail(HORA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORA_FUERA_RANGO));
			assertThat(OBJETO_DEBERIA_SER_NULO, cita, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			cita = new Cita(ALUMNO, SESION, LocalTime.of(16, 51));
			fail(HORA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORA_FUERA_RANGO));
			assertThat(OBJETO_DEBERIA_SER_NULO, cita, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			cita = new Cita(ALUMNO, SESION, LocalTime.of(16, 11));
			fail(HORA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORA_NO_MULTIPLO));
			assertThat(OBJETO_DEBERIA_SER_NULO, cita, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorCitaValidaCopiaCitaCorrectamente() {
		Cita cita1 = new Cita(ALUMNO, SESION, HORA);
		Cita cita2 = new Cita(cita1);
		assertThat(CITA_NO_ESPERADA, cita2, is(cita1));
		assertThat(ALUMNO_NO_ESPERADO, cita2.getAlumno(), is(ALUMNO));
		assertThat(REFERENCIA_NO_ESPERADA, cita2.getAlumno(), not(sameInstance(ALUMNO)));
		assertThat(SESION_NO_ESPERADA, cita2.getSesion(), is(SESION));
		assertThat(REFERENCIA_NO_ESPERADA, cita2.getSesion(), not(sameInstance(SESION)));
		assertThat(HORA_NO_ESPERADA, cita2.getHora(), is(HORA));
	}
	
	@Test
	public void constructorCitaNulaLanzaExcepcion() {
		Cita cita = null;
		try {
			cita = new Cita(null);
			fail(CITA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_COPIAR_CITA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, cita, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		Cita cita = new Cita(ALUMNO, SESION, HORA);
		assertThat(CADENA_NO_ESPERADA, cita.toString(), is(String.format("alumno=%s, sesion=%s, hora=%s", ALUMNO, SESION, HORA.format(Cita.FORMATO_HORA))));
	}
}
