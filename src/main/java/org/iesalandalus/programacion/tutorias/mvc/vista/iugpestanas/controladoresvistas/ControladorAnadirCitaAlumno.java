package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControladorAnadirCitaAlumno implements Initializable {

	private IControlador controladorMVC;

	ObservableList<Cita> citasAlumno;
	ObservableList<Profesor> profesores;
	Alumno alumno;

	@FXML
	private ListView<?> lvAnadircitaAlumnoProfesor;

	@FXML
	private ListView<?> lvAnadircitaAlumnoTutoria;

	@FXML
	private ListView<?> lvAnadircitaAlumnoSesion;

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
	public void initialize(URL location, ResourceBundle resources) {
		lvAnadircitaAlumnoProfesor.setCellFactory(l -> new CeldaProfesor());
		lvAnadircitaAlumnoProfesor.get.SetectionModel().selectedItemProperty().addListener((ob, ov, nv) -> mostrar;
		lvAnadircitaAlumnoTutoria.setCellFactory(l -> new CeldaTutoria());
		lvAnadircitaAlumnoTutoria
		lvAnadircitaAlumnoTutoria
		
		lvAnadircitaAlumnoSesion.setCellFactory(l -> new CeldaSesion());
		lvAnadircitaAlumnoSesion

	}

	public void inicializa(ObservableList<Profesor> profesores, ObservableList<Cita> citasAlumno, Alumno alumno) {
		this.profesores = profesores;
		this.citasAlumno = citasAlumno;
		this.alumno = alumno;

	}

	@FXML
	void anadirCitaAlumno(ActionEvent event) {

	}

	@FXML
	void cancelar(ActionEvent event) {

	}

}
