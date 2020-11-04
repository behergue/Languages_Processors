package ast;

public class Falso extends E {
	private Boolean v;
	
	public Falso(int fila, int col) {
		this.v = false;   
		this.fila = fila;
		this.col = col;
	}
  
	public Boolean getV() {
		return v;
	}

	public TipoE tipo() {return TipoE.FALSO;}   
  
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("false");
	}
}
