package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

public class ControladorAnadirCitaAlumno implements Initializable {

	private IControlador controladorMVC;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void inicializa(ObservableList<Alumno> alumnos, ObservableList<Cita> citasAlumno, Alumno alumno) {
		// TODO Auto-generated method stub
		
	}

}
