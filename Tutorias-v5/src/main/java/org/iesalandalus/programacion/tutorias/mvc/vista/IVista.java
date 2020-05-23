package org.iesalandalus.programacion.tutorias.mvc.vista;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;

public interface IVista {

	void setControlador(IControlador controlador);

	void comenzar();

	void terminar();

}