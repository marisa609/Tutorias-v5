package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Alumnos;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlumnosTest {

	private static final String ERROR_INSERTAR_ALUMNO_NULO = "ERROR: No se puede insertar un alumno nulo.";
	private static final String ERROR_BORRAR_ALUMNO_NULO = "ERROR: No se puede borrar un alumno nulo.";
	private static final String ERROR_BUSCAR_ALUMNO_NULO = "ERROR: No se puede buscar un alumno nulo.";
	private static final String ERROR_ALUMNO_EXISTE = "ERROR: Ya existe un alumno con ese expediente.";
	private static final String ERROR_ALUMNO_BORRAR_NO_EXISTE = "ERROR: No existe ningún alumno con ese expediente.";
	private static final String OPERACION_NO_PERMITIDA = "Debería haber saltado una excepción indicando que dicha operación no está permitida.";
	private static final String ALUMNO_NULO = "Debería haber saltado una excepción indicando que no se puede operar con un alumno con expediente nulo.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";
	private static final String TAMANO_NO_ESPERADO = "El tamaño devuelto no es el esperado.";
	private static final String ALUMNO_NO_ESPERADO = "El alumno devuelto no es la que debería ser.";
	
	private static Alumno alumno1;
	private static Alumno alumno2;
	private static Alumno alumno3;
	private static Alumno alumnoRepetido;
	
	@BeforeClass
	public static void asignarValoresAtributos() {
		alumno1 = new Alumno("Bob Esponja", "bob@gmail.com");
		alumno2 = new Alumno("Patricio Estrella", "patricio@gmail.com");
		alumno3 = new Alumno("Calamardo Tentáculos", "calamardo@gmail.com");
		alumnoRepetido = new Alumno(alumno1);
	}
	
	
	@Test
	public void getDevuelveAlumnosOrdenadosPorNombre() {
		IAlumnos alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.insertar(alumno2);
			alumnos.insertar(alumno3);
			List<Alumno> alumnosOrdenados = alumnos.get();
			assertThat(TAMANO_NO_ESPERADO, alumnosOrdenados.size(), is(3));
			assertThat(ALUMNO_NO_ESPERADO, alumnosOrdenados.get(0), is(alumno1));
			assertThat(ALUMNO_NO_ESPERADO, alumnosOrdenados.get(1), is(alumno3));
			assertThat(ALUMNO_NO_ESPERADO, alumnosOrdenados.get(2), is(alumno2));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarAlumnoValidoConAlumnosVaciosInsertaAlumnoCorrectamente() {
		IAlumnos alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(1));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno1), is(alumno1));
			assertThat(REFERENCIA_NO_ESPERADA, alumnos.buscar(alumno1), not(sameInstance(alumno1)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarDosAlumnosValidosInsertaAlumnosCorrectamente() {
		IAlumnos alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.insertar(alumno2);
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(2));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno1), is(alumno1));
			assertThat(REFERENCIA_NO_ESPERADA, alumnos.buscar(alumno1), not(sameInstance(alumno1)));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno2), is(alumno2));
			assertThat(REFERENCIA_NO_ESPERADA, alumnos.buscar(alumno2), not(sameInstance(alumno2)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarTresAlumnosValidosInsertaAlumnosCorrectamente() {
		IAlumnos alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.insertar(alumno2);
			alumnos.insertar(alumno3);
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(3));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno1), is(alumno1));
			assertThat(REFERENCIA_NO_ESPERADA, alumnos.buscar(alumno1), not(sameInstance(alumno1)));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno2), is(alumno2));
			assertThat(REFERENCIA_NO_ESPERADA, alumnos.buscar(alumno2), not(sameInstance(alumno2)));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno3), is(alumno3));
			assertThat(REFERENCIA_NO_ESPERADA, alumnos.buscar(alumno3), not(sameInstance(alumno3)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarAlumnoNuloLanzaExcepcion() {
		IAlumnos alumnos = new Alumnos();
		try {
			alumnos.insertar(null);
			fail(ALUMNO_NULO);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_INSERTAR_ALUMNO_NULO));
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(0));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarAlumnoRepetidoLanzaExcepcion() {
		IAlumnos alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.insertar(alumno2);
			alumnos.insertar(alumno3);
			alumnos.insertar(alumnoRepetido);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_ALUMNO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno2);
			alumnos.insertar(alumno1);
			alumnos.insertar(alumno3);
			alumnos.insertar(alumnoRepetido);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_ALUMNO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno2);
			alumnos.insertar(alumno3);
			alumnos.insertar(alumno1);
			alumnos.insertar(alumnoRepetido);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_ALUMNO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarAlumnoExistenteBorraAlumnoCorrectamente() throws OperationNotSupportedException {
		IAlumnos alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.borrar(alumno1);
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(0));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.insertar(alumno2);
			alumnos.borrar(alumno1);
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(1));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno2), is(alumno2));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.insertar(alumno2);
			alumnos.borrar(alumno2);
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(1));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno1), is(alumno1));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno2), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.insertar(alumno2);
			alumnos.insertar(alumno3);
			alumnos.borrar(alumno1);
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(2));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno1), is(nullValue()));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno2), is(alumno2));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno3), is(alumno3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.insertar(alumno2);
			alumnos.insertar(alumno3);
			alumnos.borrar(alumno2);
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(2));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno2), is(nullValue()));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno1), is(alumno1));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno3), is(alumno3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.insertar(alumno2);
			alumnos.insertar(alumno3);
			alumnos.borrar(alumno3);
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(2));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno3), is(nullValue()));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno1), is(alumno1));
			assertThat(ALUMNO_NO_ESPERADO, alumnos.buscar(alumno2), is(alumno2));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void borrarAlumnoNoExistenteLanzaExcepcion() {
		IAlumnos citas = new Alumnos();
		try {
			citas.insertar(alumno1);
			citas.borrar(alumno2);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_ALUMNO_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		citas = new Alumnos();
		try {
			citas.insertar(alumno1);
			citas.insertar(alumno2);
			citas.borrar(alumno3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_ALUMNO_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(2));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarAlumnoNuloLanzaExcepcion() {
		IAlumnos alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.borrar(null);
			fail(ALUMNO_NULO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BORRAR_ALUMNO_NULO));
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void buscarAlumnoNuloLanzaExcepcion() {
		IAlumnos alumnos = new Alumnos();
		try {
			alumnos.insertar(alumno1);
			alumnos.buscar(null);
			fail(ALUMNO_NULO);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BUSCAR_ALUMNO_NULO));
			assertThat(TAMANO_NO_ESPERADO, alumnos.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}

}
