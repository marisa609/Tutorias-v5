package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirCitaAlumno implements Initializable { // ACABADA

	private IControlador controladorMVC;

	ObservableList<Cita> citasAlumno;
	ObservableList<Profesor> profesores;
	private ObservableList<Cita> citasSesion = FXCollections.observableArrayList();
	private ObservableList<Tutoria> tutorias = FXCollections.observableArrayList();
	private ObservableList<Sesion> sesiones = FXCollections.observableArrayList();

	Alumno alumno;

	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String ER_HORA = "\\d{1,2}:\\d{2}";

	@FXML
	private ListView<Profesor> lvAnadircitaAlumnoProfesor;

	@FXML
	private ListView<Tutoria> lvAnadircitaAlumnoTutoria;

	@FXML
	private ListView<Sesion> lvAnadircitaAlumnoSesion;

	@FXML
	private TextField tfAnadirCitaAlumnoHora;

	@FXML
	private Button btnAnadirCitaAlumnoCitasCancelar;

	@FXML
	private Button btnAnadirCitaAlumnoAnadir;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) { // OJO
		lvAnadircitaAlumnoProfesor.setCellFactory(l -> new CeldaProfesor());
		lvAnadircitaAlumnoProfesor.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> mostrarTutoriasDelProfesor(nv)); // OJO

		lvAnadircitaAlumnoTutoria.setCellFactory(l -> new CeldaTutoria());
		lvAnadircitaAlumnoTutoria.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> mostrarSesionesDeLaTutoria(nv));
		lvAnadircitaAlumnoTutoria.setItems(tutorias);

		lvAnadircitaAlumnoSesion.setCellFactory(l -> new CeldaSesion());
		lvAnadircitaAlumnoSesion.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> mostrarCitasDeLaSesion(nv));

		tfAnadirCitaAlumnoHora.textProperty()
				.addListener((ob, ov, nv) -> compruebaCampoTexto(ER_HORA, tfAnadirCitaAlumnoHora));

	}
	
	private class CeldaTutoria extends ListCell<Tutoria> {
		@Override
		public void updateItem(Tutoria tutoria, boolean vacio) {
			super.updateItem(tutoria, vacio);
			if (vacio) {
				setText("");
			} else {
				setText(tutoria.getNombre());
			}
		}
	}

	private class CeldaSesion extends ListCell<Sesion> {
		@Override
		public void updateItem(Sesion sesion, boolean vacia) {
			super.updateItem(sesion, vacia);
			if (vacia) {
				setText("");
			} else {
				setText(sesion.getFecha().format(FORMATO_FECHA)); // ¿Solo mostramos la fecha?
			}
		}
	}

	private class CeldaProfesor extends ListCell<Profesor> {
		@Override
		public void updateItem(Profesor profesor, boolean vacio) {
			super.updateItem(profesor, vacio);
			if (vacio) {
				setText("");
			} else {
				setText(profesor.getNombre());
			}
		}
	}

	public void inicializa(ObservableList<Profesor> profesores, ObservableList<Cita> citasAlumno, Alumno alumno) {
		this.profesores = profesores;
		this.citasAlumno = citasAlumno;
		this.alumno = alumno;
		lvAnadircitaAlumnoProfesor.setItems(profesores);
		lvAnadircitaAlumnoProfesor.getSelectionModel().clearSelection();
		tfAnadirCitaAlumnoHora.setText("");
		compruebaCampoTexto(ER_HORA, tfAnadirCitaAlumnoHora);
	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}

	@FXML
	void anadirCitaAlumno(ActionEvent event) {
		Cita cita = null;
		try {
			cita = getCita();
			controladorMVC.insertar(cita);
			;
			citasAlumno.setAll(controladorMVC.getCitas(alumno));
			Stage propietario = ((Stage) btnAnadirCitaAlumnoAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Cita", "Cita añadido satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Cita", e.getMessage());
		}

	}

	private Cita getCita() {
		final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("H:mm");
		Profesor profesor = lvAnadircitaAlumnoProfesor.getSelectionModel().getSelectedItem();
		Tutoria tutoria = lvAnadircitaAlumnoTutoria.getSelectionModel().getSelectedItem();
		Sesion sesion = lvAnadircitaAlumnoSesion.getSelectionModel().getSelectedItem();
		LocalTime hora = LocalTime.parse(tfAnadirCitaAlumnoHora.getText(), formatoHora);
		return new Cita(alumno, sesion, hora);
	}

	@FXML
	void cancelar(ActionEvent event) {
		((Stage) btnAnadirCitaAlumnoCitasCancelar.getScene().getWindow()).close();
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
