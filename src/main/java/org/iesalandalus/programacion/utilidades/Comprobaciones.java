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
	
	private String formateaNombre(String nombreObjeto) {
		if (nombreObjeto == null || nombreObjeto.trim().length() == 0) {
			throw new NullPointerException("ERROR: El nombre de un objeto no puede ser nulo o vacío.");
		}

		nombreObjeto = nombreObjeto.replace("  ", " ");

		String[] words = nombreObjeto.split("\\s+");

		if (words.length == 0) {
			throw new NullPointerException("ERROR: El nombre de un objeto no puede ser nulo o vacío.");
		}

		StringBuilder nombreFormateado = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
			nombreFormateado.append(word).append(" ");
		}

		return nombreFormateado.toString().trim();
	}
	
	
	

}
