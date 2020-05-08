package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class ControladorAnadirProfesor implements Initializable { // ACABADA

	private IControlador controladorMVC;
	private ObservableList<Profesor> profesores;
	
	private static final String ER_OBLIGATORIO = ".+";
	private static final String ER_DNI = "\\d{8}[A-Z]";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}"; 
	
	 @FXML
	    private Label lbProfesoresCorreo;

	    @FXML
	    private TextField tfProfesoresCorreo;

	    @FXML
	    private Label lbProfesoresNombre;

	    @FXML
	    private TextField tfProfesorNombre;

	    @FXML
	    private Label lbProfesoresDni;

	    @FXML
	    private TextField tfProfesorDni;

	    @FXML private Button btnProfesoresCancelar;
	    @FXML private Button btnProfesoresAnadir;

	

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfProfesorNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfProfesorNombre));
		tfProfesorDni.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_DNI, tfProfesorDni));
		tfProfesoresCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CORREO, tfProfesoresCorreo));
	}

	public void setProfesores(ObservableList<Profesor> profesores) {
		this.profesores = profesores;
		
	}

	public void inicializa() {
		tfProfesorNombre.setText("");
    	compruebaCampoTexto(ER_OBLIGATORIO, tfProfesorNombre);
    	tfProfesorDni.setText("");
    	compruebaCampoTexto(ER_DNI, tfProfesorDni);
    	tfProfesoresCorreo.setText("");
    	compruebaCampoTexto(ER_CORREO, tfProfesoresCorreo);
    	
	}
	
	
	@FXML
	private void anadir() {
		Profesor profesor = null;
		try {
			profesor = getProfesor(); // lee los datos del formulario
			controladorMVC.insertar(profesor);
			profesores.setAll(controladorMVC.getProfesores()); // inserta este profesor y le pide la lista de profesores
			Stage propietario = ((Stage) btnProfesoresAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Profesor", "Profesor añadido satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Profesor", e.getMessage());
		}	
	}
	
	private Profesor getProfesor() { // Lee los datos y los devuelve
		String nombre = tfProfesorNombre.getText();
		String dni = tfProfesorDni.getText();
		String correo = tfProfesoresCorreo.getText().isEmpty() ? null : tfProfesoresCorreo.getText();
		return new Profesor(nombre, dni, correo);
	}
	
	@FXML
	private void cancelar(ActionEvent event) {
		((Stage) btnProfesoresCancelar.getScene().getWindow()).close();
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
}
