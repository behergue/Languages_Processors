package ast;

import java.util.List;

public class InsLlamadaProc extends Instruccion{
	private E iden;
	private List<E> lista;
	private NodoArbol n;

	public InsLlamadaProc(E iden, List<E> lista, int fila, int col) {
		super();
		this.iden = iden;
		this.lista = lista;
		this.fila = fila;
	    this.col = col;
	}
	
	public NodoArbol getN() {
		return n;
	}

	public void setN(NodoArbol n) {
		this.n = n;
	}

	public E getIden() {
		return iden;
	}

	public List<E> getLista() {
		return lista;
	}
	
	public TipoI tipo() {return TipoI.LLAMADAPROC;}

	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("llamada a procedimiento");
		iden.muestra(ini + 3);
		
		if(n != null) {
			System.out.println("mi referencia es ");
			n.muestra(ini + 3);
		}
		
		AS.pintaEspacios(ini + 3);
		System.out.println("atributos");
		for(E i : lista) {
			i.muestra(ini + 6);
		}
	}
}
