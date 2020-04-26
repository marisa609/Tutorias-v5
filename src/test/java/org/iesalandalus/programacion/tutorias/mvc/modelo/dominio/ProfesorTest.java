package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import org.junit.Test;

public class ProfesorTest {
	
	private static final String ERROR_NOMBRE_NULO = "ERROR: El nombre no puede ser nulo.";
	private static final String ERROR_NOMBRE_NO_VALIDO = "ERROR: El nombre no tiene un formato válido.";
	private static final String ERROR_DNI_NULO = "ERROR: El DNI no puede ser nulo.";
	private static final String ERROR_DNI_NO_VALIDO = "ERROR: El DNI no tiene un formato válido.";
	private static final String ERROR_LETRA_DNI_NO_VALIDA = "ERROR: La letra del DNI no es correcta.";
	private static final String ERROR_CORREO_NULO = "ERROR: El correo no puede ser nulo.";
	private static final String ERROR_CORREO_NO_VALIDO = "ERROR: El formato del correo no es válido.";
	private static final String ERROR_COPIAR_PROFESOR_NULO = "ERROR: No es posible copiar un profesor nulo.";
	private static final String NOMBRE_INCORRECTO = "Debería haber saltado una excepción indicando que el nombre es incorrecto";
	private static final String DNI_INCORRECTO = "Debería haber saltado una excepción indicando que el DNI es incorrecto";
	private static final String CORREO_INCORRECTO = "Debería haber saltado una excepción indicando que el correo es incorrecto";
	private static final String PROFESOR_NULO = "Debería haber saltado una excepción indicando que no se puede copiar un profesor nulo.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String PROFESOR_NO_ESPERADO = "El profesor copiado debería ser el mismo que el pasado como parámetro.";
	private static final String NOMBRE_NO_ESPERADO = "El nombre devuelto no es el mismo que el pasado al constructor.";
	private static final String DNI_NO_ESPERADO = "El DNI devuelto no es el mismo que el pasado al constructor.";
	private static final String CORREO_NO_ESPERADO = "El correo devuelto no es el mismo que el pasado al constructor.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto profesor.";
	private static final String NOMBRE_JRJR = "José Ramón Jiménez Reyes";
	private static final String DNI_JRJR = "11223344B";
	private static final String CORREO_JRJR = "joseramon.jimenez@iesalandalus.org";
	private static final String NOMBRE_MAL_ARDR = "ANDRÉS   RuBiO   dEl             río";
	private static final String NOMBRE_ARDR = "Andrés Rubio Del Río";
	private static final String DNI_ARDR = "22334455Y";
	private static final String CORREO_ARDR = "andres.rubio@iesalandalus.org";

	@Test
	public void constructorNombreValidoDniValidoCorreoValidoCreaProfesorCorrectamente() {
		Profesor profesor = new Profesor(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR);
		assertThat(NOMBRE_NO_ESPERADO, profesor.getNombre(), is(NOMBRE_JRJR));
		assertThat(DNI_NO_ESPERADO, profesor.getDni(), is(DNI_JRJR));
		assertThat(CORREO_NO_ESPERADO, profesor.getCorreo(), is(CORREO_JRJR));
		profesor = new Profesor(NOMBRE_MAL_ARDR, DNI_ARDR, CORREO_ARDR);
		assertThat(NOMBRE_NO_ESPERADO, profesor.getNombre(), is(NOMBRE_ARDR));
		assertThat(DNI_NO_ESPERADO, profesor.getDni(), is(DNI_ARDR));
		assertThat(CORREO_NO_ESPERADO, profesor.getCorreo(), is(CORREO_ARDR));
	}

	@Test
	public void constructorNombreNoValidoDniValidoCorreoValidoLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(null, DNI_JRJR, CORREO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor("", DNI_JRJR, CORREO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor("  ", DNI_JRJR, CORREO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor("Juan", DNI_JRJR, CORREO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoDniNoValidoCorreoValidoLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(NOMBRE_JRJR, null, CORREO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "", CORREO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "   ", CORREO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "11223344", CORREO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "112233445A", CORREO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "11223344AA", CORREO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, "11223344A", CORREO_JRJR);
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_LETRA_DNI_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoDniValidoCorreoNoValidoLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(NOMBRE_JRJR, DNI_JRJR, null);
			fail(CORREO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, DNI_JRJR, "");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, DNI_JRJR, "   ");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, DNI_JRJR, "a@a");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, DNI_JRJR, "@kk.es");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = new Profesor(NOMBRE_JRJR, DNI_JRJR, "pepe@kk.es.");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorProfesorValidoCopiaProfesorCorrectamente() {
		Profesor profesor1 = new Profesor(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR);
		Profesor profesor2 = new Profesor(profesor1);
		assertThat(PROFESOR_NO_ESPERADO, profesor2, is(profesor1));
		assertThat(NOMBRE_NO_ESPERADO, profesor2.getNombre(), is(NOMBRE_JRJR));
		assertThat(DNI_NO_ESPERADO, profesor2.getDni(), is(DNI_JRJR));
		assertThat(CORREO_NO_ESPERADO, profesor2.getCorreo(), is(CORREO_JRJR));
	}
	
	@Test
	public void constructorProfesorNuloLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(null);
			fail(PROFESOR_NULO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_COPIAR_PROFESOR_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getProfesorFicticioDniValidoDevuelveProfesorConDichoDni() {
		Profesor profesor = Profesor.getProfesorFicticio(DNI_ARDR);
		assertThat(DNI_NO_ESPERADO, profesor.getDni(), is(DNI_ARDR));
	}
	
	@Test
	public void getProfesorFicticionDniNoValidoLanzaExcepcion() {
		Profesor profesor = null;
		try {
			profesor = Profesor.getProfesorFicticio(null);
			fail(DNI_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = Profesor.getProfesorFicticio("");
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = Profesor.getProfesorFicticio("   ");
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = Profesor.getProfesorFicticio("11223344");
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = Profesor.getProfesorFicticio("112233445A");
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = Profesor.getProfesorFicticio("11223344AA");
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_DNI_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			profesor = Profesor.getProfesorFicticio("11223344A");
			fail(DNI_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_LETRA_DNI_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULO, profesor, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		Profesor profesor = new Profesor(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR);
		assertThat(CADENA_NO_ESPERADA, profesor.toString(), is(String.format("nombre=%s (%s), DNI=%s, correo=%s", NOMBRE_JRJR, "JRJR", DNI_JRJR, CORREO_JRJR)));
		profesor = new Profesor(NOMBRE_MAL_ARDR, DNI_ARDR, CORREO_ARDR);
		assertThat(CADENA_NO_ESPERADA, profesor.toString(), is(String.format("nombre=%s (%s), DNI=%s, correo=%s", NOMBRE_ARDR, "ARDR", DNI_ARDR, CORREO_ARDR)));
	}

}
