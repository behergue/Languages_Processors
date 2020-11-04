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
		// abrimos ámbito
		tabla.open();
		
		// vinculación
		Vinculacion v = new Vinculacion(tabla);
		for(Instruccion i : insArbol) {
			v.vincula(i);
		}
		
		// cerramos ámbito
		tabla.close();
		
		// comprobación de tipos
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
			System.out.println("Comprobación semántica y de tipos correcta");
		}
		else if(GestionErroresTiny.numErroresSemanticos == 0 && !tiposCorrectos) {
			System.out.println("Comprobación semántica correcta pero comprobación de tipos incorrecta");
		}
		else if(GestionErroresTiny.numErroresSemanticos > 0 && tiposCorrectos) {
			System.out.println("Comprobación de tipos correcta pero comprobación semántica incorrecta");
		}
		else {
			System.out.println("Comprobación semántica y de tipos incorrecta");
		}
	}
}
