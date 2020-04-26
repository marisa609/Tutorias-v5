package org.iesalandalus.programacion.tutorias.mvc.vista;

import org.iesalandalus.programacion.tutorias.mvc.vista.texto.VistaTexto;

public enum FactoriaVista {

	TEXTO {

		public IVista crear() {
			return new VistaTexto();
		}
	};

	public abstract IVista crear();
}
