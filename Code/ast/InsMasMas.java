package ast;

public class InsMasMas extends Instruccion {
	private E v;

	public InsMasMas(E v, int fila, int col) {
		this.v = v;   
		this.fila = fila;
		this.col = col;
	}
	
	public E getV() {
		return v;
	}
	
	public TipoI tipo() {return TipoI.MASMAS;}   
  
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("más más");
		v.muestra(ini + 3);
	}
}
