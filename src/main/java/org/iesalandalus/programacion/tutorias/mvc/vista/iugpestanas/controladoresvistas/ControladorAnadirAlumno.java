package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

public class ControladorAnadirAlumno implements Initializable {

	private ObservableList<Alumno> alumnos;

	private IControlador controladorMVC;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setAlumnos(ObservableList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public void inicializa() {
		// TODO Auto-generated method stub

	}

}
