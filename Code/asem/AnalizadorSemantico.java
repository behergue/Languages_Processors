package asem;

import java.util.List;

import ast.*;
import errors.GestionErroresTiny;

public class AnalizadorSemantico {
	private List<Instruccion> insArbol;
	private TablaDeSimbolos tabla = new TablaDeSimbolos();

	public AnalizadorSemantico(List<Instruccion> insArbol) {
		this.insArbol = insArbol;
	}

	public void analizaSemantica() {
		// abrimos �mbito
		tabla.open();
		
		// vinculaci�n
		Vinculacion v = new Vinculacion(tabla);
		for(Instruccion i : insArbol) {
			v.vincula(i);
		}
		
		// cerramos �mbito
		tabla.close();
		
		// comprobaci�n de tipos
		boolean tiposCorrectos = true;
		TipoInstrucciones t = new TipoInstrucciones();
		for(Instruccion i : insArbol) {
			try {
				tiposCorrectos = t.tipoInstrucciones(i) && tiposCorrectos;
			} catch(Exception e) {
				tiposCorrectos = false;
			}
		}
		
		if (GestionErroresTiny.numErroresSemanticos == 0 && tiposCorrectos) {
			System.out.println("Comprobaci�n sem�ntica y de tipos correcta");
		}
		else if(GestionErroresTiny.numErroresSemanticos == 0 && !tiposCorrectos) {
			System.out.println("Comprobaci�n sem�ntica correcta pero comprobaci�n de tipos incorrecta");
		}
		else if(GestionErroresTiny.numErroresSemanticos > 0 && tiposCorrectos) {
			System.out.println("Comprobaci�n de tipos correcta pero comprobaci�n sem�ntica incorrecta");
		}
		else {
			System.out.println("Comprobaci�n sem�ntica y de tipos incorrecta");
		}
	}
}
