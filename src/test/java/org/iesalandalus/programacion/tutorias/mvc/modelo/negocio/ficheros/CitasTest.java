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

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ICitas;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.Citas;
import org.junit.BeforeClass;
import org.junit.Test;

public class CitasTest {

	private static final String ERROR_INSERTAR_CITA_NULA = "ERROR: No se puede insertar una cita nula.";
	private static final String ERROR_BORRAR_CITA_NULA = "ERROR: No se puede borrar una cita nula.";
	private static final String ERROR_BUSCAR_CITA_NULA = "ERROR: No se puede buscar una cita nula.";
	private static final String ERROR_SESION_NULA = "ERROR: La sesión no puede ser nula.";
	private static final String ERROR_ALUMNO_NULO = "ERROR: El alumno no puede ser nulo.";
	private static final String ERROR_CITA_EXISTE = "ERROR: Ya existe una cita con esa hora.";
	private static final String ERROR_CITA_BORRAR_NO_EXISTE = "ERROR: No existe ninguna cita con esa hora.";
	private static final String OPERACION_NO_PERMITIDA = "Debería haber saltado una excepción indicando que dicha operación no está permitida.";
	private static final String SESION_NULA = "Debería haber saltado una excepción indicando que no se puede operar con una sesión con hora no válida.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";
	private static final String TAMANO_NO_ESPERADO = "El tamaño devuelto no es el esperado.";
	private static final String CITA_NO_ESPERADA = "La cita devuelta no es la que debería ser.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto.";
	
	private static Cita cita1;
	private static Cita cita2;
	private static Cita cita3;
	private static Cita cita4;
	private static Cita cita5;
	private static Cita cita6;
	private static Cita cita7;
	private static Cita cita8;
	private static Cita citaRepetida;
	
	@BeforeClass
	public static void asignarValoresAtributos() {
		Sesion sesion1 = new Sesion(new Tutoria(Profesor.getProfesorFicticio("22334455Y"), "Tutoria 1"), LocalDate.now().plusDays(8), LocalTime.of(16, 0), LocalTime.of(18, 0), 15);
		Sesion sesion2 = new Sesion(new Tutoria(Profesor.getProfesorFicticio("22334455Y"), "Tutoria 1"), LocalDate.now().plusDays(7), LocalTime.of(16, 0), LocalTime.of(18, 0), 15);
		Sesion sesion3 = new Sesion(new Tutoria(Profesor.getProfesorFicticio("11223344B"), "Tutoria 2"), LocalDate.now().plusDays(8), LocalTime.of(16, 0), LocalTime.of(18, 0), 15);
		Sesion sesion4 = new Sesion(new Tutoria(Profesor.getProfesorFicticio("11223344B"), "Tutoria 2"), LocalDate.now().plusDays(7), LocalTime.of(16, 0), LocalTime.of(18, 0), 15);
		cita1 = new Cita(Alumno.getAlumnoFicticio("patricio@gmail.com"), sesion1, LocalTime.of(17, 15));
		cita2 = new Cita(Alumno.getAlumnoFicticio("patricio@gmail.com"), sesion2, LocalTime.of(16, 0));
		cita3 = new Cita(Alumno.getAlumnoFicticio("patricio@gmail.com"), sesion3, LocalTime.of(16, 15));
		cita4 = new Cita(Alumno.getAlumnoFicticio("patricio@gmail.com"), sesion4, LocalTime.of(16, 0));
		cita5 = new Cita(Alumno.getAlumnoFicticio("bob@gmail.com"), sesion1, LocalTime.of(16, 15));
		cita6 = new Cita(Alumno.getAlumnoFicticio("bob@gmail.com"), sesion1, LocalTime.of(17, 0));
		cita7 = new Cita(Alumno.getAlumnoFicticio("bob@gmail.com"), sesion1, LocalTime.of(17, 45));
		cita8 = new Cita(Alumno.getAlumnoFicticio("bob@gmail.com"), sesion1, LocalTime.of(17, 30));
		citaRepetida = new Cita(cita1);
	}
	
	@Test
	public void getDevuelveCitasOrdenadasPorSesionYPorNombre() {
		ICitas citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.insertar(cita3);
			citas.insertar(cita4);
			citas.insertar(cita5);
			citas.insertar(cita6);
			citas.insertar(cita7);
			citas.insertar(cita8);
			List<Cita> citasOrdendadas = citas.get();
			assertThat(CITA_NO_ESPERADA, citasOrdendadas.get(0), is(cita4));
			assertThat(REFERENCIA_NO_ESPERADA, citasOrdendadas.get(0), not(sameInstance(cita4)));
			assertThat(CITA_NO_ESPERADA, citasOrdendadas.get(1), is(cita3));
			assertThat(REFERENCIA_NO_ESPERADA, citasOrdendadas.get(1), not(sameInstance(cita3)));
			assertThat(CITA_NO_ESPERADA, citasOrdendadas.get(2), is(cita2));
			assertThat(REFERENCIA_NO_ESPERADA, citasOrdendadas.get(2), not(sameInstance(cita2)));
			assertThat(CITA_NO_ESPERADA, citasOrdendadas.get(3), is(cita5));
			assertThat(REFERENCIA_NO_ESPERADA, citasOrdendadas.get(3), not(sameInstance(cita5)));
			assertThat(CITA_NO_ESPERADA, citasOrdendadas.get(4), is(cita6));
			assertThat(REFERENCIA_NO_ESPERADA, citasOrdendadas.get(4), not(sameInstance(cita6)));
			assertThat(CITA_NO_ESPERADA, citasOrdendadas.get(5), is(cita1));
			assertThat(REFERENCIA_NO_ESPERADA, citasOrdendadas.get(5), not(sameInstance(cita1)));
			assertThat(CITA_NO_ESPERADA, citasOrdendadas.get(6), is(cita8));
			assertThat(REFERENCIA_NO_ESPERADA, citasOrdendadas.get(6), not(sameInstance(cita8)));
			assertThat(CITA_NO_ESPERADA, citasOrdendadas.get(7), is(cita7));
			assertThat(REFERENCIA_NO_ESPERADA, citasOrdendadas.get(7), not(sameInstance(cita7)));
			assertThat(TAMANO_NO_ESPERADO, citasOrdendadas.size(), is(8));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getSesionValidaDevuelveCitasSesionOrdenadas() {
		ICitas citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.insertar(cita3);
			citas.insertar(cita4);
			citas.insertar(cita5);
			citas.insertar(cita6);
			citas.insertar(cita7);
			citas.insertar(cita8);
			Sesion sesion = new Sesion(new Tutoria(Profesor.getProfesorFicticio("22334455Y"), "Tutoria 1"), LocalDate.now().plusDays(8), LocalTime.of(16, 0), LocalTime.of(18, 0), 15);
			List<Cita> citasSesionOrdendadas = citas.get(sesion);
			assertThat(CITA_NO_ESPERADA, citasSesionOrdendadas.get(0), is(cita5));
			assertThat(REFERENCIA_NO_ESPERADA, citasSesionOrdendadas.get(0), not(sameInstance(cita5)));
			assertThat(CITA_NO_ESPERADA, citasSesionOrdendadas.get(1), is(cita6));
			assertThat(REFERENCIA_NO_ESPERADA, citasSesionOrdendadas.get(1), not(sameInstance(cita6)));
			assertThat(CITA_NO_ESPERADA, citasSesionOrdendadas.get(2), is(cita1));
			assertThat(REFERENCIA_NO_ESPERADA, citasSesionOrdendadas.get(2), not(sameInstance(cita1)));
			assertThat(CITA_NO_ESPERADA, citasSesionOrdendadas.get(3), is(cita8));
			assertThat(REFERENCIA_NO_ESPERADA, citasSesionOrdendadas.get(3), not(sameInstance(cita8)));
			assertThat(CITA_NO_ESPERADA, citasSesionOrdendadas.get(4), is(cita7));
			assertThat(REFERENCIA_NO_ESPERADA, citasSesionOrdendadas.get(4), not(sameInstance(cita7)));
			assertThat(TAMANO_NO_ESPERADO, citasSesionOrdendadas.size(), is(5));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getSesionNulaLanzaExcepcion() {
		ICitas citas = new Citas();
		List<Cita> citasSesion = null;
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.insertar(cita3);
			Sesion sesion = null;
			citasSesion = citas.get(sesion);
			fail(SESION_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_SESION_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULO, citasSesion, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void getAlumnoValidoDevuelveCitasAlumnoOrdenadas() {
		ICitas citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.insertar(cita3);
			citas.insertar(cita4);
			citas.insertar(cita5);
			citas.insertar(cita6);
			citas.insertar(cita7);
			citas.insertar(cita8);
			List<Cita> citasAlumnoOrdendadas = citas.get(Alumno.getAlumnoFicticio("patricio@gmail.com"));
			assertThat(CITA_NO_ESPERADA, citasAlumnoOrdendadas.get(0), is(cita4));
			assertThat(REFERENCIA_NO_ESPERADA, citasAlumnoOrdendadas.get(0), not(sameInstance(cita4)));
			assertThat(CITA_NO_ESPERADA, citasAlumnoOrdendadas.get(1), is(cita3));
			assertThat(REFERENCIA_NO_ESPERADA, citasAlumnoOrdendadas.get(1), not(sameInstance(cita3)));
			assertThat(CITA_NO_ESPERADA, citasAlumnoOrdendadas.get(2), is(cita2));
			assertThat(REFERENCIA_NO_ESPERADA, citasAlumnoOrdendadas.get(2), not(sameInstance(cita2)));
			assertThat(CITA_NO_ESPERADA, citasAlumnoOrdendadas.get(3), is(cita1));
			assertThat(REFERENCIA_NO_ESPERADA, citasAlumnoOrdendadas.get(3), not(sameInstance(cita1)));
			assertThat(TAMANO_NO_ESPERADO, citasAlumnoOrdendadas.size(), is(4));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void getAlumnoNuloLanzaExcepcion() {
		ICitas citas = new Citas();
		List<Cita> citasAlumno = null;
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.insertar(cita3);
			Alumno alumno = null;
			citasAlumno = citas.get(alumno);
			fail(SESION_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_ALUMNO_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULO, citasAlumno, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}

	@Test
	public void insertarCitaValidaConCitasVaciasInsertaCitaCorrectamente() {
		ICitas citas = new Citas();
		try {
			citas.insertar(cita1);
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(1));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita1), is(cita1));
			assertThat(REFERENCIA_NO_ESPERADA, citas.buscar(cita1), not(sameInstance(cita1)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarDosCitasValidasInsertaCitasCorrectamente() {
		ICitas citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(2));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita1), is(cita1));
			assertThat(REFERENCIA_NO_ESPERADA, citas.buscar(cita1), not(sameInstance(cita1)));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita2), is(cita2));
			assertThat(REFERENCIA_NO_ESPERADA, citas.buscar(cita2), not(sameInstance(cita2)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarTresCitasValidasInsertaCitasCorrectamente() {
		ICitas citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.insertar(cita3);
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(3));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita1), is(cita1));
			assertThat(REFERENCIA_NO_ESPERADA, citas.buscar(cita1), not(sameInstance(cita1)));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita2), is(cita2));
			assertThat(REFERENCIA_NO_ESPERADA, citas.buscar(cita2), not(sameInstance(cita2)));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita3), is(cita3));
			assertThat(REFERENCIA_NO_ESPERADA, citas.buscar(cita3), not(sameInstance(cita3)));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarCitaNulaLanzaExcepcion() {
		ICitas citas = new Citas();
		try {
			citas.insertar(null);
			fail(SESION_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_INSERTAR_CITA_NULA));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(0));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarCitaRepetidaLanzaExcepcion() {
		ICitas citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.insertar(cita3);
			citas.insertar(citaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CITA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		citas = new Citas();
		try {
			citas.insertar(cita2);
			citas.insertar(cita1);
			citas.insertar(cita3);
			citas.insertar(citaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CITA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		citas = new Citas();
		try {
			citas.insertar(cita2);
			citas.insertar(cita3);
			citas.insertar(cita1);
			citas.insertar(citaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CITA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarCitaExistenteBorraCitaCorrectamente() throws OperationNotSupportedException {
		ICitas citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.borrar(cita1);
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(0));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.borrar(cita1);
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(1));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita2), is(cita2));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.borrar(cita2);
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(1));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita1), is(cita1));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita2), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.insertar(cita3);
			citas.borrar(cita1);
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(2));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita1), is(nullValue()));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita2), is(cita2));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita3), is(cita3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.insertar(cita3);
			citas.borrar(cita2);
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(2));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita2), is(nullValue()));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita1), is(cita1));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita3), is(cita3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.insertar(cita3);
			citas.borrar(cita3);
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(2));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita3), is(nullValue()));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita1), is(cita1));
			assertThat(CITA_NO_ESPERADA, citas.buscar(cita2), is(cita2));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void borrarCitaNoExistenteLanzaExcepcion() {
		ICitas citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.borrar(cita2);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CITA_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.insertar(cita2);
			citas.borrar(cita3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CITA_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(2));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarCitaNulaLanzaExcepcion() {
		ICitas citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.borrar(null);
			fail(SESION_NULA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BORRAR_CITA_NULA));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void buscarCitaNulaLanzaExcepcion() {
		ICitas citas = new Citas();
		try {
			citas.insertar(cita1);
			citas.buscar(null);
			fail(SESION_NULA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BUSCAR_CITA_NULA));
			assertThat(TAMANO_NO_ESPERADO, citas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}

}
