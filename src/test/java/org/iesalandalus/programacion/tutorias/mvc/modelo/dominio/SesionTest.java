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

public class SesionTest {
	
	private static final String ERROR_TUTORIA_NULA = "ERROR: La tutoría no puede ser nula.";
	private static final String ERROR_FECHA_NULA = "ERROR: La fecha no puede ser nula.";
	private static final String ERROR_FECHA_NO_VALIDA = "ERROR: Las sesiones de deben planificar para fechas futuras.";
	private static final String ERROR_HORA_INICIO_NULA = "ERROR: La hora de inicio no puede ser nula.";
	private static final String ERROR_HORA_INICIO_NO_VALIDA = "ERROR: La hora de inicio no es válida.";
	private static final String ERROR_HORA_FIN_NULA = "ERROR: La hora de fin no puede ser nula.";
	private static final String ERROR_HORA_FIN_NO_VALIDA = "ERROR: La hora de fin no es válida.";
	private static final String ERROR_MINUTOS_DURACION_NO_VALIDOS = "ERROR: Los minutos de duración no son válidos.";
	private static final String ERROR_HORAS_NO_VALIDAS = "ERROR: Las hora para establecer la sesión no son válidas.";
	private static final String ERROR_MINUTOS_DURACION_NO_DIVISOR = "ERROR: Los minutos de duración no es divisor de los minutos establecidos para toda la sesión.";
	private static final String ERROR_COPIAR_SESION_NULA = "ERROR: No es posible copiar una sesión nula.";
	private static final String TUTORIA_INCORRECTA = "Debería haber saltado una excepción indicando que la tutoría es incorrecta";
	private static final String FECHA_INCORRECTA = "Debería haber saltado una excepción indicando que la fecha es incorrecta";
	private static final String HORA_INICIO_INCORRECTA = "Debería haber saltado una excepción indicando que la hora de inicio es incorrecta";
	private static final String HORA_FIN_INCORRECTA = "Debería haber saltado una excepción indicando que la hora de fin es incorrecta";
	private static final String MINUTOS_DURACION_INCORRECTOS = "Debería haber saltado una excepción indicando que los minutos de duración son incorrectos";
	private static final String SESION_NULA = "Debería haber saltado una excepción indicando que no se puede copiar una sesión nula.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String SESION_NO_ESPERADA = "La sesión copiada debería ser la misma que la pasada como parámetro.";
	private static final String TUTORIA_NO_ESPERADA = "La fecha devuelta no es la misma que la pasada al constructor.";
	private static final String FECHA_NO_ESPERADA = "La fecha devuelta no es la misma que la pasada al constructor.";
	private static final String HORA_INICIO_NO_ESPERADA = "La hora de inicio devuelta no es la misma que la pasada al constructor.";
	private static final String HORA_FIN_NO_ESPERADA = "La hora de fin devuelta no es la misma que la pasada al constructor.";
	private static final String MINUTOS_DURACION_NO_ESPERADOS = "Los minutos de duración devueltos no son los mismos que los pasados al constructor.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto sesión.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";
	private static final Tutoria TUTORIA = new Tutoria(new Profesor("Bob Esponja", "11223344B", "bob@gmail.com"), "Resolución de dudas Unidad 3");
	private static final LocalDate FECHA = LocalDate.now().plusDays(7);
	private static final LocalTime HORA_INICIO = LocalTime.of(17, 0);
	private static final LocalTime HORA_FIN = LocalTime.of(19, 0);
	private static final int MINUTOS_DURACION = 30;

	@Test
	public void constructorTutoriaValidaFechaValidaHoraInicioValidaHoraFinValidaDuracionMinutosValidosCreaSesionCorrectamente() {
		Sesion sesion = new Sesion(TUTORIA, FECHA, HORA_INICIO, HORA_FIN, MINUTOS_DURACION);
		assertThat(TUTORIA_NO_ESPERADA, sesion.getTutoria(), is(TUTORIA));
		assertThat(REFERENCIA_NO_ESPERADA, sesion.getTutoria(), not(sameInstance(TUTORIA)));
		assertThat(FECHA_NO_ESPERADA, sesion.getFecha(), is(FECHA));
		assertThat(HORA_INICIO_NO_ESPERADA, sesion.getHoraInicio(), is(HORA_INICIO));
		assertThat(HORA_FIN_NO_ESPERADA, sesion.getHoraFin(), is(HORA_FIN));
		assertThat(MINUTOS_DURACION_NO_ESPERADOS, sesion.getMinutosDuracion(), is(MINUTOS_DURACION));
	}
	
	@Test
	public void constructorTutoriaNoValidaFechaValidaHoraInicioValidaHoraFinValidaDuracionMinutosValidosLanzaExcepcion() {
		Sesion sesion = null;
		try {
			sesion = new Sesion(null, FECHA, HORA_INICIO, HORA_FIN, MINUTOS_DURACION);
			fail(TUTORIA_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TUTORIA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorTutoriaValidaFechaNoValidaHoraInicioValidaHoraFinValidaDuracionMinutosValidosLanzaExcepcion() {
		Sesion sesion = null;
		try {
			sesion = new Sesion(TUTORIA, null, HORA_INICIO, HORA_FIN, MINUTOS_DURACION);
			fail(FECHA_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_FECHA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			sesion = new Sesion(TUTORIA, LocalDate.now(), HORA_INICIO, HORA_FIN, MINUTOS_DURACION);
			fail(FECHA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_FECHA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorTutoriaValidaFechaValidaHoraInicioNoValidaHoraFinValidaDuracionMinutosValidosLanzaExcepcion() {
		Sesion sesion = null;
		try {
			sesion = new Sesion(TUTORIA, FECHA, null, HORA_FIN, MINUTOS_DURACION);
			fail(HORA_INICIO_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORA_INICIO_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			sesion = new Sesion(TUTORIA, FECHA, LocalTime.of(15, 59), HORA_FIN, MINUTOS_DURACION);
			fail(HORA_INICIO_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORA_INICIO_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			sesion = new Sesion(TUTORIA, FECHA, LocalTime.of(22, 15), HORA_FIN, MINUTOS_DURACION);
			fail(HORA_INICIO_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORA_INICIO_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			sesion = new Sesion(TUTORIA, FECHA, LocalTime.of(22, 16), HORA_FIN, MINUTOS_DURACION);
			fail(HORA_INICIO_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORA_INICIO_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorTutoriaValidaFechaValidaHoraInicioValidaHoraFinNoValidaDuracionMinutosValidosLanzaExcepcion() {
		Sesion sesion = null;
		try {
			sesion = new Sesion(TUTORIA, FECHA, HORA_INICIO, null, MINUTOS_DURACION);
			fail(HORA_FIN_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORA_FIN_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			sesion = new Sesion(TUTORIA, FECHA, HORA_INICIO, LocalTime.of(15, 59), MINUTOS_DURACION);
			fail(HORA_FIN_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORA_FIN_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			sesion = new Sesion(TUTORIA, FECHA, HORA_INICIO, LocalTime.of(22, 16), MINUTOS_DURACION);
			fail(HORA_FIN_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORA_FIN_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			sesion = new Sesion(TUTORIA, FECHA, HORA_INICIO, HORA_INICIO, MINUTOS_DURACION);
			fail(HORA_FIN_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORAS_NO_VALIDAS));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			sesion = new Sesion(TUTORIA, FECHA, HORA_INICIO.plusMinutes(1), HORA_INICIO, MINUTOS_DURACION);
			fail(HORA_FIN_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_HORAS_NO_VALIDAS));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorTutoriaValidaFechaValidaHoraInicioValidaHoraFinValidaDuracionMinutosNoValidosLanzaExcepcion() {
		Sesion sesion = null;
		try {
			sesion = new Sesion(TUTORIA, FECHA, HORA_INICIO, HORA_FIN, 0);
			fail(MINUTOS_DURACION_INCORRECTOS);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_MINUTOS_DURACION_NO_VALIDOS));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			sesion = new Sesion(TUTORIA, FECHA, HORA_INICIO, HORA_FIN, 13);
			fail(MINUTOS_DURACION_INCORRECTOS);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_MINUTOS_DURACION_NO_DIVISOR));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorSesionValidaCopiaSesionCorrectamente() {
		Sesion sesion1 = new Sesion(TUTORIA, FECHA, HORA_INICIO, HORA_FIN, MINUTOS_DURACION);
		Sesion sesion2 = new Sesion(sesion1);
		assertThat(SESION_NO_ESPERADA, sesion2, is(sesion1));
		assertThat(TUTORIA_NO_ESPERADA, sesion2.getTutoria(), is(TUTORIA));
		assertThat(REFERENCIA_NO_ESPERADA, sesion2.getTutoria(), not(sameInstance(TUTORIA)));
		assertThat(FECHA_NO_ESPERADA, sesion2.getFecha(), is(FECHA));
		assertThat(HORA_INICIO_NO_ESPERADA, sesion2.getHoraInicio(), is(HORA_INICIO));
		assertThat(HORA_FIN_NO_ESPERADA, sesion2.getHoraFin(), is(HORA_FIN));
		assertThat(MINUTOS_DURACION_NO_ESPERADOS, sesion2.getMinutosDuracion(), is(MINUTOS_DURACION));
	}
	
	@Test
	public void constructorSesionNulaLanzaExcepcion() {
		Sesion sesion = null;
		try {
			sesion = new Sesion(null);
			fail(SESION_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_COPIAR_SESION_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getSesionFicticiaDevuelveSesionConDichaTutoriaYFecha() {
		Sesion sesion = Sesion.getSesionFicticia(TUTORIA, FECHA);
		assertThat(TUTORIA_NO_ESPERADA, sesion.getTutoria(), is(TUTORIA));
		assertThat(REFERENCIA_NO_ESPERADA, sesion.getTutoria(), not(sameInstance(TUTORIA)));
		assertThat(FECHA_NO_ESPERADA, sesion.getFecha(), is(FECHA));
	}
	
	@Test
	public void getSesionFicticiaTutoriaNoValidaFechaValidaLanzaExcepcion() {
		Sesion sesion = null;
		try {
			sesion = Sesion.getSesionFicticia(null, FECHA);
			fail(TUTORIA_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TUTORIA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getSesionFicticiaTutoriaValidaFechaNoValidaLanzaExcepcion() {
		Sesion sesion = null;
		try {
			sesion = Sesion.getSesionFicticia(TUTORIA, null);
			fail(FECHA_INCORRECTA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_FECHA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		Sesion sesion = new Sesion(TUTORIA, FECHA, HORA_INICIO, HORA_FIN, MINUTOS_DURACION);
		assertThat(CADENA_NO_ESPERADA, sesion.toString(), is(String.format("tutoria=%s, fecha=%s, horaInicio=%s, horaFin=%s, minutosDuracion=%d", 
				TUTORIA, LocalDate.now().plusDays(7).format(Sesion.FORMATO_FECHA), HORA_INICIO.format(Sesion.FORMATO_HORA), 
				HORA_FIN.format(Sesion.FORMATO_HORA), MINUTOS_DURACION)));
	}
}
