package ast;

public class Asterisco extends EUn{
	
	public Asterisco(E opnd1, int fila, int col) {
		super(opnd1);
		this.fila = fila;
		this.col = col;
	}

	public TipoE tipo() {return TipoE.ASTERISCO;}   
  
	public void muestra(int ini) {
	   AS.pintaEspacios(ini);
	   System.out.println("acceso a puntero");
	   opnd.muestra(ini + 3);
	}
}