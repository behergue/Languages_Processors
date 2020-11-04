package ast;

public class Verdad extends E {
	private Boolean v;

	public Verdad(int fila, int col) {
		this.v = true;   
		this.fila = fila;
		this.col = col;
	}
	
	public Boolean getV() {
		return v;
	}

	public TipoE tipo() {return TipoE.VERDAD;}   
  
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("true");
	}
}