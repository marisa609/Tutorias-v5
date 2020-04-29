package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.utilidades.Dialogos;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

/*Implementa Initializable y añade el método initialize
 * 
 * Importar el IControlador y asignarlo
 * Crear ObservableList
 * En FactoriaVista añadir el IUGPESTANAS
 * 
 * 
 */

public class ControladorVentanaPrincipal implements Initializable {

	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String BORRAR_PROFESOR = "Borrar Profesor";
	private static final String BORRAR_ALUMNO = "Borrar Alumno";
	private static final String BORRAR_TUTORIA = "Borrar Tutoria";
	private static final String BORRAR_SESION = "Borrar Sesion";
	private static final String BORRAR_CITA = "Borrar Cita";
	private static final String ANADIR_PROFESOR = "Añadir Profesor";
	private static final String ANADIR_ALUMNO = "Añadir Alumno";
	private static final String ANADIR_TUTORIA = "Añadir Tutoria";
	private static final String ANADIR_SESION = "Añadir Sesion";
	private static final String ANADIR_CITA = "Añadir Cita";

	/*
	 * private static final String ANULAR_RESERVA = "Anular Reserva"; private static
	 * final String RESERVA_ANULADA_OK = "Reserva anulada satisfactoriamente";
	 * private static final String SEGURO_ANULAR_RESERVA =
	 * "¿Estás seguro de que quieres anular la reserva?"; private static final
	 * String PUNTOS = "puntos";
	 */

	// Tantos observablesList como los que tiene el controlador, creo que sobran
	// porque esta verisón está optimizada

	private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
	private ObservableList<Alumno> alumnos = FXCollections.observableArrayList();
	private ObservableList<Tutoria> tutorias = FXCollections.observableArrayList();
	private ObservableList<Sesion> sesiones = FXCollections.observableArrayList();
	private ObservableList<Cita> citasAlumno = FXCollections.observableArrayList();
	private ObservableList<Cita> citasSesion = FXCollections.observableArrayList();

	// Tantos escenesarios como controladoresvistas

	private Stage anadirProfesor;
	private ControladorAnadirProfesor cAnadirProfesor;

	private Stage anadirAlumno;
	private ControladorAnadirAlumno cAnadirAlumno;

	private Stage anadirCitaAlumno;
	private ControladorAnadirCitaAlumno cAnadirCitaAlumno;

	private Stage anadirCitaDeLaSesion;
	private ControladorAnadirCitaDeLaSesion cAnadirCitaDeLaSesion;

	private Stage anadirSesion;
	private ControladorAnadirSesion cAnadirSesion;

	private Stage anadirTutoria;
	private ControladorAnadirTutoria cAnadirTutoria;

	private IControlador controladorMVC;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void salir() {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?",
				null)) {
			controladorMVC.terminar();
			System.exit(0);
		}
	}

	@FXML
	private void acercaDe() throws IOException {
		VBox contenido = FXMLLoader.load(getClass().getResource("../vistas/AcercaDe.fxml"));
		Dialogos.mostrarDialogoInformacionPersonalizado("Gestión de Tutorías", contenido);
	}

	// CREAR

	private void crearAnadirProfesor() throws IOException {
		if (anadirProfesor == null) {
			anadirProfesor = new Stage();
			FXMLLoader cargadorAnadirProfesor = new FXMLLoader(getClass().getResource("../vistas/AnadirProfesor.fxml"));
			VBox raizAnadirProfesor = cargadorAnadirProfesor.load();
			cAnadirProfesor = cargadorAnadirProfesor.getController();
			cAnadirProfesor.setControladorMVC(controladorMVC);
			cAnadirProfesor.setProfesores(profesores);
			cAnadirProfesor.inicializa();
			Scene escenaAnadirProfesor = new Scene(raizAnadirProfesor);
			anadirProfesor.setTitle("Añadir Profesor");
			anadirProfesor.initModality(Modality.APPLICATION_MODAL);
			anadirProfesor.setScene(escenaAnadirProfesor);
		} else {
			cAnadirProfesor.inicializa();
		}
	}

	private void crearAnadirAlumno() throws IOException {
		if (anadirAlumno == null) {
			anadirAlumno = new Stage();
			FXMLLoader cargadorAnadirAlumno = new FXMLLoader(getClass().getResource("../vistas/AnadirAlumno.fxml"));
			VBox raizAnadirAlumno = cargadorAnadirAlumno.load();
			cAnadirAlumno = cargadorAnadirAlumno.getController(); // Controlador del fxml AnadirAlumno
			cAnadirAlumno.setControladorMVC(controladorMVC);
			cAnadirAlumno.setAlumnos(alumnos);
			cAnadirAlumno.inicializa();
			Scene escenaAnadirAlumno = new Scene(raizAnadirAlumno);
			anadirProfesor.setTitle("Añadir Alumno");
			anadirProfesor.initModality(Modality.APPLICATION_MODAL);
			anadirProfesor.setScene(escenaAnadirAlumno);
		} else {
			cAnadirProfesor.inicializa();
		}
	}

	private void crearAnadirCitasDeLaSesion() {
		Sesion sesion = tvSesiones.getSelectionModel().getSelectedItem();
		if (anadirCitaDeLaSesion == null) {
			anadirCitaDeLaSesion = new Stage();
			FXMLLoader cargadorAnadirCitaDeLaSesion = new FXMLLoader(
					getClass().getResource("../vistas/AnadirCitaDeLaSesion.fxml"));
			VBox raizAnadirCitaDeLaSesion = cargadorAnadirCitaDeLaSesion.load();
			cAnadirCitaDeLaSesion = cargadorAnadirCitaDeLaSesion.getController();
			cAnadirCitaDeLaSesion.setControladorMVC(controladorMVC);
			cAnadirCitaDeLaSesion.inicializa(alumnos, citasSesion, sesion);
			Scene escenaAnadirCitaDeLaSesion = new Scene(raizAnadirCitaDeLaSesion);
			anadirProfesor.setTitle("Añadir Cita de la Sesión");
			anadirProfesor.initModality(Modality.APPLICATION_MODAL);
			anadirProfesor.setScene(escenaAnadirCitaDeLaSesion);
		} else {
			cAnadirProfesor.inicializa();
		}

	}
	// AÑADIR

	@FXML
	void anadirProfesor(ActionEvent event) throws IOException {
		crearAnadirProfesor();
		anadirProfesor.showAndWait();
	}

	@FXML
	void anadirTutoria(ActionEvent event) throws IOException {
		Profesor profesor = null;
		profesor = tvProfesores.getSelectionModel().getSelectedItem();
		if (profesor != null) {
			String nombre = Dialogos.mostrarDialogoTexto(ANADIR_TUTORIA, "Nombre: ");
			if (nombre != null) {
				try {
					controladorMVC.insertar(new Tutoria(profesor, nombre));
					profesores.remove(profesor);
					Dialogos.mostrarDialogoInformacion(ANADIR_TUTORIA, "Tutoría creada satisfactoriamente");
				} catch (Exception e) {
					Dialogos.mostrarDialogoInformacion(ANADIR_TUTORIA, e.getMessage());
				}
			}
		}
	}

	@FXML
	void anadirSesion(ActionEvent event) throws IOException {
		crearAnadirSesion();
		anadirSesion.showAndWait();
	}

	@FXML
	void anadirAlumno(ActionEvent event) throws IOException {
		crearAnadirAlumno();
		anadirAlumno.showAndWait();
	}

	@FXML
	void anadirCitaAlumno(ActionEvent event) throws IOException {
		crearAnadirCitasAlumno();
		anadirCitaAlumno.showAndWait();
	}

	void anadirCitaDeLaSesion() throws IOException {
		crearAnadirCitasDeLaSesion();
		anadirCitaDeLaSesion.chowAnWait();
	}

	// BORRAR

	@FXML
	void borrarProfesor(ActionEvent event) {
		Profesor profesor = null;
		try {
			profesor = tvProfesores.getSelectionModel().getSelectedItem();
			if (profesor != null && Dialogos.mostrarDialogoConfirmacion(BORRAR_PROFESOR,
					"¿Estás seguro de que quieres borrar el profesor?", null)) {
				controladorMVC.borrar(profesor);
				profesores.remove(profesor);
				Dialogos.mostrarDialogoInformacion(BORRAR_PROFESOR, "Profesor borrado satisfactoriamente");
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(BORRAR_PROFESOR, e.getMessage());
		}
	}

	@FXML
	void borrarAlumno(ActionEvent event) {
		Alumno alumno = null;
		try {
			alumno = tvProfesores.getSelectionModel().getSelectedItem();
			if (alumno != null && Dialogos.mostrarDialogoConfirmacion(BORRAR_ALUMNO,
					"¿Estás seguro de que quieres borrar el alumno?", null)) {
				controladorMVC.borrar(alumno);
				profesores.remove(alumno);
				Dialogos.mostrarDialogoInformacion(BORRAR_ALUMNO, "Alumno borrado satisfactoriamente");
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(BORRAR_ALUMNO, e.getMessage());
		}
	}

	@FXML
	void borrarTutoria(ActionEvent event) {

		Tutoria tutoria = null;
		try {
			tutoria = tvProfesores.getSelectionModel().getSelectedItem();
			if (tutoria != null && Dialogos.mostrarDialogoConfirmacion(BORRAR_TUTORIA,
					"¿Estás seguro de que quieres borrar la tutoría?", null)) {
				controladorMVC.borrar(tutoria);
				profesores.remove(tutoria);
				Dialogos.mostrarDialogoInformacion(BORRAR_TUTORIA, "Tutoría borrada satisfactoriamente");
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(BORRAR_TUTORIA, e.getMessage());
		}
	}

	@FXML
	void borrarCita(ActionEvent event) {
		Cita cita = null;
		try {
			cita = tvProfesores.getSelectionModel().getSelectedItem();
			if (cita != null && Dialogos.mostrarDialogoConfirmacion(BORRAR_CITA,
					"¿Estás seguro de que quieres borrar la cita?", null)) {
				controladorMVC.borrar(cita);
				profesores.remove(cita);
				Dialogos.mostrarDialogoInformacion(BORRAR_CITA, "Cita borrada satisfactoriamente");
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(BORRAR_CITA, e.getMessage());
		}
	}

	@FXML
	void borrarSesion(ActionEvent event) {
		Sesion sesion = null;
		try {
			sesion = tvProfesores.getSelectionModel().getSelectedItem();
			if (sesion != null && Dialogos.mostrarDialogoConfirmacion(BORRAR_SESION,
					"¿Estás seguro de que quieres borrar la sesión?", null)) {
				controladorMVC.borrar(sesion);
				profesores.remove(sesion);
				Dialogos.mostrarDialogoInformacion(BORRAR_SESION, "Sesión borrada satisfactoriamente");
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(BORRAR_SESION, e.getMessage());
		}
	}

	// ACTUALIZAR

	public void actualizaProfesores() {
		profesores.setAll(controladorMVC.getProfesores());
	}

	public void actualizaAlumnos() {
		alumnos.setAll(controladorMVC.getAlumnos());
	}

	public void actualizaTutorias() {
		tutorias.setAll(controladorMVC.getTutorias());
	}

	public void actualizaSesiones() {
		sesiones.setAll(controladorMVC.getSesiones());
	}

	public void actualizaCitas() {
		citas.setAll(controladorMVC.getCitas());
	}

	// MOSTRAR

	public void mostrarReservasProfesor(Profesor profesor) {
		try {
			if (profesor != null) {
				reservasProfesor.setAll(controladorMVC.getReservas(profesor));
			}
		} catch (IllegalArgumentException e) {
			reservasProfesor.setAll(FXCollections.observableArrayList());
		}
	}

}
