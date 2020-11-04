package ast;

public class Menos extends EUn {
  
	public Menos(E opnd1, int fila, int col) {
		super(opnd1);
		this.fila = fila;
		this.col = col;
	}

	public TipoE tipo() {return TipoE.MENOS;}   
  
	public void muestra(int ini) {
	   AS.pintaEspacios(ini);
	   System.out.println("menos");
	   opnd.muestra(ini + 3);
	}
}
