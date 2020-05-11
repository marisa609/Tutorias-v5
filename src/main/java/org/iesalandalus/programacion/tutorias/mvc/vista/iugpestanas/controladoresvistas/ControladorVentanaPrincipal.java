package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.utilidades.Dialogos;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;

import javafx.beans.property.SimpleStringProperty;
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

import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.utilidades.Dialogos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/*Implementa Initializable y añade el método initialize
 * 
 * Importar el IControlador y asignarlo
 * Crear ObservableList
 * En FactoriaVista añadir el IUGPESTANAS
 * 
 * 
 */

// CREARCITA EN PESTAÑA ALUMNOS AL MENUITEM OOOOJOO

// EN PRINCIPIO ESTÁ ACABADA----------------------------------------------------------

public class ControladorVentanaPrincipal implements Initializable {

	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:hh");
	private static final String BORRAR_PROFESOR = "Borrar Profesor";
	private static final String BORRAR_ALUMNO = "Borrar Alumno";
	private static final String BORRAR_TUTORIA = "Borrar Tutoria";
	private static final String BORRAR_SESION = "Borrar Sesion";
	private static final String BORRAR_CITA = "Borrar Cita";

	@FXML
	private TableView<Alumno> tvAlumnos;
	@FXML
	private TableColumn<Alumno, String> tcNombreAlumnos;
	@FXML
	private TableColumn<Alumno, String> tcCorreoAlumnos;
	@FXML
	private TableColumn<Alumno, String> tcExpedienteAlumnos;

	@FXML
	private TableView<Cita> tvAlumnosCitas;
	@FXML
	private TableColumn<Cita, String> tcAlumnosCitasHora;
	@FXML
	private TableColumn<Cita, String> tcAlumnosCitasFecha; // En el del profesor sale dia y no la fehca
	@FXML
	private TableColumn<Cita, String> tcAlumnosCitasTutoria;
	@FXML
	private TableColumn<Cita, String> tcAlumnosCitasProfesor;

	@FXML
	private TableView<Profesor> tvProfesores;
	@FXML
	private TableColumn<Profesor, String> tcProfesoresNombre;
	@FXML
	private TableColumn<Profesor, String> tcProfesoresDni;
	@FXML
	private TableColumn<Profesor, String> tcProfesoresCorreo;

	@FXML
	private TableView<Tutoria> tvProfesoresTutorias;
	@FXML
	private TableColumn<Tutoria, String> tcProfesoresTutoriasDelProfesorNombre;

	@FXML
	private TableView<Sesion> tvProfesoresSesiones;
	@FXML
	private TableColumn<Sesion, String> tcProfesoresSesionesFecha;
	@FXML
	private TableColumn<Sesion, String> tcProfesoresSesionesInicio;
	@FXML
	private TableColumn<Sesion, String> tcProfesoresSesionesFin;
	@FXML
	private TableColumn<Sesion, String> tcProfesoresSesionesDuracion;

	@FXML
	private TableView<Cita> tvCitasDeLaSesion;
	@FXML
	private TableColumn<Cita, String> tcCitasDeLaSesionHora;
	@FXML
	private TableColumn<Cita, String> tcCitasDeLaSesionAlumno;

	/*
	 * private static final String ANULAR_RESERVA = "Anular Reserva"; private static
	 * final String RESERVA_ANULADA_OK = "Reserva anulada satisfactoriamente";
	 * private static final String SEGURO_ANULAR_RESERVA =
	 * "¿Estás seguro de que quieres anular la reserva?"; private static final
	 * String PUNTOS = "puntos";
	 */

	// Tantos observablesList como los que tiene el controlador,

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

		tcProfesoresDni.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getDni()));
		tcProfesoresNombre.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getNombre()));
		tcProfesoresCorreo.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getCorreo()));
		tvProfesores.getSelectionModel().selectedItemProperty()
				.addListener((ob, ov, nv) -> mostrarTutoriasDelProfesor(nv));
		tvProfesores.setItems(profesores);

		tcExpedienteAlumnos.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getExpediente()));
		tcNombreAlumnos.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getNombre()));
		tcCorreoAlumnos.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getCorreo()));
		tvAlumnos.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> mostrarCitasDelAlumno(nv));
		tvAlumnos.setItems(alumnos);

		tcProfesoresTutoriasDelProfesorNombre
				.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getNombre()));
		tvProfesoresTutorias.getSelectionModel().selectedItemProperty()
				.addListener((ob, ov, nv) -> mostrarSesionesDeLaTutoria(nv));
		tvProfesoresTutorias.setItems(tutorias);

		tcProfesoresSesionesFecha.setCellValueFactory(
				sesion -> new SimpleStringProperty(FORMATO_FECHA.format(sesion.getValue().getFecha())));
		tcProfesoresSesionesInicio.setCellValueFactory(
				sesion -> new SimpleStringProperty(FORMATO_HORA.format(sesion.getValue().getHoraInicio())));
		tcProfesoresSesionesFin.setCellValueFactory(
				sesion -> new SimpleStringProperty(FORMATO_HORA.format(sesion.getValue().getHoraFin())));
		tcProfesoresSesionesDuracion.setCellValueFactory(
				sesion -> new SimpleStringProperty(String.valueOf(sesion.getValue().getMinutosDuracion())));
		tvProfesoresSesiones.getSelectionModel().selectedItemProperty()
				.addListener((ob, ov, nv) -> mostrarCitasDeLaSesion(nv));
		tvProfesoresSesiones.setItems(sesiones);

		// OJO
		tcAlumnosCitasFecha.setCellValueFactory(
				cita -> new SimpleStringProperty(FORMATO_FECHA.format(cita.getValue().getSesion().getFecha())));
		tcAlumnosCitasHora
				.setCellValueFactory(cita -> new SimpleStringProperty(FORMATO_HORA.format(cita.getValue().getHora())));
		tcAlumnosCitasTutoria.setCellValueFactory(cita -> new SimpleStringProperty(cita.getValue().getTutoria()));
		tcAlumnosCitasProfesor
				.setCellValueFactory(cita -> new SimpleStringProperty(cita.getValue().getProfesor().getNombre()));
		tvAlumnosCitas.setItems(citasAlumno);

		tcCitasDeLaSesionAlumno
				.setCellValueFactory(cita -> new SimpleStringProperty(cita.getValue().getAlumno().getNombre()));
		tcCitasDeLaSesionHora
				.setCellValueFactory(cita -> new SimpleStringProperty(FORMATO_HORA.format(cita.getValue().getHora())));
		tvCitasDeLaSesion.setItems(citasSesion);

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

	// CREAR ==== En el ejemplo reservas aulas hay otra forma de hacerlo

	private void crearAnadirProfesor() throws IOException {
		if (anadirProfesor == null) {
			anadirProfesor = new Stage();
			FXMLLoader cargadorAnadirProfesor = new FXMLLoader(getClass().getResource("../vistas/AnadirProfesor.fxml"));
			VBox raizAnadirProfesor = cargadorAnadirProfesor.load();
			cAnadirProfesor = cargadorAnadirProfesor.getController();
			cAnadirProfesor.setControladorMVC(controladorMVC);
			cAnadirProfesor.setProfesores(profesores); // Pasa el observableList
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
			anadirAlumno.setTitle("Añadir Alumno");
			anadirAlumno.initModality(Modality.APPLICATION_MODAL);
			anadirAlumno.setScene(escenaAnadirAlumno);
		} else {
			cAnadirAlumno.inicializa();
		}
	}

	private void crearAnadirCitasDeLaSesion() throws IOException {
		Sesion sesion = tvProfesoresSesiones.getSelectionModel().getSelectedItem();
		if (anadirCitaDeLaSesion == null) {
			anadirCitaDeLaSesion = new Stage();
			FXMLLoader cargadorAnadirCitaDeLaSesion = new FXMLLoader(
					getClass().getResource("../vistas/AnadirCitaDeLaSesion.fxml"));
			VBox raizAnadirCitaDeLaSesion = cargadorAnadirCitaDeLaSesion.load();
			cAnadirCitaDeLaSesion = cargadorAnadirCitaDeLaSesion.getController();
			cAnadirCitaDeLaSesion.setControladorMVC(controladorMVC);
			cAnadirCitaDeLaSesion.inicializa(alumnos, citasSesion, sesion);
			Scene escenaAnadirCitaDeLaSesion = new Scene(raizAnadirCitaDeLaSesion);
			anadirCitaDeLaSesion.setTitle("Añadir Cita de la Sesión");
			anadirCitaDeLaSesion.initModality(Modality.APPLICATION_MODAL);
			anadirCitaDeLaSesion.setScene(escenaAnadirCitaDeLaSesion);
		} else {
			cAnadirCitaDeLaSesion.inicializa(alumnos, citasSesion, sesion);
		}

	}

	private void crearAnadirSesion() throws IOException {
		Tutoria tutoria = tvProfesoresTutorias.getSelectionModel().getSelectedItem();
		if (anadirSesion == null) {
			anadirSesion = new Stage();
			FXMLLoader cargadorAnadirSesion = new FXMLLoader(getClass().getResource("../vistas/AnadirSesion.fxml"));
			VBox raizAnadirSesion = cargadorAnadirSesion.load();
			cAnadirSesion = cargadorAnadirSesion.getController();
			cAnadirSesion.setControladorMVC(controladorMVC);
			cAnadirSesion.setTutoria(tutoria);
			cAnadirSesion.setSesiones(sesiones);
			cAnadirSesion.inicializa();
			Scene escenaAnadirSesion = new Scene(raizAnadirSesion);
			anadirSesion.setTitle("Añadir Sesión");
			anadirSesion.initModality(Modality.APPLICATION_MODAL);
			anadirSesion.setScene(escenaAnadirSesion);
		} else {
			cAnadirSesion.inicializa();
		}
	}

	private void crearAnadirCitaAlumno() throws IOException {
		Alumno alumno = tvAlumnos.getSelectionModel().getSelectedItem(); // cambiar el tv
		if (anadirCitaAlumno == null) {
			anadirCitaAlumno = new Stage();
			FXMLLoader cargadorAnadirCitaAlumno = new FXMLLoader(
					getClass().getResource("../vistas/AnadirCitaAlumno.fxml"));
			VBox raizAnadirCitaAlumno = cargadorAnadirCitaAlumno.load();
			cAnadirCitaAlumno = cargadorAnadirCitaAlumno.getController();
			cAnadirCitaAlumno.setControladorMVC(controladorMVC);
			cAnadirCitaAlumno.inicializa(profesores, citasAlumno, alumno);
			Scene escenaAnadirCitaAlumno = new Scene(raizAnadirCitaAlumno);
			anadirCitaAlumno.setTitle("Añadir Cita del Alumno");
			anadirCitaAlumno.initModality(Modality.APPLICATION_MODAL);
			anadirCitaAlumno.setScene(escenaAnadirCitaAlumno);
		} else {
			cAnadirCitaAlumno.inicializa(profesores, citasAlumno, alumno);
		}

	}

	private void crearAnadirTutoria() throws IOException {
		Profesor profesor = tvProfesores.getSelectionModel().getSelectedItem();
		if (anadirTutoria == null) {
			anadirTutoria = new Stage();
			FXMLLoader cargadorAnadirTutoria = new FXMLLoader(getClass().getResource("../vistas/AnadirTutoria.fxml"));
			VBox raizAnadirTutoria = cargadorAnadirTutoria.load();
			cAnadirTutoria = cargadorAnadirTutoria.getController();
			cAnadirTutoria.setControladorMVC(controladorMVC);
			cAnadirTutoria.setProfesor(profesor);
			cAnadirTutoria.setTutorias(tutorias);
			cAnadirTutoria.inicializa(profesores);
			Scene escenaAnadirTutoria = new Scene(raizAnadirTutoria);
			anadirTutoria.setTitle("Añadir tutoría");
			anadirTutoria.initModality(Modality.APPLICATION_MODAL);
			anadirTutoria.setScene(escenaAnadirTutoria);

		} else {
			cAnadirTutoria.inicializa(profesores);
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
		crearAnadirTutoria();
		anadirTutoria.showAndWait();
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
		crearAnadirCitaAlumno();
		anadirCitaAlumno.showAndWait();
	}

	@FXML
	void anadirCitaDeLaSesion() throws IOException {
		crearAnadirCitasDeLaSesion();
		anadirCitaDeLaSesion.showAndWait();
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
			alumno = tvAlumnos.getSelectionModel().getSelectedItem();
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
			tutoria = tvProfesoresTutorias.getSelectionModel().getSelectedItem();
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
	void borrarCitaAlumno(ActionEvent event) {
		Cita cita = null;
		try {
			cita = tvAlumnosCitas.getSelectionModel().getSelectedItem();
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
			sesion = tvProfesoresSesiones.getSelectionModel().getSelectedItem();
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

	@FXML
	void borrarCitaDeLaSesion(ActionEvent event) {
		Cita cita = null;
		try {
			cita = tvCitasDeLaSesion.getSelectionModel().getSelectedItem();
			if (cita != null && Dialogos.mostrarDialogoConfirmacion(BORRAR_CITA,
					"¿Estás seguro de que quieres borrar la sesión?", null)) {
				controladorMVC.borrar(cita);
				citasSesion.remove(cita);
				Dialogos.mostrarDialogoInformacion(BORRAR_CITA, "Cita borrada satisfactoriamente");
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError(BORRAR_CITA, e.getMessage());
		}
	}

	// ACTUALIZAR ¿Es necesario? Revisar, creo que no se llama desde nigún sitio

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
		citasAlumno.setAll(controladorMVC.getCitas());
	}

	public void actualizaCitasSesion() {
		citasSesion.setAll(controladorMVC.getCitas());
	}

	// MOSTRAR =============== OJO

	public void mostrarCitasDelAlumno(Alumno alumno) {
		try {
			if (alumno != null) {
				citasAlumno.setAll(controladorMVC.getCitas(alumno));
			}
		} catch (IllegalArgumentException e) {
			citasAlumno.setAll(FXCollections.observableArrayList());
		}
	}

	public void mostrarTutoriasDelProfesor(Profesor profesor) {
		try {
			if (profesor != null) {
				tutorias.setAll(controladorMVC.getTutorias(profesor));
			}
		} catch (IllegalArgumentException e) {
			tutorias.setAll(FXCollections.observableArrayList());
		}
	}

	public void mostrarSesionesDeLaTutoria(Tutoria tutoria) {
		try {
			if (tutoria != null) {
				sesiones.setAll(controladorMVC.getSesiones(tutoria));
			}
		} catch (IllegalArgumentException e) {
			sesiones.setAll(FXCollections.observableArrayList());
		}
	}

	public void mostrarCitasDeLaSesion(Sesion sesion) {
		try {
			if (sesion != null) {
				citasSesion.setAll(controladorMVC.getCitas(sesion));
			}
		} catch (IllegalArgumentException e) {
			citasSesion.setAll(FXCollections.observableArrayList());
		}
	}

}
