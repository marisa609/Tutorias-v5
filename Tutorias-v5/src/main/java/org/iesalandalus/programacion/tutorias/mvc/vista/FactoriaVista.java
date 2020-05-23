package org.iesalandalus.programacion.tutorias.mvc.vista;

import org.iesalandalus.programacion.tutorias.mvc.vista.iugpestanas.VistaIUGPestanas;
import org.iesalandalus.programacion.tutorias.mvc.vista.texto.VistaTexto;

public enum FactoriaVista {

	TEXTO {
		public IVista crear() {
			return new VistaTexto();
		}
	},
	
	IUGPESTANAS {
		public IVista crear() {
			return new VistaIUGPestanas();
		}
	};
	
	public abstract IVista crear();
}