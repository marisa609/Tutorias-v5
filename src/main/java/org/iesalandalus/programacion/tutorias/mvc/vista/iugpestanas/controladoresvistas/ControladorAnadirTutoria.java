package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;


import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirTutoria implements Initializable {  // ACABADA

	private ObservableList<Tutoria> tutorias;
	private ObservableList<Profesor> profesores;
	private Profesor profesor;
	
	
	private static final String ER_OBLIGATORIO = ".+";
	private IControlador controladorMVC;

	@FXML
	private TextField tfTutoriaNombre;

	@FXML
	private Button btnTutoriaCancelar;

	@FXML
	private Button btnTutoriaAnadir;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfTutoriaNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfTutoriaNombre));

	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
		tutorias = FXCollections.observableArrayList(controladorMVC.getTutorias(profesor));

	}

	public void setTutorias(ObservableList<Tutoria> tutorias) {
		this.tutorias = tutorias;

	}

	public void inicializa(ObservableList<Profesor> profesores) { // ojo con el observable
		tfTutoriaNombre.setText("");
    	compruebaCampoTexto(ER_OBLIGATORIO, tfTutoriaNombre);
    	this.profesores = profesores;

	}
	
	private void compruebaCampoTexto(String er, TextField campoTexto) {	
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		}
		else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}

	@FXML
	void anadirTutoria(ActionEvent event) {
		Tutoria tutoria = null;
		try {
			tutoria = getTutoria(); // lee los datos del formulario
			controladorMVC.insertar(tutoria);
			tutorias.setAll(controladorMVC.getTutorias(profesor)); 
			Stage propietario = ((Stage) btnTutoriaAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Tutoría", "Tutoría añadida satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Tutoría", e.getMessage());
		}	
	}

	private Tutoria getTutoria() {
		String nombre = tfTutoriaNombre.getText();
		return new Tutoria(profesor, nombre);
	}

	@FXML
	void cancelar(ActionEvent event) {
		((Stage) btnTutoriaCancelar.getScene().getWindow()).close();
	}
}
