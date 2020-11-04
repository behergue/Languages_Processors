package ast;

import java.util.List;

public class LlamadaFunc extends E{
	private E iden;
	private List<E> args;
	private NodoArbol n;
	
	public LlamadaFunc(E iden, List<E> args, int fila, int col) {
		this.iden = iden;
		this.args = args;
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
	
	public List<E> getArgs() {
		return args;
	}
	
	public TipoE tipo() {return TipoE.LLAMADAFUNC;}
	
	public void muestra(int ini) {
		AS.pintaEspacios(ini);
		System.out.println("llamada a función");
		iden.muestra(ini + 3);
		
		if(n != null) {
			System.out.println("mi referencia es ");
			n.muestra(ini + 3);
		}
		
		AS.pintaEspacios(ini + 3);
		System.out.println("atributos");
		for(E i : args) {
			i.muestra(ini + 6);
		}
	}
}
