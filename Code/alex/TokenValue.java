package alex;

public class TokenValue {
	private String lex;
	private int fila;
	private int col;
	
	public TokenValue(String lex, int f, int c) {
		super();
		this.lex = lex;
		this.fila = f;
		this.col = c;
	}

	public String getLex() {
		return lex;
	}

	public int getF() {
		return fila;
	}

	public int getC() {
		return col;
	}
}
