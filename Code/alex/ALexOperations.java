package alex;

import asint.ClaseLexica;

public class ALexOperations {
	private AnalizadorLexicoTiny alex;
	
	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;   
	}
	
	public UnidadLexica unidadInt() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.INT, "int");
	} 
	
	public UnidadLexica unidadBool() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.BOOL, "bool"); 
	} 
	
	public UnidadLexica unidadFloat() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.FLOAT, "float"); 
	} 
	
	public UnidadLexica unidadChar() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.CHAR, "char"); 
	} 
	  
	public UnidadLexica unidadTrue() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.TRUE, "true"); 
	} 
	  
	public UnidadLexica unidadFalse() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.FALSE, "false"); 
	} 
	  
	public UnidadLexica unidadWhile() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.WHILE, "while"); 
	} 
	  
	public UnidadLexica unidadIf() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.IF, "if"); 
	} 
	  
	public UnidadLexica unidadElse() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.ELSE, "else"); 
	} 
	  
	public UnidadLexica unidadFor() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.FOR, "for"); 
	} 
	  
	public UnidadLexica unidadReturn() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.RETURN, "return"); 
	} 
	  
	public UnidadLexica unidadVoid() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.VOID, "void"); 
	} 
	  
	public UnidadLexica unidadStruct() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.STRUCT, "struct"); 
	} 
	  
	public UnidadLexica unidadSwitch() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.SWITCH, "switch"); 
	} 
	  
	public UnidadLexica unidadCase() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.CASE, "case"); 
	} 
	  
	public UnidadLexica unidadBreak() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.BREAK, "break"); 
	} 
	  
	public UnidadLexica unidadDefault() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.DEFAULT, "default"); 
	} 
	  
	public UnidadLexica unidadNew() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.NEW, "new"); 
	}
	  
	public UnidadLexica unidadAsig() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.ASIG, "asig"); 
	}
	  
	public UnidadLexica unidadProc() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.PROC, "proc"); 
	}
	  
	public UnidadLexica unidadFunc() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.FUNC, "func"); 
	}
	  
	public UnidadLexica unidadPunto() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.PUNTO, "."); 
	} 
	  
	public UnidadLexica unidadDospuntos() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.DOSPUNTOS, ":"); 
	} 
	  
	public UnidadLexica unidadPuntoycoma() {
	   return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.PUNTOYCOMA, ";"); 
	} 
	  
	public UnidadLexica unidadNumeroEntero() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.ENTERO, alex.lexema()); 
	} 
	  
	public UnidadLexica unidadNumeroReal() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.REAL, alex.lexema()); 
	}
	  
	public UnidadLexica unidadCaracter() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.CARACTER, alex.lexema()); 
	} 
	  
	public UnidadLexica unidadComa() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.COMA, ","); 
	} 
	  
	public UnidadLexica unidadId() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.IDEN, alex.lexema()); 
	} 
	  
	public UnidadLexica unidadCorcheteApertura() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.CA, "["); 
	} 
	  
	public UnidadLexica unidadCorcheteCierre() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.CC, "]"); 
	} 
	  
	public UnidadLexica unidadParentesisApertura() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(),ClaseLexica.PA, "("); 
	} 
	  
	public UnidadLexica unidadParentesisCierre() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.PC, ")"); 
	} 
	public UnidadLexica unidadLlaveApertura() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.LLA, "{"); 
	} 
	  
	public UnidadLexica unidadLlaveCierre() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.LLC, "}"); 
	} 
	  
	public UnidadLexica unidadMasMas() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.MASMAS, "++"); 
	}
	
	public UnidadLexica unidadMenosMenos() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.MENOSMENOS, "--"); 
	} 
	  
	public UnidadLexica unidadSuma() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.SUMA, "+"); 
	} 
	  
	public UnidadLexica unidadResta() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.RESTA, "-"); 
	} 
	  
	public UnidadLexica unidadMultiplicacion() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.MULT, "*"); 
	} 
	  
	public UnidadLexica unidadDivisionEntera() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.DIVE, "/"); 
	} 
	  
	public UnidadLexica unidadDivisionDecimal() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.DIVD, "div"); 
	} 
	  
	public UnidadLexica unidadModulo() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.MOD, "%"); 
	} 
	  
	public UnidadLexica unidadMayor() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.MAYOR, ">"); 
	} 
	  
	public UnidadLexica unidadMenor() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.MENOR, "<"); 
	} 
	  
	public UnidadLexica unidadMayorIgual() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.MAI, ">="); 
	} 
	  
	public UnidadLexica unidadMenorIgual() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.MEI, "<="); 
	} 
	  
	public UnidadLexica unidadIgualIgual() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.IGIG, "=="); 
	} 
	  
	public UnidadLexica unidadDistinto() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.DIST, "!="); 
	} 
	  
	public UnidadLexica unidadNegacion() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.NEG, "!"); 
	} 
	  
	public UnidadLexica unidadAnd() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.AND, "&&"); 
	} 
	  
	public UnidadLexica unidadOr() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.OR, "||"); 
	} 
	  
	public UnidadLexica unidadIgual() {
	    return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.IG, alex.lexema()); 
	}  
	  
	public UnidadLexica unidadEof() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.col(), ClaseLexica.EOF, "EOF"); 
	}
	  
	public void error() {
	  System.err.println("*** ERROR léxico fila " + alex.fila() + ", columna " + alex.col() + 
			  ": carácter inesperado " + alex.lexema());
	}
}