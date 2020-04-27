package org.iesalandalus.programacion.tutorias;

import org.iesalandalus.programacion.tutorias.mvc.controlador.Controlador;
import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.IModelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.Modelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.FactorialFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.vista.FactoriaVista;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;
import org.iesalandalus.programacion.tutorias.mvc.vista.texto.VistaTexto;

public class MainApp {
	
	/*
	 * Cambiar la vista TEXTO por la vista gráfica IUGPESTANAS
	 * De esta forma iniciamos la vista gráfica de pestañas
	 * */
	 

	public static void main(String[] args) {
		IModelo modelo = new Modelo(FactorialFuenteDatos.FICHEROS.crear());
		IVista vista = FactoriaVista.IUGPESTANAS.crear();// Para Vista texto: FactoriaVista.TEXTO.crear();
		IControlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
	}
	
	// EN EL EJEMPLO RESERVAS AULAS HAY MÁS COSAS

}
