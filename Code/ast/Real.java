package ast;

public class Real extends E {
	private String v;

	public Real(String v, int fila, int col) {
		this.v = v;   
		this.fila = fila;
		this.col = col;
	}
	
	public String getV() {
		return v;
	}
  
	public TipoE tipo() {return TipoE.REAL;}   
  
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("real: " + v);
	}
}
