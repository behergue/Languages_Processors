package ast;

public class Mas extends EUn {
	
	public Mas(E opnd1, int fila, int col) {
		super(opnd1);
		this.fila = fila;
		this.col = col;
	}

	public TipoE tipo() {return TipoE.MAS;}   
  
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("más");
		opnd.muestra(ini + 3);
	}
}
