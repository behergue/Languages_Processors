package ast;

public abstract class NodoArbol {
	public abstract TipoN tipoNodo();
	protected int fila;
	protected int col;
	
	public abstract void muestra(int ini);
	
	public int getFila() {
		return fila;
	}
	
	public void setFila(int fila) {
		this.fila = fila;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
}
