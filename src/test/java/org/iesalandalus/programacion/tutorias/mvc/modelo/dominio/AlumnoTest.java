package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AlumnoTest {
	
	private static final String ERROR_NOMBRE_NULO = "ERROR: El nombre no puede ser nulo.";
	private static final String ERROR_NOMBRE_NO_VALIDO = "ERROR: El nombre no tiene un formato válido.";
	private static final String ERROR_CORREO_NULO = "ERROR: El correo no puede ser nulo.";
	private static final String ERROR_CORREO_NO_VALIDO = "ERROR: El formato del correo no es válido.";
	private static final String ERROR_COPIAR_ALUMNO_NULO = "ERROR: No es posible copiar un alumno nulo.";
	private static final String NOMBRE_INCORRECTO = "Debería haber saltado una excepción indicando que el nombre es incorrecto";
	private static final String CORREO_INCORRECTO = "Debería haber saltado una excepción indicando que el correo es incorrecto";
	private static final String ALUMNO_NULO = "Debería haber saltado una excepción indicando que no se puede copiar un alumno nulo.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String ALUMNO_NO_ESPERADO = "El alumno copiado debería ser el mismo que el pasado como parámetro.";
	private static final String NOMBRE_NO_ESPERADO = "El nombre devuelto no es el mismo que el pasado al constructor.";
	private static final String EXPEDIENTE_NO_ESPERADO = "El expediente devuelto no es el esperado.";
	private static final String CORREO_NO_ESPERADO = "El correo devuelto no es el mismo que el pasado al constructor.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto alumno.";
	private static final String NOMBRE_JRJR = "José Ramón Jiménez Reyes";
	private static final String CORREO_JRJR = "joseramon.jimenez@iesalandalus.org";
	private static final String NOMBRE_MAL_ARDR = "ANDRÉS   RuBiO   dEl             río";
	private static final String NOMBRE_ARDR = "Andrés Rubio Del Río";
	private static final String CORREO_ARDR = "andres.rubio@iesalandalus.org";
	
	private static Alumno primerAlumno;
	private static Alumno segundoAlumno;
	
	@BeforeClass
	public static void inicializaAtributos() {
		primerAlumno = new Alumno(NOMBRE_JRJR, CORREO_JRJR);
		segundoAlumno = new Alumno(NOMBRE_MAL_ARDR, CORREO_ARDR);
	}

	@Test
	public void constructorNombreValidoCorreoValidoCreaAlumnoCorrectamente() {
		assertThat(NOMBRE_NO_ESPERADO, primerAlumno.getNombre(), is(NOMBRE_JRJR));
		assertThat(CORREO_NO_ESPERADO, primerAlumno.getCorreo(), is(CORREO_JRJR));
		assertThat(EXPEDIENTE_NO_ESPERADO, primerAlumno.getExpediente(), is("SP_JRJR_1"));
		assertThat(NOMBRE_NO_ESPERADO, segundoAlumno.getNombre(), is(NOMBRE_ARDR));
		assertThat(CORREO_NO_ESPERADO, segundoAlumno.getCorreo(), is(CORREO_ARDR));
		assertThat(EXPEDIENTE_NO_ESPERADO, segundoAlumno.getExpediente(), is("SP_ARDR_2"));
	}

	@Test
	public void constructorNombreNoValidoCorreoValidoLanzaExcepcion() {
		Alumno alumno = null;
		try {
			alumno = new Alumno(null, CORREO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = new Alumno("", CORREO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = new Alumno("  ", CORREO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = new Alumno("Juan", CORREO_JRJR);
			fail(NOMBRE_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NOMBRE_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorNombreValidoCorreoNoValidoLanzaExcepcion() {
		Alumno alumno = null;
		try {
			alumno = new Alumno(NOMBRE_JRJR, null);
			fail(CORREO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = new Alumno(NOMBRE_JRJR, "");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = new Alumno(NOMBRE_JRJR, "   ");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = new Alumno(NOMBRE_JRJR, "a@a");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = new Alumno(NOMBRE_JRJR, "@kk.es");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = new Alumno(NOMBRE_JRJR, "pepe@kk.es.");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void constructorAlumnoValidoCopiaAlumnoCorrectamente() {
		Alumno alumno1 = new Alumno(NOMBRE_JRJR, CORREO_JRJR);
		Alumno alumno2 = new Alumno(alumno1);
		assertThat(ALUMNO_NO_ESPERADO, alumno2, is(alumno1));
		assertThat(NOMBRE_NO_ESPERADO, alumno2.getNombre(), is(NOMBRE_JRJR));
		assertThat(CORREO_NO_ESPERADO, alumno2.getCorreo(), is(CORREO_JRJR));
	}
	
	@Test
	public void constructorAlumnoNuloLanzaExcepcion() {
		Alumno alumno = null;
		try {
			alumno = new Alumno(null);
			fail(ALUMNO_NULO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_COPIAR_ALUMNO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getAlumnoFicticioDevuelveAlumnoConDichoCorreo() {
		Alumno alumno = Alumno.getAlumnoFicticio("bob@gmail.com");
		assertThat(CORREO_NO_ESPERADO, alumno.getCorreo(), is("bob@gmail.com"));
	}
	
	@Test
	public void getAlumnoFicticioCorreoNoValidoLanzaExcepcion() {
		Alumno alumno = null;
		try {
			alumno = Alumno.getAlumnoFicticio(null);
			fail(CORREO_INCORRECTO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = Alumno.getAlumnoFicticio("");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = Alumno.getAlumnoFicticio("   ");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = Alumno.getAlumnoFicticio("a@a");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = Alumno.getAlumnoFicticio("@kk.es");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			alumno = Alumno.getAlumnoFicticio("pepe@kk.es.");
			fail(CORREO_INCORRECTO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CORREO_NO_VALIDO));
			assertThat(OBJETO_DEBERIA_SER_NULO, alumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		assertThat(CADENA_NO_ESPERADA, primerAlumno.toString(), is(String.format("nombre=%s (%s), correo=%s, expediente=%s", NOMBRE_JRJR, "JRJR", CORREO_JRJR, "SP_JRJR_1")));
		assertThat(CADENA_NO_ESPERADA, segundoAlumno.toString(), is(String.format("nombre=%s (%s), correo=%s, expediente=%s", NOMBRE_ARDR, "ARDR", CORREO_ARDR, "SP_ARDR_2")));
	}

}
