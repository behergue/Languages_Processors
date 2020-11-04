package ast;

public class Iden extends E {
	private String id;
	private NodoArbol n;
	private int profundidad;
	private int dir;

	public Iden(String id, int fila, int col) {
		this.id = id;
	    this.fila = fila;
	    this.col = col;
	}
	
	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public NodoArbol getN() {
		return n;
	}

	public void setN(NodoArbol n) {
		this.n = n;
	}

	public String getId() {
		return id;
	}
	
	public TipoE tipo() { return TipoE.IDEN; }
	
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("identificador: " + id);
		
		if(n != null) {
			AS.pintaEspacios(ini + 3);
			System.out.println("mi referencia es ");
			n.muestra(ini + 6);
		}
	}
}
