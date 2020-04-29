package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

public class ControladorAnadirProfesor implements Initializable {

	private IControlador controladorMVC;
	private ObservableList<Profesor> profesores;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void setProfesores(ObservableList<Profesor> profesores) {
		this.profesores = profesores;
		
	}

	public void inicializa() {
		// TODO Auto-generated method stub
		
	}
	



}
