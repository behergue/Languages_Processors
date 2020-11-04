package ast;

public class InsMenosMenos extends Instruccion {
	private E v;
  
	public E getV() {
		return v;
	}

	public InsMenosMenos(E v, int fila, int col) {
		this.v = v;   
		this.fila = fila;
		this.col = col;
	}

	public TipoI tipo() {return TipoI.MENOSMENOS;}   
  
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("menos menos");
		v.muestra(ini + 3);
  }
}
