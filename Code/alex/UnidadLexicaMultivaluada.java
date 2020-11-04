package alex;

public class UnidadLexicaMultivaluada extends UnidadLexica {
	private String lexema;
	
	public UnidadLexicaMultivaluada(int fila, int col, int clase, String lexema) {
		super(fila, col, clase, lexema);  
		this.lexema = lexema;
	}
	
	public String lexema() {return lexema;}
}
