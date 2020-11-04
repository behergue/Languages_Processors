package ast;

public class Neg extends EUn {
	
	public Neg(E opnd1, int fila, int col) {
		super(opnd1);
		this.fila = fila;
		this.col = col;
	}

	public TipoE tipo() {return TipoE.NEG;}   
  
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("negación");
		opnd.muestra(ini + 3);
	}
}
