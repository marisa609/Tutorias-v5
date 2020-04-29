package org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;

import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.controladoresvistas.ControladorVentanaPrincipal;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.utilidades.Dialogos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/*IMplementa IVista y extiende de Application (debe crear el método start) y el launch para lanzar
 * la ventana
 */

public class VistaIUGPestanas extends Application implements IVista {

	private static IControlador controladorMVC = null; // Se refiere a Controlador

	@Override
	public void setControlador(IControlador controlador) {
		controladorMVC = controlador;
	}

	@Override
	public void comenzar() {
		launch(this.getClass());
	}

	@Override
	public void terminar() {
		controladorMVC.terminar();
	}

	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			FXMLLoader cargadorVentanaPrincipal = new FXMLLoader(
					getClass().getResource("vistas/VentanaPrincipal.fxml"));
			VBox raiz = cargadorVentanaPrincipal.load();
			ControladorVentanaPrincipal cVentanaPrincipal = cargadorVentanaPrincipal.getController();

			cVentanaPrincipal.setControladorMVC(controladorMVC);
			cVentanaPrincipal.actualizaProfesores();
			cVentanaPrincipal.actualizaTutorias();
			cVentanaPrincipal.actualizaAlumnos();
			cVentanaPrincipal.actualizaCitas();
			cVentanaPrincipal.actualizaSesiones();

			Scene escena = new Scene(raiz);
			escenarioPrincipal.setOnCloseRequest(e -> confirmarSalida(escenarioPrincipal, e));
			escenarioPrincipal.setTitle("Gestion de Tutorías");
			escenarioPrincipal.setScene(escena);
			escenarioPrincipal.setResizable(false);
			escenarioPrincipal.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void confirmarSalida(Stage escenarioPrincipal, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?",
				escenarioPrincipal)) {
			controladorMVC.terminar();
			escenarioPrincipal.close();
		} else {
			e.consume();
		}
	}

}
