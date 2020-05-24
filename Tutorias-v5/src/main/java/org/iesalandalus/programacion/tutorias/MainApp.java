package org.iesalandalus.programacion.tutorias;

import org.iesalandalus.programacion.tutorias.mvc.controlador.Controlador;
import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.IModelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.Modelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.FactorialFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.vista.FactoriaVista;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;
import org.iesalandalus.programacion.tutorias.mvc.vista.texto.VistaTexto;

public class MainApp {

	/*
	 * Cambiar la vista TEXTO por la vista gráfica IUGPESTANAS De esta forma
	 * iniciamos la vista gráfica de pestañas
	 * 
	 * Sin embargo, vamos a añadir el método procesador de argumentos para poder
	 * elegir la vista que queramos con -vpestanas o -vtexto)
	 * 
	 * Había que hacer algo para la tarea desde la consola de Windows (NO
	 * OLVIDAR*****)
	 * 
	 * ACABADO
	 */

	public static void main(String[] args) {
		IModelo modelo = new Modelo(procesarArgumentosFuenteDatos(args)); // FactorialFuenteDatos.FICHEROS.crear() (Oara
																			// acceder a ficheros)
		IVista vista = procesarArgumentos(args);// FactoriaVista.IUGPESTANAS.crear(); o Para Vista texto:
												// FactoriaVista.TEXTO.crear();
		IControlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
	}

	private static IVista procesarArgumentos(String[] args) {
		IVista vista = FactoriaVista.IUGPESTANAS.crear();
		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-vpestanas")) {
				vista = FactoriaVista.IUGPESTANAS.crear();
			} else if (argumento.equalsIgnoreCase("-vtexto")) {
				vista = FactoriaVista.TEXTO.crear();
			}
		}
		return vista;
	}

	private static IFuenteDatos procesarArgumentosFuenteDatos(String[] args) {
		IFuenteDatos fuenteDatos = FactorialFuenteDatos.MONGODB.crear();
		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-fdficheros")) {
				fuenteDatos = FactorialFuenteDatos.FICHEROS.crear();
			} else if (argumento.equalsIgnoreCase("-fdmongodb")) {
				fuenteDatos = FactorialFuenteDatos.MONGODB.crear();
			}

		}
		return fuenteDatos;
	}

}
