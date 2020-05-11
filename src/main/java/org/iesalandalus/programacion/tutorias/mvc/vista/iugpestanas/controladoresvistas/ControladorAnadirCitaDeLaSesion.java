package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.utilidades.Dialogos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ControladorAnadirCitaDeLaSesion implements Initializable {

	private ObservableList<Cita> citasSesion;
	private ObservableList<Alumno> alumnos;
	private Sesion sesion;

	// poner el ListView lcAlumno y no su observable list

	private IControlador controladorMVC;

	

	@FXML
	private ListView<?> lvAnadirCitaDeLaSesionAlumno;


	@FXML
	private TextField tfAnadirCitaDeLaSesionHora;

	@FXML
	private Button btnAnadircitadeLaSesionCancelar;

	@FXML
	private Button btnAnadircitadeLaSesionAnadir;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lvAnadirCitaDeLaSesionAlumno.setCellFactory(l -> new CeldaAlumno());
		tfAnadirCitaDeLaSesionHora.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_HORA, tfAnadirCitaDeLaSesionHora));
	}

	private void anadirCitaDeLaSesion() {
		Cita cita = null;
		try {
			cita = getCita();
			controladorMVC.insertar(cita);
			;
			citas.setAll(controladorMVC.getCitas(sesion)); // citasSesion
			Stage propietario = ((Stage) btnAnadircitadeLaSesionAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Cita", "Cita añadido satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Cita", e.getMessage());
		}

	}

	@FXML
	void cancelar(ActionEvent event) {
		((Stage) btnAnadircitadeLaSesionCancelar.getScene().getWindow()).close();
	}

	public void inicializa(ObservableList<Alumno> alumnos, ObservableList<Cita> citasSesion, Sesion sesion) {
		this.citasSesion = citasSesion;
		this.sesion = sesion;
		this.alumnos = alumnos;
		lvAnadirCitaDeLaSesionAlumno.setItems(alumnos);
		lvAnadirCitaDeLaSesionAlumno.getSelectionModel().clearSelection();
		tfAnadirCitaDeLaSesionHora.setText("");
		compruebaCampoTexto(ER_HORA, tfHora);

	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}

	private Cita getCita() {
		final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("H:mm");
		Alumno alumno = lvAnadirCitaDeLaSesionAlumno.getSelectionModel().getSelectedIdem();
		LocalTime hora = LocalTime.parse(tfAnadirCitaDeLaSesionHora.getText(), formatoHora);
		return new Cita(alumno, sesion, hora);
	}

}
