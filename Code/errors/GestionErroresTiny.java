package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
	public static int numErroresSintacticos = 0;
	public static int numErroresSemanticos = 0;
	
	public static void errorSintactico(UnidadLexica unidadLexica) {
		numErroresSintacticos++;
		System.err.println("*** ERROR sintáctico fila "+unidadLexica.fila()+ ", columna " + unidadLexica.col() +
    		 ": Elemento inesperado "+ unidadLexica.lexema());
	}
   
	public static void errorSemantico(int fila, int col, String mensajeError) {
		numErroresSemanticos++;
		System.err.println("*** ERROR semántico fila " + fila + ", columna " + col + ": " + mensajeError);
	 }
}
