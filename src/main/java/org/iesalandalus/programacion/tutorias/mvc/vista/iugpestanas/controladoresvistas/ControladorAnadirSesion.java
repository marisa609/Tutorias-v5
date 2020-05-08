package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControladorAnadirSesion implements Initializable { // ACABADA
	
	private static final String ER_HORA = "\\d{1,2}:\\d{2}";
	private static final String ER_ENTERO = "\\d{2}";
	private static final String FORMATO_FECHA = "dd/MM/yyyy";

	private IControlador controladorMVC;
	private Tutoria tutoria;
	private ObservableList<Sesion> sesiones;
	
	 @FXML
	    private Label lbSesionesFecha;

	    @FXML
	    private TextField tfSesionesFecha;

	    @FXML
	    private Label lbSesionesInicio;

	    @FXML
	    private TextField tfSesionesInicio;

	    @FXML
	    private Label lbSesionesFin;

	    @FXML
	    private TextField tfSesionesFin;

	    @FXML
	    private Label lbSesionesDuracion;

	    @FXML
	    private TextField tfSesionesDuracion;

	    @FXML
	    private Button btnSesionesCancelar;

	    @FXML
	    private Button btnSesionesAnadir;
	    

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	public void setTutoria(Tutoria tutoria) {
		this.tutoria = tutoria;
		sesiones = FXCollections.observableArrayList(controladorMVC.getSesiones(tutoria)); // ¿?¿?¿?¿?¿'
	}
	
	public void setSesiones(ObservableList<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	
	public void anadirSesion() {
		Sesion sesion = null;
		try {
			sesion = getSesion(); 
			controladorMVC.insertar(sesion);
			sesiones.setAll(controladorMVC.getSesiones(tutoria)); // OJO, tutoría
			Stage propietario = ((Stage) btnSesionesAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Sesión", "Sesión añadida satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Sesión", e.getMessage());
		}	
	}
	

	private Sesion getSesion() {
		final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
		final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate fecha = LocalDate.parse(tfSesionesFecha.getText(), formatoFecha);
		LocalTime horaInicio = LocalTime.parse(tfSesionesInicio.getText(), formatoHora);
		LocalTime horaFin = LocalTime.parse(tfSesionesFin.getText(), formatoHora);
		int minutosDuracion = Integer.parseInt(tfSesionesDuracion.getText());
		return new Sesion(tutoria, fecha, horaInicio, horaFin, minutosDuracion);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfSesionesFecha.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(FORMATO_FECHA, tfSesionesFecha));
		tfSesionesInicio.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_HORA, tfSesionesFin));
		tfSesionesFin.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_HORA, tfSesionesFin));
		tfSesionesDuracion.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_ENTERO, tfSesionesDuracion));
	}

	public void inicializa() {
		tfSesionesFecha.setText("");
    	compruebaCampoTexto(FORMATO_FECHA, tfSesionesFecha);
    	tfSesionesInicio.setText("");
    	compruebaCampoTexto(ER_HORA, tfSesionesFin);
    	tfSesionesFin.setText("");
    	compruebaCampoTexto(ER_HORA, tfSesionesFin);
    	tfSesionesDuracion.setText("");
    	compruebaCampoTexto(ER_ENTERO, tfSesionesDuracion);
		
	}
	
	@FXML
	private void cancelar() {
		((Stage) btnSesionesCancelar.getScene().getWindow()).close();
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

