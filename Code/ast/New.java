package ast;

public class New extends E{
	private int tamTotal;

	public New(int fila, int col) {
		super();
		this.fila = fila;
	    this.col = col;
	}
	
	public void setTamTotal(int tamTotal) {
		this.tamTotal = tamTotal;
	}

	public int getTamTotal() {
		return tamTotal;
	}

	public TipoE tipo() {return TipoE.NEW;}

	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("puntero");
	}
}
