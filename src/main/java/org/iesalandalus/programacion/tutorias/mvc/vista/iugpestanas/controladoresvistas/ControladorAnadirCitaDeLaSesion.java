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

public class ControladorAnadirCitaDeLaSesion implements Initializable {

	private ObservableList<Cita> citasSesion;
	private ObservableList<Alumno> alumnos;
	private Sesion sesion;
	
	//poner el ListView lcAlumno y no su observable list
	
	private IControlador controladorMVC;
	

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	private void anadirCitaDeLaSesion() {
		Cita cita = null;
		try {
			cita = getCita();
			controladorMVC.insertar(cita);;
			citas.setAll(controladorMVC.getCitas(sesion)); //citasSesion
			Stage propietario = ((Stage) btAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Cita", "Cita añadido satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Cita", e.getMessage());
		}	
			
		}
	
	@FXML
    void cancelar(ActionEvent event) {
		((Stage) btCancelar.getScene().getWindow()).close();
    }

	

	public void inicializa(ObservableList<Alumno> alumnos, ObservableList<Cita> citasSesion, Sesion sesion) {
		this.citasSesion = citasSesion;
		this.sesion = sesion;
		//this.citas = citas;
		lvAlumno.setItems(alumnos);
		lvAlumno.getSelectionModel().clearSelection();
		tfHora.setText("");
		compruebaCampoTexto(ER_HORA, tfHora);
		
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
	
	private Cita getCita() {
		final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("H:mm");
		Alumno alumno = lvAlumno.getSelectionModel().getSelectedIdem();
		LocalTime hora = LocalTime.parse(tfHora.getText(), formatoHora)
;
		return new Cita(alumno, sesion, hora);}
	
}
