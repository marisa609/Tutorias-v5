package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

public class TutoriaTest {

	
	private static final String ERROR_NOMBRE_NULO = "ERROR: El nombre no puede ser nulo.";
	private static final String ERROR_NOMBRE_NO_VALIDO = "ERROR: El nombre no tiene un formato válido.";
	private static final String ERROR_PROFESOR_NULO = "ERROR: El profesor no puede ser nulo.";
	private static final String ERROR_COPIAR_TUTORIA_NULA = "ERROR: No es posible copiar una tutoría nula.";
	private static final String NOMBRE_INCORRECTO = "Debería haber saltado una excepción indicando que el nombre es incorrecto";
	private static final String PROFESOR_INCORRECTO = "Debería haber saltado una excepción indicando que el profesor es incorrecto";
	private static final String TUTORIA_NULA = "Debería haber saltado una excepción indicando que no se puede copiar una tutoría nula.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String TUTORIA_NO_ESPERADA = "La tutoría copiada debería ser la misma que la pasada como parámetro.";
	private static final String NOMBRE_NO_ESPERADO = "El nombre devuelto no es el mismo que el pasado al constructor.";
	private static final String PROFESOR_NO_ESPERADO = "El profesor devuelto no es el mismo que el pasado al constructor.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto tutoría.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";
	private static final String NOMBRE1 = "Resolución de dudas de la unidad 5";
	private static final String NOMBRE2_MAL = "    Revisión tarea presencial 4   ";
	private static final String NOMBRE2_BIEN = "Revisión tarea presencial 4";
	
	private static Profesor profesor1;
	private static Profesor profesor2;
	private static Tutoria tutoria1;
	private static Tutoria tutoria2;
	
	@BeforeClass
	public static void inicializaAtributos() {
		profesor1 = new Profesor("Bob Esponja", "11223344B", "bob@gmail.com");
		profesor2 = new Profesor("Patricio Estrella", "22334455Y", "patricio@gmail.com");
		tutoria1 = new Tutoria(profesor1, NOMBRE1);
		tutoria2 = new Tutoria(profesor2, NOMBRE2_MAL);
	}

	@Test
	public void constructorProfesorValidoNombreValidoCreaTutoriaCorrectamente() {
		assertThat(PROFESOR_NO_ESPERADO, tutoria1.getProfesor(), is(profesor1));
		assertThat(REFERENCIA_NO_ESPERADA, tutoria1.getProfesor(), not(sameInstance(profesor1)));
		assertThat(NOMBRE_NO_ESPERADO, tutoria1.getNombre(), is(NOMBRE1));
		assertThat(PROFESOR_NO_ESPERADO, tutoria2.getProfesor(), is(profesor2));
		assertThat(REFERENCIA_NO_ESPERADA, tutoria2.getProfesor(), not(sameInstance(profesor2)));
		assertThat(NOMBRE_NO_ESPERADO, tutoria2.getNombre(), is(NOMBRE2_BIEN));
	}

	@Test
	public void constructorProfesorNoValidoNombreValidoLanzaExcepcion() {
		Tutoria tutoria = null;
		String nombre = null;
		try {
			tutoria = new Tutoria(null, nombre);
			fail(PROFESOR_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_PROFESOR_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, tutoria, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorProfesorValidoNombreNoValidoLanzaExcepcion() {
		Tutoria tutoria = null;
		String nombre = null;
		try {
			tutoria = new Tutoria(profesor1, nombre);
			fail(NOMBRE_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, tutoria, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			tutoria = new Tutoria(profesor1, "");
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, tutoria, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			tutoria = new Tutoria(profesor1, "  ");
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, tutoria, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorTutoriaValidaCopiaTutoriaCorrectamente() {
		Tutoria tutoria1 = new Tutoria(profesor1, NOMBRE1);
		Tutoria tutoria2 = new Tutoria(tutoria1);
		assertThat(TUTORIA_NO_ESPERADA, tutoria2, is(tutoria1));
		assertThat(REFERENCIA_NO_ESPERADA, tutoria2, not(sameInstance(tutoria1)));
		assertThat(PROFESOR_NO_ESPERADO, tutoria1.getProfesor(), is(profesor1));
		assertThat(REFERENCIA_NO_ESPERADA, tutoria1.getProfesor(), not(sameInstance(profesor1)));
		assertThat(NOMBRE_NO_ESPERADO, tutoria2.getNombre(), is(NOMBRE1));
	}
	
	@Test
	public void constructorTutoriaNulaLanzaExcepcion() {
		Tutoria tutoria = null;
		try {
			tutoria = new Tutoria(tutoria);
			fail(TUTORIA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_COPIAR_TUTORIA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, tutoria, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
		
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		assertThat(CADENA_NO_ESPERADA, tutoria1.toString(), is(String.format("profesor=%s, nombre=%s", profesor1, NOMBRE1)));
		assertThat(CADENA_NO_ESPERADA, tutoria2.toString(), is(String.format("profesor=%s, nombre=%s", profesor2, NOMBRE2_BIEN)));
	}
}
