package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.net.URL;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.utilidades.Dialogos;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControladorAnadirAlumno implements Initializable {  // ACABADA
	
	// CUANDO BORRO UN ALUMNO NO SE ACTUALIZA LA TABLA DE ALUMNOS
	
	private ObservableList<Alumno> alumnos;
	private static final String ER_OBLIGATORIO = ".+";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";

	@FXML
	private Label lbAlumnosNombre;

	@FXML
	private TextField tfAlumnosNombre;

	@FXML
	private Label lbAlumnosCorreo;

	@FXML
	private TextField tfAlumnosCorreo;

	@FXML
	private Button btnAlumnosCancelar;

	@FXML
	private Button btnAlumnosAnadir;

	private IControlador controladorMVC;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfAlumnosNombre.textProperty()
				.addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfAlumnosNombre));
		tfAlumnosCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CORREO, tfAlumnosCorreo));
	}

	public void setAlumnos(ObservableList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public void inicializa() {
		tfAlumnosNombre.setText("");
		compruebaCampoTexto(ER_OBLIGATORIO, tfAlumnosNombre);
		tfAlumnosCorreo.setText("");
		compruebaCampoTexto(ER_CORREO, tfAlumnosCorreo);
	}

	@FXML
	private void cancelar() {
		((Stage) btnAlumnosCancelar.getScene().getWindow()).close();
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
	private void anadirAlumno() {
		Alumno alumno = null;
		try {
			alumno = getAlumno(); // lee los datos del formulario
			controladorMVC.insertar(alumno);
			alumnos.setAll(controladorMVC.getAlumnos()); // inserta este profesor y le pide la lista de profesores
			Stage propietario = ((Stage) btnAlumnosAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Alumno", "Alumno añadido satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Alumno", e.getMessage());
		}
	}

	private Alumno getAlumno() { // Lee los datos y los devuelve
		String nombre = tfAlumnosNombre.getText();
		String correo = tfAlumnosCorreo.getText().isEmpty() ? null : tfAlumnosCorreo.getText();
		return new Alumno(nombre, correo);
	}

}
