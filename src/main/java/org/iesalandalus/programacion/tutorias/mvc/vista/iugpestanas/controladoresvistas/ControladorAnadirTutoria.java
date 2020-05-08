package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

public class ControladorAnadirTutoria implements Initializable {

	
	private IControlador controladorMVC;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void setProfesor(Profesor profesor) {
		// TODO Auto-generated method stub
		
	}

	public void setTutorias(ObservableList<Tutoria> tutorias) {
		// TODO Auto-generated method stub
		
	}

	public void inicializa() {
		// TODO Auto-generated method stub
		
	}
}
