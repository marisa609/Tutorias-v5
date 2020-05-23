package org.iesalandalus.programacion.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Comprobaciones {
	
	// En esta clase iré metiendo las comprobaciones que haya que ir realizando para llamarla desde las clases que vaya a utilizar
	// y así hacer un código reutilizable
	
	private static final String ER_DNI = "([0-9]{8})([a-zA-Z])";
	
	
	public static boolean dniValido(String dni) {
		
		if (dni == null || dni.trim().isEmpty())
			throw new NullPointerException("ERROR: El dni no puede ser nulo ni estar vacío.");
		
		int numerosDni;
		char letra;

		char[] asignarLetra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
		
		Pattern p = Pattern.compile(ER_DNI);
		Matcher m = p.matcher(dni);
		
		boolean dniFormado=false;
		
		if (!m.matches()) {
			return false;
		}
		
		try {
			numerosDni = Integer.parseInt(m.group(1));
		}
		catch (NumberFormatException e) 
		{
			numerosDni = 0;
		}
		
		letra = asignarLetra [numerosDni % 23];
		
		if(m.group(2).charAt(0)==letra) {
			dniFormado = true;
			
		} else {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		return dniFormado;

	}
	
	
	

}
