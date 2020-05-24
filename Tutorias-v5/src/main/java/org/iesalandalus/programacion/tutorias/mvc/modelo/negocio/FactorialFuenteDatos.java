package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb.FactoriaFuenteDatosMongoDB;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros.FactoriaFuenteDatosFicheros;

public enum FactorialFuenteDatos {


	FICHEROS {
		public IFuenteDatos crear() {
			return new FactoriaFuenteDatosFicheros();
		}
	},
	
	MONGODB {
		public IFuenteDatos crear() {
			return new FactoriaFuenteDatosMongoDB();
		}
	};
	
	public abstract IFuenteDatos crear();
}
