package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ISesiones;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Sesiones;
import org.junit.BeforeClass;
import org.junit.Test;

public class SesionesTest {

	private static final String ERROR_INSERTAR_SESION_NULA = "ERROR: No se puede insertar una sesión nula.";
	private static final String ERROR_BORRAR_SESION_NULA = "ERROR: No se puede borrar una sesión nula.";
	private static final String ERROR_BUSCAR_SESION_NULA = "ERROR: No se puede buscar una sesión nula.";
	private static final String ERROR_SESION_EXISTE = "ERROR: Ya existe una sesión con esa fecha.";
	private static final String ERROR_SESION_BORRAR_NO_EXISTE = "ERROR: No existe ninguna sesión con esa fecha.";
	private static final String ERROR_TUTORIA_NULA = "ERROR: La tutoría no puede ser nula.";
	private static final String OPERACION_NO_PERMITIDA = "Debería haber saltado una excepción indicando que dicha operación no está permitida.";
	private static final String SESION_NULA = "Debería haber saltado una excepción indicando que no se puede operar con una sesión con fecha no válido.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	private static final String OPERACION_NO_REALIZADA = "La operación no la ha realizado correctamente.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";
	private static final String TAMANO_NO_ESPERADO = "El tamaño devuelto no es el esperado.";
	private static final String SESION_NO_ESPERADA = "La sesión devuelta no es la que debería ser.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto.";
	
	private static Sesion sesion1;
	private static Sesion sesion2;
	private static Sesion sesion3;
	private static Sesion sesion4;
	private static Sesion sesion5;
	private static Sesion sesion6;
	private static Sesion sesionRepetida;
	
	@BeforeClass
	public static void asignarValoresAtributos() {
		sesion1 = new Sesion(new Tutoria(Profesor.getProfesorFicticio("22334455Y"), "Tutoria 1"), LocalDate.now().plusDays(9), LocalTime.of(16, 0), LocalTime.of(18, 0), 30);
		sesion2 = new Sesion(new Tutoria(Profesor.getProfesorFicticio("22334455Y"), "Tutoria 1"), LocalDate.now().plusDays(8), LocalTime.of(16, 0), LocalTime.of(18, 0), 30);
		sesion3 = new Sesion(new Tutoria(Profesor.getProfesorFicticio("22334455Y"), "Tutoria 1"), LocalDate.now().plusDays(7), LocalTime.of(16, 0), LocalTime.of(18, 0), 15);
		sesion4 = new Sesion(new Tutoria(Profesor.getProfesorFicticio("11223344B"), "Tutoria 1"), LocalDate.now().plusDays(9), LocalTime.of(16, 0), LocalTime.of(18, 0), 30);
		sesion5 = new Sesion(new Tutoria(Profesor.getProfesorFicticio("11223344B"), "Tutoria 1"), LocalDate.now().plusDays(8), LocalTime.of(16, 0), LocalTime.of(18, 0), 30);
		sesion6 = new Sesion(new Tutoria(Profesor.getProfesorFicticio("11223344B"), "Tutoria 2"), LocalDate.now().plusDays(7), LocalTime.of(16, 0), LocalTime.of(18, 0), 15);
		sesionRepetida = new Sesion(sesion1);
	}
	
	@Test
	public void getDevuelveSesionesOrdenadasPorTutoriaYPorFechaSesion() {
		ISesiones sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			sesiones.insertar(sesion3);
			sesiones.insertar(sesion4);
			sesiones.insertar(sesion5);
			sesiones.insertar(sesion6);
			List<Sesion> tutoriasOrdenadas = sesiones.get();
			assertThat(OPERACION_NO_REALIZADA, tutoriasOrdenadas.get(0), is(sesion5));
			assertThat(REFERENCIA_NO_ESPERADA, tutoriasOrdenadas.get(0), not(sameInstance(sesion5)));
			assertThat(OPERACION_NO_REALIZADA, tutoriasOrdenadas.get(1), is(sesion4));
			assertThat(REFERENCIA_NO_ESPERADA, tutoriasOrdenadas.get(1), not(sameInstance(sesion4)));
			assertThat(OPERACION_NO_REALIZADA, tutoriasOrdenadas.get(2), is(sesion6));
			assertThat(REFERENCIA_NO_ESPERADA, tutoriasOrdenadas.get(2), not(sameInstance(sesion6)));
			assertThat(OPERACION_NO_REALIZADA, tutoriasOrdenadas.get(3), is(sesion3));
			assertThat(REFERENCIA_NO_ESPERADA, tutoriasOrdenadas.get(3), not(sameInstance(sesion3)));
			assertThat(OPERACION_NO_REALIZADA, tutoriasOrdenadas.get(4), is(sesion2));
			assertThat(REFERENCIA_NO_ESPERADA, tutoriasOrdenadas.get(4), not(sameInstance(sesion2)));
			assertThat(OPERACION_NO_REALIZADA, tutoriasOrdenadas.get(5), is(sesion1));
			assertThat(REFERENCIA_NO_ESPERADA, tutoriasOrdenadas.get(5), not(sameInstance(sesion1)));
			assertThat(OBJETO_DEBERIA_SER_NULO, tutoriasOrdenadas.size(), is(6));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getTutoriaValidaDevuelveSesionesTutoriaOrdenadasPorFechaSesion() {
		ISesiones sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			sesiones.insertar(sesion3);
			List<Sesion> sesionesTutoria = sesiones.get(new Tutoria(Profesor.getProfesorFicticio("22334455Y"), "Tutoria 1"));
			assertThat(OPERACION_NO_REALIZADA, sesionesTutoria.get(0), is(sesion3));
			assertThat(REFERENCIA_NO_ESPERADA, sesionesTutoria.get(0), not(sameInstance(sesion3)));
			assertThat(OPERACION_NO_REALIZADA, sesionesTutoria.get(1), is(sesion2));
			assertThat(REFERENCIA_NO_ESPERADA, sesionesTutoria.get(1), not(sameInstance(sesion2)));
			assertThat(OPERACION_NO_REALIZADA, sesionesTutoria.get(2), is(sesion1));
			assertThat(REFERENCIA_NO_ESPERADA, sesionesTutoria.get(2), not(sameInstance(sesion1)));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesionesTutoria.size(), is(3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getTutoriaNoValidaLanzaExcepcion() {
		ISesiones sesiones = new Sesiones();
		List<Sesion> sesionesTutoria = null;
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			sesiones.insertar(sesion3);
			sesionesTutoria = sesiones.get(null);
			fail(OPERACION_NO_PERMITIDA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_TUTORIA_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, sesionesTutoria, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarSesionValidaConSesionesVaciasInsertaSesionCorrectamente() {
		ISesiones sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(1));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion1), is(sesion1));
			assertThat(REFERENCIA_NO_ESPERADA, sesiones.buscar(sesion1), not(sameInstance(sesion1)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarDosSesionesValidasInsertaSesionesCorrectamente() {
		ISesiones sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(2));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion1), is(sesion1));
			assertThat(REFERENCIA_NO_ESPERADA, sesiones.buscar(sesion1), not(sameInstance(sesion1)));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion2), is(sesion2));
			assertThat(REFERENCIA_NO_ESPERADA, sesiones.buscar(sesion2), not(sameInstance(sesion2)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarTresSesionesValidasInsertaSesionesCorrectamente() {
		ISesiones sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			sesiones.insertar(sesion3);
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(3));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion1), is(sesion1));
			assertThat(REFERENCIA_NO_ESPERADA, sesiones.buscar(sesion1), not(sameInstance(sesion1)));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion2), is(sesion2));
			assertThat(REFERENCIA_NO_ESPERADA, sesiones.buscar(sesion2), not(sameInstance(sesion2)));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion3), is(sesion3));
			assertThat(REFERENCIA_NO_ESPERADA, sesiones.buscar(sesion3), not(sameInstance(sesion3)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarSesionNulaLanzaExcepcion() {
		ISesiones sesiones = new Sesiones();
		try {
			sesiones.insertar(null);
			fail(SESION_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_INSERTAR_SESION_NULA));
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(0));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarSesionRepetidaLanzaExcepcion() {
		ISesiones sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			sesiones.insertar(sesion3);
			sesiones.insertar(sesionRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_SESION_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion2);
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion3);
			sesiones.insertar(sesionRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_SESION_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion2);
			sesiones.insertar(sesion3);
			sesiones.insertar(sesion1);
			sesiones.insertar(sesionRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_SESION_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarSesionExistenteBorraSesionCorrectamente() throws OperationNotSupportedException {
		ISesiones sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.borrar(sesion1);
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(0));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			sesiones.borrar(sesion1);
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(1));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion2), is(sesion2));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			sesiones.borrar(sesion2);
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(1));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion1), is(sesion1));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion2), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			sesiones.insertar(sesion3);
			sesiones.borrar(sesion1);
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(2));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion1), is(nullValue()));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion2), is(sesion2));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion3), is(sesion3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			sesiones.insertar(sesion3);
			sesiones.borrar(sesion2);
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(2));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion2), is(nullValue()));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion1), is(sesion1));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion3), is(sesion3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			sesiones.insertar(sesion3);
			sesiones.borrar(sesion3);
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(2));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion3), is(nullValue()));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion1), is(sesion1));
			assertThat(SESION_NO_ESPERADA, sesiones.buscar(sesion2), is(sesion2));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void borrarSesionNoExistenteLanzaExcepcion() {
		ISesiones sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.borrar(sesion2);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_SESION_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.insertar(sesion2);
			sesiones.borrar(sesion3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_SESION_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(2));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarSesionNulaLanzaExcepcion() {
		ISesiones sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.borrar(null);
			fail(SESION_NULA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BORRAR_SESION_NULA));
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void buscarSesionNulaLanzaExcepcion() {
		ISesiones sesiones = new Sesiones();
		try {
			sesiones.insertar(sesion1);
			sesiones.buscar(null);
			fail(SESION_NULA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BUSCAR_SESION_NULA));
			assertThat(TAMANO_NO_ESPERADO, sesiones.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}

}
